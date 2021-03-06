package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Activitat;
import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Jugador;
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
import org.apache.commons.codec.digest.DigestUtils;

public class DecisioValoracioJugadorEntrCommand implements Command {
private static int id = 0;
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
                    ArrayList<Jugador> jugadors = null;
        try {
            valoracioIndividual(Float.parseFloat(request.getParameter("nota")),request.getParameter("comentari"),Date.valueOf(request.getParameter("data")),request.getParameter("idjugador"),Integer.parseInt(request.getParameter("idactivitat")),request.getParameter("condfis"),request.getParameter("actitud"),Boolean.parseBoolean(request.getParameter("puntualitat")),Boolean.parseBoolean(request.getParameter("havingut")));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DecisioValoracioJugadorEntrCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
            if("true".equals(request.getParameter("decisio"))){
                try {
                    jugadors = getJugadorsEquipNoValorats(request.getParameter("nomequip"), Integer.parseInt(request.getParameter("idactivitat")));
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DecisioValoracioJugadorEntrCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("jugadors", jugadors);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/valorar_entrenament4.jsp").forward(request, response);
            }
            else{
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/valorar_entrenament.jsp?entrenamentvalorat=true&jugadorsvalorats=true").forward(request, response);
            }
    }
    
    public void valoracioIndividual(float nota, String comentari, Date data, String fkJugador, int fkEntrenament, String condicioFisica, String actitud, boolean puntualitat, boolean haVingut) throws ClassNotFoundException, SQLException{
        Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "INSERT INTO `team_management`.`valoracio` (`nota`, `comentari`, `data`) VALUES (?,?,?);";
            ps = con.prepareStatement(query);
            ps.setFloat(1, nota);
            ps.setString(2, comentari);
            ps.setDate(3, data);
            ps.executeUpdate();
            id++;
            query = "SELECT count(`fk_entrenament`) FROM `team_management`.`valoracio_entrenament` WHERE `fk_jugador`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, fkJugador);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                query = "INSERT INTO `team_management`.`valoracio_entrenament` (`fk_valoracio`, `fk_jugador`, `fk_entrenament`,`data_entrenament`,`condicio_fisica`,`actitud`,`puntualitat`,`ha_vingut`) VALUES (?,?,?,?,?,?,?,?);";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, fkJugador);
                ps.setInt(3, fkEntrenament);
                ps.setDate(4, data);
                ps.setString(5, condicioFisica);
                ps.setString(6, actitud);
                ps.setBoolean(7, puntualitat);
                ps.setBoolean(8, haVingut);
                ps.executeUpdate();
            }
            
    }
    public ArrayList<Jugador> getJugadorsEquipNoValorats (String nomequip, int idActivitat) throws SQLException, ClassNotFoundException{
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
            ps.setString(1, nomequip);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                query = "SELECT `nom`, `cognom1` FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, resultSet.getString(1));
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    query = "SELECT * FROM `team_management`.`valoracio_entrenament` WHERE `fk_jugador`=? AND `fk_entrenament`=?;";
                    ps = con.prepareStatement(query);
                    ps.setString(1, resultSet.getString(1));
                    ps.setInt(2, idActivitat);
                    ResultSet resultSet3=ps.executeQuery();
                    if(resultSet3.next()){}
                    else{
                        jugador = new Jugador(resultSet2.getString(1),resultSet2.getString(2),resultSet.getString(1));
                        jugadors.add(jugador);
                    }
                }
            }
            return jugadors;
    }
    
    
}
