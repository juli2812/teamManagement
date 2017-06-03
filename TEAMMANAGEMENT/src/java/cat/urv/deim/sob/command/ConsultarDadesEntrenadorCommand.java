/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ConsultarDadesEntrenadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            Entrenador dades=null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            
             dades=getEntrenador(request.getParameter("entrenador"));

            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("ent", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/consultar_dades_entrenador2.jsp").forward(request, response);
    }
    
    
    public Entrenador getEntrenador (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`entrenador` WHERE `fk_usuari`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                query = "SELECT * FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, idUsuari);
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    entrenador = new Entrenador(resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getBoolean(12),resultSet2.getString(1),resultSet2.getString(2),resultSet2.getString(3),resultSet2.getString(4),resultSet2.getString(5),resultSet2.getInt(6),idUsuari,resultSet2.getDate(8),resultSet2.getString(9),resultSet2.getDate(10));
                }
            }
            return entrenador;
    }
    
}
