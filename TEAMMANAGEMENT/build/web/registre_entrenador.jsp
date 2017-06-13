<%-- 
    Document   : registre_jugador
    Created on : 18-may-2017, 18-may-2017 20:03:49
    Author     : Maria
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <%@ page session="true" %>
        <%
            String userId ="";
            userId = (String) session.getAttribute("idUsuari");
            String userName ="";
            userName = (String) session.getAttribute("nomUsuari");
            String surName ="";
            surName = (String) session.getAttribute("cognomUsuari");
            String userType ="";
            userType = (String) session.getAttribute("tipusUsuari");
        %>
        <% if(null==userId || "".equals(userId)){
    String redirectURL = "login.jsp";
    response.sendRedirect(redirectURL);}
%>
<title>Team Management</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Team Management</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">                
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> <% out.println(userName+" "+surName); %></a>
                        </li>
                        <%if((userType!=null) && userType.equals("Entrenador")){%>
                        
                        <%if((null!=request.getParameter("type")) && "a".equals(request.getParameter("type"))){%>
                        <%session.setAttribute("type","a");%>
                        <li><a href="index.jsp?type=a"><i class="glyphicon glyphicon-ok" style="color:greenyellow"></i> Oficina</a>
                        </li>
                        <li><a href="index.jsp?type=b"><i class="fa fa-fw"></i> Partit</a>
                        </li>
                        <li><a href="index.jsp?type=c"><i class="fa fa-fw"></i> Entrenament</a>
                        </li>
                        <%}else if((null!=request.getParameter("type")) && "b".equals(request.getParameter("type"))){%>
                        <%session.setAttribute("type","b");%>
                        <li><a href="index.jsp?type=a"><i class="fa fa-fw"></i> Oficina</a>
                        </li>
                        <li><a href="index.jsp?type=b"><i class="glyphicon glyphicon-ok" style="color:greenyellow"></i> Partit</a>
                        </li>
                        <li><a href="index.jsp?type=c"><i class="fa fa-fw"></i> Entrenament</a>
                        </li>
                        <%}else if((null!=request.getParameter("type")) && "c".equals(request.getParameter("type"))){%>
                        <%session.setAttribute("type","c");%>
                        <li><a href="index.jsp?type=a"><i class="fa fa-fw"></i> Oficina</a>
                        </li>
                        <li><a href="index.jsp?type=b"><i class="fa fa-fw"></i> Partit</a>
                        </li>
                        <li><a href="index.jsp?type=c"><i class="glyphicon glyphicon-ok" style="color:greenyellow"></i> Entrenament</a>
                        </li>
                        <%}else if((null!=session.getAttribute("type")&&"a".equals(session.getAttribute("type").toString()))){%>
                        <%session.setAttribute("type","a");%>
                        <li><a href="index.jsp?type=a"><i class="glyphicon glyphicon-ok" style="color:greenyellow"></i> Oficina</a>
                        </li>
                        <li><a href="index.jsp?type=b"><i class="fa fa-fw"></i> Partit</a>
                        </li>
                        <li><a href="index.jsp?type=c"><i class="fa fa-fw"></i> Entrenament</a>
                        </li>
                        <%}else if((null!=session.getAttribute("type")&&"b".equals(session.getAttribute("type").toString()))){%>
                        <%session.setAttribute("type","b");%>
                        <li><a href="index.jsp?type=a"><i class="fa fa-fw"></i> Oficina</a>
                        </li>
                        <li><a href="index.jsp?type=b"><i class="glyphicon glyphicon-ok" style="color:greenyellow"></i> Partit</a>
                        </li>
                        <li><a href="index.jsp?type=c"><i class="fa fa-fw"></i> Entrenament</a>
                        </li>
                        <%}else if((null!=session.getAttribute("type")&&"c".equals(session.getAttribute("type").toString()))){%>
                        <%session.setAttribute("type","c");%>
                        <li><a href="index.jsp?type=a"><i class="fa fa-fw"></i> Oficina</a>
                        </li>
                        <li><a href="index.jsp?type=b"><i class="fa fa-fw"></i> Partit</a>
                        </li>
                        <li><a href="index.jsp?type=c"><i class="glyphicon glyphicon-ok" style="color:greenyellow"></i> Entrenament</a>
                        </li>
                        <%}else{%>
                        <li><a href="index.jsp?type=a"><i class="glyphicon glyphicon-ok" style="color:greenyellow"></i> Oficina</a>
                        </li>
                        <li><a href="index.jsp?type=b"><i class="fa fa-fw"></i> Partit</a>
                        </li>
                        <li><a href="index.jsp?type=c"><i class="fa fa-fw"></i> Entrenament</a>
                        </li>
                        <%}%>
                        <%}%>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    
                    <%if((userType!=null) && userType.equals("President")){%>
                    <ul class="nav" id="side-menu">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="#"><i class="fa fa-home fa-fw"></i> Director Esportiu<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="afegir_dir_esportiu.jsp?club=true&af=1">Afegir director esportiu</a>  
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-home fa-fw"></i> Equip<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="avisarIncidencia.jsp">Avisar incidència o esdeveniment</a>
                                </li>
                                <li>
                                    <a href="alta_equip.jsp">Donar alta equip</a>
                                </li>
                                <li>
                                    <a href="assignar_equip.jsp">Assignar equip</a>
                                </li>
                                <li>
                                    <a href="baixa_equip.jsp">Donar baixa equip</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-graduation-cap fa-fw"></i> Entrenador<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="registre_entrenador.jsp">Donar alta entrenador</a>
                                </li>
                                <li>
                                    <a href="assignar_entrenador.jsp">Assignar entrenador</a>
                                </li>
                                <li>
                                    <a href="consultar_dades_entrenador.jsp">Consultar dades entrenador</a>
                                </li>
                                <li>
                                    <a href="actualitzar_dades_entrenador.jsp">Actualizar dades entrenador</a>
                                </li>
                                <li>
                                    <a href="baixa_entrenador.jsp">Donar baixa entrenador</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-gamepad fa-fw"></i> Jugador<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="registre_jugador.jsp">Donar alta jugador</a>
                                </li>
                                <li>
                                    <a href="assignar_jugador.jsp">Assignar jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_dades_jugador.jsp">Consultar dades jugador</a>
                                </li>
                                <li>
                                    <a href="actualitzar_dades_jugador.jsp">Actualizar dades jugador</a>
                                </li>
                                <li>
                                    <a href="donar_baixa_jugador.jsp">Donar baixa jugador</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                    <%}else if((userType!=null) && userType.equals("Jugador")){%>
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.jsp"><i class="fa fa-info-circle fa-fw"></i> General<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="quota.jsp">Consultar quota</a>
                                </li>
                                <li>
                                    <a href="consultar_assistencia.jsp">Consultar assistència</a>
                                </li>
                                <li>
                                    <a href="avisar_absencia.jsp">Avisar absencia</a>
                                </li>
                                <li>
                                    <a href="consultar_fitxa_jugador.jsp">Consultar fitxa jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_tramit_fed_jugador.jsp">Consultar tràmit federatiu jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_fitxa_entrenador_j.jsp">Consultar fitxa entrenador</a>
                                </li>
                                <li>
                                    <a href="consultar_incid_cast.jsp">Consultar incidència o càstig</a>
                                </li>
                                <li>
                                    <a href="consultar_class_comp.jsp">Consultar classificació i competició</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-graduation-cap fa-fw"></i> Entrenament<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                            <li>
                               <a href="consultar_valor_entrenament.jsp">Consultar valoracions entrenament</a>
                            </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-gamepad fa-fw"></i> Partit<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="consultar_est_partit.jsp">Consultar estadistica partit/s</a>
                                </li>
                                <li>
                                    <a href="consultar_convocatoria.jsp">Consultar convocatoria</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                    <%}else if((userType!=null) && userType.equals("Entrenador")){%>
                    <%if((null!=session.getAttribute("type")&&"a".equals(session.getAttribute("type").toString()))){%>
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.jsp"><i class="fa fa-info-circle fa-fw"></i> General<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="consultar_absencia.jsp">Consultar absència</a>
                                </li>
                                <li>
                                    <a href="consultar_fitxa_jugador.jsp">Consultar fitxa jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_tramit_fed_jugador_e.jsp">Consultar tràmit federatiu jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_fitxa_entrenador.jsp">Consultar fitxa entrenador</a>
                                </li>
                                <li>
                                    <a href="consultar_tramit_fed_entrenador.jsp">Consultar tràmit federatiu entrenador</a>
                                </li>
                                <li>
                                    <a href="assignar_incidencia.jsp">Assignar incidència jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_class_comp.jsp">Consultar classificació i competició</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-graduation-cap fa-fw"></i> Entrenament<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="planificar_entrenament.jsp">Planificar entrenament</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-gamepad fa-fw"></i> Partit<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="alta_partit.jsp">Donar alta partit</a>
                                </li>
                                <li>
                                    <a href="consultar_est_partit.jsp">Consultar estadistica partit/s</a>
                                </li>
                                <li>
                                    <a href="consultar_est_equip.jsp">Consultar estadistica equip</a>
                                </li>
                                <li>
                                    <a href="consultar_convocatoria.jsp">Consultar convocatoria</a>
                                </li>
                                <li>
                                    <a href="realitzar_alineacio.jsp">Realitzar alineació</a>
                                </li>
                                <li>
                                    <a href="realitzar_convocatoria.jsp">Realitzar convocatoria</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                    
                    <%}else if((null!=session.getAttribute("type")&&"b".equals(session.getAttribute("type").toString()))){%>
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.jsp"><i class="fa fa-info-circle fa-fw"></i> General<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="consultar_absencia.jsp">Consultar absència</a>
                                </li>
                                <li>
                                    <a href="consultar_fitxa_jugador.jsp">Consultar fitxa jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_fitxa_entrenador.jsp">Consultar fitxa entrenador</a>
                                </li>
                                <li>
                                    <a href="consultar_tramit_fed_entrenador.jsp">Consultar tràmit federatiu entrenador</a>
                                </li>
                                <li>
                                    <a href="assignar_incidencia.jsp">Assignar incidència jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_incid_cast.jsp">Consultar incidència o càstig</a>
                                </li>
                                <li>
                                    <a href="consultar_class_comp.jsp">Consultar classificació i competició</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-gamepad fa-fw"></i> Partit<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="valorar_partit.jsp">Valorar partit</a>
                                </li>
                                <li>
                                    <a href="consultar_est_partit.jsp">Consultar estadistica partit/s</a>
                                </li>
                                <li>
                                    <a href="consultar_est_equip.jsp">Consultar estadistica equip</a>
                                </li>
                                <li>
                                    <a href="controlar_assistencia.jsp">Controlar assistencia</a>
                                </li>
                                <li>
                                    <a href="realitzar_alineacio.jsp">Realitzar alineació</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                    
                    <%}else if((null!=session.getAttribute("type")&&"c".equals(session.getAttribute("type").toString()))){%>
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.jsp"><i class="fa fa-info-circle fa-fw"></i> General<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="consultar_absencia.jsp">Consultar absència</a>
                                </li>
                                <li>
                                    <a href="consultar_fitxa_jugador.jsp">Consultar fitxa jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_tramit_fed_jugador_e.jsp">Consultar tràmit federatiu jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_fitxa_entrenador.jsp">Consultar fitxa entrenador</a>
                                </li>
                                <li>
                                    <a href="consultar_tramit_fed_entrenador.jsp">Consultar tràmit federatiu entrenador</a>
                                </li>
                                <li>
                                    <a href="assignar_incidencia.jsp">Assignar incidència jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_class_comp.jsp">Consultar classificació i competició</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-graduation-cap fa-fw"></i> Entrenament<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="valorar_entrenament.jsp">Valorar entrenament</a>
                                </li>
                                <li>
                                    <a href="realitzar_seg_entrenament.jsp">Realitzar seguiment entrenament</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-gamepad fa-fw"></i> Partit<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="consultar_est_partit.jsp">Consultar estadistica partit/s</a>
                                </li>
                                <li>
                                    <a href="consultar_est_equip.jsp">Consultar estadistica equip</a>
                                </li>
                                <li>
                                    <a href="realitzar_convocatoria.jsp">Realitzar convocatoria</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                    <%}%>
                    <%}else if((userType!=null) && userType.equals("Director esportiu")){%>
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.jsp"><i class="fa fa-home fa-fw"></i> Equip<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="avisarIncidencia.jsp">Avisar incidència o esdeveniment</a>
                                </li>
                                <li>
                                    <a href="alta_equip.jsp">Donar alta equip</a>
                                </li>
                                <li>
                                    <a href="assignar_equip.jsp">Assignar equip</a>
                                </li>
                                <li>
                                    <a href="baixa_equip.jsp">Donar baixa equip</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-graduation-cap fa-fw"></i> Entrenador<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="registre_entrenador.jsp">Donar alta entrenador</a>
                                </li>
                                <li>
                                    <a href="assignar_entrenador.jsp">Assignar entrenador</a>
                                </li>
                                <li>
                                    <a href="consultar_dades_entrenador.jsp">Consultar dades entrenador</a>
                                </li>
                                <li>
                                    <a href="actualitzar_dades_entrenador.jsp">Actualizar dades entrenador</a>
                                </li>
                                <li>
                                    <a href="baixa_entrenador.jsp">Donar baixa entrenador</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-gamepad fa-fw"></i> Jugador<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="registre_jugador.jsp">Donar alta jugador</a>
                                </li>
                                <li>
                                    <a href="assignar_jugador.jsp">Assignar jugador</a>
                                </li>
                                <li>
                                    <a href="consultar_dades_jugador.jsp">Consultar dades jugador</a>
                                </li>
                                <li>
                                    <a href="actualitzar_dades_jugador.jsp">Actualizar dades jugador</a>
                                </li>
                                <li>
                                    <a href="donar_baixa_jugador.jsp">Donar baixa jugador</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                    <%}%>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Registra un entrenador</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Formulari a omplir
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <img src="images/article-2511941-1995BB5F00000578-133_964x859.jpg" alt="President" width=482 height=430>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <h1>Entrenador</h1>
                                    <form role="form" method="post" action="controller.do">
                                       
                        <input type="hidden" name="form_action" value="altaentrenador"/>
                                    <input type="hidden" name="altaentrenador" value="false"/>
                      
                                        <div class="form-group">
                                            <label>Nom d'usuari</label>
                                            <input class="form-control" placeholder="Segueix l'estil EddMMyyXXX" maxlength="11" name="identrenador" required>
                                            <p>On E és l’identificador del tipus d’usuari que serà sempre aquest per al entrenador, ddMMyy és la data de naixement en el format diaMESany i finalment XXX són les inicials del nom i cognoms.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>DNI</label>
                                            <input class="form-control" maxlength="9" name="dni" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Nom</label>
                                            <input class="form-control" maxlength="20" name="nom" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Primer cognom</label>
                                            <input class="form-control" maxlength="20" name="cognom1" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Segon cognom</label>
                                            <input class="form-control" placeholder="(Opcional)" maxlength="20" name="cognom2">
                                        </div>
                                        <div class="form-group">
                                            <label>Adreça</label>
                                            <input class="form-control" maxlength="40" name="address" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Telèfon</label>
                                            <input class="form-control" type="number" min="600000000" max="99999999999" name="telefon" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Data naixement</label>
                                            <input class="form-control" placeholder="yyyy-mm-dd" type="date" name="datanaix" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Contrasenya</label>
                                            <input class="form-control" type="password" maxlength="40" name="contrasenya" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Data incorporació</label>
                                            <input class="form-control" placeholder="yyyy-mm-dd" type="date" name="dataincorp" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Nivell entrenador</label>
                                            <input class="form-control" maxlength="20" name="nivellentrenador" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Compte bancari</label>
                                            <input class="form-control" placeholder="ESXXXXXXXXXXXXXXXXXX" maxlength="24" name="comptebancari" required>
                                        </div>
                                            <input class="form-control" type="hidden" maxlength="11" name="tipusmodalitatactual" value="1" required>
                                        <div class="form-group">
                                            <label>Certificat entrenador</label>
                                            <input class="form-control"  maxlength="20" name="certificatentrenador" required>
                                        </div>                
                                        <div class="form-group">
                                            <label>Funció entrenador</label>
                                            <input class="form-control"  maxlength="30" name="funcio" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Fotocopia DNI</label>
                                            <input class="form-control"  maxlength="40" name="fotocopia_DNI" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Foto</label>
                                            <input class="form-control"  maxlength="200" name="foto" required>
                                        </div> 
                                        <div class="form-group">
                                            <label>Catsalut</label>
                                            <input class="form-control"  maxlength="40" name="numsalut" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Reconeixement mèdic</label>
                                            <input class="form-control"  maxlength="40" name="reconeixemt_medic" required>
                                        </div>
                                        <div class="form-group">
                                        <label>Tot entregat</label>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="tot_entregat" value="true" required>Tot entregat
                                                </label>
                                            </div>
                                        </div>
                        <%
                            if((request.getParameter("faltaParam")!=null) && request.getParameter("faltaParam").equals("true")){
                                 out.println("Omple els camps obligatoris.");
                        }
                        %></font></b><br>
                                        <button type="submit" class="btn btn-primary">Crear</button>
                                            <button type="reset" class="btn btn-default">Reset</button>
                                           <input type="button" onclick="location.href='index.jsp';" value="Tornar a Inici" class="btn btn-default"/>
                                    </form>
                                </div>
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

</body>

</html>