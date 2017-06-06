package cat.urv.deim.sob.command;
import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Jugador;
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
            Entrenador datosEntrenador=null;
            Jugador datosJug=null;
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
                if("Jugador".equals(tipusUsuari)){
                    try {
                        datosJug= getJugador(resposta);
                        session.setAttribute("jugador", datosJug);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if("Entrenador".equals(tipusUsuari)){
                    try {
                        datosEntrenador= getEntrenador(resposta);
                        session.setAttribute("entrenador", datosEntrenador);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            session.setAttribute("tipusUsuari", tipusUsuari);
            session.setAttribute("idUsuari", resposta);
            session.setAttribute("nomUsuari", nomUsuari.get(0));
            session.setAttribute("cognomUsuari", nomUsuari.get(1));
            context.getRequestDispatcher("/index.jsp?type=a").forward(request, response);        
        }
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
