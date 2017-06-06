/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Convocatoria;
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
public class ObtenirConvocatoriaCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Convocatoria convocatoria = new Convocatoria();
          
        // 1. process the request
        try {
            convocatoria=obtenirConvo(request.getParameter("convo"));
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ObtenirConvocatoriaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        HttpSession session = request.getSession(true);
        //session.setAttribute("convocatories", convocatories);
             session.setAttribute("convocatoria", convocatoria);
             if("consulta".equals(request.getParameter("origen"))){
              context.getRequestDispatcher("/consultar_convocatoria_2.jsp").forward(request, response);    
             }else{
             context.getRequestDispatcher("/mostrar_convocatoria.jsp").forward(request, response);
             }
 
    }
    
    public Convocatoria obtenirConvo(String convocatoria) throws SQLException, ClassNotFoundException{
        Convocatoria resultado ;
        Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        
        String[] c = convocatoria.split("-");

        String query = "";
        query = "SELECT * FROM `team_management`.`convocatoria` WHERE `fk_jugador` = ? AND `fk_partit` = ?;";
        ps = con.prepareStatement(query);
        ps.setString(1, c[1]);
        ps.setString(2, c[0]);
        ResultSet resultSet=ps.executeQuery();
        
        System.out.println("element -"+c[1]);
        if(resultSet.next()) {
            resultado = new Convocatoria(c[1], c[0], Date.valueOf(resultSet.getString(3)), resultSet.getInt(4), resultSet.getInt(5), resultSet.getString(6), Date.valueOf(resultSet.getString(7)), resultSet.getInt(8), resultSet.getInt(9), Boolean.getBoolean(resultSet.getString(10)), Boolean.getBoolean(resultSet.getString(11))); 
            return resultado;
        }
        return null;
    }
}