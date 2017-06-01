package cat.urv.deim.sob.command;

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

public class DadesEntrenadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            Entrenador dades=null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            numFedClub=getNumFed(request.getParameter("idusuari"),request.getParameter("tipususuari"));
            if(numFedClub!=0){
            dades=getEntrenador(request.getParameter("entrenador"));
            }
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        if(numFedClub!=0){
            session.setAttribute("ent", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/actualitzar_dades_entrenador_2.jsp").forward(request, response);
        }else{
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/actualitzar_dades_entrenador_1.jsp").forward(request, response);
        }
    }
    
    
    public Entrenador getEntrenador (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`entrenador` WHERE `fk_usuari`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                query = "SELECT * FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, idUsuari);
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    entrenador = new Entrenador(resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getBoolean(12),resultSet2.getString(1),resultSet2.getString(2),resultSet2.getString(3),resultSet2.getString(4),resultSet2.getString(5),resultSet2.getInt(6),idUsuari,resultSet2.getDate(8),resultSet2.getString(9),resultSet2.getDate(10));
                }
            }
            return entrenador;
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
    
}
