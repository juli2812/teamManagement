package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Convocatoria;
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

public class JugadorConfirmatAssistenciaCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            ArrayList<Convocatoria> convocatorias = new ArrayList<Convocatoria>();
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            convocatorias=getConvocatoriasEntrenador(Integer.parseInt(request.getParameter("idpartit")));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(JugadorConfirmatAssistenciaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result            
            
            session.setAttribute("convocatorias", convocatorias);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/controlar_assistencia_3.jsp").forward(request, response);
    }
    
    public ArrayList<Convocatoria> getConvocatoriasEntrenador (int idPartit) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        Convocatoria convocatoria=null;
        ArrayList<Convocatoria> convocatorias = new ArrayList<Convocatoria>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
        con.setSchema("team_management");
        String query = "SELECT * FROM `team_management`.`convocatoria` WHERE `confirmat`=true AND `fk_partit`=?;";
        ps = con.prepareStatement(query);
        ps.setInt(1, idPartit);
        ResultSet resultSet=ps.executeQuery();
        while (resultSet.next()) {
            query = "SELECT `nom`, `cognom1` FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, resultSet.getString(1));
            ResultSet resultSet2=ps.executeQuery();
            if(resultSet2.next()){
                convocatoria=new Convocatoria(resultSet2.getString(1),resultSet2.getString(2),resultSet.getString(1),resultSet.getInt(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getDate(7),resultSet.getInt(8),resultSet.getInt(9),resultSet.getBoolean(10),resultSet.getBoolean(11));
                convocatorias.add(convocatoria);
            }
            }
            return convocatorias;
    }
    
}
