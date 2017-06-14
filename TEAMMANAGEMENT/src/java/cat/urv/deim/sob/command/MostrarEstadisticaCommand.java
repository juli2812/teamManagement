/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Assistencia;
import cat.urv.deim.sob.ValoracioPartit;
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
public class MostrarEstadisticaCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            ArrayList<ValoracioPartit> dades=null;
            ArrayList<String> partits=null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            
            if("temporada".equals(request.getParameter("opcio"))){
            dades=obtenirEstadistica(request.getParameter("jugador"));
            }else{
                 partits = (ArrayList<String>)obtenirPartits(request.getParameter("jugador"));
                session.setAttribute("partits", partits);
            }

            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("valoracio", dades);
            session.setAttribute("jugador", request.getParameter("jugador"));
        ServletContext context = request.getSession().getServletContext();
            if(dades != null || partits !=null){
            if("temporada".equals(request.getParameter("opcio"))){
            context.getRequestDispatcher("/mostrar_est_temporada.jsp").forward(request, response);
            }else{               
            context.getRequestDispatcher("/seleccionar_partit.jsp").forward(request, response);    
            }
            }else context.getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
    
    
    public ArrayList<ValoracioPartit> obtenirEstadistica (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<ValoracioPartit> valtemp = new ArrayList<>();
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`valoracio_partit` WHERE `fk_jugador`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
       
                while (resultSet.next()) {
                    String quer3 = "SELECT * FROM `team_management`.`valoracio` WHERE `id_valoracio` = ? ;";
                    ps = con.prepareStatement(quer3);
                    ps.setString(1, resultSet.getString(1));
            
                    ResultSet resultSet3=ps.executeQuery();
                    if(resultSet3.next()){
                        valtemp.add(new ValoracioPartit(resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(1),resultSet.getString(8),resultSet.getBoolean(9),resultSet.getInt(10),resultSet.getInt(11), resultSet3.getInt(2), resultSet3.getString(3)));
                    }
                }
            return valtemp;
    }
    
       public ArrayList<String> obtenirPartits (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<String> valtemp = new ArrayList<>();
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT fk_partit FROM `team_management`.`valoracio_partit` WHERE `fk_jugador`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
       
                while (resultSet.next()) {
                    valtemp.add(resultSet.getString(1));
                }
            return valtemp;
    }
}
