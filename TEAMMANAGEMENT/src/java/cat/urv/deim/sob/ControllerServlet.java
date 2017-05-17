package cat.urv.deim.sob;

import cat.urv.deim.sob.command.AltaEquipCommand;
import cat.urv.deim.sob.command.ClubCommand;
import cat.urv.deim.sob.command.Command;
import cat.urv.deim.sob.command.CrearCommand;
import cat.urv.deim.sob.command.DirEsportiuCommand;
import cat.urv.deim.sob.command.EntrenadorNoEquipCommand;
import cat.urv.deim.sob.command.WriteCommand;
import cat.urv.deim.sob.command.InitCommand;
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
        this.commands.put("club", new ClubCommand());
        this.commands.put("diresportiu", new DirEsportiuCommand());
        this.commands.put("crear", new CrearCommand());
        this.commands.put("login", new LoginCommand());
        this.commands.put("altaequip", new AltaEquipCommand());
        this.commands.put("entrenadornoequip", new EntrenadorNoEquipCommand());
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
