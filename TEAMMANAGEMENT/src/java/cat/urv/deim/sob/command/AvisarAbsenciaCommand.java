package cat.urv.deim.sob.command;

import cat.urv.deim.sob.command.AltaJugadorCommand;
import cat.urv.deim.sob.command.Command;
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
public class AvisarAbsenciaCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        
        //if(!"".equals(request.getParameter("idjugador"))&&!"".equals(request.getParameter("dni"))&&!"".equals(request.getParameter("nom"))&&!"".equals(request.getParameter("cognom1"))&&!"".equals(request.getParameter("cognom2"))&&!"".equals(request.getParameter("adress"))&&!"".equals(request.getParameter("contrsenya"))&&!"".equals(request.getParameter("telefon"))&&!"".equals(request.getParameter("dataincorp"))&&!"".equals(request.getParameter("datanaix"))&&!"".equals(request.getParameter("dorsal"))&&!"".equals(request.getParameter("numcatsalut"))&&!"".equals(request.getParameter("reconeixementmedic"))&&!"".equals(request.getParameter("idclub"))&&!"".equals(request.getParameter("compte_bancari"))){
            //si usuarios
        // 1. process the request
        try {
            registrarAbsencia(request.getParameter("usuari"),request.getParameter("motiu"),request.getParameter("data"), Boolean.valueOf(request.getParameter("justificada")),Integer.parseInt(request.getParameter("num_absencies")),request.getParameter("justificantop"));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AltaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
       /* }
        else{
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        
        }*/
    }
    public void registrarAbsencia (String idjugador,String motiu, String data, boolean justificada, int num_absencies,String justificantop) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
           // ArrayList<String> idsUsuari  = new ArrayList();
            String obtenerSQL = "";
            ResultSet resultSet;
            
            /*switch(destino){
                case "club":
                    obtenerSQL = "SELECT `id_usuari` FROM `usuari`;";
                    ps = con.prepareStatement(obtenerSQL);
                    resultSet=ps.executeQuery();
                    
                    while (resultSet.next()) {
                        idsUsuari.add(resultSet.getString(1));
                    }
                    break;
                case "equip":
                    obtenerSQL = "SELECT `fk_usuari` FROM `team_management`.`jugador` WHERE `fk_equip` = ?;";
                    ps = con.prepareStatement(obtenerSQL);
                    ps.setString(1, usuaris);
                    resultSet=ps.executeQuery();
                    
                    while (resultSet.next()) {
                        System.out.println("equipU-  " +resultSet.getString(1));
                        idsUsuari.add(resultSet.getString(1));
                    }
                    break;
                case "jugador":
                    idsUsuari.add(usuaris);
                    break;
                case "entrenador":
                    idsUsuari.add(usuaris);
                    break;
            }*/
            
            //for(String user: idsUsuari){
                String sentenciaSQL = "INSERT INTO `team_management`.`absencia` (fk_jugador, motiu, dia, justificada, num_absencies, justificant_op) VALUES (?,?,?,?,?,?);";
                ps = con.prepareStatement(sentenciaSQL);
                        ps.setString(1, idjugador);
                        ps.setString(2, motiu);
                        ps.setString(3, data);
                        ps.setBoolean(4, justificada);
                        ps.setString(6, justificantop);
                        ps.setInt(5, num_absencies);
                ps.executeUpdate();
            //}
    }
}