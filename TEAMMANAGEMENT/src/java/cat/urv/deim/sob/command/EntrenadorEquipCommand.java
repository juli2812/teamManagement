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
import org.apache.commons.codec.digest.DigestUtils;

public class EntrenadorEquipCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            ArrayList<Equip> dades=new ArrayList();
            Entrenador entrenador = null;
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            numFedClub=getNumFed(request.getParameter("idusuari"),request.getParameter("tipususuari"));
            if(numFedClub!=0){
            dades=getEquips(numFedClub);
            entrenador = getUsuari(request.getParameter("entrenador"));
            }
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EntrenadorEquipCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        if(numFedClub!=0){
            session.setAttribute("equips", dades);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/assignar_entrenador_2.jsp?entrenador="+request.getParameter("entrenador")+"&nom="+entrenador.getNom()+"&cognom="+entrenador.getCognom()).forward(request, response);
        }else{
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/assignar_entrenador.jsp").forward(request, response);
        }
    }
    
    
    public ArrayList<Equip> getEquips (int numFed) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<Equip> equips = new ArrayList();
        Equip equip = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`equip` WHERE `fk_club`=?;";
            ps = con.prepareStatement(query);
            ps.setInt(1, numFed);
            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                equip= new Equip(resultSet.getString(1),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
                System.out.println(equip.toString());
                equips.add(equip);
            }
            return equips;
    }
    
    public Entrenador getUsuari (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        Entrenador entrenador = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT * FROM `team_management`.`usuari` WHERE `id_usuari`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                    entrenador = new Entrenador("","","","","","","","",false,"",resultSet.getString(2),resultSet.getString(3),"","",0,idUsuari,null,"",null);
            }
            return entrenador;
    }
    
    public int getNumFed (String idUsuari, String tipusUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query;
            if("President".equals(tipusUsuari)){
                query = "SELECT `id_club` FROM `team_management`.`club` WHERE `fk_president`=?;";
            }
            else{
                query = "SELECT `id_club` FROM `team_management`.`club` WHERE `fk_dir_esportiu`=?;";            
            }
            ps = con.prepareStatement(query);
                    ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
            return resultSet.getInt(1);
            }
            return 0;
    }
    
}
