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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Maria
 */
public class EscollirDestinatariCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

          if(!"".equals(request.getParameter("destinatari"))){
        // 1. process the request
        try {
            obtenirDestinataris(request.getParameter("dni"));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else{
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        
        }
    }
    public ArrayList<String> obtenirDestinataris (String destinatari) throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            String query = "";
            
            if("jugador".equals(destinatari)){
                query = "SELECT `fk_usuari`FROM `team_management`.`jugador`;";
            }else if("equip".equals(destinatari)){
                query = "SELECT  FROM `team_management`.`equip` WHERE `id_usuari`=? AND`contrasenya`=?;";
            }else{
                query = "SELECT  FROM `team_management`.`usuari`;";
            }
            
            ps = con.prepareStatement(query);
                    ps.setString(1, destinatari);
            
            ResultSet resultSet=ps.executeQuery();
            
            if (resultSet.next()) {
                resultado.add(resultSet.getString(1));
                resultado.add(resultSet.getString(2));
            }
            return resultado;
    }
    
}