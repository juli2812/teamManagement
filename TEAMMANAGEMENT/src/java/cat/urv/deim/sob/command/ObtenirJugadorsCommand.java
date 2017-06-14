/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Jugador;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maria
 */
public class ObtenirJugadorsCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> usuaris = new ArrayList();
        ArrayList<Jugador> users = new ArrayList();
        
         
        // 1. process the request
        try {
                String equip = getEquip(request.getParameter("idusuari"));
                users = getJugadorsEquip(equip);
                usuaris=obtenirDestinataris(equip);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        HttpSession session = request.getSession(true);
        
        session.setAttribute("usuaris", usuaris);
        if("estadistica".equals(request.getParameter("opcio"))){
            session.setAttribute("usuarisJ", users);
            context.getRequestDispatcher("/consultar_est_partit_1.jsp").forward(request, response);
        }else if("fitxajugador".equals(request.getParameter("opcio"))){
            context.getRequestDispatcher("/consultar_fitxa_jugador_1.jsp").forward(request, response);
        }else{
        context.getRequestDispatcher("/consultar_assistencia_1.jsp").forward(request, response);
        }
    }
    
    public String getEquip(String user)throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ResultSet resultSet = null;
        
        String query = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            query = "SELECT fk_equip FROM `team_management`.`entrenador` WHERE `fk_usuari`=?;";    
            
                ps = con.prepareStatement(query);
                ps.setString(1, user);
                resultSet=ps.executeQuery();
            
                
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
    }
    
    public ArrayList<String> obtenirDestinataris (String equip) throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            String query = "";
            query = "SELECT `fk_usuari`FROM `team_management`.`jugador` WHERE `fk_equip`= ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, equip);
            
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {
                resultado.add(resultSet.getString(1));
            }
            return resultado;
    }
    
     public ArrayList<Jugador> getJugadorsEquip (String equip) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<Jugador> jugadors = new ArrayList();
        Jugador jugador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_usuari` FROM `team_management`.`jugador` WHERE `fk_equip` =?;";
            ps = con.prepareStatement(query);
            ps.setString(1, equip);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                query = "SELECT `nom`, `cognom1` FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, resultSet.getString(1));
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    jugador = new Jugador(resultSet2.getString(1),resultSet2.getString(2),resultSet.getString(1));
                    jugadors.add(jugador);
                }
            }
            return jugadors;
    }
    
}