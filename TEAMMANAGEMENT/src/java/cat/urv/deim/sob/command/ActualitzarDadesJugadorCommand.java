package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Jugador;
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

public class ActualitzarDadesJugadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            updateJugador(request.getParameter("cursescolar"),request.getParameter("escola"),request.getParameter("nompare"),request.getParameter("nommare"),Boolean.valueOf(request.getParameter("comptetutoritzat")),request.getParameter("comptebancari"),Integer.parseInt(request.getParameter("dorsal")),request.getParameter("fotocopiadni"),"",request.getParameter("catsalut"),request.getParameter("reconmedic"),Boolean.valueOf(request.getParameter("totentregat")),Boolean.valueOf(request.getParameter("lesionat")),request.getParameter("NIF"),request.getParameter("nom"),request.getParameter("cognom"),request.getParameter("cognom2"),request.getParameter("address"),Integer.parseInt(request.getParameter("telefon")),request.getParameter("usuari"),Date.valueOf(request.getParameter("datanaix")),request.getParameter("contrasenya"),Date.valueOf(request.getParameter("dataincorp")));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ActualitzarDadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/actualitzar_dades_jugador.jsp?actualitzat=true").forward(request, response);
    }
    
    
    public void updateJugador (String cursEscolar, String escola, String nomCompletPare, String nomCompletMare, boolean compteTutoritzat, String compteBancari, int dorsal, String fotocopiaDNI, String foto, String numSalut, String reconeixementMedic, boolean totEntregat, boolean lesionat, String NIF, String nom, String cognom, String cognom2, String address, int telefon, String idUsuari, Date dataNaix, String contrasenya, Date dataIncorporacio) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "UPDATE `team_management`.`usuari` SET `NIF`=?, `nom`=?, `cognom1`=?, `cognom2`=?, `adress`=?, `telefon`=?, `dataNaix`=?, `contrasenya`=?, `dataIncorp`=? WHERE `id_usuari`= ?";
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
            
            query = "UPDATE `team_management`.`jugador` SET `compte_bancari`=?, `curs_escolar`=?, `escola`=?, `nom_complet_pare`=?, `nom_complet_mare`=?, `compte_tutoritzat`=?, `dorsal`=?, `fotocopia_DNI`=?, `num_catsalut`=?, `reconeixement_medic`=?, `tot_entregat`=?, `lesionat`=? WHERE `fk_usuari`= ?";
            ps = con.prepareStatement(query);
            ps.setString(1, compteBancari);
            ps.setString(2, cursEscolar);
            ps.setString(3, escola);
            ps.setString(4, nomCompletPare);
            ps.setString(5, nomCompletMare);
            ps.setBoolean(6, compteTutoritzat);
            ps.setInt(7, dorsal);
            ps.setString(8, fotocopiaDNI);
            ps.setString(9, numSalut);
            ps.setString(10, reconeixementMedic);
            ps.setBoolean(11, totEntregat);
            ps.setBoolean(12, lesionat);
            ps.setString(13, idUsuari);
            ps.execute();
            
    }
    
}
