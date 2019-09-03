<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*;" %>
<%@page import="javax.servlet.*;" %>
<%@page import="br.com.gestint.orcamento.*;" %>
<%@page import="br.com.gestint.orcamentoDAO.*;" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Gestao Interna</title>
        <!-- Favicon-->
        <link rel="icon" href="../favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="../plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="../plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Multi Select Css -->
        <link href="../plugins/multi-select/css/multi-select.css" rel="stylesheet">

        <!-- Bootstrap Select Css -->
        <link href="../plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />

        <!-- JQuery DataTable Css -->
        <link href="../plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.css" rel="stylesheet">

        <!-- Custom Css -->
        <link href="../css/style.css" rel="stylesheet">

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="../css/themes/all-themes.css" rel="stylesheet" />

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
                    response.sendRedirect("../../index.jsp");}%>
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
                    <a class="navbar-brand" href="paginainicia.html"><b>LDC. - </b>Sistema de Gestão Interna</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <!-- Call Search -->
                        <li><a href="javascript:void(0);" class="js-search" data-close="true"><i class="material-icons" hidden>search</i></a></li>
                        <!-- #END# Call Search -->
                        <!-- Notifications -->
                        <li class="dropdown">
                            <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button">
                                <i class="material-icons">notifications</i>
                                <span id="notificacoes" name="notificacoes" class="label-count" class="label-count">0</span> <!-- Inserir uma contagem de número de notificações para análise -->
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">ANÁLISES PARA JUSTIFICATIVA</li>
                                <li class="body">
                                    <ul class="menu">
                                        <li>
                                            <a href="../pages/analiseorcamento/pendenciajustificativa.jsp">
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
                                                    <h4 id="numeropendente" name="numeropendente">4 Próximas Revisões </h4><!-- inserir número de revizões -->
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
                        <img src="../images/LDC_logo.jpg" width="48" height="48" alt="User" /> <!-- Buscar imagem no banco de dados-->
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
                                <li id="logout" name="logout"><a href="logout.jsp"><i class="material-icons">input</i>Sign Out</a></li>
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
                            <a href="../pages/paginainicial.jsp">
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
                                    <a href="../pages/analiseorcamento/dashboarjustificativas.jsp">Dashboard</a>
                                </li>
                                <li>
                                    <a href="../pages/analiseorcamento/lancarjustificativa.jsp">Lançar</a>
                                </li>
                                <li>
                                    <a href="../pages/analiseorcamento/controlejustificativa.jsp">Controle</a>
                                </li>
                                <li>
                                    <a href="../pages/analiseorcamento/analisejustificativa.jsp">Consulta</a>
                                </li>
                            </ul>
                        </li>
                        <%if(!perfillog.equals("Administrador")){
                        if(!setorlog.equals("Administrativo")){%>
                        <li<%=" hidden"%>>
                            <%}}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">assignment_turned_in</i>
                                <span>Controle</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="../pages/controle/controledashboard.jsp">Dashboard</a>
                                </li>
                                <li>
                                    <a href="../pages/controle/controlecombustivel.jsp">Combustível</a>
                                </li>
                                <li>
                                    <a href="../pages/controle/controleinfracao.jsp">Infração</a>
                                </li>
                                <li>
                                    <a href="../pages/controle/controlemanutencao.jsp">Manutenção</a>
                                </li>
                                <li>
                                    <a href="../pages/controle/controlerevisao.jsp">Revisão</a>
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
                                    <a href="../pages/cadastro/cadastrocartaoabast.jsp">Cartão de Abastecimento</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!setorlog.equals("Administrativo")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}}%>
                                    <a href="../pages/cadastro/cadastrocondutor.jsp">Condutor</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!setorlog.equals("Administrativo")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}}%>
                                    <a href="../pages/cadastro/cadastrotipoveiculo.jsp">Tipo de Veículo</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!setorlog.equals("Administrativo")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}}%>
                                    <a href="../pages/cadastro/cadastroveiculo.jsp">Veículo</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}%>
                                    <a href="../pages/cadastro/cadastrofilial.jsp">Filial</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                                if(!perfillog.equals("Supervisor")){%>
                                <li<%=" hidden"%>>
                                    <%}}%>
                                    <a href="../pages/cadastro/cadastroapoio.jsp">Apoio</a>
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
                                    <a href="../pages/configuracao/usuario.jsp">Usuário</a>
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
                                    <a href="../pages/importacaodearquivos/arquivosorcamento.jsp">Orçamento</a>
                                </li>
                                <%if(!perfillog.equals("Administrador")){
                            if(!setorlog.equals("Administrativo")){%>
                                <li<%=" hidden"%>>
                                    <%}}%>
                                    <a href="../pages/importacaodearquivos/arquivosabastecimento.jsp">Tabela Abastecimento</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- #Menu -->
                <!-- Footer -->
                <div class="legal">
                    <div class="copyright">
                        &copy; 2017 <a href="https://www.linkedin.com/in/adm-antonioneto/" target="_blank">Antonio Neto - Analista</a>.
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
            <% FiltroDAO fdao = new FiltroDAO();%>
            <% AnaliseDAO adao = new AnaliseDAO();%>
            <% for(Contagens cont: fdao.somarValores()){%>
            <%}%>
            <!-- Widgets -->
            <div>
                <div class="col-xs-12 ol-sm-12 col-md-12 col-lg-12">
                    <div class="panel-group" id="accordion_9" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-col-light-green">
                            <div class="panel-heading" role="tab" id="headingOne_9">
                                <h4 class="panel-title">
                                    <a role="button" data-toggle="collapse" data-parent="#accordion_9" href="#collapseOne_9" aria-expanded="true" aria-controls="collapseOne_9">
                                        Filtro de informações<i class="material-icons">view_headline</i>
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne_9" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingOne_9">
                                <div class="panel-body">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="body">
                                            <div class="col-md-2">
                                                <p class = "">
                                                    <b>Ano</b>
                                                </p>
                                                <div class="form-group form-float">
                                                    <div class="form-line">
                                                        <% 
                                                            int ano = DadosFiltro.getAno();
                                                            if (ano < 2017){
                                                            ano = 2017;
                                                            }
                                                        %>
                                                        <input type="number" class="form-control" id="campoano" name="campoano" value="<%=ano%>" required min="2017" max="2050">
                                                        <label class="form-label">Digitar ano</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <p class = "">
                                                    <b>Mês</b>
                                                </p>
                                                <select id="mes" name="mes" class="form-control show-tick" data-live-search="true">
                                                    <option <% if (DadosFiltro.getNumeromes() == 0){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="1">JAN</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 1){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="2">FEV</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 2){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="3">MAR</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 3){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="4">ABR</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 4){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="5">MAI</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 5){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="6">JUN</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 6){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="7">JUL</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 7){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="8">AGO</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 8){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="9">SET</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 9){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="10">OUT</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 10){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="11">NOV</option>
                                                    <option <% if (DadosFiltro.getNumeromes() == 11){%>
                                                        <%="Selected"%>
                                                        <%}%>
                                                        value="12">DEZ</option>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                                <p>
                                                    <b>Área</b>
                                                </p>
                                                <select id="campoarea" name="campoarea" class="form-control show-tick" data-live-search="true">
                                                    <% for(Area area: fdao.listarArea()){ %>
                                                    <option value=<%=area.getId()%>><%=area.getArea()%></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                                <p>
                                                    <b>Plataforma</b>
                                                </p>
                                                <select id="campoplataforma" name="campoplataforma" class="form-control show-tick" data-live-search="true">
                                                    <% for(Plataforma plataforma: fdao.listarPlataforma()){ %>
                                                    <option value=<%=plataforma.getId()%>><%=plataforma.getPlataforma()%></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="col-md-4">
                                                <p>
                                                    <b>Região</b>
                                                </p>
                                                <% 
                                                  String liberarregiao = "";
                                                  if(!perfillog.equals("Administrador")){
                                                  liberarregiao = " disabled";
                                                }%>
                                                <select id="camporegiao" name="camporegiao" class="form-control show-tick" data-live-search="true" <%=liberarregiao%>>
                                                    <%if(!perfillog.equals("Administrador")){%>
                                                    <option value=<%=regiaolog%>><%=regiaolog%></option>
                                                    <%} else {%>
                                                    <% for(Regiao regiao: fdao.listarRegiao()){ %>
                                                    <option value=<%=regiao.getId()%> ><%=regiao.getRegiao()%></option>
                                                    <% }} %>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                                <p>
                                                    <b>Filial</b>
                                                </p>
                                                <% 
                                                  String liberarfilial = "";
                                                  if(perfillog.equals("Operacional")){
                                                  liberarfilial = " disabled";
                                                }%>
                                                <select id="campofilial" name="campofilial" class="form-control show-tick" data-live-search="true" <%=liberarfilial%>>
                                                    <%if(perfillog.equals("Operacional")){%>
                                                    <option value=<%=filiallog%>><%=filiallog%></option>
                                                    <%} else {%>
                                                    <% for(Filial filial: fdao.listarFilial()){ %>
                                                    <option value=<%=filial.getId()%>><%=filial.getFilial()%></option>
                                                    <% }} %>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                                <p>
                                                    <b>Magnitude</b>
                                                </p>
                                                <select id="campomagnitude" name="campomagnitude" class="form-control show-tick" data-live-search="true">
                                                    <% for(Magnitude magnitude: fdao.listarMagnitude()){ %>
                                                    <option value=<%=magnitude.getId()%>><%=magnitude.getMagnitude()%></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>
                                % REALIZADO / ACUMULADO
                            </h2>
                        </div>
                        <div class="body">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs tab-nav-right tab-col-cyan" role="tablist">
                                <li role="presentation" class="active"><a href="#mensal" data-toggle="tab">MENSAL</a></li>
                                <li role="presentation"><a href="#anual" data-toggle="tab">ACUMULADO</a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div>
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="mensal">
                                        <div class="body">
                                            <div class="row clearfix">
                                                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                    <h4 onclick="filtrarValores()">Total</h4>
                                                    <input id="totalmensal" name="totalmensal" type="text" class="knob" value="42" data-width="125" data-height="125" data-thickness="0.25" data-fgColor="#40C4FF"
                                                           readonly>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                    <h4 id="labour" name="labour">Labour Group</h4>
                                                    <input id="labourmensal" name="labourmensal" type="text" class="knob" value="56" data-width="125" data-height="125" data-thickness="0.25" data-fgColor="#69F0AE"
                                                           readonly>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                    <h4 id="operational" name="operational">Operational Group</h4>
                                                    <input id="operationalmensal" name="operationalmensal" type="text" class="knob" value="48" data-width="125" data-height="125" data-thickness="0.25" data-fgColor="#FF4081"
                                                           readonly>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                    <h4 id="business" name="business">Business Group</h4>
                                                    <input id="businessmensal" name="businessmensal" type="text" class="knob" value="65" data-width="125" data-height="125" data-thickness="0.25" data-fgColor="#FFAB40"
                                                           readonly>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="anual">
                                        <div class="body">
                                            <div class="row clearfix">
                                                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                    <h4>Total</h4>
                                                    <input id="totalacum" name="totalacum" type="text" class="knob" value="42" data-width="125" data-height="125" data-thickness="0.25" data-fgColor="#40C4FF"
                                                           readonly>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                    <h4>Labour Group</h4>
                                                    <input id="labouracum" name="labouracum" type="text" class="knob" value="56" data-width="125" data-height="125" data-thickness="0.25" data-fgColor="#69F0AE"
                                                           readonly>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                    <h4>Operational Group</h4>
                                                    <input id="operationalacum" name="operationalacum" type="text" class="knob" value="48" data-width="125" data-height="125" data-thickness="0.25" data-fgColor="#FF4081"
                                                           readonly>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                    <h4>Business Group</h4>
                                                    <input id="businessacum" name="businessacum" type="text" class="knob" value="65" data-width="125" data-height="125" data-thickness="0.25" data-fgColor="#FFAB40"
                                                           readonly>
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
            <div class="container-fluid">
                <!-- Real-Time Chart -->
                <div class="card" hidden>
                    <div class="header">
                        <h2>REAL-TIME CHART</h2>
                        <div class="pull-right">
                            <div class="switch panel-switch-btn">
                                <span class="m-r-10 font-12">REAL TIME</span>
                                <label>OFF<input type="checkbox" id="realtime" checked><span class="lever switch-col-cyan"></span>ON</label>
                            </div>
                        </div>
                    </div>
                    <div class="body">
                        <div id="real_time_chart" class="flot-chart"></div>
                    </div>
                </div>
                <!-- #END# Real-Time Chart -->

                <!-- Multiple Axis -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>REALIZADO / ORÇADO</h2>
                            </div>
                            <div class="body">
                                <div id="multiple_axis_chart" class="flot-chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- #END# Multiple Axis -->
                <!-- Tracking -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card" hidden>
                            <div class="header">
                                <h2>REALIZADO / ORÇADO</h2>
                            </div>
                            <div class="body">
                                <div id="tracking_chart" class="flot-chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- #END# Tracking -->
                <div class="row clearfix">
                    <!-- Pie Chart -->
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card" hidden>
                            <div class="header">
                                <h2>PIE CHART</h2>
                            </div>
                            <div class="body">
                                <div id="pie_chart" class="flot-chart"></div>
                            </div>
                        </div>
                    </div>
                    <!-- #END# Pie Chart -->
                    <!-- Bar Chart -->

                    <div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="card">
                                <div class="header">
                                    <h2>
                                        KPI - Key Performance Indicator
                                    </h2>
                                </div>
                                <div class="body">
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs tab-col-cyan" role="tablist">
                                        <li role="presentation" class="active"><a href="#custoton" data-toggle="tab"><i class="material-icons">attach_money</i>CUSTO/TON</a></li>
                                        <li role="presentation"><a href="#hc" data-toggle="tab"><i class="material-icons">people</i>HEADCOUNT</a></li>
                                        <li role="presentation"><a href="#outsource" data-toggle="tab"><i class="material-icons">people_outline</i>OUTSOURCE</a></li>
                                        <li role="presentation"><a href="#cellphone" data-toggle="tab"><i class="material-icons">phone_android</i>CELL</a></li>
                                        <li role="presentation"><a href="#car" data-toggle="tab"><i class="material-icons">directions_car</i>CAR</a></li>
                                        <li role="presentation"><a href="#km" data-toggle="tab"><i class="material-icons">confirmation_number</i>KM</a></li>
                                        <li role="presentation"><a href="#wood" data-toggle="tab"><i class="material-icons">whatshot</i>WOOD</a></li>
                                        <li role="presentation"><a href="#power" data-toggle="tab"><i class="material-icons">flash_on</i>POWER</a></li>
                                        <li role="presentation"><a href="#fuel" data-toggle="tab"><i class="material-icons">local_gas_station</i>FUEL</a></li>
                                    </ul>
                                    <!-- Tab panes -->
                                    <div>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade in active" id="custoton">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <input type="checkbox" id="md_checkbox_33" name="md_checkbox_33" class="chk-col-light-blue" checked="true"/>
                                                                <label for="md_checkbox_33">CIF EXP  </label>
                                                                <input type="checkbox" id="md_checkbox_34" name="md_checkbox_34" class="chk-col-light-blue" checked="true"/>
                                                                <label for="md_checkbox_34">CIF REC  </label>
                                                                <input type="checkbox" id="md_checkbox_35" name="md_checkbox_35" class="chk-col-light-blue" checked="true"/>
                                                                <label for="md_checkbox_35">FOB  </label>
                                                                <div id="bar_chart" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" hidden="true">
                                                            <div class="clearfix">
                                                                <div id="tabela" name="tabela" class="body">
                                                                    <div class="table-responsive">
                                                                        <table id="tabelaCustoTon" name="tabelaCustoTon" class="table table-bordered table-striped table-hover dataTable js-exportable">
                                                                            <thead class="bg-cyan">
                                                                                <tr>
                                                                                    <th>Filial</th>
                                                                                    <th>Custo</th>
                                                                                    <th>Volume</th>
                                                                                    <th>Custo por Tonelada</th>
                                                                                                                                                                    </tr>
                                                                            </thead>
                                                                            <tbody id="tabelaorc" name="tabelaorc">
                                                                                <tr>
                                                                                    <td>Teste</td>
                                                                                    <td>Teste</td>
                                                                                    <td>Teste</td>
                                                                                    <td>Teste</td>
                                                                                </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="hc">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <div id="bar_chart1" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-light-blue">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-pink">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="outsource">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <div id="bar_chart2" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-light-blue">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-pink">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="cellphone">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <div id="bar_chart3" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-light-blue">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-pink">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="car">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <div id="bar_chart4" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-light-blue">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-pink">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="km">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <div id="bar_chart5" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-light-blue">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-pink">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="wood">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <div id="bar_chart6" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-light-blue">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-pink">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="power">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <div id="bar_chart7" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-light-blue">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-pink">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="fuel">
                                                <div class="body">
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="body">
                                                                <div id="bar_chart8" class="flot-chart"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-light-blue">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" hidden="true">
                                                            <div class="card">
                                                                <div class="body bg-pink">
                                                                    <div class="font-bold m-b--35">ANSWERED TICKETS</div>
                                                                    <ul class="dashboard-stat-list">
                                                                        <li>
                                                                            TODAY
                                                                            <span class="pull-right"><b>12</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            YESTERDAY
                                                                            <span class="pull-right"><b>15</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST WEEK
                                                                            <span class="pull-right"><b>90</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST MONTH
                                                                            <span class="pull-right"><b>342</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            LAST YEAR
                                                                            <span class="pull-right"><b>4 225</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                        <li>
                                                                            ALL
                                                                            <span class="pull-right"><b>8 752</b> <small>TICKETS</small></span>
                                                                        </li>
                                                                    </ul>
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
                        </div>
                    </div>
                    <!-- #END# Bar Chart -->
                </div>
            </div>
        </section>

        <!-- Jquery Core Js -->
        <script src="../plugins/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core Js -->
        <script src="../plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Select Plugin Js -->
        <script src="../plugins/bootstrap-select/js/bootstrap-select.js"></script>

        <!-- Slimscroll Plugin Js -->
        <script src="../plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="../plugins/node-waves/waves.js"></script>

        <!-- Jquery CountTo Plugin Js -->
        <script src="../plugins/jquery-countto/jquery.countTo.js"></script>

        <!-- Jquery Knob Plugin Js -->
        <script src="../plugins/jquery-knob/jquery.knob.min.js"></script>

        <!-- Flot Charts Plugin Js -->
        <script src="../plugins/flot-charts/jquery.flot.js"></script>
        <script src="../plugins/flot-charts/jquery.flot.resize.js"></script>
        <script src="../plugins/flot-charts/jquery.flot.pie.js"></script>
        <script src="../plugins/flot-charts/jquery.flot.categories.js"></script>
        <script src="../plugins/flot-charts/jquery.flot.time.js"></script>

        <!-- Jquery DataTable Plugin Js -->
        <script src="../plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="../plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.js"></script>
        <script src="../plugins/jquery-datatable/extensions/export/dataTables.buttons.min.js"></script>
        <script src="../plugins/jquery-datatable/extensions/export/buttons.flash.min.js"></script>
        <script src="../plugins/jquery-datatable/extensions/export/jszip.min.js"></script>
        <script src="../plugins/jquery-datatable/extensions/export/pdfmake.min.js"></script>
        <script src="../plugins/jquery-datatable/extensions/export/vfs_fonts.js"></script>
        <script src="../plugins/jquery-datatable/extensions/export/buttons.html5.min.js"></script>
        <script src="../plugins/jquery-datatable/extensions/export/buttons.print.min.js"></script>

        <!-- Custom Js -->
        <script src="../js/pages/charts/jquery-knob.js"></script>
        <script src="../js/pages/charts/flot.js"></script>
        <script src="../js/admin.js"></script>
        <script src="../js/scriptinicial.js"></script>
        <script src="../js/pages/tables/jquery-datatable.js"></script>


        <!-- Demo Js -->
        <script src="../js/demo.js"></script>

    </body>

</html>