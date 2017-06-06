/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Quota;
import cat.urv.deim.sob.ValoracioPartit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
public class MostrarQuotaCommand  implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            Quota dades=null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            
            dades=obtenirQuota(request.getParameter("jugador"));
            

            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("quota", dades);
            session.setAttribute("jugador", request.getParameter("jugador"));
        ServletContext context = request.getSession().getServletContext();
            if(dades != null){
             
            context.getRequestDispatcher("/quota2.jsp").forward(request, response);    
            
            }else context.getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
    
    
    public Quota obtenirQuota (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        Quota quo = null;
        PreparedStatement ps;
        boolean pagada = false;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`quota` WHERE `fk_jugador`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
       
                if (resultSet.next()) {
                    System.out.println("quota "+resultSet.getString(4));
                    if("1".equals(resultSet.getString(4))){
                        pagada = true;
                    }
                    quo = new Quota(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), pagada, resultSet.getInt(5));
                }
            return quo;
    }

}