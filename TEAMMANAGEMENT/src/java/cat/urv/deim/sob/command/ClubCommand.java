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

public class ClubCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
                ServletContext context = request.getSession().getServletContext();
        if(!"".equals(request.getParameter("dni"))&&!"".equals(request.getParameter("nom"))&&!"".equals(request.getParameter("cognom1"))&&!"".equals(request.getParameter("adress"))&&!"".equals(request.getParameter("telefon"))&&!"".equals(request.getParameter("iddiresportiu"))&&!"".equals(request.getParameter("datanaix"))&&!"".equals(request.getParameter("contrasenya"))&&!"".equals(request.getParameter("dataincorporacio"))){
        // 1. process the request
        try {
            String existeix = getUsuariId(request.getParameter("iddiresportiu"),request.getParameter("dni"));
            if(existeix.equals("si")){
            context.getRequestDispatcher("/registre_1.jsp?faltaParam=true").forward(request, response);
            }else{
            registrar(request.getParameter("dni"),request.getParameter("nom"),request.getParameter("cognom1"),request.getParameter("cognom2"),request.getParameter("address"),Integer.parseInt(request.getParameter("telefon")),request.getParameter("iddiresportiu"),request.getParameter("datanaix"),request.getParameter("contrasenya"),request.getParameter("dataincorp"));
            registrarDirEsportiu(request.getParameter("iddiresportiu"), request.getParameter("comptebancari"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DirEsportiuCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            if("true".equals(request.getParameter("club"))){
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
                context.getRequestDispatcher("/registre_2.jsp").forward(request, response);
            }
        }
        else{
              if("true".equals(request.getParameter("club"))){
                context.getRequestDispatcher("/registre_1.jsp?faltaParam=true&club=true").forward(request, response);
            }
            else{
                context.getRequestDispatcher("/registre_1.jsp?faltaParam=true").forward(request, response);}
        }
    }
    public String getUsuariId (String idPresident, String dni) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `id_usuari` FROM `team_management`.`usuari` WHERE `id_usuari`=? OR `NIF`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idPresident);
            ps.setString(2, dni);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                return "si";
            }
            return "no";
    }
    
    public void registrar (String dni, String nom,String cognom1, String cognom2, String address, int telefon, String idUsuari, String dataNaix, String contrasenya, String dataIncorp) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "INSERT INTO `team_management`.`usuari` (NIF,nom,cognom1,cognom2,adress,telefon,id_usuari,dataNaix,contrasenya,dataIncorp) VALUES (?,?,?,?,?,?,?,?,?,?);";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, dni);
                    ps.setString(2, nom);
                    ps.setString(3, cognom1);
                    ps.setString(4, cognom2);
                    ps.setString(5, address);
                    ps.setInt(6, telefon);
                    ps.setString(7, idUsuari);
                    ps.setString(8, dataNaix);
                    ps.setString(9, DigestUtils.sha1Hex(contrasenya));
                    ps.setString(10, dataIncorp);
            ps.executeUpdate();
    }
    
    public void registrarDirEsportiu (String fkUsuari, String compteBancari) throws SQLException, ClassNotFoundException{
            Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con;
        PreparedStatement ps;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String sentenciaSQL = "INSERT INTO `director_esportiu` (`fk_usuari`, `compte_bancari`) VALUES (?,?);";
        ps = con.prepareStatement(sentenciaSQL);
        ps.setString(1, fkUsuari);
        if("".equals(compteBancari)){
            ps.setString(2,null);
        }
        else{
            ps.setString(2, compteBancari);
        }
        ps.executeUpdate();
    }
}
