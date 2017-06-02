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

public class DecisioValoracioEntrCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
        try {
            // 1. process the request
            valoracioGeneral(request.getParameter("valoraciogeneral"), Integer.parseInt(request.getParameter("idactivitat")));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DecisioValoracioEntrCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
            if("true".equals(request.getParameter("decisio"))){
                ArrayList<Jugador> jugadors = new ArrayList<Jugador>();
                try {
                    jugadors=getJugadorsEquipNoValorats(request.getParameter("nomequip"),Integer.parseInt(request.getParameter("idactivitat")));
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DecisioValoracioEntrCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("jugadors", jugadors);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/valorar_entrenament4.jsp").forward(request, response);
            }
            else{
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/valorar_entrenament.jsp?entrenamentvalorat=true").forward(request, response);
            }
    }
    
    public void valoracioGeneral(String valoracio, int idActivitat) throws ClassNotFoundException, SQLException{
        Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "UPDATE `team_management`.`activitat` SET `valoracio_general`=? WHERE `id_activitat`= ?";
            ps = con.prepareStatement(query);
            ps.setString(1, valoracio);
            ps.setInt(2, idActivitat);
            ps.execute();
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
