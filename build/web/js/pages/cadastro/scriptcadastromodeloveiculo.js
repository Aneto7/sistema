$(document).ready(function () {

    numeroPendentes();

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaTipoVeiculo',
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
                        var tipo = itemfiltradas[i].split("%")[0];
                        var modelo = itemfiltradas[i].split("%")[1];
                        var id = itemfiltradas[i].split("%")[2];
                        $('#campotipoveic').val(tipo);
                        $('#campomodeloveic').val(modelo);
                        $('#campoid').val($('option:contains("' + id + '")').val());
                        $('#campoid').selectpicker('refresh');
                    }
                }
            });
        }
    });
});

function limparModelo() {
    $("#campotipoveic").val("");
    $("#campomodeloveic").val("");
    $("#campoid").val("");
    $('#campoid').selectpicker('refresh');
    var oTable = $('#tabelaVeiculo').dataTable();
    oTable.fnFilter("");

    window.location.reload();
}

function inserirModelo() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirModeloVeiculo',
        data: 'idtipo=' + $('#campotipoveic').val()
                + '&idmodelo=' + $('#campomodeloveic').val(),

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

function atualizarModelo() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarModeloVeiculo',
        data: 'idtipo=' + $('#campotipoveic').val()
                + '&idmodelo=' + $('#campomodeloveic').val()
                + '&idid=' + $('#campoid').find('option:selected').text(),

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

function excluirModelo() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirModeloVeiculo',
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