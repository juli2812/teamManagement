package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Jugador;
import cat.urv.deim.sob.Partit;
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
import org.apache.commons.codec.digest.DigestUtils;

public class RealitzarAlineacioMostrarPartitsCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            ArrayList<Partit> dades=new ArrayList<Partit>();
            HttpSession session = request.getSession(true);
        // 1. process the request
            String equip="";
        try {
            equip=getEquipEntrenador(request.getParameter("idusuari"));
            dades=getPartitsDisp(equip);
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RealitzarAlineacioMostrarPartitsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        // 2. produce the view with the web result
        
            session.setAttribute("nomequip", equip);
            session.setAttribute("partits", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/realitzar_alineacio_1.jsp").forward(request, response);
         
    }
    
    public ArrayList<Partit> getPartitsDisp (String nomequip) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ArrayList<Partit> partits = new ArrayList<Partit>();
        Partit partit = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = " SELECT `fk_activitat`,`rival` FROM `team_management`.`partit` WHERE `fk_activitat` IN (SELECT `fk_partit` FROM `team_management`.`convocatoria` WHERE `fk_partit` IN (SELECT `id_activitat` FROM `team_management`.`activitat` WHERE `fk_equip`=?));";            
            ps = con.prepareStatement(query);
            ps.setString(1, nomequip);
            
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                partit = new Partit(resultSet.getString(2),resultSet.getInt(1),new Date(0),"");
                partits.add(partit);
            }
            return partits;
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
