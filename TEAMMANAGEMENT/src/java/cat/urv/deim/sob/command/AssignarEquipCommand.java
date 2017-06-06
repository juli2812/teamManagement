package cat.urv.deim.sob.command;

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

public class AssignarEquipCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
           // HttpSession session = request.getSession(true);
 
            ArrayList<String> nomUsuari = new ArrayList();
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        
       // session.setAttribute("tipusUsuari", request.getAttribute("tipususuari"));
       // session.setAttribute("idUsuari", request.getAttribute("idusuari"));
        
        if("jugador".equals(request.getParameter("qui"))){
            context.getRequestDispatcher("/assignar_jugador.jsp").forward(request, response);
        }
        else{
            context.getRequestDispatcher("/assignar_entrenador.jsp").forward(request, response);        
        }
    }
}
