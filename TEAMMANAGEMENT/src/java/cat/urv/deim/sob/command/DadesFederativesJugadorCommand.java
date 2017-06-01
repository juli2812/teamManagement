package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Jugador;
import cat.urv.deim.sob.Entrenador;
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

public class DadesFederativesJugadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            Jugador dades=null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            dades=getJugador(request.getParameter("jugador"));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesFederativesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            session.setAttribute("jugador", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/consultar_tramit_fed_jugador_e2.jsp").forward(request, response);
    }
    
    
    public Jugador getJugador (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        Jugador jugador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`jugador` WHERE `fk_usuari`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                query = "SELECT * FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, idUsuari);
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    jugador = new Jugador(resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getBoolean(9),resultSet.getString(4),resultSet.getInt(10),resultSet.getString(11),"foto",resultSet.getString(12),resultSet.getString(13),resultSet.getBoolean(14),resultSet.getBoolean(15),resultSet2.getString(1),resultSet2.getString(2),resultSet2.getString(3),resultSet2.getString(4),resultSet2.getString(5),resultSet2.getInt(6),idUsuari,resultSet2.getDate(8),resultSet2.getString(9),resultSet2.getDate(10));
                }
            }
            return jugador;
    }
    
}
