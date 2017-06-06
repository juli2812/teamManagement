/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Maria
 */
public class DonarBaixaJugadorCommand implements Command{
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        
        if(!"".equals(request.getParameter("jugador"))){
            
        // 1. process the request
        try {
            donarBaixa(request.getParameter("jugador"));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DonarBaixaJugadorCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 2. produce the view with the web result
       
        
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/donar_baixa_jugador_2.jsp").forward(request, response);
        }
        else{
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);
        
        }
    }
    public void donarBaixa(String id_usuari) throws SQLException, ClassNotFoundException{
        Connection con;
        PreparedStatement ps;
            Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_management?serverTimezone=UTC", "root", "");
            con.setSchema("team_management");
            String sentenciaSQL = "UPDATE `team_management`.`jugador` SET `fk_equip` = NULL WHERE `fk_usuari` = ?;";
            ps = con.prepareStatement(sentenciaSQL);
                    ps.setString(1, id_usuari);

            ps.executeUpdate();
    }
    
    }