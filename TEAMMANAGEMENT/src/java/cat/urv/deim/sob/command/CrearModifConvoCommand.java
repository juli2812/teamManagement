/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Convocatoria;
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
public class CrearModifConvoCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Convocatoria> convocatories = new ArrayList();
         ArrayList<String> destinataris = new ArrayList();
        ArrayList<String> partits = new ArrayList();
          
        // 1. process the request
        try {
            destinataris=obtenirDestinataris("jugador");
            partits=obtenirPartits();
            if("modificar".equals(request.getParameter("opcio")) || "consulta".equals(request.getParameter("origen")) ){
                convocatories=obtenirConvocatories();
            }else if("crear".equals(request.getParameter("opcio"))){
                destinataris=obtenirDestinataris("jugador");
                partits=obtenirPartits();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        HttpSession session = request.getSession(true);
        //session.setAttribute("convocatories", convocatories);
        
        if("consulta".equals(request.getParameter("origen"))){
             
             session.setAttribute("convocatories", convocatories);
        context.getRequestDispatcher("/consultar_convocatoria_1.jsp").forward(request, response);
         }
        
        
         if("modificar".equals(request.getParameter("opcio"))){
             session.setAttribute("convocatories", convocatories);
        context.getRequestDispatcher("/mostrar_convocatories.jsp").forward(request, response);
        } else {
             session.setAttribute("partits", partits);
             session.setAttribute("destinataris", destinataris);
             context.getRequestDispatcher("/crear_convocatoria.jsp").forward(request, response);
         }

 
    }
    
    public ArrayList<String> obtenirDestinataris(String destinatari) throws SQLException, ClassNotFoundException{
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
    
    public ArrayList<String> obtenirPartits() throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
            
        String query = "";
        query = "SELECT `fk_activitat`FROM `team_management`.`partit`;";
        ps = con.prepareStatement(query);
        ResultSet resultSet=ps.executeQuery();
            
        while (resultSet.next()) {
            resultado.add(resultSet.getString(1));
        }
        return resultado;
    }
    public ArrayList<Convocatoria> obtenirConvocatories() throws SQLException, ClassNotFoundException{
        ArrayList<Convocatoria> resultado;
        resultado = new ArrayList();
        Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
            
        String query = "";
        query = "SELECT `fk_jugador`, `fk_partit` FROM `team_management`.`convocatoria`;";
        ps = con.prepareStatement(query);
        ResultSet resultSet=ps.executeQuery();
            
        while (resultSet.next()) {
            resultado.add(new Convocatoria(resultSet.getString(1), resultSet.getString(2)));
        }
        return resultado;
    }
    
}