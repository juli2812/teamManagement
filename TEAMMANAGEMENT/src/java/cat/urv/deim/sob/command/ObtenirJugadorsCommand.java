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

/**
 *
 * @author Maria
 */
public class ObtenirJugadorsCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> usuaris = new ArrayList();
        
         
        // 1. process the request
        try {
            
                usuaris=obtenirDestinataris();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        HttpSession session = request.getSession(true);
        
        session.setAttribute("usuaris", usuaris);
        if("estadistica".equals(request.getParameter("opcio"))){
            context.getRequestDispatcher("/consultar_est_partit_1.jsp").forward(request, response);
        }else if("fitxajugador".equals(request.getParameter("opcio"))){
            context.getRequestDispatcher("/consultar_fitxa_jugador_1.jsp").forward(request, response);
        }else{
        context.getRequestDispatcher("/consultar_assistencia_1.jsp").forward(request, response);
        }
    }
    public ArrayList<String> obtenirDestinataris () throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            String query = "";
            query = "SELECT `fk_usuari`FROM `team_management`.`jugador`;";

            ps = con.prepareStatement(query);
            
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {
                resultado.add(resultSet.getString(1));
            }
            return resultado;
    }
    
}