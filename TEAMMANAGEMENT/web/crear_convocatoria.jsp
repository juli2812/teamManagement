<%-- 
    Document   : crear_convocatoria.jsp
    Created on : 04-jun-2017, 04-jun-2017 10:51:22
    Author     : Maria
--%>


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
                    <h1 class="page-header">Crear convocatoria</h1>
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
                                    <h1>Crear convocatoria</h1>
                                    <form role="form" method="post" action="controller.do">
                                       
                        <input type="hidden" name="form_action" value="crearconvocommand"/>
                                        <div class="form-group">
                                            <div class="form-group">
                                            <label>Escull jugador</label>
                                            <select name="jugador">   
                                        <%
                                        ArrayList<String> usuaris;
                                        ArrayList<String> partits;
                                        usuaris = (ArrayList<String>) session.getAttribute("destinataris");
                                        partits = (ArrayList<String>) session.getAttribute("partits");
                                        for(String user: usuaris){
                                        %>
                                        <option value="<%out.print(user);%>"><% out.println(user +"<br/>"); %></option>
                                        <% }%>
                                            </select>
                                        </div>
                                             <div class="form-group">
                                            <label>Escull partit</label>
                                            <select name="partit">   
                                        <%
                                        for(String partit: partits){
                                        %>
                                        <option value="<%out.print(partit);%>"><% out.println(partit +"<br/>"); %></option>
                                        <% }%>
                                            </select>
                                            </div>
                                            
                                        <div class="form-group">
                                            <label>Data partit</label>
                                            <input class="form-control" type="Data" name="datapartit" required>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Numero de jugadors confirmats</label>
                                            <input class="form-control" type="Number" name="numJconf" required>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Numero de jugadors no confirmats</label>
                                            <input class="form-control" type="Number" name="numJnoconf" required>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Lloc partit</label>
                                            <input class="form-control" type="text" name="llocpartit" required>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Data limit confirmacio</label>
                                            <input class="form-control" placeholder="yyyy-mm-dd"type="Data" name="dataLconf" required>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Jugadors minims</label>
                                            <input class="form-control" type="Number" name="jugMin" required>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Jugadors maxims</label>
                                            <input class="form-control" type="Number"  name="jugMax" required>
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Confirmació</label>
                                            <input class="checkbox" type="checkbox" name="confirmacio" >
                                        </div>
                                      
                                             <div class="form-group">
                                            <label>Ha vingut</label>
                                            <input class="checkbox" type="checkbox" name="havingut" >
                                        </div>
                                        
                                        </div>                                  
                        <%
                            if((request.getParameter("faltaParam")!=null) && request.getParameter("faltaParam").equals("true")){
                                 out.println("Omple els camps obligatoris.");
                        }
                        %></font></b><br>
                                        <button type="submit" class="btn btn-default">Continuar</button>
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
