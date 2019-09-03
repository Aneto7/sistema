<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*;" %>
<%@page import="javax.servlet.*;" %>
<%@page import="br.com.gestint.cadastro.*;" %>
<%@page import="br.com.gestint.cadastroDAO.*;" %>
<%@page import="br.com.gestint.orcamento.*;" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title id="nometela" name="nometela" value="controlejustificativa">Gestao Interna - Cadastro de Tabela de Apoio</title>
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
                    <a class="navbar-brand" href="../paginainicial.jsp"><b>LDC. - </b>Cadastro de Informações de apoio</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="../paginainicial.jsp">Início ></a></li>
                        <li><a href="dashboarcontrole.jsp">Cadastro ></a></li>
                        <li><a href="javascript:void(0);">Apoio </a></li>
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
                                            Cadastro de Informações de apoio<i class="material-icons">view_headline</i>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne_9" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne_9">
                                    <div class="panel-body">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="header">
                                                <h4>Dados para filtro de lançamento de apoio</h4>
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                    <div class="col-md-3">
                                                        <p class = "">
                                                            <b>Ano</b>
                                                        </p>
                                                        <div class="form-group form-float">
                                                            <div class="form-line">
                                                                <input type="number" class="form-control" id="campoano" name="campoano" required value="<%=DadosFiltro.getAno()%>" onchange="filtrarAnoMes()">
                                                                <label class="form-label">Digitar ano</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-3">
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
                                                </div>
                                            </div>
                                            <div class="body">
                                                <!-- Nav tabs -->
                                                <ul class="nav nav-tabs tab-nav-right" role="tablist">
                                                    <li role="presentation" class="active"><a href="#headcount" data-toggle="tab">HEADCOUNT</a></li>
                                                    <li role="presentation"><a href="#volume" data-toggle="tab">VOLUME</a></li>
                                                    <li role="presentation"><a href="#volumeplan" data-toggle="tab">VOLUME PLAN</a></li>
                                                    <li role="presentation"><a href="#km" data-toggle="tab">KILOMETERS</a></li>
                                                    <li role="presentation"><a href="#wood" data-toggle="tab">WOOD</a></li>
                                                    <li role="presentation"><a href="#secagem" data-toggle="tab">DRY</a></li>
                                                    <li role="presentation"><a href="#power" data-toggle="tab">POWER</a></li>
                                                    <li role="presentation"><a href="#fuel" data-toggle="tab">FUEL</a></li>
                                                    <li role="presentation"><a href="#cell" data-toggle="tab">CELL</a></li>
                                                    <li role="presentation"><a href="#car" data-toggle="tab">CAR</a></li>
                                                </ul>
                                                <!-- Tab panes -->
                                                <div class="tab-content">
                                                    <div role="tabpanel" class="tab-pane fade in active" id="headcount">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelahc" name="HEADCOUNT" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Real 115</th>
                                                                            <th>Real 201</th>
                                                                            <th>Real 333</th>
                                                                            <th>Real 322</th>
                                                                            <th>Real Total</th>
                                                                            <th>Plan 115</th>
                                                                            <th>Plan 201</th>
                                                                            <th>Plan 333</th>
                                                                            <th>Plan 322</th>
                                                                            <th>Plan Total</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio hc: fdao.listarHeadcount()){ %>
                                                                        <tr value="<%=hc.getId()%>">
                                                                            <td><%=hc.getRegiao()%></td>
                                                                            <td><%=hc.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="115" plataforma="GERAL"><%=hc.getReal115()%></td>
                                                                            <td planoureal="REALIZADO" area="201" plataforma="GERAL"><%=hc.getReal201()%></td>
                                                                            <td planoureal="REALIZADO" area="333" plataforma="GERAL"><%=hc.getReal333()%></td>
                                                                            <td planoureal="REALIZADO" area="322" plataforma="GERAL"><%=hc.getReal322()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=hc.getRealacum()%></td>
                                                                            <td planoureal="PLANEJADO" area="115" plataforma="GERAL"><%=hc.getPlan115()%></td>
                                                                            <td planoureal="PLANEJADO" area="201" plataforma="GERAL"><%=hc.getPlan201()%></td>
                                                                            <td planoureal="PLANEJADO" area="333" plataforma="GERAL"><%=hc.getPlan333()%></td>
                                                                            <td planoureal="PLANEJADO" area="322" plataforma="GERAL"><%=hc.getPlan322()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=hc.getPlanacum()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="volume">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelavolume" name="VOLUME" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Soja CIF Rec</th>
                                                                            <th>Milho CIF Rec</th>
                                                                            <th>Total CIF Rec</th>
                                                                            <th>Soja CIF Exp</th>
                                                                            <th>Milho CIF Exp</th>
                                                                            <th>Total CIF Exp</th>
                                                                            <th>Soja FOB</th>
                                                                            <th>Milho FOB</th>
                                                                            <th>Total FOB</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio volume: fdao.listarVolume()){ %>
                                                                        <tr value="<%=volume.getId()%>">
                                                                            <td><%=volume.getRegiao()%></td>
                                                                            <td><%=volume.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="CIF REC" plataforma="SOJA"><%=volume.getRealcifrecsoja()%></td>
                                                                            <td planoureal="REALIZADO" area="CIF REC" plataforma="MILHO"><%=volume.getRealcifrecmilho()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=volume.getRealcifrec()%></td>
                                                                            <td planoureal="REALIZADO" area="CIF EXP" plataforma="SOJA"><%=volume.getRealcifexpsoja()%></td>
                                                                            <td planoureal="REALIZADO" area="CIF EXP" plataforma="MILHO"><%=volume.getRealcifexpmilho()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=volume.getRealcifexp()%></td>
                                                                            <td planoureal="REALIZADO" area="FOB" plataforma="SOJA"><%=volume.getRealfobsoja()%></td>
                                                                            <td planoureal="REALIZADO" area="FOB" plataforma="MILHO"><%=volume.getRealfobmilho()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=volume.getRealfob()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="volumeplan">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelavolumeplan" name="VOLUME" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Soja CIF Rec</th>
                                                                            <th>Milho CIF Rec</th>
                                                                            <th>Total CIF Rec</th>
                                                                            <th>Soja CIF Exp</th>
                                                                            <th>Milho CIF Exp</th>
                                                                            <th>Total CIF Exp</th>
                                                                            <th>Soja FOB</th>
                                                                            <th>Milho FOB</th>
                                                                            <th>Total FOB</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio volume: fdao.listarVolumePlan()){ %>
                                                                        <tr value="<%=volume.getId()%>">
                                                                            <td><%=volume.getRegiao()%></td>
                                                                            <td><%=volume.getFilial()%></td>
                                                                            <td planoureal="PLANEJADO" area="CIF REC" plataforma="SOJA"><%=volume.getRealcifrecsoja()%></td>
                                                                            <td planoureal="PLANEJADO" area="CIF REC" plataforma="MILHO"><%=volume.getRealcifrecmilho()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=volume.getRealcifrec()%></td>
                                                                            <td planoureal="PLANEJADO" area="CIF EXP" plataforma="SOJA"><%=volume.getRealcifexpsoja()%></td>
                                                                            <td planoureal="PLANEJADO" area="CIF EXP" plataforma="MILHO"><%=volume.getRealcifexpmilho()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=volume.getRealcifexp()%></td>
                                                                            <td planoureal="PLANEJADO" area="FOB" plataforma="SOJA"><%=volume.getRealfobsoja()%></td>
                                                                            <td planoureal="PLANEJADO" area="FOB" plataforma="MILHO"><%=volume.getRealfobmilho()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=volume.getRealfob()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="km">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelakm" name="QUILOMETRAGEM" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>KM</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio km: fdao.listarKm()){ %>
                                                                        <tr value="<%=km.getId()%>">
                                                                            <td><%=km.getRegiao()%></td>
                                                                            <td><%=km.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="GERAL" plataforma="GERAL"><%=km.getRealacum()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="wood">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelawood" name="WOOD" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Wood Soja</th>
                                                                            <th>Wood Milho</th>
                                                                            <th>Wood Total</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio wood: fdao.listarWood()){ %>
                                                                        <tr value="<%=wood.getId()%>">
                                                                            <td><%=wood.getRegiao()%></td>
                                                                            <td><%=wood.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="GERAL" plataforma="SOJA"><%=wood.getRealsoja()%></td>
                                                                            <td planoureal="REALIZADO" area="GERAL" plataforma="MILHO"><%=wood.getRealfob()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=wood.getRealacum()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="secagem">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabeladry" name="SECAGEM" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Dry Soja</th>
                                                                            <th>Dry Milho</th>
                                                                            <th>Dry Total</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio dry: fdao.listarDry()){ %>
                                                                        <tr value="<%=dry.getId()%>">
                                                                            <td><%=dry.getRegiao()%></td>
                                                                            <td><%=dry.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="GERAL" plataforma="SOJA"><%=dry.getRealsoja()%></td>
                                                                            <td planoureal="REALIZADO" area="GERAL" plataforma="MILHO"><%=dry.getRealfob()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=dry.getRealacum()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="power">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelapower" name="PURCHASED POWER" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Power</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio power: fdao.listarPower()){ %>
                                                                        <tr value="<%=power.getId()%>">
                                                                            <td><%=power.getRegiao()%></td>
                                                                            <td><%=power.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="GERAL" plataforma="GERAL"><%=power.getRealacum()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="fuel">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelafuel" name="FUEL" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Fuel Soja</th>
                                                                            <th>Fuel Milho</th>
                                                                            <th>Fuel Total</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio fuel: fdao.listarFuel()){ %>
                                                                        <tr value="<%=fuel.getId()%>">
                                                                            <td><%=fuel.getRegiao()%></td>
                                                                            <td><%=fuel.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="GERAL" plataforma="SOJA"><%=fuel.getRealsoja()%></td>
                                                                            <td planoureal="REALIZADO" area="GERAL" plataforma="MILHO"><%=fuel.getRealfob()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=fuel.getRealacum()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="cell">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelacell" name="CELLPHONE" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Cell 115</th>
                                                                            <th>Cell 201</th>
                                                                            <th>Cell 333</th>
                                                                            <th>Cell 322</th>
                                                                            <th>Cell Total</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio cell: fdao.listarCell()){ %>
                                                                        <tr value="<%=cell.getId()%>">
                                                                            <td><%=cell.getRegiao()%></td>
                                                                            <td><%=cell.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="115" plataforma="GERAL"><%=cell.getReal115()%></td>
                                                                            <td planoureal="REALIZADO" area="201" plataforma="GERAL"><%=cell.getReal201()%></td>
                                                                            <td planoureal="REALIZADO" area="333" plataforma="GERAL"><%=cell.getReal333()%></td>
                                                                            <td planoureal="REALIZADO" area="322" plataforma="GERAL"><%=cell.getReal322()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=cell.getRealacum()%></td>
                                                                        </tr>
                                                                        <% } %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="car">
                                                        <div class="card">
                                                            <div class="body">
                                                                <table id="tabelacar" name="CAR" class="table table-bordered table-condensed table-responsive table-striped table-hover">
                                                                    <thead>
                                                                        <tr class="bg-light-green">
                                                                            <th>Região</th>
                                                                            <th>Filial</th>
                                                                            <th>Cell 115</th>
                                                                            <th>Cell 201</th>
                                                                            <th>Cell 333</th>
                                                                            <th>Cell 322</th>
                                                                            <th>Cell Total</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <% for(Apoio car: fdao.listarCar()){ %>
                                                                        <tr value="<%=car.getId()%>">
                                                                            <td><%=car.getRegiao()%></td>
                                                                            <td><%=car.getFilial()%></td>
                                                                            <td planoureal="REALIZADO" area="115" plataforma="GERAL"><%=car.getReal115()%></td>
                                                                            <td planoureal="REALIZADO" area="201" plataforma="GERAL"><%=car.getReal201()%></td>
                                                                            <td planoureal="REALIZADO" area="333" plataforma="GERAL"><%=car.getReal333()%></td>
                                                                            <td planoureal="REALIZADO" area="322" plataforma="GERAL"><%=car.getReal322()%></td>
                                                                            <td planoureal="NO" area="NO" plataforma="NO"><%=car.getRealacum()%></td>
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
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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

        <!-- Editable Table Plugin Js -->
        <script src="../../plugins/editable-table/mindmup-editabletable.js"></script>

        <!-- Sparkline Chart Plugin Js -->
        <script src="../../plugins/jquery-sparkline/jquery.sparkline.js"></script>

        <!-- Custom Js -->
        <script src="../../js/admin.js"></script>
        <script src="../../js/pages/cadastro/scriptcadastroapoio.js"></script>
        <script src="../../js/pages/tables/editable-table.js"></script>
        <script src="../../js/pages/tables/jquery-datatable.js"></script>
        <script src="../../js/pages/ui/dialogs.js"></script>
        <script src="../../js/pages/index.js"></script>


    </body>
</html>