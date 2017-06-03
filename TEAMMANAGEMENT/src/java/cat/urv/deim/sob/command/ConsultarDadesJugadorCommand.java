/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Jugador;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maria
 */
public class ConsultarDadesJugadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            Jugador dades=null;
            byte[] fB = null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            
            dades=getJugador(request.getParameter("jugador"));
            fB = getFotoB("jugador");

            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("jug", dades);
            if (fB != null) {
               /* response.setContentType("image/jpeg");
            ServletOutputStream out2 = response.getOutputStream();
            out2.write(fB);*/
            //session.setAttribute("fB", fB);
        }
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/consultar_dades_jugador2.jsp").forward(request, response);
        
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
    
    public byte[] getFotoB(String idUsuari)  {
        Connection cn=null;
        ResultSet rs = null;
        PreparedStatement ps;
        
        byte[] buffer = null;
        try {
           
            String sql = "select foto from `jugador` where id_usuari= '"+idUsuari+"'";
            ps = cn.prepareStatement(sql);
            ps.setString(1, idUsuari);
            rs=ps.executeQuery();
            
            while (rs.next()){
                Blob bin = rs.getBlob("ImgCedula");
                if (bin != null) {
                    InputStream inStream = bin.getBinaryStream();
                    int size = (int) bin.length();
                    buffer = new byte[size];
                    int length = -1;
                    int k = 0;
                    try {
                        inStream.read(buffer, 0, size);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            return null;
        } finally {
            cn=null;
            rs = null;
            
        }
        return buffer;
    }
    
}
