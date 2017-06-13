/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Maria
 */
public class AltaEntrenadorCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
            int result0 = 0;
            int result = 0;
            String error = "0"; //1 = no s'ha pogut insertar 2= ha anat bé 3 = ja existeix
            HttpSession session = request.getSession(true);
            
          if(!"".equals(request.getParameter("id_usuari"))&&!"".equals(request.getParameter("dni"))&&!"".equals(request.getParameter("nom"))&&!"".equals(request.getParameter("cognom1"))&&!"".equals(request.getParameter("cognom2"))&&!"".equals(request.getParameter("adress"))&&!"".equals(request.getParameter("contrsenya"))&&!"".equals(request.getParameter("telefon"))&&!"".equals(request.getParameter("dataincorp"))&&!"".equals(request.getParameter("datanaix"))&&!"".equals(request.getParameter("id_equip"))&&!"".equals(request.getParameter("compte_bancari"))&&!"".equals(request.getParameter("nivell_entrenador"))&&!"".equals(request.getParameter("tipus_modalitat_actual"))&&!"".equals(request.getParameter("certificat"))&&!"".equals(request.getParameter("funcio"))&&!"".equals(request.getParameter("fotocopia_DNI"))&&!"".equals(request.getParameter("foto"))&&!"".equals(request.getParameter("num_salut"))&&!"".equals(request.getParameter("reconeixemt_medic"))&&!"".equals(request.getParameter("tot_entregat"))){
        // 1. process the request
        try {
            result0 = registrar(request.getParameter("dni"),request.getParameter("nom"),request.getParameter("cognom1"),request.getParameter("cognom2"),request.getParameter("address"),Integer.parseInt(request.getParameter("telefon")),request.getParameter("identrenador"),request.getParameter("datanaix"),request.getParameter("contrasenya"),request.getParameter("dataincorp"));
            result = registrarEntrenador(request.getParameter("identrenador"), request.getParameter("comptebancari"), request.getParameter("nivellentrenador"), Integer.parseInt(request.getParameter("tipusmodalitatactual")), request.getParameter("certificatentrenador"), request.getParameter("funcio"), request.getParameter("fotocopia_DNI"), request.getParameter("foto"), request.getParameter("numsalut"), request.getParameter("reconeixemt_medic"), Boolean.parseBoolean(request.getParameter("totentregat")));
            error = "2";
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result0 == 1 && result != 1){
            error = "1";
            // esborrem l'usuari
            try {
            esborrarUsuari(request.getParameter("id_usuari"));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }else if(result0 != 1){
            error = "3";
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        session.setAttribute("error", error);
        context.getRequestDispatcher("/registre_entrenador_1.jsp").forward(request, response);
        }
        else{
              
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        
        }
    }
    
    public void esborrarUsuari (String idUsuari) throws ClassNotFoundException, SQLException{
         Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "DELETE FROM `team_management`.`usuari` WHERE `id_usuari` = ?;";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, idUsuari);
            ps.executeUpdate();
    }
    
    public int registrar (String dni, String nom,String cognom1, String cognom2, String address, int telefon, String idUsuari, String dataNaix, String contrasenya, String dataIncorp) throws SQLException, ClassNotFoundException{
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
            
            return 1;
    }
    
    public int registrarEntrenador (String fkUsuari, String compteBancari, String nivellEntrenador, int tipusModalitatActual, String certificatEntrenador, String funcioEntrenador, String fotocopiaDNI, String foto, String numSalut, String reconeixementMedic, Boolean totEntregat) throws SQLException, ClassNotFoundException{
            Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con;
        PreparedStatement ps;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String sentenciaSQL = "INSERT INTO `entrenador` (`fk_usuari`, `compte_bancari`, `nivell_entrenador`, `tipus_modalitat_actual`, `certificat`, `funcio`, `fotocopia_DNI`, `foto`, `num_salut`, `reconeixement_medic`, `tot_entregat`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
       
        ps = con.prepareStatement(sentenciaSQL);
        ps.setString(1, fkUsuari);
        
        if("".equals(compteBancari)){ ps.setString(2,null);}
        else ps.setString(2, compteBancari);
        
        if("".equals(nivellEntrenador)){ ps.setString(3,null);}
        else ps.setString(3, nivellEntrenador);
        
        if(tipusModalitatActual<0 || tipusModalitatActual>2){ ps.setString(4,null);}
        else {
            ps.setString(4, Integer.toString(tipusModalitatActual));
        }
        
        if("".equals(certificatEntrenador)){ ps.setString(5,null);}
        else ps.setString(5, certificatEntrenador);
        
        if("".equals(funcioEntrenador)){ ps.setString(6,null);}
        else ps.setString(6, funcioEntrenador);
        
        if("".equals(fotocopiaDNI)){ ps.setString(7,null);}
        else ps.setString(7, fotocopiaDNI);
        
        if("".equals(numSalut)){ ps.setString(9,null);}
        else ps.setString(9, numSalut);
        
        if("".equals(reconeixementMedic)){ ps.setString(10,null);}
        else ps.setString(10, reconeixementMedic);
        
        if("false".equals(totEntregat)){ ps.setString(11, "0");}
        else ps.setString(11, "1");
        
                //Para la foto
        FileInputStream fis = null;
        ps.setBinaryStream(8, null);
        try {
            File file = new File(foto);
            System.out.println("llega a aqui");
            fis = new FileInputStream(file);
            
            ps.setBinaryStream(8, fis, (int) file.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AltaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        //---------------
        
        ps.executeUpdate();
        return 1;
        
        /*RETOCADO EN EL CURRO*/
    }
}