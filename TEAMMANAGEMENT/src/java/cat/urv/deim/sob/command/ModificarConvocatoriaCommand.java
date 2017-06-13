/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class ModificarConvocatoriaCommand  implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        
        if(!"".equals(request.getParameter("jugador"))){
            
        // 1. process the request
        try {
            modificarConvo(request.getParameter("jugador"),request.getParameter("partit"),request.getParameter("datapartit"), Integer.parseInt(request.getParameter("numJconf")), Integer.parseInt(request.getParameter("numJnoconf")), request.getParameter("llocpartit"), request.getParameter("dataLconf"),Integer.parseInt(request.getParameter("jugMin")),Integer.parseInt(request.getParameter("jugMax")),Boolean.valueOf(request.getParameter("confirmacio")),Boolean.valueOf(request.getParameter("havingut")));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DonarBaixaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
       
        
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/opcio_alinear.jsp").forward(request, response);
        }
        else{
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        
        }
    }
    public void modificarConvo(String jugador, String partit, String datapartit, int numJconf, int numJnoconf, String llocpartit, String dataLconf,int jugMin,int jugMax, boolean confirmacio,boolean havingut) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "UPDATE `team_management`.`convocatoria` SET `data_partit` = ?, `num_confirmats` = ?, `num_no_confirmats` = ?, `lloc_partit` = ?, `data_limit_confirmacio` = ?, `min_jugadors` = ?, `max_jugadors` = ?, `confirmat` = ?, `ha_vingut` = ? WHERE `fk_jugador` = ? AND `fk_partit`= ?;";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, datapartit);
                    ps.setInt(2, numJconf);
                    ps.setInt(3, numJnoconf);
                    ps.setString(4, llocpartit);
                    ps.setString(5, dataLconf);
                    ps.setInt(6, jugMin);
                    ps.setInt(7, jugMax);
                    ps.setBoolean(8, confirmacio);
                    ps.setBoolean(9, false);
                    ps.setString(10, jugador);
                    ps.setString(11, partit);
            ps.executeUpdate();
    }
    
    }