/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.ValoracioEntrenament;
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
public class ObtenirValoracionsEntrenamentCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> valE = new ArrayList();
        ValoracioEntrenament valEnt = null;
         
        // 1. process the request
        try {
            if("tots".equals(request.getParameter("accio"))){
                valE=obtenirValoracionsE();
            }else if("una".equals(request.getParameter("accio"))){
                valEnt=obtenirValoracioE(request.getParameter("ve"));
            }
                

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ObtenirAbsenciesCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        HttpSession session = request.getSession(true);
        
        if("tots".equals(request.getParameter("accio"))){
            session.setAttribute("ves", valE);
            context.getRequestDispatcher("/consultar_valor_entrenament_1.jsp").forward(request, response);
        }else if("una".equals(request.getParameter("accio"))){
            session.setAttribute("ve", valEnt);
            context.getRequestDispatcher("/consultar_valor_entrenament_2.jsp").forward(request, response);
        }else{
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        
        
    }
    public ArrayList<String> obtenirValoracionsE () throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        String concat;
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            String query = "";
            query = "SELECT `fk_entrenament` FROM `team_management`.`valoracio_entrenament`;";
            ps = con.prepareStatement(query);
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {
                resultado.add(resultSet.getString(1));
            }
            return resultado;
    }
        public ValoracioEntrenament obtenirValoracioE (String entrenament) throws SQLException, ClassNotFoundException{
        ValoracioEntrenament resultado=null;
        Connection con;
        PreparedStatement ps;
            ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
                        
            String query = "";
            query = "SELECT * FROM `team_management`.`valoracio_entrenament` WHERE `fk_entrenament` = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, entrenament);
            ResultSet resultSet=ps.executeQuery();
            
            if (resultSet.next()) {
            query = "SELECT `comentari`, `nota` FROM `team_management`.`valoracio` WHERE `id_valoracio` = ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, resultSet.getString(1));
            resultSet2=ps.executeQuery();
            
            if(resultSet2.next()){

                resultado=new ValoracioEntrenament(resultSet.getString(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getBoolean(8), resultSet2.getString(1), resultSet2.getInt(2));
            } else{
                resultado=new ValoracioEntrenament(resultSet.getString(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getBoolean(8), null, 0);        
                        }
            }
            return resultado;
    }
}