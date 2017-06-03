package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Jugador;
import cat.urv.deim.sob.Entrenament;
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

public class EntrenamentsExerciciEntrenadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            String equip="";
            ArrayList<Entrenament> entrenaments = new ArrayList<Entrenament>();
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            equip=getEquipEntrenador(request.getParameter("idusuari"));
            entrenaments=getEntrenamentsEntrenador(equip);
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EntrenamentsExerciciEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        // 2. produce the view with the web result
            
            
            session.setAttribute("nomequip", equip);
            session.setAttribute("entrenaments", entrenaments);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/realitzar_seg_entrenament2.jsp").forward(request, response);
    }
    
    public ArrayList<Entrenament> getEntrenamentsEntrenador (String nomequip) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        PreparedStatement ps;
        Entrenament entrenament=null;
        ArrayList<Entrenament> entrenaments = new ArrayList<Entrenament>();
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
                query = "SELECT `fk_activitat` FROM `team_management`.`entrenament` WHERE `fk_activitat`=?;";
                ps = con.prepareStatement(query);
                ps.setInt(1, resultSet.getInt(1));
                resultSet2=ps.executeQuery();
                if(resultSet2.next()){
                    t = resultSet.getTime(4);
                    d = resultSet.getDate(3);
                    data= new Date(0);
                    data.setDate(d.getDate());
                    data.setTime(t.getTime());
                    entrenament = new Entrenament( resultSet.getInt(1), data, resultSet.getString(5));
                    entrenaments.add(entrenament);
                }
            }
            return entrenaments;
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
