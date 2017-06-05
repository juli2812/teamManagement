package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Jugador;
import cat.urv.deim.sob.Entrenament;
import cat.urv.deim.sob.Exercici;
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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.sql.Date;

public class IniciPlanificarEntrenamentCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            String equip="";
            ArrayList<Exercici> exercicis = new ArrayList<Exercici>();
            Exercici exercici = null;
            HttpSession session = request.getSession(true);
            int numEx=0;
            if(null!=request.getParameter("afegir")&&"true".equals(request.getParameter("afegir"))){
                numEx=Integer.parseInt(session.getAttribute("numexercicis").toString());
                session.setAttribute("numexercicis", (numEx+1));
            }
            else{
                numEx=Integer.parseInt(session.getAttribute("numexercicis").toString());
            }
            
            session.setAttribute("hora", request.getParameter("hora"));
            session.setAttribute("dataent", request.getParameter("dataent"));
            // 1. process the request
            
            if(null!=request.getParameter("afegir")&&"false".equals(request.getParameter("afegir"))){
                int i = 0;
                for(i=0; i<numEx; i++){
                    exercici=new Exercici(0, 0, request.getParameter("explicacio"+(i)), Integer.parseInt(request.getParameter("temps"+(i))), request.getParameter("material"+(i)), false, "");
                    exercicis.add(exercici);
                }
                try {
                    equip=getEquipEntrenador(request.getParameter("idusuari"));
                    int id = insertEntrenament(equip,request.getParameter("dataent"), request.getParameter("hora"));
                for(i=0; i<numEx; i++){
                    insertExercici(id,exercicis.get(i).getExplicacio(), exercicis.get(i).getTempsMin(), exercicis.get(i).getMaterial());
                }
                } catch (SQLException ex) {
                    Logger.getLogger(IniciPlanificarEntrenamentCommand.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IniciPlanificarEntrenamentCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            int i = 0;
                for(i=0; i<numEx; i++){
                    exercici=new Exercici(0, 0, request.getParameter("explicacio"+(i)), Integer.parseInt(request.getParameter("temps"+(i))), request.getParameter("material"+(i)), false, "");
                    exercicis.add(exercici);
                }
            }
        // 2. produce the view with the web result
            
            session.setAttribute("exercicis", exercicis);
            //session.setAttribute("nomequip", equip);
            //session.setAttribute("entrenament", entrenament);
            if(null!=request.getParameter("afegir")&&"false".equals(request.getParameter("afegir"))){
                ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else{
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/planificar_entrenament.jsp?noprimer=true").forward(request, response);
            }
    }
            
    
    public int insertEntrenament (String nomequip, String data, String hora) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "INSERT INTO `team_management`.`activitat` (fk_equip,data,hora) VALUES (?,?,?);";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, nomequip);
                    ps.setString(2, data);
                    ps.setString(3, hora);
            ps.executeUpdate();
            sentenciaSQL = "SELECT `id_activitat` FROM `activitat` WHERE `fk_equip`=? ORDER BY `id_activitat` DESC LIMIT 1";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, nomequip);
            ResultSet resultSet=ps.executeQuery();
            int idAct=0;
            if (resultSet.next()) {
                idAct=resultSet.getInt(1);
            }
            sentenciaSQL = "INSERT INTO `team_management`.`entrenament` (fk_activitat) VALUES (?);";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setInt(1, idAct);
            ps.executeUpdate();
            return idAct;
            
    }
    
    public void insertExercici (int id, String explicacio, int tempsMin, String material) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "INSERT INTO `team_management`.`exercici` (fk_entrenament,explicacio,temps_min,material,fet) VALUES (?,?,?,?,false);";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setInt(1,id);
                    ps.setString(2, explicacio);
                    ps.setInt(3, tempsMin);
                    ps.setString(4, material);
            ps.executeUpdate();
            
    }
    public String getEquipEntrenador (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        Entrenador entrenador = null;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `fk_equip` FROM `team_management`.`entrenador` WHERE `fk_usuari`=?;";
            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            ResultSet resultSet=ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return "noequip";
    }
    
    
}
