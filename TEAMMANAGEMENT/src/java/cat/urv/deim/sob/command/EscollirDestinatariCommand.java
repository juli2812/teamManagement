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
import javax.servlet.http.HttpSession;
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

        ArrayList<String> usuaris = new ArrayList();
        
          if(!"".equals(request.getParameter("destinatari"))){
        // 1. process the request
        try {
            usuaris=obtenirDestinataris(request.getParameter("destinatari"));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        HttpSession session = request.getSession(true);
        session.setAttribute("destinatari", request.getParameter("destinatari"));
        session.setAttribute("usuaris", usuaris);
        if("".equals(request.getParameter("accio")) || request.getParameter("accio") == null){
        context.getRequestDispatcher("/registre_incidencia.jsp").forward(request, response);
        }else{context.getRequestDispatcher("/alta_partit.jsp").forward(request, response);}
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
                query = "SELECT nom_equip FROM `team_management`.`equip`;";
            }else if("entrenador".equals(destinatari)){
                query = "SELECT fk_usuari FROM `team_management`.`entrenador`;";
            }
            else{
                query = "SELECT id_usuari FROM `team_management`.`usuari`;";
            }
            
            ps = con.prepareStatement(query);
            
            ResultSet resultSet=ps.executeQuery();
            
            if (resultSet.next()) {
                resultado.add(resultSet.getString(1));
            }
            return resultado;
    }
    
}