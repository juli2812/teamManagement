package cat.urv.deim.sob;

import cat.urv.deim.sob.command.*;
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
        this.commands.put("incidjugador", new IncidenciaJugadorCommand());
        this.commands.put("mostrarincid", new MostrarIncidenciaCommand());
        this.commands.put("segentrenament", new EntrenamentsExerciciEntrenadorCommand());
        this.commands.put("iniplanifentrenament", new IniciPlanificarEntrenamentCommand());
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
        this.commands.put("dadesassistencia", new JugadorConfirmatAssistenciaCommand());
        this.commands.put("conass", new ConsAssistenciaPartitsCommand());
        this.commands.put("decisioventrenament", new DecisioValoracioEntrCommand());
        this.commands.put("dadesvalorarpartit", new ValoracioGralPartitEntrenadorCommand());
        this.commands.put("dadesvalorarentrenament", new ValoracioGralEntrenamentEntrenadorCommand());
        this.commands.put("marcarfet", new MarcarFetCommand());
        this.commands.put("marcarconfirmat", new MarcarConfirmatCommand());
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
        this.commands.put("altajugador", new AltaJugadorCommand());
        this.commands.put("altaentrenador", new AltaEntrenadorCommand());
        this.commands.put("jugadorclub", new JugadorClubCommand());
        this.commands.put("dadesjugador", new DadesJugadorCommand());
        this.commands.put("actualitzardadesjugador", new ActualitzarDadesJugadorCommand());
        this.commands.put("escollirDestinatari", new EscollirDestinatariCommand());
        this.commands.put("crearIncidencia", new CrearIncidenciaCommand());
        this.commands.put("assignarequip", new AssignarEquipCommand());
        this.commands.put("altapartit", new AltaPartitCommand());
        this.commands.put("consultardadesentrenador", new ConsultarDadesEntrenadorCommand());
        this.commands.put("consultardadesjugador", new ConsultarDadesJugadorCommand());
        this.commands.put("donarbaixajugador", new DonarBaixaJugadorCommand()); 
        this.commands.put("donarbaixaentrenador", new DonarBaixaEntrenadorCommand());
        this.commands.put("avisarabsencia", new AvisarAbsenciaCommand());
        this.commands.put("crearModifConvo", new CrearModifConvoCommand());
        this.commands.put("crearconvocommand", new CrearConvoCommand());
        this.commands.put("obtenirConvo", new ObtenirConvocatoriaCommand());
        this.commands.put("modificarConvo", new ModificarConvocatoriaCommand());
        this.commands.put("obtenirjugadors", new ObtenirJugadorsCommand());
        this.commands.put("consultarassistencia", new ConsultarAssistenciaCommand());
        this.commands.put("mostrarestadistica", new MostrarEstadisticaCommand());
        this.commands.put("mostrarestadisticapartit", new MostrarEstadisticaPartitCommand());
        this.commands.put("obtenirequips", new ObtenirEquipsCommand());
        this.commands.put("mostrarestadisticaequip", new MostrarEstadisticaEquipCommand());
        this.commands.put("mostrarestadisticapartitequip", new MostrarEstadisticaPartitEquipCommand());
        this.commands.put("obtenirAbsencies", new ObtenirAbsenciesCommand());
        this.commands.put("obtenirValoracionsEntrenament", new ObtenirValoracionsEntrenamentCommand());
        this.commands.put("mostrarquota", new MostrarQuotaCommand());
        this.commands.put("fitxajugador", new FitxaJugadorCommand());
        this.commands.put("tramfedjug", new TramFedJugadorCommand());
        this.commands.put("jugadornoequip", new JugadorNoEquipCommand());
        this.commands.put("jugadorequip", new JugadorEquipCommand());
        this.commands.put("assignarjugador", new AssignarJugadorCommand());
        this.commands.put("entrenadorclub", new EntrenadorClubCommand());
        this.commands.put("mostrarequips", new MostrarLlistaEquipCommand());
        this.commands.put("baixaequip", new BaixaEquipCommand());
        this.commands.put("dadesentrenador", new DadesEntrenadorCommand());
        this.commands.put("actualitzardadesentrenador", new ActualitzarDadesEntrenadorCommand());

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
