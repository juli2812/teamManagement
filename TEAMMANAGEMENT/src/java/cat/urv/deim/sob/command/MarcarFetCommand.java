package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Activitat;
import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Entrenament;
import cat.urv.deim.sob.Exercici;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class MarcarFetCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
        // 1. process the request
        ArrayList<Exercici> exercicis= null;
        try {
            updateExercici(request.getParameter("valoracio"), Integer.parseInt(request.getParameter("idexercici").toString()));
            exercicis = getExercicis(Integer.parseInt(session.getAttribute("identrenament").toString()));
            // 2. produce the view with the web result
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MarcarFetCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            session.setAttribute("exercicis", exercicis);
            if("false".equals(request.getParameter("incid"))){
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/realitzar_seg_entrenament3.jsp").forward(request, response);
            }
            else{
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/assignar_incidencia.jsp").forward(request, response);
            }
    }
    
    public void updateExercici (String valoracio, int idExercici) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        System.out.println("DD");
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String query = "UPDATE `team_management`.`exercici` SET `valoracio`=?, `fet`=true WHERE `id_exercici`= ?;";
        ps = con.prepareStatement(query);
        ps.setString(1,valoracio);
        ps.setInt(2, idExercici);
        ps.execute();
    }
    
    public ArrayList<Exercici> getExercicis (int idActivitat) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ArrayList<Exercici> exercicis= new ArrayList<Exercici>();
        Exercici exercici = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String query = "SELECT * FROM `team_management`.`exercici` WHERE `fk_entrenament`=?;";
        ps = con.prepareStatement(query);
        ps.setInt(1, idActivitat);
        ResultSet resultSet=ps.executeQuery();
        while (resultSet.next()) {
        exercici = new Exercici(resultSet.getInt(1), idActivitat, resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getBoolean(6), resultSet.getString(7));
        exercicis.add(exercici);
        }
            return exercicis;
    }
    
    
}
