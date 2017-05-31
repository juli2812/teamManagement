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
                Registra un entrenador.
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
                                    <img src="images/monchi-sevilla-cosas-avanzadas.jpg" alt="Entrenador" width=480 height=280>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <h1>Entrenador</h1>
                                    <form role="form" method="post" action="controller.do">
                                       
                        <input type="hidden" name="form_action" value="altaentrenador"/>
                                    <input type="hidden" name="altaentrenador" value="false"/>
                      
                                        <div class="form-group">
                                            <label>Nom d'usuari</label>
                                            <input class="form-control" placeholder="Segueix l'estil EddMMyyXXX" maxlength="9" name="identrenador" required>
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
                                        <div class="form-group">
                                            <label>Modalitat</label>
                                            <input class="form-control"  maxlength="11" name="tipusmodalitatactual" required>
                                        </div>  
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
                                            <input class="form-control"  maxlength="40" name="foto" required>
                                        </div> 
                                        <div class="form-group">
                                            <label>Reconeixement mèdic</label>
                                            <input class="form-control"  maxlength="40" name="reconeixemt_medic" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Tot entregat</label>
                                            <input class="form-control"  type="checkbox" name="tot_entregat" required>
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

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

</body>

</html>
