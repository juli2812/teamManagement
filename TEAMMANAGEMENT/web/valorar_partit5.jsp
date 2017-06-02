<%-- 
    Document   : login
    Created on : 03-nov-2016, 12:03:44
    Author     : BEC.CA2
--%>

<%@page import="cat.urv.deim.sob.Jugador"%>
<%@page import="java.util.ArrayList"%>
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
            String idpartit =(String) session.getAttribute("idpartit");
            String idjugador =(String) session.getAttribute("idjugador");
            String nomequip =(String) session.getAttribute("nomequip");
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
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> <% out.println(userName+" "+surName); %></a>
                        </li>
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
                                    <a href="alta_equip.jsp">Donar alta equip</a>
                                </li>
                                <li>
                                    <a href="assingar_equip.jsp">Assignar equip</a>
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
                                    <a href="alta_entrenador.jsp">Donar alta entrenador</a>
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
                                    <a href="alta_jugador.jsp">Donar alta jugador</a>
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
                            <a href="index.html"><i class="fa fa-info-circle fa-fw"></i> Dades generals<span class="fa arrow"></span></a>
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
                                    <a href="consultar_fitxa_entrenador.jsp">Consultar fitxa entrenador</a>
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
                                    <a href="consultar_est_partit.jsp">Consultar estadistica partit/s</a>
                                </li>
                                <li>
                                    <a href="consultar_convocatoria.jsp">Consultar convocatoria</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="consultar_valor_entrenament.jsp"><i class="fa fa-bar-chart-o fa-fw"></i> Consultar valoracions entrenament</a>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                    
                    <%}else if((userType!=null) && userType.equals("Director esportiu")){%>
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="index.jsp"><i class="fa fa-home fa-fw"></i> Equip<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="alta_equip.jsp">Donar alta equip</a>
                                </li>
                                <li>
                                    <a href="assingar_equip.jsp">Assignar equip</a>
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
                                    <a href="alta_entrenador.jsp">Donar alta entrenador</a>
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
                                    <a href="alta_jugador.jsp">Donar alta jugador</a>
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
                    <h1 class="page-header">Partit</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Segon pas, valoració individual
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <img src="images/WVrLRHWp.jpg" alt="President" width=400 height=400>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <h1>Partit</h1>
                                    <form role="form" method="post" action="controller.do">
                                        <input type="hidden" name="form_action" value="valorarjugador"/>
                                        <input type="hidden" name="idusuari" value="<%out.print(userId);%>"/>
                                        <input type="hidden" name="nomequip" value="<%out.print(nomequip);%>"/>
                                        <input type="hidden" name="idjugador" value="<%out.print(idjugador);%>"/>
                                        <input type="hidden" name="idactivitat" value="<%out.print(idpartit);%>"/>
                                        <input type="hidden" name="tipususuari" value="<%out.print(userType);%>"/>
                                        <div class="form-group">
                                            <label>Nota</label>
                                            <input class="form-control" type="number" min="0" max="10" name="nota" step="0.01" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Comentari</label>
                                            <textarea class="form-control" rows="3" name="comentari" maxlength="100" required></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Data</label>
                                            <input class="form-control" type="date" name="data" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Assistencia</label>
                                            <input class="form-control" type="number" min="0" max="100" name="assistencia" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Gols</label>
                                            <input class="form-control" type="number" min="0" max="100" name="gols" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Tarjetes grogues</label>
                                            <input class="form-control" type="number" min="0" max="2" name="tarjeta_groga" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Tarjetes vermelles</label>
                                            <input class="form-control" type="number" min="0" max="1" name="tarjeta_vermella" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Lessions</label>
                                            <textarea class="form-control" rows="3" name="lessions" maxlength="75" required></textarea>
                                        </div>
                                        <div class="form-group">
                                        <label>Puntualitat</label>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="puntualitat" value="true">Puntual
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Minuts jugats</label>
                                            <input class="form-control" type="number" min="0" max="150" name="min_jugats" required>
                                        </div>
                                            <button name="decisio" type="submit" class="btn btn-primary" value="false">Valorar jugador</button>
                                            <button name="decisio" type="submit" class="btn btn-primary" value="true">Valorar jugador i continuar</button>
                                            <button type="reset" class="btn btn-default">Reset</button>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            
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

    <!-- Morris Charts JavaScript -->
    <script src="vendor/raphael/raphael.min.js"></script>
    <script src="vendor/morrisjs/morris.min.js"></script>
    <script src="data/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

</body>

</html>
