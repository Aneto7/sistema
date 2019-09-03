<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*;" %>
<%@page import="javax.servlet.*;" %>
<%@page import="br.com.gestint.cadastro.*;" %>
<%@page import="br.com.gestint.cadastroDAO.*;" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title id="nometela" name="nometela" value="controlejustificativa">Gestao Interna - Cadastro de Filial</title>
        <!-- Favicon-->
        <link rel="icon" href="../../favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="../../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="../../plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="../../plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Colorpicker Css -->
        <link href="../../plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.css" rel="stylesheet" />

        <!-- Dropzone Css -->
        <link href="../../plugins/dropzone/dropzone.css" rel="stylesheet">

        <!-- Multi Select Css -->
        <link href="../../plugins/multi-select/css/multi-select.css" rel="stylesheet">

        <!-- Bootstrap Spinner Css -->
        <link href="../../plugins/jquery-spinner/css/bootstrap-spinner.css" rel="stylesheet">

        <!-- Bootstrap Tagsinput Css -->
        <link href="../../plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">

        <!-- Bootstrap Select Css -->
        <link href="../../plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />

        <!-- noUISlider Css -->
        <link href="../../plugins/nouislider/nouislider.min.css" rel="stylesheet" />

        <!-- Sweetalert Css -->
        <link href="../../plugins/sweetalert/sweetalert.css" rel="stylesheet" />

        <!-- JQuery DataTable Css -->
        <link href="../../plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.css" rel="stylesheet">

        <!-- Morris Chart Css-->
        <link href="../../plugins/morrisjs/morris.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="../../css/style.css" rel="stylesheet">

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="../../css/themes/all-themes.css" rel="stylesheet" />

    </head>

    <body class="theme-green">
        <!-- Page Loader -->
        <div class="page-loader-wrapper">
            <div class="loader">
                <div class="preloader">
                    <div class="spinner-layer pl-green">
                        <div class="circle-clipper left">
                            <div class="circle"></div>
                        </div>
                        <div class="circle-clipper right">
                            <div class="circle"></div>
                        </div>
                    </div>
                </div>
                <% 
                String usuariolog = (String) session.getAttribute("usuariolog");
                String arealog = (String) session.getAttribute("arealog");
                String filiallog = (String) session.getAttribute("filiallog");
                String nomelog = (String) session.getAttribute("nomelog");
                String perfillog = (String) session.getAttribute("perfillog");
                String regiaolog = (String) session.getAttribute("regiaolog");
                String setorlog = (String) session.getAttribute("setorlog");
                String telalog = (String) session.getAttribute("telalog");
                String emaillog = (String) session.getAttribute("emaillog");
                if(nomelog == "null"){
                    response.sendRedirect("../paginainicial.jsp");}
                if (!perfillog.equals("Administrador")){
                    if(!setorlog.equals("Controle de Estoque e Orcamento")){
                        response.sendRedirect("../paginainicial.jsp");
                    }
                }%>
                <p>Olá <%=nomelog%> </p>
                <p>Aguarde, carregando...</p>
            </div>
        </div>
        <!-- #END# Page Loader -->
        <!-- Overlay For Sidebars -->
        <div class="overlay"></div>
        <!-- #END# Overlay For Sidebars -->
        <!-- Search Bar -->
        <div class="search-bar">
            <div class="search-icon">
                <i class="material-icons">search</i>
            </div>
            <input type="text" placeholder="DIGITE AQUI SUA PESQUISA...">
            <div class="close-search">
                <i class="material-icons">close</i>
            </div>
        </div>
        <!-- #END# Search Bar -->
        <!-- Top Bar -->
        <nav class="navbar">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                    <a href="javascript:void(0);" class="bars"></a>
                    <a class="navbar-brand" href="../paginainicial.jsp"><b>LDC. - </b>Cadastro de Filial</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="../paginainicial.jsp">Início ></a></li>
                        <li><a href="dashboarcontrole.jsp">Cadastro ></a></li>
                        <li><a href="javascript:void(0);">Filial </a></li>
                        <!-- Call Search -->
                        <li><a href="javascript:void(0);" class="js-search" data-close="true"><i class="material-icons" hidden>search</i></a></li>
                        <!-- #END# Call Search -->
                        <!-- Notifications -->
                        <li class="dropdown">
                            <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button">
                                <i class="material-icons">notifications</i>
                                <span id="notificacoes" name="notificacoes" class="label-count">0</span> <!-- Inserir uma contagem de número de notificações para análise -->
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">ANÁLISES PARA JUSTIFICATIVA</li>
                                <li class="body">
                                    <ul class="menu">
                                        <li>
                                            <a href="../../pages/analiseorcamento/pendenciajustificativa.jsp">
                                                <div class="icon-circle bg-light-green">
                                                    <i class="material-icons">announcement</i>
                                                </div>
                                                <div class="menu-info">
                                                    <h4 id="numeropendente" name="numeropendente">12 Solicitações de análise</h4><!-- Inserir o número de análises para este usuário-->
                                                </div>
                                            </a>
                                        </li>
                                        <li hidden>
                                            <a href="javascript:void(0);">
                                                <div class="icon-circle bg-cyan">
                                                    <i class="material-icons">directions_car</i>
                                                </div>
                                                <div class="menu-info">
                                                    <h4>4 Próximas Revisões </h4><!-- inserir número de revizões -->
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <!-- #END# Notifications -->
                        <li class="pull-right"><a href="javascript:void(0);" class="js-right-sidebar" data-close="true"><i class="material-icons">more_vert</i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- #Top Bar -->
        <section>
            <!-- Left Sidebar -->
            <aside id="leftsidebar" class="sidebar">
                <!-- User Info -->
                <div class="user-info">
                    <div class="image">
                        <img src="../../images/LDC_logo.jpg" width="48" height="48" alt="User" /> <!-- Buscar imagem no banco de dados-->
                    </div>
                    <div class="info-container">
                        <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=nomelog%></div> <!-- Buscar nome no banco de dados-->
