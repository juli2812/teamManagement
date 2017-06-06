/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Absencia;
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
public class ObtenirAbsenciesCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> absencies = new ArrayList();
        Absencia absencia = null;
         
        // 1. process the request
        try {
            if("tots".equals(request.getParameter("accio"))){
                absencies=obtenirAbsencies();
            }else if("una".equals(request.getParameter("accio"))){
                absencia=obtenirAbsencia(request.getParameter("absencia"));
            }
                

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ObtenirAbsenciesCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        HttpSession session = request.getSession(true);
        
        if("tots".equals(request.getParameter("accio"))){
            session.setAttribute("absencies", absencies);
            context.getRequestDispatcher("/consultar_absencia_1.jsp").forward(request, response);
        }else if("una".equals(request.getParameter("accio"))){
            session.setAttribute("absencia", absencia);
            context.getRequestDispatcher("/consultar_absencia_2.jsp").forward(request, response);
        }else{
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        
        
    }
    public ArrayList<String> obtenirAbsencies () throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        String concat;
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            String query = "";
            query = "SELECT `fk_jugador`, `dia` FROM `team_management`.`absencia`;";
            ps = con.prepareStatement(query);
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {
                concat = resultSet.getString(1)+"/"+resultSet.getString(2);
                resultado.add(concat);
            }
            return resultado;
    }
        public Absencia obtenirAbsencia (String jugadorData) throws SQLException, ClassNotFoundException{
        Absencia resultado=null;
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            String[] input = jugadorData.split("/");
            
            String query = "";
            query = "SELECT `fk_jugador`, `motiu`, `dia`, `justificada` FROM `team_management`.`absencia` WHERE `fk_jugador` = ? AND `dia` = ? ;";
            ps = con.prepareStatement(query);
            ps.setString(1, input[0]);
            ps.setString(2, input[1]);
            ResultSet resultSet=ps.executeQuery();
            
            if (resultSet.next()) {
                resultado=new Absencia(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3),resultSet.getBoolean(4));
            }
            return resultado;
    }
}