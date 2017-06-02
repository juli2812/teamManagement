package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Entrenador;
import cat.urv.deim.sob.Jugador;
import cat.urv.deim.sob.Partit;
import cat.urv.deim.sob.Usuari;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

public class EmplenarAlineacioCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            int numFedClub=0;
            ArrayList<Partit> dades=new ArrayList<Partit>();
            HttpSession session = request.getSession(true);
        // 1. process the request
        // 2. produce the view with the web result
        // 2. produce the view with the web result
        // 2. produce the view with the web result
        // 2. produce the view with the web result
        
            session.setAttribute("idpartit", request.getParameter("idpartit"));
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/realitzar_alineacio_2.jsp").forward(request, response);
         
    }
    
}
