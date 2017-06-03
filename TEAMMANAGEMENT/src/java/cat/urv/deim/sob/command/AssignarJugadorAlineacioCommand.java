package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Jugador;
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

public class AssignarJugadorAlineacioCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            ArrayList<Jugador> jugadors = new ArrayList<Jugador>();
        // 1. process the request
        
        try {
            jugadors=getJugadorsEquip(request.getParameter("nomequip"));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AssignarJugadorAlineacioCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            session.setAttribute("numjugtit", request.getParameter("numjugtit"));
            session.setAttribute("numjugsup", request.getParameter("numjugsup"));
            session.setAttribute("numdeftit", request.getParameter("numdeftit"));
            session.setAttribute("nummigtit", request.getParameter("nummigtit"));
            session.setAttribute("numdavtit", request.getParameter("numdavtit"));
            session.setAttribute("formacio", request.getParameter("formacio"));
            session.setAttribute("jugadors",jugadors);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/realitzar_alineacio_3.jsp").forward(request, response);
    }
    public ArrayList<Jugador> getJugadorsEquip (String nomequip) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<Jugador> jugadors = new ArrayList();
        Jugador jugador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_usuari` FROM `team_management`.`jugador` WHERE `fk_equip`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1,nomequip);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                query = "SELECT `nom`, `cognom1` FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, resultSet.getString(1));
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    jugador = new Jugador(resultSet2.getString(1),resultSet2.getString(2),resultSet.getString(1));
                    jugadors.add(jugador);
                }
            }
            return jugadors;
    }
    
}
