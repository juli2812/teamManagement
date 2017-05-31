/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Maria
 */
public class AltaJugadorCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        if(!"".equals(request.getParameter("idjugador"))&&!"".equals(request.getParameter("dni"))&&!"".equals(request.getParameter("nom"))&&!"".equals(request.getParameter("cognom1"))&&!"".equals(request.getParameter("cognom2"))&&!"".equals(request.getParameter("adress"))&&!"".equals(request.getParameter("contrsenya"))&&!"".equals(request.getParameter("telefon"))&&!"".equals(request.getParameter("dataincorp"))&&!"".equals(request.getParameter("datanaix"))&&!"".equals(request.getParameter("dorsal"))&&!"".equals(request.getParameter("numcatsalut"))&&!"".equals(request.getParameter("reconeixementmedic"))&&!"".equals(request.getParameter("idclub"))&&!"".equals(request.getParameter("compte_bancari"))){
            
        // 1. process the request
        try {
            registrar(request.getParameter("dni"),request.getParameter("nom"),request.getParameter("cognom1"),request.getParameter("cognom2"),request.getParameter("address"),Integer.parseInt(request.getParameter("telefon")),request.getParameter("idjugador"),request.getParameter("datanaix"),request.getParameter("contrasenya"),request.getParameter("dataincorp"));
            registrarJugador(request.getParameter("idjugador"),request.getParameter("idequip"),request.getParameter("comptebancari"), request.getParameter("cursescolar"), request.getParameter("escola"), request.getParameter("nompare"), request.getParameter("nommare"), request.getParameter("comptetutoritzat"), Integer.parseInt(request.getParameter("dorsal")), request.getParameter("fotocopiadni"), request.getParameter("numcatsalut"), request.getParameter("reconeixementmedic"), Boolean.parseBoolean(request.getParameter("totentregat")), Boolean.parseBoolean(request.getParameter("lesionat")));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else{
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        
        }
    }
    public void registrar (String dni, String nom,String cognom1, String cognom2, String address, int telefon, String id_usuari, String dataNaix, String contrasenya, String dataIncorp) throws SQLException, ClassNotFoundException{
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
                    ps.setString(7, id_usuari);
                    ps.setString(8, dataNaix);
                    ps.setString(9, DigestUtils.sha1Hex(contrasenya));
                    ps.setString(10, dataIncorp);
            ps.executeUpdate();
    }
    
    public void registrarJugador (String fkUsuari, String fkEquip, String compteBancari, String cursEscolar, String escola, String nompare, String nommare, String comptetutoritzat, int dorsal, String fotocopiaDNI, String numCatSalut, String reconeixementMedic, Boolean totEntregat, Boolean lesionat) throws SQLException, ClassNotFoundException{
            Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con;
        PreparedStatement ps;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        int fkClub;
        
        String obtenirClubSQL = "SELECT id_club FROM club";
        ps = con.prepareStatement(obtenirClubSQL);
       // ps.setString(1, president);
        ResultSet rs = ps.executeQuery(obtenirClubSQL);
        rs.next();
        fkClub = Integer.parseInt(rs.getString("id_club"));
        
        String sentenciaSQL = "INSERT INTO `jugador` (`fk_usuari`, `fk_club`, `fk_equip`, `compte_bancari`, `curs_escolar`, `escola`, `nom_complet_pare`, `nom_complet_mare`, `compte_tutoritzat`, `dorsal`, `fotocopia_DNI`, `num_catsalut`, `reconeixement_medic`, `tot_entregat`, `lesionat`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
       
        ps = con.prepareStatement(sentenciaSQL);
        ps.setString(1, fkUsuari);
        ps.setString(2, Integer.toString(fkClub));
        ps.setString(3, fkEquip);
        
        if("".equals(compteBancari)){ ps.setString(4,null);}
        else ps.setString(4, compteBancari);
        
        if("".equals(cursEscolar)){ ps.setString(5,null);}
        else ps.setString(5, cursEscolar);
        
        if("".equals(escola)){ ps.setString(6,null);}
        else ps.setString(6, escola);     
        
        if("".equals(nompare)){ ps.setString(7,null);}
        else ps.setString(7, nompare);
        
        if("".equals(nommare)){ ps.setString(8,null);}
        else ps.setString(8, nommare);
        
        if("false".equals(comptetutoritzat)){ ps.setString(9, "0");}
        else ps.setString(9, "1");

        if(dorsal < 1 || dorsal > 17){ ps.setString(10,null);}
        else ps.setString(10, Integer.toString(dorsal));
        
        if("".equals(fotocopiaDNI)){ ps.setString(11,null);}
        else ps.setString(11, fotocopiaDNI);
        
        if("".equals(numCatSalut)){ ps.setString(12,null);}
        else ps.setString(12, numCatSalut);
        
        if("".equals(reconeixementMedic)){ ps.setString(13,null);}
        else ps.setString(13, reconeixementMedic);
        
        if("false".equals(totEntregat)){ ps.setString(14, "0");}
        else ps.setString(14, "1");
        
        if("false".equals(lesionat)){ ps.setString(15, "0");}
        else ps.setString(15, "1");
        
        ps.executeUpdate();
    }
}