package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Equip;
import cat.urv.deim.sob.Usuari;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
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
import javax.servlet.http.HttpSession;

public class AssignarIncidenciaJugadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            assignarIncidencia(request.getParameter("idjugador"),Integer.parseInt(request.getParameter("idactivitat")),request.getParameter("motiu"),Date.valueOf(request.getParameter("dataincid")),request.getParameter("tipusincidencia"),Integer.parseInt(request.getParameter("numpartits")));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AssignarIncidenciaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/assignar_incidencia.jsp?afegit=true").forward(request, response);
    }
    
    public void assignarIncidencia(String fkJugador, int fkActivitat,String motiu, Date dataIncid, String tipusIncid, int numPartSanc) throws ClassNotFoundException, SQLException{
        Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "INSERT INTO `team_management`.`incidencia` (`fk_jugador`, `fk_activitat`, `motiu`, `data_incidencia`, `tipus_incidencia`, `num_part_sancionat`) VALUES (?, ?, ?, ?, ?, ?);";
            ps = con.prepareStatement(query);
            ps.setString(1, fkJugador);
            ps.setInt(2, fkActivitat);
            ps.setString(3, motiu);
            ps.setDate(4, dataIncid);
            ps.setString(5,tipusIncid);
            ps.setInt(6,numPartSanc);
            ps.executeUpdate();
            }
      
    
}
