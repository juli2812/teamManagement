/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maria
 */
public class CrearConvoCommand   implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //if(!"".equals(request.getParameter("idjugador"))&&!"".equals(request.getParameter("dni"))&&!"".equals(request.getParameter("nom"))&&!"".equals(request.getParameter("cognom1"))&&!"".equals(request.getParameter("cognom2"))&&!"".equals(request.getParameter("adress"))&&!"".equals(request.getParameter("contrsenya"))&&!"".equals(request.getParameter("telefon"))&&!"".equals(request.getParameter("dataincorp"))&&!"".equals(request.getParameter("datanaix"))&&!"".equals(request.getParameter("dorsal"))&&!"".equals(request.getParameter("numcatsalut"))&&!"".equals(request.getParameter("reconeixementmedic"))&&!"".equals(request.getParameter("idclub"))&&!"".equals(request.getParameter("compte_bancari"))){
            
        // 1. process the request
        try {
            registrarConvocatoria(request.getParameter("jugador"),request.getParameter("partit"),request.getParameter("datapartit"), Integer.parseInt(request.getParameter("numJconf")), Integer.parseInt(request.getParameter("numJnoconf")), request.getParameter("llocpartit"), request.getParameter("dataLconf"),Integer.parseInt(request.getParameter("jugMin")),Integer.parseInt(request.getParameter("jugMax")),Boolean.getBoolean(request.getParameter("confirmacio")),Boolean.getBoolean(request.getParameter("havingut")));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/opcio_alinear.jsp").forward(request, response);
        /*}
        else{
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        }*/
    }
    public void registrarConvocatoria(String jugador, String partit, String datapartit, int numJconf, int numJnoconf, String llocpartit, String dataLconf,int jugMin,int jugMax, boolean confirmacio,boolean havingut) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "INSERT INTO `team_management`.`convocatoria` (fk_jugador, fk_partit, data_partit, num_confirmats, num_no_confirmats, lloc_partit, data_limit_confirmacio, min_jugadors, max_jugadors, confirmat, ha_vingut) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, jugador);
                    ps.setString(2, partit);
                    ps.setString(3, datapartit);
                    ps.setInt(4, numJconf);
                    ps.setInt(5, numJnoconf);
                    ps.setString(6, llocpartit);
                    ps.setString(7, dataLconf);
                    ps.setInt(8, jugMin);
                    ps.setInt(9, jugMax);
                    if("true".equals(confirmacio)){ps.setInt(10, 1);}
                    else{ps.setInt(10, 0);}
                    if("true".equals(havingut)){ps.setInt(11, 1);}
                    else{ps.setInt(11, 0);}
            ps.executeUpdate();
             
    }
    
}