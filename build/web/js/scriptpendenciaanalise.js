$(document).ready(function () {
    var perfillog = $('#perfillogado').text();
    if (perfillog !== "Operacional") {
        filtrarFilialOrcamento();
    }
    filtrarAnalise();
    numeroPendentes();

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
    });
    $('#campofilial').on('change', function () {
        filtrarMagnitudeOrcamento();
        filtrarCentroOrcamento();
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarTabelaOrcamento();
        filtrarAnalise();
    });
    $('#campomagnitude').on('change', function () {
        filtrarCentroOrcamento();
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });
    $('#campocentrodecusto').on('change', function () {
        filtrarContaOrcamento();
        filtrarResponsavel();
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });
    $('#campoconta').on('change', function () {
        filtrarResponsavel();
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
        filtrarTabelaOrcamento();
        filtrarAnalise();
        $('#spinneranalise').val("1");
    });

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        var nomeid = $(this).find('td[data-name]').data('name');
        console.log(nomeid);
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaAnalise',
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
                        $('#campoidpendencia').val($('option:contains("' + nomeid + '")').val());
                        $('#campoidpendencia').selectpicker('refresh');
                    }
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
        url: '../../ServletAnalisePendente',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idid=' + $('#campoidpendencia').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
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

            document.getElementById('analisenumero').innerHTML = cont;
            document.getElementById('analise').innerHTML = analise;
            document.getElementById('analiseregiao').innerHTML = analiseregiao;
            document.getElementById('analisefilial').innerHTML = analisefilial;
            document.getElementById('analisemagnitude').innerHTML = analisemagnitude;
            document.getElementById('analisecentro').innerHTML = analisecentro;
            document.getElementById('analiseconta').innerHTML = analiseconta;
            document.getElementById('analiseplataforma').innerHTML = analiseplataforma;
        }
    });
}

function salvarAnalise() {
    $.ajax({
        type: 'GET',
        url: '../../ServletSalvarAnalisePendente',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idid=' + $('#campoidpendencia').find('option:selected').text()
                + '&idtipojus=' + $('#tipojus').find('option:selected').text()
                + '&idresponsavel=' + $('#campoures').find('option:selected').text()
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
            var teste = $('#campojustificativa').val();
            console.log(dados);
            console.log(teste);
            window.location.reload();
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
            npendente = dados;
            document.getElementById('numeropendente').innerHTML = npendente + ' - Análises Pendentes';
            document.getElementById('notificacoes').innerHTML = npendente;
        }
    });
}