package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Incidencia;
import cat.urv.deim.sob.Jugador;
import cat.urv.deim.sob.Partit;
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

public class IncidenciaJugadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            String equip="";
            ArrayList<Incidencia> incidencies = new ArrayList<Incidencia>();
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            incidencies=getIncidencies(request.getParameter("idusuari"));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(IncidenciaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        // 2. produce the view with the web result
            
            
            session.setAttribute("incidencies", incidencies);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/consultar_incid_cast_1.jsp").forward(request, response);
    }
    
    public ArrayList<Incidencia> getIncidencies (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        Jugador jugador = null;
        PreparedStatement ps;
        Incidencia incid=null;
        ArrayList<Incidencia> incids = new ArrayList<Incidencia>();
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`incidencia` WHERE `fk_usuari`=? ORDER BY `data_incidencia` DESC;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                incid = new Incidencia(resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getInt(7), idUsuari, resultSet.getInt(2),resultSet.getInt(3));
                incids.add(incid);
            }
            return incids;
    }
    
    
}
