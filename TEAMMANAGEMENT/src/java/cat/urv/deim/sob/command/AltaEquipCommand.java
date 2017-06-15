package cat.urv.deim.sob.command;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

public class AltaEquipCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            
        ServletContext context = request.getSession().getServletContext();
        // 1. process the request
        if(!"".equals(request.getParameter("nomequip"))&&!"".equals(request.getParameter("categoria"))){
        try {
            String existeix = getEquipId(request.getParameter("nomequip"));
            if(existeix.equals("si")){
                context.getRequestDispatcher("/alta_equip.jsp?faltaParam=true").forward(request, response);
            }else{
            numFedClub=getNumFed(request.getParameter("idusuari"),request.getParameter("tipususuari"));
            if(numFedClub!=0){
            altaEquip(request.getParameter("nomequip"),request.getParameter("categoria"),request.getParameter("classificacio"),request.getParameter("calendari"),numFedClub);
            }}
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEquipCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        if(numFedClub!=0){
            if(null!=request.getParameter("altaeq")&&"true".equals(request.getParameter("altaeq"))){
            context.getRequestDispatcher("/assignar_equip.jsp").forward(request, response);
            }else{
            context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }else{
            context.getRequestDispatcher("/alta_equip.jsp?faltaParam=true").forward(request, response);
        }
        }
        else{
        context.getRequestDispatcher("/alta_equip.jsp?faltaParam=true").forward(request, response);
        
        }
    }
    public void altaEquip (String nomequip, String categoria, String classificacio, String calendari, int numFed) throws ClassNotFoundException, SQLException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "INSERT INTO `team_management`.`equip` (`nom_equip`, `fk_club`, `categoria`, `classificacio`, `calendari`) VALUES (?,?,?,?,?);";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, nomequip);
                    ps.setInt(2, numFed);
                    ps.setString(3, categoria);
                    ps.setString(4, classificacio);
                    ps.setString(5, calendari);
            ps.executeUpdate();
    }
    
    public String getEquipId (String nomEquip) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `nom_equip` FROM `team_management`.`equip` WHERE `nom_equip`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, nomEquip);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                return "si";
            }
            return "no";
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
