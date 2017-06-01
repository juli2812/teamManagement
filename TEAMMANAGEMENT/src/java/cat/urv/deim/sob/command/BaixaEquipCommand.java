package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Equip;
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

public class BaixaEquipCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            baixaEquip(request.getParameter("nomequip"));
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BaixaEquipCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/baixa_equip.jsp?baixa=true").forward(request, response);
    }
    
    
    public void baixaEquip (String nomequip) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<Equip> equips = new ArrayList();
        Equip equip = null;
        PreparedStatement ps;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "UPDATE `team_management`.`equip` SET `calendari`=?, `classificacio`=? WHERE `nom_equip`= ?";
            ps = con.prepareStatement(query);
            ps.setString(1, null);
            ps.setString(2, null);
            ps.setString(3, nomequip);
            System.out.println("EXEC: "+ps.execute());
    }
    
    
}
