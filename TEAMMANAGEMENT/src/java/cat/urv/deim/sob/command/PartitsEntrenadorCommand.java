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

public class PartitsEntrenadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            String equip="";
            ArrayList<Partit> partits = new ArrayList<Partit>();
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            equip=getEquipEntrenador(request.getParameter("idusuari"));
            partits=getPartitsEntrenador(equip);
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PartitsEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            
            
            session.setAttribute("nomequip", equip);
            session.setAttribute("partits", partits);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/valorar_partit2.jsp").forward(request, response);
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
    
    public ArrayList<Partit> getPartitsEntrenador (String nomequip) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        PreparedStatement ps;
        Partit partit=null;
        ArrayList<Partit> partits = new ArrayList<Partit>();
        ResultSet resultSet2 = null;
        Date data = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String query = "SELECT * FROM `team_management`.`activitat` WHERE `fk_equip`=?;";
        ps = con.prepareStatement(query);
        ps.setString(1, nomequip);
        ResultSet resultSet=ps.executeQuery();
        Time t=null;
        Date d=null;
        while (resultSet.next()) {
                query = "SELECT * FROM `team_management`.`partit` WHERE `fk_activitat`=?;";
                ps = con.prepareStatement(query);
                ps.setInt(1, resultSet.getInt(1));
                resultSet2=ps.executeQuery();
                if(resultSet2.next()){
                    t = resultSet.getTime(4);
                    d = resultSet.getDate(3);
                    data= new Date(0);
                    data.setDate(d.getDate());
                    data.setTime(t.getTime());
                    partit = new Partit(resultSet2.getString(2), resultSet.getInt(1), data, resultSet.getString(5));
                    partits.add(partit);
                }
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
