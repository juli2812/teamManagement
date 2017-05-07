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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrarClubCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. process the request
            registrarClub(request.getParameter("fkpresident"),request.getParameter("fkdiresportiu"),request.getParameter("nomclub"),Integer.parseInt(request.getParameter("idclub")),Integer.parseInt(request.getParameter("telefon")),request.getParameter("address"),request.getParameter("colorlocal"),request.getParameter("colorvisitant"),Float.parseFloat(request.getParameter("quotatotal")));
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarClubCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarClubCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/login.html").forward(request, response);
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
