/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Assistencia;
import cat.urv.deim.sob.Jugador;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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
public class ConsultarAssistenciaCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            ArrayList<Integer> dades=null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            
            dades=getAssistencia(request.getParameter("jugador"));

            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("assist", dades);
            session.setAttribute("jugador", request.getParameter("jugador"));
        ServletContext context = request.getSession().getServletContext();
            if(dades != null){
            
            context.getRequestDispatcher("/consultar_assistencia_2.jsp").forward(request, response);
            }else context.getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
    
    
    public ArrayList<Integer> getAssistencia (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<Integer> as = new ArrayList<Integer>();
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT COUNT(*) FROM `convocatoria` WHERE `fk_jugador`=? AND `ha_vingut`=true;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                    as.add(resultSet.getInt(1));
                }
            query = "SELECT COUNT(*) FROM `valoracio_entrenament` WHERE `fk_jugador`=? AND `ha_vingut`=true;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet2=ps.executeQuery();
            if (resultSet2.next()) {
                    as.add(resultSet2.getInt(1));
                }
            return as;
    }
    
    
}
