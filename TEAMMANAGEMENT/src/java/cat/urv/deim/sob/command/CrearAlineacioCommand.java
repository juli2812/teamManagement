package cat.urv.deim.sob.command;

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

public class CrearAlineacioCommand implements Command {
private static int id=0;

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
        String[] lista=null;
    try {
        // 1. process the request
        // 2. produce the view with the web result
        lista = request.getParameterValues("jugadors_alineacio");
        if((Integer.parseInt(request.getParameter("numjugtit"))+Integer.parseInt(request.getParameter("numjugsup")))==lista.length){
            crearAlineacio(request.getParameter("nomequip"), Integer.parseInt(request.getParameter("idpartit")), Integer.parseInt(request.getParameter("numjugtit")), Integer.parseInt(request.getParameter("numjugsup")),Integer.parseInt(request.getParameter("numdeftit")),Integer.parseInt(request.getParameter("nummigtit")),Integer.parseInt(request.getParameter("numdavtit")), request.getParameter("formacio"));
            crearJugadorAlineacio(lista, lista.length);
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(CrearAlineacioCommand.class.getName()).log(Level.SEVERE, null, ex);
    }
    
            if((Integer.parseInt(request.getParameter("numjugtit"))+Integer.parseInt(request.getParameter("numjugsup")))!=lista.length){
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/realitzar_alineacio_3.jsp?falten=true").forward(request, response);
            }else{
                ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/realitzar_alineacio.jsp?afegit=true").forward(request, response);
            }
    }
    
    public void crearJugadorAlineacio(String[] idUsuari, int numJugadors) throws ClassNotFoundException, SQLException{
    Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String sentenciaSQL="";
        int i=0;
        for(i=0; i<numJugadors;i++){
            sentenciaSQL= "INSERT INTO `team_management`.`jugador_alineacio` (`fk_alineacio`, `fk_jugador`) VALUES (?,?);";
            ps = con.prepareStatement(sentenciaSQL);
                        ps.setInt(1, id);
                        ps.setString(2, idUsuari[i]);
                ps.executeUpdate();
        }
    }
    
    public void crearAlineacio(String nomEquip, int idPartit, int numJugTit, int numJugSup, int numDefTit, int numMigTit, int numDavTit, String formacio) throws ClassNotFoundException, SQLException{
    Connection con;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String sentenciaSQL = "INSERT INTO `team_management`.`alineacio` (`fk_equip`, `fk_partit`, `num_jug_titulars`, `num_jug_suplents`, `num_def_titulars`,`num_mig_titulars`, `num_dav_titulars`,`formacio`) VALUES (?,?,?,?,?,?,?,?);";
        ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, nomEquip);
                    ps.setInt(2, idPartit);
                    ps.setInt(3, numJugTit);
                    ps.setInt(4, numJugSup);
                    ps.setInt(5, numDefTit);
                    ps.setInt(6, numMigTit);
                    ps.setInt(7, numDavTit);
                    ps.setString(8, formacio);
                    id++;
                ps.executeUpdate();
        }
    
}