<!--                        <div class="email"><%=emaillog%></div> Buscar email no banco de dados-->
                        <div class="name" name="perfillogado" id="perfillogado"><%=perfillog%></div>
                        <div class="btn-group user-helper-dropdown">
                            <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                            <ul class="dropdown-menu pull-right">
                                <li><a href="javascript:void(0);"><i class="material-icons">person</i>Profile</a></li>
                                <li role="seperator" class="divider"></li>
                                <li id="logout" name="logout"><a href="../logout.jsp"><i class="material-icons">input</i>Sign Out</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- #User Info -->
                <!-- Menu -->
                <div class="menu">
                    <ul class="list">
                        <li class="header">MENU PRINCIPAL</li>
                        <li class="active">
                            <a href="../../pages/paginainicial.jsp">
                                <i class="material-icons">home</i>
                                <span>Início</span>
                            </a>
                        </li>
                        <%if(!perfillog.equals("Administrador")){
                        if(!setorlog.equals("Controle de Estoque e Orcamento")){%>
                        <li<%=" hidden"%>>
                            <%}}%>
                            <a class="menu-toggle">
                                <i class="material-icons">attach_money</i>
                                <span>Justificativa</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="../../pages/analiseorcamento/dashboarjustificativas.jsp">Dashboard</a>
                                </li>
                                <li>
                                    <a href="../../pages/analiseorcamento/lancarjustificativa.jsp">Lançar</a>
                                </li>
                                <li>
                                    <a href="../../pages/analiseorcamento/controlejustificativa.jsp">Controle</a>
                                </li>
                                <li>
                                    <a href="../../pages/analiseorcamento/analisejustificativa.jsp">Consulta</a>
                                </li>
                            </ul>
                        </li>
                        <%if(!perfillog.equals("Administrador")){
                        if(!setorlog.equals("Administrativo")){%>
                        <li class="active"<%=" hidden"%>>
                            <%}}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">assignment_turned_in</i>
                                <span>Controle</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="../../pages/controle/controledashboard.jsp">Dashboard</a>
                                </li>
                                <li>
                                    <a href="../../pages/controle/controlecombustivel.jsp">Combustível</a>
                                </li>
                                <li>
                                    <a href="../../pages/controle/controleinfracao.jsp">Infração</a>
                                </li>
                                <li>
                                    <a href="../../pages/controle/controlemanutencao.jsp">Manutenção</a>
                                </li>
                                <li>
                                    <a href="../../pages/controle/controlerevisao.jsp">Revisão</a>
                                </li>
                            </ul>
                        </li>
                        <%if(!perfillog.equals("Administrador")){
                        if(!perfillog.equals("Administrador")){%>
                        <li<%=" hidden"%>>
                            <%}}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">assignment</i>
                                <span>Cadastro</span>
                            </a>
                            <ul class="ml-menu">
                                <%if(!perfillog.equals("Administrador")){
                                if(!setorlog.equals("Administrativo")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}}%>
                                    <a href="../../pages/cadastro/cadastrocartaoabast.jsp">Cartão de Abastecimento</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!setorlog.equals("Administrativo")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}}%>
                                    <a href="../../pages/cadastro/cadastrocondutor.jsp">Condutor</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!setorlog.equals("Administrativo")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}}%>
                                    <a href="../../pages/cadastro/cadastrotipoveiculo.jsp">Tipo de Veículo</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!setorlog.equals("Administrativo")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}}%>
                                    <a href="../../pages/cadastro/cadastroveiculo.jsp">Veículo</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}%>
                                    <a href="../../pages/cadastro/cadastrofilial.jsp">Filial</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}%>
                                    <a href="../../pages/cadastro/cadastroapoio.jsp">Apoio</a>
                                </li>
                            </ul>
                        </li>
                        <%if(!perfillog.equals("Administrador")){%>
                        <li<%=" hidden"%>>
                            <%}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">build</i>
                                <span>Configuração</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="../../pages/configuracao/usuario.jsp">Usuário</a>
                                </li>
                            </ul>
                        </li>
                        <%if(!perfillog.equals("Administrador")){
                        if(!perfillog.equals("Administrador")){%>
                        <li<%=" hidden"%>>
                            <%}}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">folder</i>
                                <span>Arquivos</span>
                            </a>
                            <ul class="ml-menu">
                                <%if(!perfillog.equals("Administrador")){
                            if(!setorlog.equals("Controle de Estoque e Orcamento")){%>
                                <li<%=" hidden"%>>
                                    <%}}%>
                                    <a href="../../pages/importacaodearquivos/arquivosorcamento.jsp">Orçamento</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                            if(!setorlog.equals("Administrativo")){%>
                                <li<%=" hidden"%>>
                                    <%}}%>
                                    <a href="../../pages/importacaodearquivos/arquivosabastecimento.jsp">Tabela Abastecimento</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- #Menu -->
                <!-- Footer -->
                <div class="legal">
                    <div class="copyright">
                        &copy; 2017 <a href="https://www.linkedin.com/in/adm-antonioneto/" target="_blank">Antonio Neto</a>
                    </div>
                    <div class="version">
                        <b>Version: </b> 2.0.1
                    </div>
                </div>
                <!-- #Footer -->
            </aside>
            <!-- #END# Left Sidebar -->
            <!-- Right Sidebar -->
            <aside id="rightsidebar" class="right-sidebar">
                <ul class="nav nav-tabs tab-nav-right" role="tablist">
                    <li role="presentation" class="active"><a href="#skins" data-toggle="tab">Sobre</a></li>
                    <li role="presentation"><a href="#settings" data-toggle="tab">Funções</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active in active" id="skins">
                        <ul>
                            <div class="thumbnail">
                                <img src="http://placehold.it/500x300">
                                <div class="caption">
                                    <h3>Thumbnail label</h3>
                                    <p>
                                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy
                                        text ever since the 1500s
                                    </p>
                                    <p>
                                        <button type="button" class="btn btn-primary btn-circle-lg waves-effect waves-circle waves-float waves-black">
                                            <i class="material-icons">email</i>
                                        </button>
                                    </p>
                                </div>s
                            </div>
                        </ul>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="settings">
                        <ul>
                            <div class="thumbnail">
                                <img src="http://placehold.it/500x300">
                                <div class="caption">
                                    <h3>Thumbnail label</h3>
                                    <p>
                                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy
                                        text ever since the 1500s
                                    </p>
                                    <p>
                                        <button type="button" class="btn btn-primary btn-circle-lg waves-effect waves-circle waves-float waves-black">
                                            <i class="material-icons">email</i>
                                        </button>
                                    </p>
                                </div>
                            </div>
                        </ul>
                    </div>
                </div>
            </aside>
            <!-- #END# Right Sidebar -->
        </section>

        <section class="content">
            <% VeiculoDAO vdao = new VeiculoDAO();%>
            <% FiltroCadastroDAO fdao = new FiltroCadastroDAO();%>

            <!-- Widgets -->

            <div>
                <div class="row clearfix">
                    <div class="col-xs-12 ol-sm-12 col-md-12 col-lg-12">
                        <div class="panel-group" id="accordion_9" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-col-light-green">
                                <div class="panel-heading" role="tab" id="headingOne_9">
                                    <h4 class="panel-title">
                                        <a role="button" data-toggle="collapse" data-parent="#accordion_9" href="#collapseOne_9" aria-expanded="true" aria-controls="collapseOne_9">
                                            Cadastro de Filial<i class="material-icons">view_headline</i>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne_9" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne_9">
                                    <div class="panel-body">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="body">
                                                <div class="col-md-4">
                                                    <p class = "">
                                                        <b>Filial</b>
                                                    </p>
                                                    <div class="form-group form-float">
                                                        <div class="form-line">
                                                            <input type="text" class="form-control" id="campofilial" name="campofilial" required>
                                                            <label class="form-label">Digitar nome da filial</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <p class = "">
                                                        <b>Região</b>
                                                    </p>
                                                    <div class="form-group form-float">
                                                        <div class="form-line">
                                                            <input type="text" class="form-control" id="camporegiao" name="camporegiao" required>
                                                            <label class="form-label">Digitar região</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <p class = "">
                                                        <b>Tipo de Filial</b>
                                                    </p>
                                                    <div class="form-group form-float">
                                                        <div class="form-line">
                                                            <input type="text" class="form-control" id="campotipofilial" name="campotipofilial" required>
                                                            <label class="form-label">Digitar tipo de filial</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <p class = "">
                                                        <b>Status Filial</b>
                                                    </p>
                                                    <div class="form-group form-float">
                                                        <div class="form-line">
                                                            <input type="text" class="form-control" id="campostatusfilial" name="campostatusfilial" required>
                                                            <label class="form-label">Digitar status da filial</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <p class = "">
                                                        <b>Gestor Sub Regional</b>
                                                    </p>
                                                    <div class="form-group form-float">
                                                        <div class="form-line">
                                                            <input type="text" class="form-control" id="campogsub" name="campogsub" required>
                                                            <label class="form-label">Digitar nome do gestor</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <p class = "">
                                                        <b>Gestor Regional</b>
                                                    </p>
                                                    <div class="form-group form-float">
                                                        <div class="form-line">
                                                            <input type="text" class="form-control" id="campogreg" name="campogreg" required>
                                                            <label class="form-label">Digitar nome do gestor</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <p class = "">
                                                        <b>Gestor Divisional</b>
                                                    </p>
                                                    <div class="form-group form-float">
                                                        <div class="form-line">
                                                            <input type="text" class="form-control" id="campogdiv" name="campogdiv" required>
                                                            <label class="form-label">Digitar nome do gestor</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <p>
                                                        <b>ID </b><small>- escolher para atualizar ou excluir</small>
                                                    </p>
                                                    <select id="campoid" name="campoid" class="form-control show-tick" data-live-search="true">
                                                        <option value="0"></option>
                                                        <% for(Unidade filial: fdao.listarIdFilial()){ %>
                                                        <option value=<%=filial.getId()%>><%=filial.getId()%></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="js-sweetalert col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                                    <button data-type="success" type="button" name="atualizarfilial" id="atualizarfilial" value="atualizarfilial" class="btn bg-cyan btn-block waves-effect" onclick="atualizarFilial()">
                                                        <i class="material-icons">refresh</i>
                                                        <span>Atualizar</span>
                                                    </button>
                                                </div>
                                                <div class="js-sweetalert col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                                    <button data-type="success" type="button" name="excluirfilial" id="excluirfilial" value="excluirfilial" class="btn bg-cyan btn-block waves-effect" onclick="excluirFilial()">
                                                        <i class="material-icons">delete_forever</i>
                                                        <span>Excluir</span>
                                                    </button>
                                                </div>
                                                <div class="js-sweetalert col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                                    <button data-type="success" type="button" name="inserirfilial" id="inserirfilial" value="inserirfilial" class="btn bg-cyan btn-block waves-effect" onclick="inserirFilial()">
                                                        <i class="material-icons">save</i>
                                                        <span>Inserir</span>
                                                    </button>
                                                </div>
                                                <div class="js-sweetalert col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                                    <button type="button" name="limparfilial" id="limparfilial" value="limparfilial" class="btn bg-cyan btn-block waves-effect" onclick="limparFilial()">
                                                        <i class="material-icons">clear</i>
                                                        <span>Limpar</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Full Body Examples With Material Design Colors -->

            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel-group" id="accordion_77" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-col-cyan">
                            <div class="panel-heading" role="tab" id="headingOne_1">
                                <h4 class="panel-title">
                                    <a role="button" data-toggle="collapse" data-parent="#accordion_77" href="#collapseOne_77" aria-expanded="true" aria-controls="collapseOne_77">
                                        TABELA FILIAIS<i class="material-icons">view_headline</i>
                                    </a>
                                </h4>
                            </div>
                            <!-- Exportable Table -->
                            <div id="collapseOne_77" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne_9">
                                <div class="clearfix">
                                    <div id="tabela" name="tabela" class="body">
                                        <div class="table-responsive">
                                            <table id="tabelaFilial" name="tabelaFilial" class="table table-bordered table-striped table-hover dataTable js-exportable">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Filial</th>
                                                        <th>Regiao</th>
                                                        <th>Tipo</th>
                                                        <th>Status</th>
                                                        <th>Gestor Sub</th>
                                                        <th>Gestor Reg</th>
                                                        <th>Gestor Div</th>

                                                    </tr>
                                                </thead>
                                                <tbody id="tabelaorc" name="tabelaorc">
                                                    <% for(Unidade filial: fdao.listarIdFilial()){ %>
                                                    <tr value="<%=filial.getId()%>">
                                                        <td id="id" data-name="<%=filial.getId()%>"><%=filial.getId()%></td>
                                                        <td><%=filial.getFilial()%></td>
                                                        <td><%=filial.getRegiao()%></td>
                                                        <td><%=filial.getTipo()%></td>
                                                        <td><%=filial.getStatus()%></td>
                                                        <td><%=filial.getGsub()%></td>
                                                        <td><%=filial.getGrg()%></td>
                                                        <td><%=filial.getGdiv()%></td>
                                                    </tr>
                                                    <% } %>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- #END# Exportable Table -->
            </div>
        </section>

        <!-- Jquery Core Js -->
        <script src="../../plugins/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core Js -->
        <script src="../../plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Select Plugin Js -->
        <script src="../../plugins/bootstrap-select/js/bootstrap-select.js"></script>

        <!-- Slimscroll Plugin Js -->
        <script src="../../plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

        <!-- Bootstrap Notify Plugin Js -->
        <script src="../../plugins/bootstrap-notify/bootstrap-notify.js"></script>

        <!-- Bootstrap Colorpicker Js -->
        <script src="../../plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>

        <!-- Dropzone Plugin Js -->
        <script src="../../plugins/dropzone/dropzone.js"></script>

        <!-- Input Mask Plugin Js -->
        <script src="../../plugins/jquery-inputmask/jquery.inputmask.bundle.js"></script>

        <!-- Multi Select Plugin Js -->
        <script src="../../plugins/multi-select/js/jquery.multi-select.js"></script>

        <!-- Jquery Spinner Plugin Js -->
        <script src="../../plugins/jquery-spinner/js/jquery.spinner.js"></script>

        <!-- Bootstrap Tags Input Plugin Js -->
        <script src="../../plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>

        <!-- noUISlider Plugin Js -->
        <script src="../../plugins/nouislider/nouislider.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="../../plugins/node-waves/waves.js"></script>

        <!-- SweetAlert Plugin Js -->
        <script src="../../plugins/sweetalert/sweetalert.min.js"></script>

        <!-- Jquery CountTo Plugin Js -->
        <script src="../../plugins/jquery-countto/jquery.countTo.js"></script>

        <!-- Morris Plugin Js -->
        <script src="../../plugins/raphael/raphael.min.js"></script>
        <script src="../../plugins/morrisjs/morris.js"></script>

        <!-- Jquery DataTable Plugin Js -->
        <script src="../../plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="../../plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.js"></script>
        <script src="../../plugins/jquery-datatable/extensions/export/dataTables.buttons.min.js"></script>
        <script src="../../plugins/jquery-datatable/extensions/export/buttons.flash.min.js"></script>
        <script src="../../plugins/jquery-datatable/extensions/export/jszip.min.js"></script>
        <script src="../../plugins/jquery-datatable/extensions/export/pdfmake.min.js"></script>
        <script src="../../plugins/jquery-datatable/extensions/export/vfs_fonts.js"></script>
        <script src="../../plugins/jquery-datatable/extensions/export/buttons.html5.min.js"></script>
        <script src="../../plugins/jquery-datatable/extensions/export/buttons.print.min.js"></script>

        <!-- Sparkline Chart Plugin Js -->
        <script src="../../plugins/jquery-sparkline/jquery.sparkline.js"></script>

        <!-- Custom Js -->
        <script src="../../js/admin.js"></script>
        <script src="../../js/pages/cadastro/scriptcadastrofilial.js"></script>
        <script src="../../js/pages/tables/jquery-datatable.js"></script>
        <script src="../../js/pages/ui/dialogs.js"></script>
        <script src="../../js/pages/index.js"></script>


    </body>
</html>