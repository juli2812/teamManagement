/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.ClassifCalend;
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
public class ConsultarClassCalendCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
            ArrayList <String> llistaEquips = null;
            ClassifCalend clcal = null;
            String equip = null;
            
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            
           
                //obtenir equip del jugador
                equip=getEquip(request.getParameter("idusuari"), request.getParameter("destinatari"));
                //obtenir classificació i calendari
                clcal=getClassificacio(equip);
           
            
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result

            ServletContext context = request.getSession().getServletContext();
            

                session.setAttribute("clcal", clcal);
                context.getRequestDispatcher("/consultar_class_comp_1.jsp").forward(request, response);
         
    }
    

    
    public String getEquip(String user, String destinatari)throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ResultSet resultSet = null;
        
        String query = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            if("Jugador".equals(destinatari)){
            query = "SELECT fk_equip FROM `team_management`.`jugador` WHERE `fk_usuari`=?;";
            }else{
            query = "SELECT fk_equip FROM `team_management`.`entrenador` WHERE `fk_usuari`=?;";    
            }
                ps = con.prepareStatement(query);
                ps.setString(1, user);
                resultSet=ps.executeQuery();
            
                
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
    }
    
    public ClassifCalend getClassificacio (String equip) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ResultSet resultSet = null;
        ClassifCalend clcal = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT classificacio, calendari FROM `team_management`.`equip` WHERE `nom_equip`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, equip);
                resultSet=ps.executeQuery();
            
            if (resultSet.next()) {
                clcal = new ClassifCalend(resultSet.getString(1), resultSet.getString(2));
            }
            return clcal;
    }
    
}
