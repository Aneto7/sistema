$(document).ready(function () {

    filtrarValores();
    numeroPendentes();

    $('#campoplaca').on('change', function () {
        filtrarTabelaRevisao();
        filtrarValores();
        filtrarRevisao();
        $('#spinnerrevisao').val("1");
    });

    $('#campolocadora').on('change', function () {
        filtrarTabelaRevisao();
        filtrarValores();
        filtrarRevisao();
        $('#spinnerrevisao').val("1");
    });

    $('#campoid').on('change', function () {
        filtrarTabelaRevisao();
        filtrarValores();
        filtrarRevisao();
        $('#spinnerrevisao').val("1");
    });

    $('#campoid').on('change', function () {
        filtrarTabelaRevisao();
        filtrarValores();
        filtrarRevisao();
        $('#spinnerrevisao').val("1");
    });

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaRevisao',
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
                        var placa = itemfiltradas[i].split("%")[0];
                        var locadora = itemfiltradas[i].split("%")[1];
                        var base = itemfiltradas[i].split("%")[2];
                        var data = itemfiltradas[i].split("%")[3];
                        var km = itemfiltradas[i].split("%")[4];
                        var id = itemfiltradas[i].split("%")[5];
                        var detalhe = itemfiltradas[i].split("%")[6];
                        $('#campoplaca').val($('option:contains("' + placa + '")').val());
                        $('#campoplaca').selectpicker('refresh');
                        $('#campolocadora').val($('option:contains("' + locadora + '")').val());
                        $('#campolocadora').selectpicker('refresh');
                        $('#campobase').val(base);
                        $('#campodata').val(data);
                        $('#campokm').val(km);
                        $('#campoid').val(id);
                        $('#campoid').selectpicker('refresh');
                        $('#campodetalherevisao').val(detalhe);
                    }
                    filtrarValores();
                    filtrarRevisao();
                    $('#spinnerrevisao').val("1");
                }
            });
        }
    });

});

function limparRevisao() {
    $("#campoplaca").val("");
    $('#campoplaca').selectpicker('refresh');
    $("#campolocadora").val("");
    $('#campolocadora').selectpicker('refresh');
    $("#campobase").val("");
    $("#campodata").val("");
    $("#campokm").val("");
    $('#campodetalherevisao').val("");

    var oTable = $('#tabelaRevisao').dataTable();
    oTable.fnFilter("");

    window.location.reload();
}

function filtrarId() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltrarIdRevisao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text()
                + '&idbase=' + $('#campobase').text()
                + '&iddata=' + $('#campodata').val()
                + '&idkm=' + $('#campokm').text()
                + '&iddetalhe=' + $('#campodetalherevisao').val()
                + '&idid=' + $('#campoid').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            $("#campoid").empty();
            idfiltrado = dados.split(";");
            $('#campoid').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < idfiltrado.length; i++) {
                var codfilial = idfiltrado[i].split("-")[0];
                var nomefilial = idfiltrado[i].split("-")[1];
                $('#campoid').append($('<option>', {
                    value: codfilial,
                    text: nomefilial
                }));
                $('#campoid').selectpicker('refresh');
            }
        }
    });
}

function filtrarTabelaRevisao() {
    var placa = document.getElementById("campoplaca");
    var placaselecionada = placa.options[placa.selectedIndex].text;
    var locadora = document.getElementById("campolocadora");
    var locadoraselecionada = locadora.options[locadora.selectedIndex].text;
    var id = document.getElementById("campoid");
    var idselecionado = id.options[id.selectedIndex].text;
    var oTable = $('#tabelaRevisao').dataTable();
    oTable.fnFilter(placaselecionada + " " + locadoraselecionada + " " + idselecionado);
}


function filtrarValores() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroNumerosRevisoes',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text()
                + '&idbase=' + $('#campobase').text()
                + '&iddata=' + $('#campodata').val()
                + '&idkm=' + $('#campokm').text()
                + '&iddetalhe=' + $('#campodetalherevisao').val()
                + '&idid=' + $('#campoid').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            revisaofiltrado = dados.split(";");
            var i = 0;
            var id = revisaofiltrado[i].split("%")[0];
            var quantidade = revisaofiltrado[i].split("%")[1];
            var kmatual = revisaofiltrado[i].split("%")[2];
            $('#camporevisoesnum').countTo({
                from: 0,
                to: kmatual,
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
            $('#campokmatual').countTo({
                from: 0,
                to: quantidade,
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

function inserirRevisao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirRevisao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text()
                + '&idbase=' + $('#campobase').val()
                + '&iddata=' + $('#campodata').val()
                + '&idkm=' + $('#campokm').val()
                + '&iddetalhe=' + $('#campodetalherevisao').val()
                + '&idid=' + $('#campoid').val(),

        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
//            window.location.reload();
        }
    });
}

function atualizarRevisao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarRevisao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text()
                + '&idbase=' + $('#campobase').val()
                + '&iddata=' + $('#campodata').val()
                + '&idkm=' + $('#campokm').val()
                + '&iddetalhe=' + $('#campodetalherevisao').val()
                + '&idid=' + $('#campoid').val(),

        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
//            window.location.reload();
        }
    });
}

function excluirRevisao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirRevisao',
        data: 'idid=' + $('#campoid').find('option:selected').text(),

        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
//            window.location.reload();
        }
    });
}

function numeroPendentes() {
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcPendente',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idgrupomanutencao=' + $('#campogrupomanutencao').find('option:selected').text()
                + '&idorcamento=' + $('#campoorcamento').find('option:selected').text()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idvalor=' + $('#campovalor').text()
                + '&idcarroreserva=' + $('#md_checkbox_10').is(':checked')
                + '&iddetalhemanutencao=' + $('#campodetalhemanutencao').val()
                + '&idid=' + $('#campoid').val(),
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

function filtrarRevisao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletRevisao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text()
                + '&idbase=' + $('#campobase').text()
                + '&iddata=' + $('#campodata').val()
                + '&idkm=' + $('#campokm').text()
                + '&iddetalhe=' + $('#campodetalherevisao').val()
                + '&idid=' + $('#campoid').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            revisaofiltradas = dados.split(";");

            var i = $('#spinnerrevisao').val() - 1;
            var cont = revisaofiltradas.length;
            var codmanutencao = revisaofiltradas[i].split("#")[0];
            var revisaoplaca = revisaofiltradas[i].split("#")[1];
            var revisaolocadora = revisaofiltradas[i].split("#")[2];
            var revisaobase = revisaofiltradas[i].split("#")[3];
            var revisaokm = revisaofiltradas[i].split("#")[4];
            var revisaodata = revisaofiltradas[i].split("#")[5];
            var revisaodetalhe = revisaofiltradas[i].split("#")[6];
            var revisaoproxima = revisaofiltradas[i].split("#")[7];

            document.getElementById('revisaonumero').innerHTML = cont;
            document.getElementById('revisaoplaca').innerHTML = revisaoplaca;
            document.getElementById('revisaolocadora').innerHTML = revisaolocadora;
            document.getElementById('revisaobase').innerHTML = revisaobase;
            document.getElementById('revisaokm').innerHTML = revisaokm;
            document.getElementById('revisaodata').innerHTML = revisaodata;
            document.getElementById('revisaoproxima').innerHTML = revisaoproxima;
            document.getElementById('revisaodetalhe').innerHTML = revisaodetalhe;

        }
    });
}