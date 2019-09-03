$(document).ready(function () {

    var perfillog = $('#perfillogado').text();
    if (perfillog !== "Operacional") {
        filtrarFilialOrcamento();
    }
    filtrarAnalise();
    numeroPendentes();
    filtrarValores();

    $('#campoano').on('change', function () {
        filtrarAnoMes();
    });

    $('#mes').on('change', function () {
        filtrarMes();
    });
    $('#campoarea').on('change', function () {
        if (perfillog !== "Supervisor" && perfillog !== "Operacional") {
            filtrarRegiaoOrcamento();
        }
        if (perfillog !== "Operacional") {
            filtrarFilialOrcamento();
        }
        filtrarPlataformaOrcamento();
        filtrarMagnitudeOrcamento();
        filtrarCentroOrcamento();
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarValores();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });
    $('#campoplataforma').on('change', function () {
        if (perfillog !== "Supervisor" && perfillog !== "Operacional") {
            filtrarRegiaoOrcamento();
        }
        if (perfillog !== "Operacional") {
            filtrarFilialOrcamento();
        }
        filtrarMagnitudeOrcamento();
        filtrarCentroOrcamento();
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarValores();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });
    $('#camporegiao').on('change', function () {
        filtrarFilialOrcamento();
        filtrarMagnitudeOrcamento();
        filtrarCentroOrcamento();
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
        filtrarValores();
    });
    $('#campofilial').on('change', function () {
        filtrarMagnitudeOrcamento();
        filtrarCentroOrcamento();
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarValores();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });
    $('#campomagnitude').on('change', function () {
        filtrarCentroOrcamento();
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarValores();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });
    $('#campocentrodecusto').on('change', function () {
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarValores();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });
    $('#campoconta').on('change', function () {
        filtrarResponsavel();
        filtrarValores();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });
    $('#limparselecao').on('click', function () {

        if (perfillog !== "Supervisor" && perfillog !== "Operacional") {
            filtrarRegiaoOrcamento();
        }
        if (perfillog !== "Operacional") {
            filtrarFilialOrcamento();
        }
        $("#campoarea").val("0");
        $('#campoarea').selectpicker('refresh');
        $("#campoplataforma").val("0");
        $('#campoplataforma').selectpicker('refresh');
        $("#camporegiao").val("0");
        $('#camporegiao').selectpicker('refresh');
        $("#campofilial").val("0");
        $('#campofilial').selectpicker('refresh');
        $("#campomagnitude").val("0");
        $('#campomagnitude').selectpicker('refresh');
        $("#campocentrodecusto").val("0");
        $('#campocentrodecusto').selectpicker('refresh');
        $("#campoconta").val("0");
        $('#campoconta').selectpicker('refresh');
        var oTable = $('#tabelaOrcamento').dataTable();
        oTable.fnFilter("");

        filtrarPlataformaOrcamento();
        filtrarMagnitudeOrcamento();
        filtrarCentroOrcamento();
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarValores();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });

    $('tr').click(function () {
        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaOrcamento',
                data: 'idid=' + $(this).find('td[data-name]').data('name'),
                statusCode: {
                    404: function () {
                        alert('Pagina não encontrada no filtro');
                    },
                    500: function () {
                        alert('erro no servidor')
                    }
                },
                success: function (dados) {
                    itemfiltradas = dados.split(";");
                    for (var i = 0; i < itemfiltradas.length; i++) {
                        var responsavel = itemfiltradas[i].split("%")[0];
                        var segmento = itemfiltradas[i].split("%")[1];
                        var regiao = itemfiltradas[i].split("%")[2];
                        var filial = itemfiltradas[i].split("%")[3];
                        var magnitude = itemfiltradas[i].split("%")[4];
                        var centro = itemfiltradas[i].split("%")[5];
                        var conta = itemfiltradas[i].split("%")[6];
                        $('#campoarea').val($('option:contains("' + responsavel + '")').val());
                        $('#campoarea').selectpicker('refresh');
                        $('#campoplataforma').val($('option:contains("' + segmento + '")').val());
                        $('#campoplataforma').selectpicker('refresh');
                        $('#camporegiao').val($('option:contains("' + regiao + '")').val());
                        $('#camporegiao').selectpicker('refresh');
                        $('#campofilial').val($('option:contains("' + filial + '")').val());
                        $('#campofilial').selectpicker('refresh');
                        $('#campomagnitude').val($('option:contains("' + magnitude + '")').val());
                        $('#campomagnitude').selectpicker('refresh');
                        $('#campocentrodecusto').val($('option:contains("' + centro + '")').val());
                        $('#campocentrodecusto').selectpicker('refresh');
                        $('#campoconta').val($('option:contains("' + conta + '")').val());
                        $('#campoconta').selectpicker('refresh');
                    }
                    filtrarValores();
                    filtrarAnalise();
                    $('#spinneranalise').val("1");
                }
            });
        }
    });
});

function filtrarFilialOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcamentoFilial',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            $("#campofilial").empty();
            filiaisfiltradas = dados.split(";");
            $('#campofilial').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < filiaisfiltradas.length; i++) {
                var codfilial = filiaisfiltradas[i].split("%")[0];
                var nomefilial = filiaisfiltradas[i].split("%")[1];
                $('#campofilial').append($('<option>', {
                    value: codfilial,
                    text: nomefilial
                }));
                $('#campofilial').selectpicker('refresh');
            }
        }
    });
}

function filtrarAreaOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcArea',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na área')
            }
        },
        success: function (dados) {
            $("#campoarea").empty();
            areasfiltradas = dados.split(";");
            $('#campofilial').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < areasfiltradas.length; i++) {
                var codarea = areasfiltradas[i].split("-")[0];
                var nomearea = areasfiltradas[i].split("-")[1];
                $('#campoarea').append($('<option>', {
                    value: codarea,
                    text: nomearea
                }));
            }
            $('#campoarea').selectpicker('refresh');
        }
    });
}

function filtrarPlataformaOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcPlataforma',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na plataforma')
            }
        },
        success: function (dados) {
            $("#campoplataforma").empty();
            plataformasfiltradas = dados.split(";");
            $('#campoplataforma').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < plataformasfiltradas.length; i++) {
                var codplataforma = plataformasfiltradas[i].split("-")[0];
                var nomeplataforma = plataformasfiltradas[i].split("-")[1];
                $('#campoplataforma').append($('<option>', {
                    value: codplataforma,
                    text: nomeplataforma
                }));
            }
            $('#campoplataforma').selectpicker('refresh');
        }
    });
}

function filtrarRegiaoOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcRegiao',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na regiao')
            }
        },
        success: function (dados) {
            $("#camporegiao").empty();
            regiaofiltradas = dados.split(";");
            $('#camporegiao').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < regiaofiltradas.length; i++) {
                var codregiao = regiaofiltradas[i].split("/")[0];
                var nomeregiao = regiaofiltradas[i].split("/")[1];
                $('#camporegiao').append($('<option>', {
                    value: codregiao,
                    text: nomeregiao
                }));
            }
            $('#camporegiao').selectpicker('refresh');
        }
    });
}

function filtrarMagnitudeOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcMagnitude',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na magnitude')
            }
        },
        success: function (dados) {
            $("#campomagnitude").empty();
            magnitudefiltradas = dados.split(";");
            $('#campomagnitude').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < magnitudefiltradas.length; i++) {
                var codmagnitude = magnitudefiltradas[i].split("-")[0];
                var nomemagnitude = magnitudefiltradas[i].split("-")[1];
                $('#campomagnitude').append($('<option>', {
                    value: codmagnitude,
                    text: nomemagnitude
                }));
            }
            $('#campomagnitude').selectpicker('refresh');
        }
    });
}

function filtrarCentroOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcCentro',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor no centor de custo')
            }
        },
        success: function (dados) {
            $("#campocentrodecusto").empty();
            centrofiltradas = dados.split(";");
            $('#campocentrodecusto').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < centrofiltradas.length; i++) {
                var codcentro = centrofiltradas[i].split("-")[0];
                var nomecentro = centrofiltradas[i].split("-")[1];
                $('#campocentrodecusto').append($('<option>', {
                    value: codcentro,
                    text: nomecentro
                }));
            }
            $('#campocentrodecusto').selectpicker('refresh');
        }
    });
}

function filtrarContaOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcConta',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na conta')
            }
        },
        success: function (dados) {
            $("#campoconta").empty();
            contafiltradas = dados.split(";");
            $('#campoconta').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < contafiltradas.length; i++) {
                var codcconta = contafiltradas[i].split("-")[0];
                var nomeconta = contafiltradas[i].split("-")[1];
                $('#campoconta').append($('<option>', {
                    value: codcconta,
                    text: nomeconta
                }));
            }
            $('#campoconta').selectpicker('refresh');
        }
    });
}

function filtrarTabelaOrcamento() {
    var area = document.getElementById("campoarea");
    var areaselecionada = area.options[area.selectedIndex].text;
    var plataforma = document.getElementById("campoplataforma");
    var plataformaselecionada = plataforma.options[plataforma.selectedIndex].text;
    var regiao = document.getElementById("camporegiao");
    var regiaoselecionada = regiao.options[regiao.selectedIndex].text;
    var filial = document.getElementById("campofilial");
    var filialselecionada = filial.options[filial.selectedIndex].text;
    var magnitude = document.getElementById("campomagnitude");
    var magnitudeselecionada = magnitude.options[magnitude.selectedIndex].text;
    var centrodecusto = document.getElementById("campocentrodecusto");
    var centrodecustoselecionada = centrodecusto.options[centrodecusto.selectedIndex].text;
    var conta = document.getElementById("campoconta");
    var contaselecionada = conta.options[conta.selectedIndex].text;
    var oTable = $('#tabelaOrcamento').dataTable();
    oTable.fnFilter(areaselecionada + " " + plataformaselecionada + " " + regiaoselecionada + " "
            + filialselecionada + " " + magnitudeselecionada + " " + centrodecustoselecionada + " " + contaselecionada);
}

function filtrarResponsavel() {
    $.ajax({
        type: 'GET',
        url: '../../ServletResponsavel',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            $("#campoures").empty();
            responsavelfiltradas = dados.split(";");
            $('#campoures').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < responsavelfiltradas.length; i++) {
                var codresp = responsavelfiltradas[i].split("%")[0];
                var nomeresp = responsavelfiltradas[i].split("%")[1];
                $('#campoures').append($('<option>', {
                    value: codresp,
                    text: nomeresp
                }));
            }
            $("#campoures").selectpicker('refresh');
        }
    });
}

function filtrarValores() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroNumeros',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').val()
                + '&idano=' + $('#campoano').val()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");

            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcbdgmes = orcfiltrado[i].split("%")[1];
            var orcrealmes = orcfiltrado[i].split("%")[2];
            var orcdifmes = orcfiltrado[i].split("%")[3];
            var orcbdgacum = orcfiltrado[i].split("%")[4];
            var orcrealacum = orcfiltrado[i].split("%")[5];
            var orcdifacum = orcfiltrado[i].split("%")[6];
            var orcbdgano = orcfiltrado[i].split("%")[7];
            var orcrealano = orcfiltrado[i].split("%")[8];
            var orcdifano = orcfiltrado[i].split("%")[9];

            $('#campobdgmes').countTo({
                from: 0,
                to: orcbdgmes,
                speed: 1000,
                refreshInterval: 20,
                formatter: function (value, options) {
                    return value.toFixed(2).replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                },
                onUpdate: function (value) {
                    console.debug(this);
                },
                onComplete: function (value) {
                    console.debug(this);
                }
            });
            $('#camporealmes').countTo({
                from: 0,
                to: orcrealmes,
                speed: 1000,
                refreshInterval: 20,
                formatter: function (value, options) {
                    return value.toFixed(2).replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                },
                onUpdate: function (value) {
                    console.debug(this);
                },
                onComplete: function (value) {
                    console.debug(this);
                }
            });
            $('#campodifmes').countTo({
                from: 0,
                to: orcdifmes,
                speed: 1000,
                refreshInterval: 20,
                formatter: function (value, options) {
                    return value.toFixed(2).replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                },
                onUpdate: function (value) {
                    console.debug(this);
                },
                onComplete: function (value) {
                    console.debug(this);
                }
            });
            $('#campobdgacum').countTo({
                from: 0,
                to: orcbdgacum,
                speed: 1000,
                refreshInterval: 20,
                formatter: function (value, options) {
                    return value.toFixed(2).replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                },
                onUpdate: function (value) {
                    console.debug(this);
                },
                onComplete: function (value) {
                    console.debug(this);
                }
            });
            $('#camporealacum').countTo({
                from: 0,
                to: orcrealacum,
                speed: 1000,
                refreshInterval: 20,
                formatter: function (value, options) {
                    return value.toFixed(2).replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                },
                onUpdate: function (value) {
                    console.debug(this);
                },
                onComplete: function (value) {
                    console.debug(this);
                }
            });
            $('#campodifacum').countTo({
                from: 0,
                to: orcdifacum,
                speed: 1000,
                refreshInterval: 20,
                formatter: function (value, options) {
                    return value.toFixed(2).replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                },
                onUpdate: function (value) {
                    console.debug(this);
                },
                onComplete: function (value) {
                    console.debug(this);
                }
            });
            $('#campoorcadoanual').countTo({
                from: 0,
                to: orcbdgano,
                speed: 1000,
                refreshInterval: 20,
                formatter: function (value, options) {
                    return value.toFixed(2).replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                },
                onUpdate: function (value) {
                    console.debug(this);
                },
                onComplete: function (value) {
                    console.debug(this);
                }
            });
            $('#camposaldoanual').countTo({
                from: 0,
                to: orcdifano,
                speed: 1000,
                refreshInterval: 20,
                formatter: function (value, options) {
                    return value.toFixed(2).replace(/./g, function (c, i, a) {
                        return i && c !== "." && ((a.length - i) % 3 === 0) ? ',' + c : c;
                    });
                },
                onUpdate: function (value) {
                    console.debug(this);
                },
                onComplete: function (value) {
                    console.debug(this);
                }
            });
        }
    });
}

