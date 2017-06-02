package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Activitat;
import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Entrenament;
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

public class ValoracioGralEntrenamentEntrenadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
        // 1. process the request
        Activitat activitat = null;
        try {
            activitat = getActivitat(Integer.parseInt(request.getParameter("identrenament")));
            // 2. produce the view with the web result
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ValoracioGralEntrenamentEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            session.setAttribute("activitat", activitat);
            session.setAttribute("identrenament",request.getParameter("identrenament"));
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/valorar_entrenament3.jsp").forward(request, response);
    }
    public Activitat getActivitat (int idActivitat) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        PreparedStatement ps;
        Activitat activitat=null;
        Date data = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String query = "SELECT * FROM `team_management`.`activitat` WHERE `id_activitat`=?;";
        ps = con.prepareStatement(query);
        ps.setInt(1, idActivitat);
        ResultSet resultSet=ps.executeQuery();
        Time t=null;
        Date d=null;
        if (resultSet.next()) {
            t = resultSet.getTime(4);
            d = resultSet.getDate(3);
            data= new Date(0);
            data.setDate(d.getDate());
            data.setTime(t.getTime());
            activitat = new Activitat(idActivitat, data, resultSet.getString(5));
            }
            return activitat;
    }
    
    
}
