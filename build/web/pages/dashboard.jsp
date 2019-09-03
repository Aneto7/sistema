<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*;" %>
<%@page import="javax.servlet.*;" %>
<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>HabSys - DashBoard</title>
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

        <!-- Colorpicker Css -->
        <link href="../plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.css" rel="stylesheet" />

        <!-- Dropzone Css -->
        <link href="../plugins/dropzone/dropzone.css" rel="stylesheet">

        <!-- Multi Select Css -->
        <link href="../plugins/multi-select/css/multi-select.css" rel="stylesheet">

        <!-- Bootstrap Spinner Css -->
        <link href="../plugins/jquery-spinner/css/bootstrap-spinner.css" rel="stylesheet">

        <!-- Bootstrap Tagsinput Css -->
        <link href="../plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">

        <!-- Bootstrap Material Datetime Picker Css -->
        <link href="../plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />

        <!-- Wait Me Css -->
        <link href="../plugins/waitme/waitMe.css" rel="stylesheet" />

        <!-- Bootstrap Select Css -->
        <link href="../plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />

        <!-- noUISlider Css -->
        <link href="../plugins/nouislider/nouislider.min.css" rel="stylesheet" />

        <!-- Morris Chart Css-->
        <link href="../plugins/morrisjs/morris.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="../css/style.css" rel="stylesheet">

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="../css/themes/all-themes.css" rel="stylesheet" />
    </head>

    <body class="theme-blue">
        <!-- Page Loader -->
        <div class="page-loader-wrapper">
            <div class="loader">
                <div class="preloader">
                    <div class="spinner-layer pl-blue">
                        <div class="circle-clipper left">
                            <div class="circle"></div>
                        </div>
                        <div class="circle-clipper right">
                            <div class="circle"></div>
                        </div>
                    </div>
                </div>
                <%
                String ativo = "";
                    if(session.getAttribute("idlog") == null){
                        response.sendRedirect("../login.jsp");
                    } else {
                    
                    int idlog = (Integer) session.getAttribute("idlog");
                    String usuariolog = (String) session.getAttribute("usuariolog");
                    String nomelog = (String) session.getAttribute("nomelog");
                    String perfillog = (String) session.getAttribute("perfillog");
                    String empresalog = (String) session.getAttribute("empresalog");
                    String setorlog = (String) session.getAttribute("setorlog");
                    String iniciolog = (String) session.getAttribute("iniciolog");
                    String lancamentolog = (String) session.getAttribute("lancamentolog");
                    String atendimentolog = (String) session.getAttribute("atendimentolog");
                    String cadastrolog = (String) session.getAttribute("cadastrolog");
                    String relatorioslog = (String) session.getAttribute("relatorioslog");
                    String dashboardlog = (String) session.getAttribute("dashboardlog");
                    String usuarioslog = (String) session.getAttribute("usuarioslog");
                    String controlelog = (String) session.getAttribute("controlelog");
                                       
                    if (lancamentolog.equals("false")){
                        response.sendRedirect("../login.jsp");
                    }
                %>
                <p><%=nomelog%></p>
                <p>Por favor aguarde...</p>
            </div>
        </div>
        <!-- #END# Page Loader -->
        <!-- Overlay For Sidebars -->
        <div class="overlay"></div>
        <!-- #END# Overlay For Sidebars -->
        <!-- Top Bar -->
        <nav class="navbar">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                    <a href="javascript:void(0);" class="bars"></a>
                    <a class="navbar-brand" href="../pages/inicio.jsp">HabSys - DashBoard</a>
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
                        <img src="../images/image001.png" width="48" height="48" alt="User" />
                    </div>
                    <div class="info-container">
                        <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=nomelog%></div>
                        <div class="email"> <%=usuariolog%></div>
                        <div class="btn-group user-helper-dropdown">
                            <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                            <ul class="dropdown-menu pull-right">
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
                        <li>
                            <a href="../pages/inicio.jsp">
                                <i class="material-icons">home</i>
                                <span>Início</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">attach_money</i>
                                <span>Lançamentos Financeiros</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="../pages/lancamentos.jsp">Lançamentos</a>
                                </li>
                                <li>
                                    <a href="../pages/dashboardfinanceiro.jsp">Dashboard</a>
                                </li>
                            </ul>
                        </li>
                        <%if (atendimentolog.equals("false")){%>
                        <li <%=" hidden"%>>
                            <%}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">folder</i>
                                <span>Atendimento</span>
                            </a>
                            <ul class="ml-menu">
                                <%if (atendimentolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/aberturaatendimento.jsp">Abertura</a>
                                </li<%=" hidden"%>>
                                <%if (atendimentolog.equals("false")){%>
                                <li>
                                    <%}%>
                                    <a href="../pages/lancamentoatendimento.jsp">Lançamento</a>
                                </li>
                            </ul>
                        </li>
                        <%if (controlelog.equals("false")){%>
                        <li <%=" hidden"%>>
                            <%}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">playlist_add_check</i>
                                <span>Controle</span>
                            </a>
                            <ul class="ml-menu">
                                <%if (controlelog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/pedidomaterial.jsp">Pedido de Material</a>
                                </li>
                                <%if (controlelog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/valorobra.jsp">Personalização</a>
                                </li>
                            </ul>
                        </li>
                        <%if (cadastrolog.equals("false")){%>
                        <li <%=" hidden"%>>
                            <%}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">assignment</i>
                                <span>Cadastro</span>
                            </a>
                            <ul class="ml-menu">
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/cliente.jsp">Cliente</a>
                                </li>
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/fornecedor.jsp">Fornecedor</a>
                                </li>
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/produto.jsp">Processo</a>
                                </li>
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/material.jsp">Material</a>
                                </li>
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/centrodecusto.jsp">Centro de Custo</a>
                                </li>
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/conta.jsp">Conta</a>
                                </li>
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/itemfinanceiro.jsp">Item Financeiro</a>
                                </li>
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/mestredeobras.jsp">Mestre de Obras</a>
                                </li>
                                <%if (cadastrolog.equals("false")){%>
                                <li<%=" hidden"%>>
                                    <%}%>
                                    <a href="../pages/vendedor.jsp">Vendedor</a>
                                </li>
                            </ul>
                        </li>
                        <%if (relatorioslog.equals("false")){%>
                        <li<%=" hidden"%>>
                            <%}%>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">list</i>
                                <span>Relatório</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="../pages/relatorio.jsp">Financeiro</a>
                                </li>
                                <li>
                                    <a href="../pages/relatorioatendimento.jsp">Atendimento</a>
                                </li>
                                <li>
                                    <a href="../pages/relatoriocliente.jsp">Cliente</a>
                                </li>
                                <li>
                                    <a href="../pages/relatoriofornecedor.jsp">Fornecedores</a>
                                </li>
                                <li>
                                    <a href="../pages/relatoriovendedor.jsp">Vendedores</a>
                                </li>
                                <li>
                                    <a href="../pages/relatoriopedidos.jsp">Pedidos</a>
                                </li>
                                <li>
                                    <a href="../pages/relatoriopersonalizacao.jsp">Personalização</a>
                                </li>
                            </ul>
                        </li>
                        <li class="active">
                            <a href="../pages/dashboard.jsp">
                                <i class="material-icons">dashboard</i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <%if (usuarioslog.equals("false")){%>
                        <li <%=" hidden"%>>
                            <%}%>
                            <a href="../pages/usuarios.jsp">
                                <i class="material-icons">people</i>
                                <span>Usuários</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- #Menu -->
                <!-- Footer -->
                <div class="legal">
                    <div class="copyright">
                        &copy; 2019 <a href="https://www.linkedin.com/in/adm-antonioneto/">Antonio Neto</a>.
                    </div>
                    <div class="version">
                        <b>Version: </b> 1.0.0
                    </div>
                </div>
                <!-- #Footer -->
            </aside>
            <!-- #END# Left Sidebar -->
        </section>
        <section class="content">
            <!-- Widgets -->
            <div>
                <div class="col-xs-12 ol-sm-12 col-md-12 col-lg-12">
                    <div class="panel-group" id="accordion_9" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-col-blue">
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
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <p>
                                                    <b>Ano de Referência</b>
                                                </p>
                                                <div class="form-line" >
                                                    <input id="ano" name="ano" type="number" class="form-control" required="true" value="2019">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <p>
                                                    <b>Grupo Item Financeiro</b>
                                                </p>
                                                <select id="grupoitem" name="grupoitem" registro="" class="form-control show-tick" data-live-search="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <p>
                                                    <b>Item Financeiro</b>
                                                </p>
                                                <select id="itemfinanceiro" name="itemfinanceiro" registro="" class="form-control show-tick" data-live-search="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <p>
                                                    <b>Nº do Documento</b>
                                                </p>
                                                <div class="form-line" >
                                                    <input id="documento" name="documento" type="number" class="form-control" placeholder="inserir valor"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Tipo de Recebimento</b>
                                                </p>
                                                <select id="recebimento" name="recebimento" class="form-control show-tick" data-live-search="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Status</b>
                                                </p>
                                                <select id="recebimento" name="recebimento" class="form-control show-tick" data-live-search="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Pendente</b>
                                                </p>
                                                <select id="recebimento" name="recebimento" class="form-control show-tick" data-live-search="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Centro de Custo</b>
                                                </p>
                                                <select id="centrocusto" name="centrocusto" multiple class="form-control show-tick" data-live-search="true" data-actions-box="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <p>
                                                    <b>Atendimento</b>
                                                </p>
                                                <select id="atendimento" name="atendimento" multiple class="form-control show-tick" data-live-search="true" data-actions-box="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <p>
                                                    <b>Processo</b>
                                                </p>
                                                <select id="produto" name="produto" class="form-control show-tick" data-live-search="true" data-actions-box="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <p>
                                                    <b>Conta Recebimento</b>
                                                </p>
                                                <select id="contabanco" name="contabanco" class="form-control show-tick" data-live-search="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Cliente</b>
                                                </p>
                                                <select id="cliente" name="cliente" class="form-control show-tick" data-live-search="true" data-actions-box="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Fornecedor</b>
                                                </p>
                                                <select id="fornecedor" name="fornecedor" class="form-control show-tick" data-live-search="true" data-actions-box="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Vendedor</b>
                                                </p>
                                                <select id="vendedor" name="vendedor" class="form-control show-tick" data-live-search="true">
                                                    <option></option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Data de Emissão</b>
                                                </p>
                                                <div class="form-line" >
                                                    <input id="emissao" name="emissao" type="text" class="datepicker form-control" required="false">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Data de Vencimento</b>
                                                </p>
                                                <div class="form-line" >
                                                    <input id="vencimento" name="vencimento" type="text" class="datepicker form-control" required="false">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <p>
                                                    <b>Data de Pagamento</b>
                                                </p>
                                                <div class="form-line" >
                                                    <input id="pagamento" name="pagamento" type="text" class="datepicker form-control" required="false">
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
        <div class="container-fluid">
            <!-- Multiple Axis -->
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>Lançamento Mensal</h2>
                        </div>
                        <div class="body">
                            <div id="graficolinha" class="graph"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Multiple Axis -->
            <!-- Bar Chart -->
            <div>
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2>
                                Quadro de Gráficos
                            </h2>
                        </div>
                        <div class="body">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs tab-col-blue" role="tablist">
                                <li role="presentation" class="active"><a href="#custoton" data-toggle="tab"><i class="material-icons">attach_money</i>CUSTO OBRA</a></li>
                                <li role="presentation"><a href="#hc" data-toggle="tab"><i class="material-icons">people</i>RECEITAS</a></li>
                                <li role="presentation"><a href="#outsource" data-toggle="tab"><i class="material-icons">people_outline</i>DESPESAS</a></li>
                                <li role="presentation"><a href="#cellphone" data-toggle="tab"><i class="material-icons">phone_android</i>PROCESSOS</a></li>
                                <li role="presentation"><a href="#car" data-toggle="tab"><i class="material-icons">directions_car</i>OBRAS</a></li>
                                <li role="presentation"><a href="#km" data-toggle="tab"><i class="material-icons">confirmation_number</i>PEDIDOS</a></li>
                                <li role="presentation"><a href="#wood" data-toggle="tab"><i class="material-icons">whatshot</i>CLIENTES</a></li>
                                <li role="presentation"><a href="#power" data-toggle="tab"><i class="material-icons">flash_on</i>EXECUÇÃO</a></li>
                                <li role="presentation"><a href="#fuel" data-toggle="tab"><i class="material-icons">local_gas_station</i>PRODUTIVIDADE</a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div>
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="custoton">
                                        <div class="body">
                                            <div class="row clearfix">
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                    <div class="body">
                                                        <div id="bar_chart1" class="flot-chart"></div>
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
    <%}%>
</section>

<!-- Jquery Core Js -->
<script src="../plugins/jquery/jquery.min.js"></script>

<!-- Bootstrap Core Js -->
<script src="../plugins/bootstrap/js/bootstrap.js"></script>

<!-- Select Plugin Js -->
<script src="../plugins/bootstrap-select/js/bootstrap-select.js"></script>

<!-- Slimscroll Plugin Js -->
<script src="../plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

<!-- Bootstrap Colorpicker Js -->
<script src="../plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>

<!-- Dropzone Plugin Js -->
<script src="../plugins/dropzone/dropzone.js"></script>

<!-- Input Mask Plugin Js -->
<script src="../plugins/jquery-inputmask/jquery.inputmask.bundle.js"></script>

<!-- Multi Select Plugin Js -->
<script src="../plugins/multi-select/js/jquery.multi-select.js"></script>

<!-- Jquery Spinner Plugin Js -->
<script src="../plugins/jquery-spinner/js/jquery.spinner.js"></script>

<!-- Bootstrap Tags Input Plugin Js -->
<script src="../plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>

<!-- noUISlider Plugin Js -->
<script src="../plugins/nouislider/nouislider.js"></script>

<!-- Waves Effect Plugin Js -->
<script src="../plugins/node-waves/waves.js"></script>

<!-- Jquery CountTo Plugin Js -->
<script src="../plugins/jquery-countto/jquery.countTo.js"></script>

<!-- Autosize Plugin Js -->
<script src="../plugins/autosize/autosize.js"></script>

<!-- Moment Plugin Js -->
<script src="../plugins/momentjs/moment.js"></script>

<!-- Bootstrap Material Datetime Picker Plugin Js -->
<script src="../plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>

<!-- Morris Plugin Js -->
<script src="../plugins/raphael/raphael.min.js"></script>
<script src="../plugins/morrisjs/morris.js"></script>

<!-- Morris Plugin Js -->
<script src="../plugins/raphael/raphael.min.js"></script>
<script src="../plugins/morrisjs/morris.js"></script>

<!-- Sparkline Chart Plugin Js -->
<script src="../plugins/jquery-sparkline/jquery.sparkline.js"></script>

<!-- Custom Js -->
<script src="../js/pages/dashboard.js"></script>
<script src="../js/admin.js"></script>
<script src="../js/pages/index.js"></script>
<script src="../js/pages/forms/basic-form-elements.js"></script>
<script src="../js/pages/forms/advanced-form-elements.js"></script>

<!-- Demo Js -->
<script src="../js/demo.js"></script>
</body>

</html>