package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Jugador;
import cat.urv.deim.sob.Usuari;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

public class DadesAssignarIncidenciaJugadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            ArrayList<Jugador> dades=new ArrayList();
            HttpSession session = request.getSession(true);
            ArrayList<Integer> idsActivitat = new ArrayList<Integer>();
        // 1. process the request
        try {
            idsActivitat=getActivitatsEquip(request.getParameter("nomequip"));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesAssignarIncidenciaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            session.setAttribute("idsactivitat", idsActivitat);
            session.setAttribute("idjugador", request.getParameter("idjugador"));
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/assignar_incidencia_2.jsp").forward(request, response);
         
    }
    
    
    public ArrayList<Integer> getActivitatsEquip (String nomequip) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        PreparedStatement ps;
        ArrayList<Integer> idsActivitat=new ArrayList<Integer>();
        ResultSet resultSet2 = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `id_activitat` FROM `team_management`.`activitat` WHERE `fk_equip`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, nomequip);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                idsActivitat.add(resultSet.getInt(1));
            }
            return idsActivitat;
    }
    
}
