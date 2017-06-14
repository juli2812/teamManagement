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
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Maria
 */
public class AltaPartitCommand  implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int result0 = 0;
            int result = 0;
            String error = "1"; //1 = no s'ha pogut insertar 2= ha anat bé 3 = ja existeix
            HttpSession session = request.getSession(true);
        
        if(!"".equals(request.getParameter("idjugador"))&&!"".equals(request.getParameter("dni"))&&!"".equals(request.getParameter("nom"))&&!"".equals(request.getParameter("cognom1"))&&!"".equals(request.getParameter("cognom2"))&&!"".equals(request.getParameter("adress"))&&!"".equals(request.getParameter("contrsenya"))&&!"".equals(request.getParameter("telefon"))&&!"".equals(request.getParameter("dataincorp"))&&!"".equals(request.getParameter("datanaix"))&&!"".equals(request.getParameter("dorsal"))&&!"".equals(request.getParameter("numcatsalut"))&&!"".equals(request.getParameter("reconeixementmedic"))&&!"".equals(request.getParameter("idclub"))&&!"".equals(request.getParameter("compte_bancari"))){
            
        // 1. process the request
        try {
            registrarPartit(Date.valueOf(request.getParameter("data")),Time.valueOf(request.getParameter("hora")),request.getParameter("rival"), request.getParameter("equip"));
            error = "2";
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        session.setAttribute("error", error);
        context.getRequestDispatcher("/alta_partit_2.jsp").forward(request, response);
        }
        else{
        ServletContext context = request.getSession().getServletContext();
        error = "1";
        session.setAttribute("error", error);
        context.getRequestDispatcher("/alta_partit_2.jsp").forward(request, response);
        
        }
    }
    public void registrarPartit (Date dataActivitat, Time horaActivitat, String rival, String equip) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "INSERT INTO `team_management`.`activitat` (data, hora, fk_equip) VALUES (?,?,?);";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, dataActivitat.toString());
                    ps.setString(2, horaActivitat.toString());
                    ps.setString(3, equip);
            ps.executeUpdate();
            
            //OBTENER CON UN SELECT EL IDENTIFICADOR DE LA ACTIVIDAD
            
            String obtenirActivitatSQL = "SELECT `id_activitat` FROM  `activitat` WHERE `data` = ? AND `hora` =  ?;";
            ps = con.prepareStatement(obtenirActivitatSQL);
                    ps.setString(1, dataActivitat.toString());
                    ps.setString(2, horaActivitat.toString());
            ResultSet resultSet = ps.executeQuery();
            
            String identiActiv = "";
            int idA;
            
            if (resultSet.next()) {
            idA = resultSet.getInt(1);
            identiActiv = Integer.toString(idA);
            
            String sentencia1SQL = "INSERT INTO `team_management`.`partit` (rival, fk_activitat) VALUES (?, ?);";
            ps = con.prepareStatement(sentencia1SQL);
                    ps.setString(1, rival);
                    ps.setString(2, identiActiv);
            ps.executeUpdate();
            }
            
            
    }
    
}