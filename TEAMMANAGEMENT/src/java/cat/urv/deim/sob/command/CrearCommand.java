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

public class CrearCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getSession().getServletContext();
        if(!"".equals(request.getParameter("nomclub"))&&!"".equals(request.getParameter("idclub"))&&!"".equals(request.getParameter("telefon"))&&!"".equals(request.getParameter("adress"))&&!"".equals(request.getParameter("colorlocal"))&&!"".equals(request.getParameter("colorvisitant"))&&!"".equals(request.getParameter("quotatotal"))){
        try {
            // 1. process the request
            String existeix = getUsuariId(request.getParameter("fkpresident"));
            String existeix2 = getUsuariId(request.getParameter("fkdiresportiu"));
            String existeix3 = getIdClub(Integer.parseInt(request.getParameter("idclub")));
            
            if(existeix.equals("no")||(null!=request.getParameter("fkdiresportiu")&&!request.getParameter("fkdiresportiu").equals("")&&existeix2.equals("no"))||existeix3.equals("si")){
                context.getRequestDispatcher("/registre_2.jsp?faltaParam=true").forward(request, response);
            }
            else{
                registrarClub(request.getParameter("fkpresident"),request.getParameter("fkdiresportiu"),request.getParameter("nomclub"),Integer.parseInt(request.getParameter("idclub")),Integer.parseInt(request.getParameter("telefon")),request.getParameter("address"),request.getParameter("colorlocal"),request.getParameter("colorvisitant"),Float.parseFloat(request.getParameter("quotatotal")));
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(CrearCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CrearCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        context.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else{
        context.getRequestDispatcher("/registre_2.jsp?faltaParam=true").forward(request, response);}
        
    }
    
    
    public String getIdClub (int idClub) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `id_club` FROM `team_management`.`club` WHERE `id_club`=?;";
            ps = con.prepareStatement(query);
            ps.setInt(1, idClub);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                return "si";
            }
            return "no";
    }
    
    public String getUsuariId (String idPresident) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `id_usuari` FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idPresident);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                return "si";
            }
            return "no";
    }
    
    
    public void registrarClub (String fkPresident, String fkDirEsportiu,String nomClub, int idClub, int telefon, String address, String colorLocal, String colorVisitant, float preuTotalQuota) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "INSERT INTO `club` (`fk_president`, `fk_dir_esportiu`, `nom_club`, `id_club`, `telefon`, `adress`, `color_local`, `color_visitant`, `preu_total_quota`) VALUES (?,?,?,?,?,?,?,?,?);";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, fkPresident);
                    if("".equals(fkDirEsportiu)){
                        System.out.println("diresp");
                        ps.setString(2, null);
                    }
                    else{
                        System.out.println("dirsss");
                        ps.setString(2, fkDirEsportiu);
                    }
                    ps.setString(3, nomClub);
                    ps.setInt(4, idClub);
                    ps.setInt(5, telefon);
                    if("".equals(address)){
                        ps.setString(6, null);
                    }
                    else{
                        ps.setString(6, address);
                    }
                    if("".equals(colorLocal)){
                        ps.setString(7, null);
                    }
                    else{
                        ps.setString(7, colorLocal);
                    }
                    if("".equals(colorVisitant)){
                        ps.setString(8, null);
                    }
                    else{
                        ps.setString(8, colorVisitant);
                    }
                    ps.setFloat(9, preuTotalQuota);
            ps.executeUpdate();
    }
}
