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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession(true);
            String resposta="";
            String tipusUsuari="";
            ArrayList<String> nomUsuari = new ArrayList();
        try {
            // 1. process the request
            resposta=getLogin(request.getParameter("idusuari"),request.getParameter("contrasenya"));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        if("dadesErronees".equals(resposta)){
            context.getRequestDispatcher("/login.jsp?dadesErronees=true").forward(request, response);
        }
        else{
                try {
                    nomUsuari=getNomUsuari(request.getParameter("idusuari"),request.getParameter("contrasenya"));
                    tipusUsuari = getTipusUsuari(request.getParameter("idusuari"),request.getParameter("contrasenya"));
                } catch (SQLException ex) {
                    Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            session.setAttribute("tipusUsuari", tipusUsuari);
            session.setAttribute("idUsuari", resposta);
            session.setAttribute("nomUsuari", nomUsuari.get(0));
            session.setAttribute("cognomUsuari", nomUsuari.get(1));
            context.getRequestDispatcher("/index.jsp").forward(request, response);        
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
            return idUsuari;
            }
            else return "dadesErronees";
    }
    public ArrayList<String> getNomUsuari (String idUsuari, String contrasenya) throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `nom`,`cognom1` FROM `team_management`.`usuari` WHERE `id_usuari`=? AND`contrasenya`=?;";
            ps = con.prepareStatement(query);
                    ps.setString(1, idUsuari);
                    ps.setString(2, DigestUtils.sha1Hex(contrasenya));
            
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                resultado.add(resultSet.getString(1));
                resultado.add(resultSet.getString(2));
            }
            return resultado;
    }
    public String getTipusUsuari (String idUsuari, String contrasenya) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_usuari` FROM `team_management`.`president` WHERE `fk_usuari`=?;";
            ps = con.prepareStatement(query);
                    ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                return "President";
            }
            else{
                query = "SELECT `fk_usuari` FROM `team_management`.`jugador` WHERE `fk_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, idUsuari);
            
                resultSet=ps.executeQuery();
                if (resultSet.next()) {
                    return "Jugador";
                }
                else{
                    query = "SELECT `fk_usuari` FROM `team_management`.`entrenador` WHERE `fk_usuari`=?;";
                    ps = con.prepareStatement(query);
                    ps.setString(1, idUsuari);

                    resultSet=ps.executeQuery();
                    if (resultSet.next()) {
                        return "Entrenador";
                    }
                    else{
                        query = "SELECT `fk_usuari` FROM `team_management`.`director_esportiu` WHERE `fk_usuari`=?;";
                        ps = con.prepareStatement(query);
                        ps.setString(1, idUsuari);

                        resultSet=ps.executeQuery();
                        if (resultSet.next()) {
                            return "Director esportiu";
                        }
                    }
                }
            }
            return"";
    }
}
