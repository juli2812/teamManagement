<%-- 
    Document   : login
    Created on : 03-nov-2016, 12:03:44
    Author     : BEC.CA2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Team Management</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

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
                Registra el teu club.
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Registra el club</h1>
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
                                    <img src="images/monchi-sevilla-cosas-avanzadas.jpg" alt="Director esportiu" width=480 height=280>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <h1>Club</h1>
                                    <form role="form" method="post" action="controller.do">
                                    <input type="hidden" name="form_action" value="crear"/>
                                        <div class="form-group">
                                            <label>Usuari president</label>
                                            <input class="form-control" placeholder="Segueix l'estil PddMMyyXXX" maxlength="9" name="fkpresident" required>
                                            <p>On P és l’identificador del tipus d’usuari que serà sempre aquest per al president, ddMMyy és la data de naixement en el format diaMESany i finalment XXX són les inicials del nom i cognoms)</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Usuari director esportiu</label>
                                            <input class="form-control" placeholder="(Opcional) Segueix l'estil DEddMMyyXXX" maxlength="9" name="fkdiresportiu">
                                            <p>On DE és l’identificador del tipus d’usuari que serà sempre aquest per al director esportiu, ddMMyy és la data de naixement en el format diaMESany i finalment XXX són les inicials del nom i cognoms.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Nom club</label>
                                            <input class="form-control" maxlength="10" name="nomclub" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Núm. federatiu club</label>
                                            <input class="form-control" type="number" min="0" max="9999" name="idclub" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Color local</label>
                                            <input class="form-control" maxlength="10" name="colorlocal" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Color visitant</label>
                                            <input class="form-control" maxlength="10" name="colorvisitant" required>
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
                                            <label>Quota total</label>
                                            <input class="form-control" type="number" min="0" max="999999" name="quotatotal" required>
                                        </div><b><font color = "red">
                        <%
                            if((request.getParameter("faltaParam")!=null) && request.getParameter("faltaParam").equals("true")){
                                 out.println("Ompleix els camps obligatoris.");
                        }
                        %></font></b><br>
                                        <button type="submit" class="btn btn-primary">Crear</button>
                                        <button type="reset" class="btn btn-default">Reset</button>
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