function filtrarMes() {
    $.ajax({
        type: 'GET',
        url: '../../ServeletFiltroMes',
        data: 'idregiao=' + $('#camporegiao').val() + '&idarea=' + $('#campoarea').val() + '&idplataforma=' + $('#campoplataforma').val() + '&idmagnitude=' + $('#campomagnitude').val() + '&idcentrodecusto=' + $('#campocentrodecusto').val() + '&idconta=' + $('#campoconta').val() + '&idfilial=' + $('#campofilial').val() + '&idtab=' + $('#tabelaOrcamento').val() + '&idmes=' + $('#mes').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            window.location.reload();
        }

    });
}

function filtrarAnalise() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAnalise',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').val()
                + '&idano=' + $('#campoano').val()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            analisefiltrado = dados.split(";");
            
            var i = $('#spinneranalise').val() - 1;
            var cont = analisefiltrado.length;
            var codanalise = analisefiltrado[i].split("#")[0];
            var analiseplataforma = analisefiltrado[i].split("#")[1];
            var analiseregiao = analisefiltrado[i].split("#")[2];
            var analisefilial = analisefiltrado[i].split("#")[3];
            var analisemagnitude = analisefiltrado[i].split("#")[4];
            var analisecentro = analisefiltrado[i].split("#")[5];
            var analiseconta = analisefiltrado[i].split("#")[6];
            var numeroanalises = analisefiltrado[i].split("#")[7];
            var analise = analisefiltrado[i].split("#")[8];
                        
            $('#analisenumero').text(cont);
            $('#analiseregiao').text(analiseregiao);
            $('#analisefilial').text(analisefilial);
            $('#analisemagnitude').text(analisemagnitude);
            $('#analisecentro').text(analisecentro);
            $('#analiseconta').text(analiseconta);
            $('#analiseplataforma').text(analiseplataforma);
            $('#analise').text(analise);
        }
    });
}

function salvarAnalise() {
    $.ajax({
        type: 'GET',
        url: '../../ServletSalvarAnalise',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            var teste = $('#campojustificativa').val();
        }
    });
}

function numeroPendentes() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcPendente',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
                + '&idbdgmes=' + $('#campobdgmes').text()
                + '&idrealmes=' + $('#camporealmes').text()
                + '&idbdgacum=' + $('#campobdgacum').text()
                + '&idrealacum=' + $('#camporealacum').text()
                + '&idjustificativa=' + $('#campojustificativa').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            console.log(dados);
            npendente = dados;
            document.getElementById('numeropendente').innerHTML = npendente + ' - Análises Pendentes';
            document.getElementById('notificacoes').innerHTML = npendente;
        }
    });
}

function filtrarAnoMes() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroAnoMes',
        data: 'idano=' + $('#campoano').val()
                + '&idmes=' + $('#mes').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            window.location.reload();
        }
    });
}