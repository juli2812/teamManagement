package cat.urv.deim.sob;

import cat.urv.deim.sob.command.ActualitzarDadesEntrenadorCommand;
import cat.urv.deim.sob.command.ActualitzarDadesJugadorCommand;
import cat.urv.deim.sob.command.AltaEquipCommand;
import cat.urv.deim.sob.command.AssignarEntrenadorCommand;
import cat.urv.deim.sob.command.AssignarIncidenciaJugadorCommand;
import cat.urv.deim.sob.command.AssignarJugadorAlineacioCommand;
import cat.urv.deim.sob.command.AssignarJugadorCommand;
import cat.urv.deim.sob.command.BaixaEquipCommand;
import cat.urv.deim.sob.command.ClubCommand;
import cat.urv.deim.sob.command.Command;
import cat.urv.deim.sob.command.CrearAlineacioCommand;
import cat.urv.deim.sob.command.CrearCommand;
import cat.urv.deim.sob.command.DadesAssignarIncidenciaJugadorCommand;
import cat.urv.deim.sob.command.DadesEntrenadorCommand;
import cat.urv.deim.sob.command.DadesFederativesJugadorCommand;
import cat.urv.deim.sob.command.DadesJugadorCommand;
import cat.urv.deim.sob.command.DecisioValoracioCommand;
import cat.urv.deim.sob.command.DecisioValoracioEntrCommand;
import cat.urv.deim.sob.command.DecisioValoracioJugadorCommand;
import cat.urv.deim.sob.command.DecisioValoracioJugadorEntrCommand;
import cat.urv.deim.sob.command.DirEsportiuCommand;
import cat.urv.deim.sob.command.EmplenarAlineacioCommand;
import cat.urv.deim.sob.command.EntrenadorClubCommand;
import cat.urv.deim.sob.command.EntrenadorEquipCommand;
import cat.urv.deim.sob.command.EntrenadorNoEquipCommand;
import cat.urv.deim.sob.command.EntrenamentsEntrenadorCommand;
import cat.urv.deim.sob.command.EntrenamentsExerciciEntrenadorCommand;
import cat.urv.deim.sob.command.EscollitJugValoracioCommand;
import cat.urv.deim.sob.command.EscollitJugValoracioEntrCommand;
import cat.urv.deim.sob.command.FitxaEntrenadorJCommand;
import cat.urv.deim.sob.command.InitCommand;
import cat.urv.deim.sob.command.JugadorClubCommand;
import cat.urv.deim.sob.command.JugadorEquipCommand;
import cat.urv.deim.sob.command.JugadorNoEquipCommand;
import cat.urv.deim.sob.command.LlistaEntrenadorJugadorCommand;
import cat.urv.deim.sob.command.LoginCommand;
import cat.urv.deim.sob.command.MostrarLlistaEquipCommand;
import cat.urv.deim.sob.command.ObtenirSegEntrenamentCommand;
import cat.urv.deim.sob.command.PartitsEntrenadorCommand;
import cat.urv.deim.sob.command.RealitzarAlineacioMostrarPartitsCommand;
import cat.urv.deim.sob.command.TramFedJugadorCommand;
import cat.urv.deim.sob.command.ValoracioGralEntrenamentEntrenadorCommand;
import cat.urv.deim.sob.command.ValoracioGralPartitEntrenadorCommand;
import cat.urv.deim.sob.command.VeureAssignarIncidenciaJugadorCommand;
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
        this.commands.put("club", new ClubCommand());
        this.commands.put("segentrenament", new EntrenamentsExerciciEntrenadorCommand());
        this.commands.put("crearalineacio", new AssignarJugadorAlineacioCommand());
        this.commands.put("crearalineacio2", new CrearAlineacioCommand());
        this.commands.put("veurepartitsdisponibles", new RealitzarAlineacioMostrarPartitsCommand());
        this.commands.put("rivalalineaciosel", new EmplenarAlineacioCommand());
        this.commands.put("vassignarincidenciaj", new VeureAssignarIncidenciaJugadorCommand());
        this.commands.put("dadesassignarincid", new DadesAssignarIncidenciaJugadorCommand());
        this.commands.put("assignarincjug", new AssignarIncidenciaJugadorCommand());
        this.commands.put("escollirvjugador", new EscollitJugValoracioCommand());
        this.commands.put("escollirvejugador", new EscollitJugValoracioEntrCommand());
        this.commands.put("decisiovpartit", new DecisioValoracioCommand());
        this.commands.put("decisioventrenament", new DecisioValoracioEntrCommand());
        this.commands.put("dadesvalorarpartit", new ValoracioGralPartitEntrenadorCommand());
        this.commands.put("dadesvalorarentrenament", new ValoracioGralEntrenamentEntrenadorCommand());
        this.commands.put("dadesseguimententrenament", new ObtenirSegEntrenamentCommand());
        this.commands.put("partitsentrenador", new PartitsEntrenadorCommand());
        this.commands.put("entrenamentsentrenador", new EntrenamentsEntrenadorCommand());
        this.commands.put("llistaentrenadors", new LlistaEntrenadorJugadorCommand());
        this.commands.put("fitxaentrenadorj", new FitxaEntrenadorJCommand());
        this.commands.put("tramfedjug", new TramFedJugadorCommand());
        this.commands.put("mostratramfedjug", new DadesFederativesJugadorCommand());
        this.commands.put("diresportiu", new DirEsportiuCommand());
        this.commands.put("crear", new CrearCommand());
        this.commands.put("login", new LoginCommand());
        this.commands.put("altaequip", new AltaEquipCommand());
        this.commands.put("valorarjugador", new DecisioValoracioJugadorCommand());
        this.commands.put("valorarjugadorentr", new DecisioValoracioJugadorEntrCommand());
        this.commands.put("entrenadornoequip", new EntrenadorNoEquipCommand());
        this.commands.put("entrenadorequip", new EntrenadorEquipCommand());
        this.commands.put("assignarentrenador", new AssignarEntrenadorCommand());
        this.commands.put("jugadornoequip", new JugadorNoEquipCommand());
        this.commands.put("jugadorequip", new JugadorEquipCommand());
        this.commands.put("assignarjugador", new AssignarJugadorCommand());
        this.commands.put("entrenadorclub", new EntrenadorClubCommand());
        this.commands.put("jugadorclub", new JugadorClubCommand());
        this.commands.put("mostrarequips", new MostrarLlistaEquipCommand());
        this.commands.put("baixaequip", new BaixaEquipCommand());
        this.commands.put("dadesjugador", new DadesJugadorCommand());
        this.commands.put("dadesentrenador", new DadesEntrenadorCommand());
        this.commands.put("actualitzardadesentrenador", new ActualitzarDadesEntrenadorCommand());
        this.commands.put("actualitzardadesjugador", new ActualitzarDadesJugadorCommand());

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
