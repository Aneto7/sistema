$(document).ready(function () {
    var perfillog = $('#perfillogado').text();
    $('#campoano').on('change', function () {
        upGraficos();
    });
    $('#mes').on('change', function () {
        upGraficos();
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
        upGraficos();
    });
    $('#campoplataforma').on('change', function () {
        if (perfillog !== "Supervisor" && perfillog !== "Operacional") {
            filtrarRegiaoOrcamento();
        }
        if (perfillog !== "Operacional") {
            filtrarFilialOrcamento();
        }
        filtrarMagnitudeOrcamento();
        upGraficos();

    });
    $('#camporegiao').on('change', function () {
        filtrarFilialOrcamento();
        filtrarMagnitudeOrcamento();
        upGraficos();

    });
    $('#campofilial').on('change', function () {
        filtrarMagnitudeOrcamento();
        upGraficos();

    });
    $('#campomagnitude').on('change', function () {
        upGraficos();

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

        filtrarPlataformaOrcamento();
        filtrarMagnitudeOrcamento();
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
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            console.log(dados);
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
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text(),
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
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na plataforma');
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
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text(),
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
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idcentrodecusto=' + $('#campocentrodecusto').find('option:selected').text()
                + '&idconta=' + $('#campoconta').find('option:selected').text()
                + '&idmes=' + $('#mes').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na magnitude');
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

function upGraficos() {
    $('#line_chart').empty();
    $('#bar_chart').empty();
    $('#area_chart').empty();
    $('#donut_chart').empty();
    getMorris('line', 'line_chart');
    getMorris('bar', 'bar_chart');
    getMorris('area', 'area_chart');
    getMorris('donut', 'donut_chart');
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