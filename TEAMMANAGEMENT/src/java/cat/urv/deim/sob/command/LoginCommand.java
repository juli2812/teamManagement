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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            String resposta="";
        try {
            // 1. process the request
            resposta=getLogin(request.getParameter("idusuari"),request.getParameter("contrasenya"));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        if("".equals(resposta)){
            context.getRequestDispatcher("/login.html").forward(request, response);
        }
        else{
            context.getRequestDispatcher("/index.html").forward(request, response);        
        }
    }
    public String getLogin (String idUsuari, String contrasenya) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `id_usuari` FROM `team_management`.`usuari` WHERE `id_usuari`=? AND`contrasenya`=?;";
            ps = con.prepareStatement(query);
                    ps.setString(1, idUsuari);
                    ps.setString(2, DigestUtils.sha1Hex(contrasenya));
            
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                System.out.println("hola");
            return idUsuari;
            }
            else return "";
    }
}
