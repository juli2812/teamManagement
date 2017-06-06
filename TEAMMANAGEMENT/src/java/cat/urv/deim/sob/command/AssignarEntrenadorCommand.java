package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Equip;
import cat.urv.deim.sob.Usuari;
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

public class AssignarEntrenadorCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            updateEntrenadorEquip(request.getParameter("identrenador"),request.getParameter("equip"));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AssignarEntrenadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/assignar_entrenador.jsp?afegit=true").forward(request, response);
    }
    
    public void updateEntrenadorEquip (String idUsuari, String nomEquip) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "UPDATE `team_management`.`entrenador` SET `fk_equip`=? WHERE `fk_usuari`= ?;";
            ps = con.prepareStatement(query);
                    ps.setString(1, nomEquip);
                    ps.setString(2, idUsuari);
            
            ps.execute();
    }
    
}
