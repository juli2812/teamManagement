package cat.urv.deim.sob;

import cat.urv.deim.sob.command.DirEsportiuCommand;
import cat.urv.deim.sob.command.Command;
import cat.urv.deim.sob.command.EntrenadorCommand;
import cat.urv.deim.sob.command.RegistrarClubCommand;
import cat.urv.deim.sob.command.PresidentCommand;
import cat.urv.deim.sob.command.WriteCommand;
import cat.urv.deim.sob.command.InitCommand;
import cat.urv.deim.sob.command.JugadorCommand;
import cat.urv.deim.sob.command.LoginCommand;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class ControllerServlet extends HttpServlet {

    private Map commands = new HashMap();

    @Override
    public void init() {
        // list of commands
        this.commands.put("init", new InitCommand());
        this.commands.put("write", new WriteCommand());
        this.commands.put("club", new DirEsportiuCommand());
        this.commands.put("diresportiu", new PresidentCommand());
        this.commands.put("crear", new RegistrarClubCommand());
        this.commands.put("login", new LoginCommand());
        this.commands.put("entrenador", new EntrenadorCommand());
        this.commands.put("jugador", new JugadorCommand());
    }

    protected void processCommand(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. choose action
        String formAction = request.getParameter("form_action");

        if (null == formAction) {
            formAction = "login";
        }

        // 2. choose related command
        Command command = (Command) commands.get(formAction);

        if (null == command) {
            throw new IllegalArgumentException(
                    "No command for form action: " + formAction);
        }

        // 3. run the command
        command.execute(request, response);
    }

    @Override
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processCommand(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processCommand(request, response);
    }
}
