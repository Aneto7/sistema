$(document).ready(function () {
    numeroPendentes();
    
    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaFilial',
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
                        var filial = itemfiltradas[i].split("%")[0];
                        var regiao = itemfiltradas[i].split("%")[1];
                        var tipo = itemfiltradas[i].split("%")[2];
                        var status = itemfiltradas[i].split("%")[3];
                        var gsub = itemfiltradas[i].split("%")[4];
                        var greg = itemfiltradas[i].split("%")[5];
                        var gdiv = itemfiltradas[i].split("%")[6];
                        var id = itemfiltradas[i].split("%")[7];
                        $('#campofilial').val(filial);
                        $('#camporegiao').val(regiao);
                        $('#campotipofilial').val(tipo);
                        $('#campostatusfilial').val(status);
                        $('#campogsub').val(gsub);
                        $('#campogreg').val(greg);
                        $('#campogdiv').val(gdiv);
                        $('#campoid').val($('option:contains("' + id + '")').val());
                        $('#campoid').selectpicker('refresh');
                    }
                }
            });
        }
    });
});

function limparFilial() {
    $("#campofilial").val("");
    $('#camporegiao').val("");
    $("#campotipofilial").val("");
    $("#campostatusfilial").val("");
    $("#campogsub").val("");
    $("#campogreg").val("");
    $("#campogdiv").val("");
    $("#campoid").val("");
    $('#campoid').selectpicker('refresh');
    var oTable = $('#tabelaFilial').dataTable();
    oTable.fnFilter("");

    window.location.reload();
}

function inserirFilial() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirFilial',
        data: 'idfilial=' + $('#campofilial').val()
                + '&idregiao=' + $('#camporegiao').val()
                + '&idtipo=' + $('#campotipofilial').val()
                + '&idstatus=' + $('#campostatusfilial').val()
                + '&idgsub=' + $('#campogsub').val()
                + '&idgreg=' + $('#campogreg').val()
                + '&idgdiv=' + $('#campogdiv').val()
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

function atualizarFilial() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarFilial',
        data: 'idfilial=' + $('#campofilial').val()
                + '&idregiao=' + $('#camporegiao').val()
                + '&idtipo=' + $('#campotipofilial').val()
                + '&idstatus=' + $('#campostatusfilial').val()
                + '&idgsub=' + $('#campogsub').val()
                + '&idgreg=' + $('#campogreg').val()
                + '&idgdiv=' + $('#campogdiv').val()
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

function excluirFilial() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirFilial',
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
