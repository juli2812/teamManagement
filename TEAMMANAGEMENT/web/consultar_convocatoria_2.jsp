<%-- 
    Document   : consultar_convocatoria_2
    Created on : 04-jun-2017, 04-jun-2017 17:45:32
    Author     : Maria
--%>

<%@page import="cat.urv.deim.sob.Convocatoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%
            String userId ="";
            userId = (String) session.getAttribute("idUsuari");
            String userName ="";
            userName = (String) session.getAttribute("nomUsuari");
            String surName ="";
            surName = (String) session.getAttribute("cognomUsuari");
            String userType ="";
            userType = (String) session.getAttribute("tipusUsuari");
            
            Convocatoria c = (Convocatoria) session.getAttribute("convocatoria");
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
                <a class="navbar-brand" href="login.html">Team Management</a>
            </div>
            <!-- /.navbar-header -->


            <div class="navbar-default sidebar" role="navigation">
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Consultar convocatoria</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <img src="images/warning.jpg" alt="Jugador" width=480 height=280>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <h1>Consultar convocatoria</h1>
                                    
                                       
                        
                                        <div class="form-group">
                                            <div class="form-group">
                                            <label>Jugador</label>
                                            <input class="form-control" type="String" name="jugador" value="<% out.print(c.getJugador()); %>" disabled>
                                        </div>
                                             <div class="form-group">
                                            <label>Partit</label>
                                            <input class="form-control" type="String" name="partit" value="<% out.print(c.getPartit()); %>" disabled>
                                            </div>
                                            
                                        <div class="form-group">
                                            <label>Data partit</label>
                                            <input class="form-control" type="Data" name="datapartit" value="<% out.print(c.getDataPartit()); %>" disabled>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Numero de jugadors confirmats</label>
                                            <input class="form-control" type="Number" name="numJconf"  value="<% out.print(c.getNumJugsConf()); %>" disabled>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Numero de jugadors no confirmats</label>
                                            <input class="form-control" type="Number" name="numJnoconf"  value="<% out.print(c.getNumJugsNoConf()); %>" disabled>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Lloc partit</label>
                                            <input class="form-control" type="text" name="llocpartit" value="<% out.print(c.getLlocPartit()); %>" disabled>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Data limit confirmacio</label>
                                            <input class="form-control" placeholder="yyyy-mm-dd"type="Data" name="dataLconf"  value="<% out.print(c.getDataLimitConf()); %>" disabled>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Jugadors minims</label>
                                            <input class="form-control" type="Number" name="jugMin"  value="<% out.print(c.getMinJug()); %>" disabled>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Jugadors maxims</label>
                                            <input class="form-control" type="Number"  name="jugMax" value="<% out.print(c.getMaxJug()); %>" disabled>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Confirmació</label>
                                            <input class="checkbox" type="checkbox" name="confirmacio" value="<%out.print(c.isConfirmacio());%>" disabled>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Ha vingut</label>
                                            <input class="checkbox" type="checkbox" name="havingut" value="<%out.print(c.isHaVingut());%>" disabled>
                                        </div>
                                        
                                        </div>                                  
                                    <a href="index.jsp" class="btn btn-default">Continuar</a>
                                    
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
