/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Jugador;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class EscollirDestinatariCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> usuaris = new ArrayList();
        ArrayList<Jugador> jugadors = new ArrayList<Jugador>();
        ArrayList<Entrenador> entrenadors = new ArrayList<Entrenador>();
        
          if(!"".equals(request.getParameter("destinatari"))){
        // 1. process the request
        try {
            if("dbj".equals(request.getParameter("accio"))||"dbe".equals(request.getParameter("accio"))){
                if("jugador".equals(request.getParameter("destinatari"))){
                jugadors = getJugadorsEquip ();
                }
                else if("entrenador".equals(request.getParameter("destinatari"))){
                    entrenadors = getEntrenadorsJugador();
                }
                else{
                usuaris=obtenirDestinatarisAmbEquip(request.getParameter("destinatari"));}
            }else{
            jugadors = obtenirDestinatarisJ();
            entrenadors = obtenirDestinatarisE();
            usuaris=obtenirDestinataris(request.getParameter("destinatari"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        HttpSession session = request.getSession(true);
        session.setAttribute("destinatari", request.getParameter("destinatari"));
        session.setAttribute("usuaris", usuaris);
        session.setAttribute("j", jugadors);
        session.setAttribute("entrenadors", entrenadors);
        if("".equals(request.getParameter("accio")) || request.getParameter("accio") == null){
        context.getRequestDispatcher("/registre_incidencia.jsp").forward(request, response);
        }else if ("partit".equals(request.getParameter("accio"))){context.getRequestDispatcher("/alta_partit_1.jsp").forward(request, response);}
        else if ("cde".equals(request.getParameter("accio"))){context.getRequestDispatcher("/consultar_dades_entrenador_1.jsp").forward(request, response);}
        else if ("cdj".equals(request.getParameter("accio"))){context.getRequestDispatcher("/consultar_dades_jugador_1.jsp").forward(request, response);}
        else if ("dbj".equals(request.getParameter("accio"))){context.getRequestDispatcher("/donar_baixa_jugador_1.jsp").forward(request, response);}
        else if ("dbe".equals(request.getParameter("accio"))){context.getRequestDispatcher("/donar_baixa_entrenador_1.jsp").forward(request, response);}
        }else{
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        
        }
    }
     public ArrayList<Entrenador> obtenirDestinatarisE () throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<Entrenador> jugadors = new ArrayList();
        Entrenador jugador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_usuari` FROM `team_management`.`entrenador`;";
            ps = con.prepareStatement(query);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                query = "SELECT `nom`, `cognom1` FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, resultSet.getString(1));
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    jugador = new Entrenador(resultSet2.getString(1),resultSet2.getString(2),resultSet.getString(1));
                    jugadors.add(jugador);
                }
            }
            return jugadors;
    }
    
    public ArrayList<Jugador> obtenirDestinatarisJ () throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<Jugador> jugadors = new ArrayList();
        Jugador jugador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_usuari` FROM `team_management`.`jugador`;";
            ps = con.prepareStatement(query);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                query = "SELECT `nom`, `cognom1` FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, resultSet.getString(1));
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    jugador = new Jugador(resultSet2.getString(1),resultSet2.getString(2),resultSet.getString(1));
                    jugadors.add(jugador);
                }
            }
            return jugadors;
    }
    public ArrayList<String> obtenirDestinataris (String destinatari) throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            String query = "";
            
            if("jugador".equals(destinatari)){
                query = "SELECT `fk_usuari`FROM `team_management`.`jugador`;";
            }else if("equip".equals(destinatari)){
                query = "SELECT nom_equip FROM `team_management`.`equip`;";
            }else if("entrenador".equals(destinatari)){
                query = "SELECT fk_usuari FROM `team_management`.`entrenador`;";
            }
            else{
                query = "SELECT id_usuari FROM `team_management`.`usuari`;";
            }
            
            ps = con.prepareStatement(query);
            
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {
                resultado.add(resultSet.getString(1));
            }
            return resultado;
    }
    
    public ArrayList<Jugador> getJugadorsEquip () throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<Jugador> jugadors = new ArrayList();
        Jugador jugador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_usuari` FROM `team_management`.`jugador` WHERE `fk_equip` IS NOT NULL;";
            ps = con.prepareStatement(query);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                query = "SELECT `nom`, `cognom1` FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, resultSet.getString(1));
                resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    jugador = new Jugador(resultSet2.getString(1),resultSet2.getString(2),resultSet.getString(1));
                    jugadors.add(jugador);
                }
            }
            return jugadors;
    }
    
    public ArrayList<Entrenador> getEntrenadorsJugador () throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        ArrayList<Entrenador> entrenadors=new ArrayList<Entrenador>();
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String query = "SELECT * FROM `team_management`.`entrenador` WHERE `fk_equip` IS NOT NULL;";
        ps = con.prepareStatement(query);
        ResultSet resultSet3=ps.executeQuery();
            while (resultSet3.next()) {
                query = "SELECT * FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
                ps = con.prepareStatement(query);
                ps.setString(1, resultSet3.getString(1));
                //ps = con.prepareStatement(query);
                ResultSet resultSet2=ps.executeQuery();
                if (resultSet2.next()) {
                    entrenador = new Entrenador(resultSet3.getString(3),resultSet3.getString(4),resultSet3.getInt(5),resultSet3.getString(6),resultSet3.getString(7),resultSet3.getString(8),resultSet3.getString(9),resultSet3.getString(10),resultSet3.getString(11),resultSet3.getBoolean(12),resultSet2.getString(1),resultSet2.getString(2),resultSet2.getString(3),resultSet2.getString(4),resultSet2.getString(5),resultSet2.getInt(6),resultSet3.getString(1),resultSet2.getDate(8),resultSet2.getString(9),resultSet2.getDate(10));
                        
                    entrenadors.add(entrenador);
                }
            }
            return entrenadors;
    }
    
    
    public ArrayList<String> obtenirDestinatarisAmbEquip (String destinatari) throws SQLException, ClassNotFoundException{
        ArrayList<String> resultado;
        resultado = new ArrayList();
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            String query = "";
            
            if("jugador".equals(destinatari)){
                query = "SELECT `fk_usuari`FROM `team_management`.`jugador` WHERE `fk_equip` IS NOT NULL ;";
            }else if("equip".equals(destinatari)){
                query = "SELECT nom_equip FROM `team_management`.`equip`;";
            }else if("entrenador".equals(destinatari)){
                query = "SELECT fk_usuari FROM `team_management`.`entrenador`WHERE `fk_equip` IS NOT NULL ;;";
            }
            else{
                query = "SELECT id_usuari FROM `team_management`.`usuari`;";
            }
            
            ps = con.prepareStatement(query);
            
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {
                resultado.add(resultSet.getString(1));
            }
            return resultado;
    }
    
}