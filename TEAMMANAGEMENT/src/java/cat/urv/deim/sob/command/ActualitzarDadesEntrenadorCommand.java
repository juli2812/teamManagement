package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Usuari;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

public class ActualitzarDadesEntrenadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
        // 1. process the request
        try {
            updateEntrenador(request.getParameter("comptebancari"),request.getParameter("nivellentrenador"),request.getParameter("certificatentrenador"),request.getParameter("funcio"),request.getParameter("fotocopia_DNI"),request.getParameter("foto"),request.getParameter("catsalut"),request.getParameter("reconeixement_medic"),Boolean.valueOf(request.getParameter("tot_entregat")),request.getParameter("dni"),request.getParameter("nom"),request.getParameter("cognom1"),request.getParameter("cognom2"),request.getParameter("address"),Integer.parseInt(request.getParameter("telefon")),request.getParameter("identrenador"),Date.valueOf(request.getParameter("datanaix")),request.getParameter("contrasenya"),Date.valueOf(request.getParameter("dataincorp")));
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ActualitzarDadesEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/actualitzar_dades_entrenador.jsp?actualitzat=true").forward(request, response);
    }
    
    
    public void updateEntrenador (String compteBancari, String nivellEntrenador, String certificat, String funcEntrenador, String fotocopiaDNI, String foto, String numSalut, String reconeixementMedic, boolean totEntregat, String NIF, String nom, String cognom, String cognom2, String address, int telefon, String idUsuari, Date dataNaix, String contrasenya, Date dataIncorporacio) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query="";
            if(!"".equals(contrasenya)){
            query = "UPDATE `team_management`.`usuari` SET `NIF`=?, `nom`=?, `cognom1`=?, `cognom2`=?, `adress`=?, `telefon`=?, `dataNaix`=?, `contrasenya`=?, `dataIncorp`=? WHERE `id_usuari`= ?";
            ps = con.prepareStatement(query);
            ps.setString(1, NIF);
            ps.setString(2, nom);
            ps.setString(3, cognom);
            ps.setString(4, cognom2);
            ps.setString(5, address);
            ps.setInt(6, telefon);
            ps.setDate(7, dataNaix);
            ps.setString(8, DigestUtils.sha1Hex(contrasenya));
            ps.setDate(9, dataIncorporacio);
            ps.setString(10, idUsuari);
            ps.execute();
            }
            else{
            query = "UPDATE `team_management`.`usuari` SET `NIF`=?, `nom`=?, `cognom1`=?, `cognom2`=?, `adress`=?, `telefon`=?, `dataNaix`=?, `dataIncorp`=? WHERE `id_usuari`= ?";
            ps = con.prepareStatement(query);
            ps.setString(1, NIF);
            ps.setString(2, nom);
            ps.setString(3, cognom);
            ps.setString(4, cognom2);
            ps.setString(5, address);
            ps.setInt(6, telefon);
            ps.setDate(7, dataNaix);
            ps.setDate(8, dataIncorporacio);
            ps.setString(9, idUsuari);
            ps.execute();
            }
            
            
            query = "UPDATE `team_management`.`entrenador` SET `compte_bancari`=?, `nivell_entrenador`=?, `certificat`=?, `funcio`=?, `fotocopia_DNI`=?, `foto`=?, `num_salut`=?, `reconeixement_medic`=?, `tot_entregat`=? WHERE `fk_usuari`= ?";
            ps = con.prepareStatement(query);
            ps.setString(1, compteBancari);
            ps.setString(2, nivellEntrenador);
            ps.setString(3, certificat);
            ps.setString(4, funcEntrenador);
            ps.setString(5, fotocopiaDNI);
            ps.setString(6, foto);
            ps.setString(7, numSalut);
            ps.setString(8, reconeixementMedic);
            ps.setBoolean(9, totEntregat);
            ps.setString(10, idUsuari);
            ps.execute();
    }
    
}
