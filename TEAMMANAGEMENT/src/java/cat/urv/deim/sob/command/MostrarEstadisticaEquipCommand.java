/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.ValoracioPartit;
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

/**
 *
 * @author Maria
 */
public class MostrarEstadisticaEquipCommand  implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            ArrayList<ValoracioPartit> dades=null;
            ArrayList<String> partits=null;
            String equip = "";
            HttpSession session = request.getSession(true);
        // 1. process the request
        
        try {
            //obtenim l'equip de l'entrenador
            equip = getEquip(request.getParameter("idusuari"));
            if("temporada".equals(request.getParameter("opcio"))){
                dades=obtenirEstadistica(equip);
            }else{
                partits = (ArrayList<String>)obtenirPartits(equip);
                session.setAttribute("partits", partits);
            }

            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DadesJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
        
            session.setAttribute("valoracio", dades);
            session.setAttribute("equip", equip);
        ServletContext context = request.getSession().getServletContext();
            if(dades != null || partits !=null){
            if("temporada".equals(request.getParameter("opcio"))){
            context.getRequestDispatcher("/consultar_est_equip_2.jsp").forward(request, response);
            }else{
               
            context.getRequestDispatcher("/seleccionar_partit_equip.jsp").forward(request, response);    
            }
            }else context.getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
    
    
     public String getEquip(String user)throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
        ResultSet resultSet = null;
        
        String query = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            query = "SELECT fk_equip FROM `team_management`.`entrenador` WHERE `fk_usuari`=?;";    
            
                ps = con.prepareStatement(query);
                ps.setString(1, user);
                resultSet=ps.executeQuery();
            
                
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
    }
    
    public ArrayList<ValoracioPartit> obtenirEstadistica (String equip) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<ValoracioPartit> valtemp = new ArrayList<>();
        int gols=0, nota=0, as=0, tg =0, tv = 0;
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            
            //Obtenemos todos los partidos de un equipo
            String query = "SELECT `fk_activitat` FROM `team_management`.`partit` WHERE fk_activitat IN (SELECT `id_activitat` FROM `team_management`.`activitat` WHERE `fk_equip` = ?);";
            ps = con.prepareStatement(query);
            ps.setString(1, equip);
            ResultSet resultSet=ps.executeQuery();
            
            while(resultSet.next()){
                //obtenemos la valoración de cada partido
                String quer = "SELECT * FROM `team_management`.`valoracio_partit` WHERE `fk_partit` = ?;";
                ps = con.prepareStatement(quer);
                ps.setInt(1, resultSet.getInt(1));
                resultSet2=ps.executeQuery();
            
                //de cada valoración obtenemos el total
                while(resultSet2.next()) {
                    String quer3 = "SELECT * FROM `team_management`.`valoracio` WHERE `id_valoracio` = ? ;";
                    ps = con.prepareStatement(quer3);
                    ps.setString(1, resultSet2.getString(1));
            
                    ResultSet resultSet3=ps.executeQuery();
                    if (resultSet3.next()) {
                        gols += resultSet2.getInt(5); 
                        nota += resultSet3.getInt(2); 
                        as += resultSet2.getInt(4); 
                        tg += resultSet2.getInt(6); 
                        tv += resultSet2.getInt(7); 
                        //valtemp.add(new ValoracioPartit(resultSet2.getString(2),resultSet2.getInt(3),resultSet2.getInt(4),resultSet2.getInt(5),resultSet2.getInt(6),resultSet2.getInt(7),resultSet2.getInt(1),resultSet2.getString(8),resultSet2.getBoolean(9),resultSet2.getInt(10),resultSet2.getInt(11), resultSet3.getInt(2)));
                    }
                }
                /*System.out.println("-------- ");
                System.out.println("partit: "+resultSet.getInt(1)+"= gols: "+gols+", nota: "+nota+", assist: "+as+", tg: "+tg+", tv: "+tv);
                System.out.println("-------- ");*/
                
                valtemp.add(new ValoracioPartit(null, resultSet.getInt(1), as, gols, tg, tv, 0, null, false, 0, 0, nota));
                
                gols = 0; 
                nota = 0; 
                as = 0; 
                tg = 0; 
                tv = 0; 
            }    
            
            /*for(ValoracioPartit vpp: valtemp){
                System.out.println("partit: "+vpp.getFk_partit()+"= gols: "+vpp.getGols()+", nota: "+vpp.getNota()+", assist: "+vpp.getAssistencia()+", tg: "+vpp.getTarjetes_grogues()+", tv: "+vpp.getTarjetes_vermelles());
            }*/
            
            return valtemp;
    }
    
       public ArrayList<String> obtenirPartits (String idUsuari) throws SQLException, ClassNotFoundException{
        Connection con;
        ArrayList<String> valtemp = new ArrayList<>();
        PreparedStatement ps;
        ResultSet resultSet2 = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String query = "SELECT `id_activitat` FROM `team_management`.`activitat` WHERE `fk_equip` = ?;";

            ps = con.prepareStatement(query);
            ps.setString(1, idUsuari);
            
            ResultSet resultSet=ps.executeQuery();
       
            while(resultSet.next()){
            String quer = "SELECT `fk_activitat` FROM `team_management`.`partit` WHERE `fk_activitat` = ?;";
            ps = con.prepareStatement(quer);
            ps.setString(1, resultSet.getString(1));
            
            resultSet2=ps.executeQuery();
            
            
                if (resultSet2.next()) {
                    valtemp.add(resultSet2.getString(1));
                }
            }
            return valtemp;
    }
}
