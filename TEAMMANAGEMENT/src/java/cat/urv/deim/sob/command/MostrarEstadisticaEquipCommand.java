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
public class MostrarEstadisticaEquipCommand  implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            ArrayList<ValoracioPartit> dades=null;
            ArrayList<String> partits=null;
            String equip = "";
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            //obtenim l'equip de l'entrenador
            equip = getEquip(request.getParameter("idusuari"));
            System.out.println(equip);
            if("temporada".equals(request.getParameter("opcio"))){
                dades=obtenirEstadistica(request.getParameter("equip"));
            }else{
                partits = (ArrayList<String>)obtenirPartits(equip);
                session.setAttribute("partits", partits);
            }

            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("valoracio", dades);
            session.setAttribute("equip", equip);
        ServletContext context = request.getSession().getServletContext();
            if(dades != null || partits !=null){
            if("temporada".equals(request.getParameter("opcio"))){
            context.getRequestDispatcher("/consultar_est_equip_2.jsp").forward(request, response);
            }else{
               
            context.getRequestDispatcher("/seleccionar_partit_equip.jsp").forward(request, response);    
            }
            }else context.getRequestDispatcher("/index.jsp").forward(request, response);
        
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
    
    public ArrayList<ValoracioPartit> obtenirEstadistica (String idUsuari) throws SQLException, ClassNotFoundException{
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
            String quer = "SELECT * FROM `team_management`.`valoracio_partit` WHERE `fk_jugador` = ?;";
            ps = con.prepareStatement(quer);
            ps.setString(1, resultSet.getString(1));
            
            resultSet2=ps.executeQuery();
            
            
                if (resultSet2.next()) {
                    valtemp.add(new ValoracioPartit(resultSet2.getString(2),resultSet2.getInt(3),resultSet2.getInt(4),resultSet2.getInt(5),resultSet2.getInt(6),resultSet2.getInt(7),resultSet2.getInt(1),resultSet2.getString(8),resultSet2.getBoolean(9),resultSet2.getInt(10),resultSet2.getInt(11)));
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