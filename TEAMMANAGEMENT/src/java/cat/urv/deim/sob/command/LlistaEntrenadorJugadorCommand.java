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

public class LlistaEntrenadorJugadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            ArrayList <Entrenador> dades=new ArrayList<Entrenador>();
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            dades=getEntrenadorsJugador(request.getParameter("idusuari"));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LlistaEntrenadorJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            session.setAttribute("entrenadors", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/consultar_fitxa_entrenador_j1.jsp").forward(request, response);
    }
    public ArrayList<Entrenador> getEntrenadorsJugador (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        ArrayList<Entrenador> entrenadors=new ArrayList<Entrenador>();
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String query = "SELECT `fk_equip` FROM `team_management`.`jugador` WHERE `fk_usuari`=?;";
        ps = con.prepareStatement(query);
        ps.setString(1, idUsuari);
        ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                query = "SELECT * FROM `team_management`.`entrenador` WHERE `fk_equip`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, resultSet.getString(1));
                ResultSet resultSet3=ps.executeQuery();
                while (resultSet3.next()) {
                    query = "SELECT * FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                    ps = con.prepareStatement(query);
                    ps.setString(1, idUsuari);
                    ResultSet resultSet2=ps.executeQuery();
                    if(resultSet2.next()){
                        entrenador = new Entrenador(resultSet3.getString(3),resultSet3.getString(4),resultSet3.getInt(5),resultSet3.getString(6),resultSet3.getString(7),resultSet3.getString(8),resultSet3.getString(9),resultSet3.getString(10),resultSet3.getString(11),resultSet3.getBoolean(12),resultSet2.getString(1),resultSet2.getString(2),resultSet2.getString(3),resultSet2.getString(4),resultSet2.getString(5),resultSet2.getInt(6),resultSet3.getString(1),resultSet2.getDate(8),resultSet2.getString(9),resultSet2.getDate(10));
                        entrenadors.add(entrenador);
                    }
                }
            }
            return entrenadors;
    }
    
}
