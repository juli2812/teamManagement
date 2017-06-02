package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Activitat;
import cat.urv.deim.sob.Entrenador;
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
import org.apache.commons.codec.digest.DigestUtils;

public class DecisioValoracioJugadorCommand implements Command {
private static int id = 0;
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
                    ArrayList<Jugador> jugadors = null;
        try {
            valoracioIndividual(Float.parseFloat(request.getParameter("nota")),request.getParameter("comentari"),Date.valueOf(request.getParameter("data")),request.getParameter("idjugador"),Integer.parseInt(request.getParameter("idactivitat")),Integer.parseInt(request.getParameter("assistencia")),Integer.parseInt(request.getParameter("gols")),Integer.parseInt(request.getParameter("tarjeta_groga")),Integer.parseInt(request.getParameter("tarjeta_vermella")),request.getParameter("lessions"),Boolean.parseBoolean(request.getParameter("puntualitat")),Integer.parseInt(request.getParameter("min_jugats")));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DecisioValoracioJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
            if("true".equals(request.getParameter("decisio"))){
                try {
                    jugadors = getJugadorsEquipNoValorats(request.getParameter("nomequip"), Integer.parseInt(request.getParameter("idactivitat")));
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DecisioValoracioJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("jugadors", jugadors);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/valorar_partit4.jsp").forward(request, response);
            }
            else{
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/valorar_partit.jsp?partitvalorat=true?jugadorsvalorats=true").forward(request, response);
            }
    }
    
    public void valoracioIndividual(float nota, String comentari, Date data, String fkJugador, int fkPartit, int assistencia, int gols, int tarjetaGroga, int tarjetaVermella, String lessions, boolean puntualitat, int minJugats) throws ClassNotFoundException, SQLException{
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
            query = "SELECT count(`fk_partit`) FROM `team_management`.`valoracio_partit` WHERE `fk_jugador`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, fkJugador);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                query = "INSERT INTO `team_management`.`valoracio_partit` (`fk_valoracio`, `fk_jugador`, `fk_partit`,`assistencia`,`gols`,`tarjeta_groga`,`tarjeta_vermella`,`lessions`,`puntualitat`,`min_jugats`,`partits_jugats`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, fkJugador);
                ps.setInt(3, fkPartit);
                ps.setInt(4, assistencia);
                ps.setInt(5, gols);
                ps.setInt(6, tarjetaGroga);
                ps.setInt(7, tarjetaVermella);
                ps.setString(8, lessions);
                ps.setBoolean(9,puntualitat);
                ps.setInt(10, minJugats);
                ps.setInt(11,(resultSet.getInt(1)+1));
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
                    query = "SELECT * FROM `team_management`.`valoracio_partit` WHERE `fk_jugador`=? AND `fk_partit`=?;";
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
