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

public class VeureAssignarIncidenciaJugadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            ArrayList<Jugador> dades=new ArrayList();
            HttpSession session = request.getSession(true);
        // 1. process the request
            String equip="";
        try {
            equip=getEquipEntrenador(request.getParameter("idusuari"));
            dades=getJugadorsEquip(equip);
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VeureAssignarIncidenciaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("nomequip", equip);
            session.setAttribute("jugadors", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/assignar_incidencia_1.jsp").forward(request, response);
         
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
    
    
    public int getNumFed (String idUsuari, String tipusUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query;
            if("President".equals(tipusUsuari)){
                query = "SELECT `id_club` FROM `team_management`.`club` WHERE `fk_president`=?;";
            }
            else{
                query = "SELECT `id_club` FROM `team_management`.`club` WHERE `fk_dir_esportiu`=?;";            
            }
            ps = con.prepareStatement(query);
                    ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
            return resultSet.getInt(1);
            }
            return 0;
    }
    
    public String getEquipEntrenador (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_equip` FROM `team_management`.`entrenador` WHERE `fk_usuari`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return "noequip";
    }
    
}
