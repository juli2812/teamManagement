package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Incidencia;
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

public class MostrarIncidenciaCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            Incidencia dades=null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            dades=getIncidencia(Integer.parseInt(request.getParameter("idincidencia")));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MostrarIncidenciaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
            session.setAttribute("incidencia", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/consultar_incid_cast_2.jsp").forward(request, response);
    }
    
    
    public Incidencia getIncidencia (int idIncid) throws SQLException, ClassNotFoundException{
        Connection con;
        Incidencia incidencia = null;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`incidencia` WHERE `id_incidencia`=?;";
            ps = con.prepareStatement(query);
            ps.setInt(1, idIncid);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                incidencia = new Incidencia(resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getString(1), resultSet.getInt(2),resultSet.getInt(3));
            }
            return incidencia;
    }
    
    
}
