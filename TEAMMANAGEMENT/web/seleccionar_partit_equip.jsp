<%-- 
    Document   : seleccionar_partit_equip
    Created on : 05-jun-2017, 05-jun-2017 10:06:53
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
                    <h1 class="page-header">Estadistica partit</h1>
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
                                    <h1></h1>
                                    <form role="form" method="post" action="controller.do">
                                       
                        <input type="hidden" name="form_action" value="mostrarestadisticapartitequip"/>
                        <input type="hidden" name="equip" value="<%out.print(session.getAttribute("equip"));%>">
                                        <div class="form-group">
                                            <div class="form-group">
                                            <label>Escull partit</label>
                                            <select name="partit">   
                                        <%
                                        ArrayList<String> partits;
                                        partits = (ArrayList<String>) session.getAttribute("partits");
                                        for(String p: partits){
                                        %>
                                        <option value="<%out.print(p);%>"><% out.println(p +"<br/>"); %></option>
                                        <% }%>
                                            </select>
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

