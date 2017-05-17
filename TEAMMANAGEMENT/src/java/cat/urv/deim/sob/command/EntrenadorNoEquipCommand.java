package cat.urv.deim.sob.command;

import cat.urv.deim.sob.User;
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

public class EntrenadorNoEquipCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            ArrayList<User> dades=new ArrayList();
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            numFedClub=getNumFed(request.getParameter("idusuari"),request.getParameter("tipususuari"));
            if(numFedClub!=0){
            dades=getEntrenadors(numFedClub);
            }
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EntrenadorNoEquipCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        if(numFedClub!=0){
            session.setAttribute("entrenadors", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/assignar_entrenador.jsp").forward(request, response);
        }else{
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/assignar_entrenador.jsp").forward(request, response);
        }
    }
    public ArrayList<User> getEntrenadors (int numFed) throws ClassNotFoundException, SQLException{
        Connection con;
        ArrayList<User> usuaris = new ArrayList();
        User usuari = null;
        PreparedStatement ps, ps2;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "SELECT `fk_usuari` FROM `entrenador` WHERE `fk_equip` IS NULL;";
            ps = con.prepareStatement(sentenciaSQL);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                String sentenciaSQL2 = "SELECT `nom`, `cognom1` FROM `usuari` WHERE `id_usuari`=?;";
                ps2 = con.prepareStatement(sentenciaSQL2);
                ps2.setString(1, resultSet.getString(1));
                ResultSet resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    usuari = new User(resultSet.getString(1),resultSet2.getString(1)+" "+resultSet2.getString(2));
                }
           
            }
        return usuaris;
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
