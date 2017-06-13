/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

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
public class MostrarEstadisticaPartitEquipCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            ArrayList<ValoracioPartit> dades=null;
            HttpSession session = request.getSession(true);
            ValoracioPartit mitjaPartit = null;
        // 1. process the request
        
        try {
            
            
            dades=obtenirEstadistica(request.getParameter("equip"), Integer.parseInt(request.getParameter("partit")));
            mitjaPartit = obtenirMitjaPartit(dades);
         
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("valoracio", dades);
            session.setAttribute("partit", request.getParameter("partit"));
            session.setAttribute("vp", mitjaPartit);
        ServletContext context = request.getSession().getServletContext();
            if(dades != null ){
          
            context.getRequestDispatcher("/mostrar_estadistica_partit_equip.jsp").forward(request, response);
          
            }else context.getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
    
    public ValoracioPartit obtenirMitjaPartit( ArrayList<ValoracioPartit> dades){
        ValoracioPartit mitjana = new ValoracioPartit(0, 0, 0);
        int contValoracions = 0;
        
        for(ValoracioPartit vp: dades){
            mitjana.setAssistencia(mitjana.getAssistencia()+vp.getAssistencia());
            mitjana.setGols(mitjana.getGols()+vp.getGols());
            mitjana.setNota(mitjana.getNota()+vp.getNota());
            contValoracions++;
        }
        if(contValoracions > 0){
            mitjana.setNota(mitjana.getNota()/contValoracions);
        }
        return mitjana;
    }
    
    public ArrayList<ValoracioPartit> obtenirEstadistica (String idUsuari, int fk_partit) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<ValoracioPartit> valtemp = new ArrayList<>();
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_usuari` FROM `team_management`.`jugador` WHERE `fk_equip` = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
       
            while(resultSet.next()){
            String quer = "SELECT * FROM `team_management`.`valoracio_partit` WHERE `fk_jugador` = ? AND `fk_partit` = ?;";
            ps = con.prepareStatement(quer);
            ps.setString(1, resultSet.getString(1));
            ps.setInt(2, fk_partit);
            
            resultSet2=ps.executeQuery();
            
            
                if (resultSet2.next()) {
                    String quer3 = "SELECT * FROM `team_management`.`valoracio` WHERE `id_valoracio` = ? ;";
                    ps = con.prepareStatement(quer3);
                    ps.setString(1, resultSet2.getString(1));
            
                    ResultSet resultSet3=ps.executeQuery();
                    if (resultSet3.next()) {
                    valtemp.add(new ValoracioPartit(resultSet2.getString(2),resultSet2.getInt(3),resultSet2.getInt(4),resultSet2.getInt(5),resultSet2.getInt(6),resultSet2.getInt(7),resultSet2.getInt(1),resultSet2.getString(8),resultSet2.getBoolean(9),resultSet2.getInt(10),resultSet2.getInt(11), resultSet3.getInt(1)));
                    }
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
            String query = "SELECT `id_activitat` FROM `team_management`.`activitat` WHERE `fk_equip` = ?;";

            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
       
            while(resultSet.next()){
            String quer = "SELECT `fk_activitat` FROM `team_management`.`partit` WHERE `fk_activitat` = ?;";
            ps = con.prepareStatement(quer);
            ps.setString(1, resultSet.getString(1));
            
            resultSet2=ps.executeQuery();
            
            
                if (resultSet2.next()) {
                    valtemp.add(resultSet2.getString(1));
                }
            }
            return valtemp;
    }
}
