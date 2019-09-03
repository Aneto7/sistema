$(document).ready(function () {

    numeroPendentes();
    $('#campoplaca').on('change', function () {
        filtrarTabelaCartaoCar();
    });


    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaCartao',
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
                    console.log(itemfiltradas);
                    for (var i = 0; i < itemfiltradas.length; i++) {
                        var placa = itemfiltradas[i].split("%")[0];
                        console.log(placa);
                        var cartao = itemfiltradas[i].split("%")[1];
                        var descricao = itemfiltradas[i].split("%")[2];
                        var responsavel = itemfiltradas[i].split("%")[3];
                        var status = itemfiltradas[i].split("%")[4];
                        var centro = itemfiltradas[i].split("%")[5];
                        $('#campoplaca').val($('option:contains("' + placa + '")').val());
                        $('#campoplaca').selectpicker('refresh');
                        $('#campocartao').val(cartao);
                        $('#campodescricao').val($('option:contains("' + descricao + '")').val());
                        $('#campodescricao').selectpicker('refresh');
                        $('#camporesponsavel').val(responsavel);
                        $('#campostatus').val($('option:contains("' + status + '")').val());
                        $('#campostatus').selectpicker('refresh');
                        $('#campocentrocusto').val(centro);
                    }
                }
            });
        }
    });


});
function limparCartao() {
    $("#campoplaca").val("");
    $('#campoplaca').selectpicker('refresh');
    $("#campocartao").val("");
    $("#campodescricao").val("");
    $('#campodescricao').selectpicker('refresh');
    $("#camporesponsavel").val("");
    $("#campostatus").val("");
    $('#campostatus').selectpicker('refresh');
    $("#campocentrocusto").val("");
    var oTable = $('#tabelaAbastecimento').dataTable();
    oTable.fnFilter("");
    window.location.reload();
}

function filtrarCentro() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroCentroCom',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idcentro=' + $('#campocentro').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            $("#campocentro").empty();
            centrofiltradas = dados.split(";");
            $('#campocentro').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < centrofiltradas.length; i++) {
                var codfilial = centrofiltradas[i].split("-")[0];
                var nomefilial = centrofiltradas[i].split("-")[1];
                $('#campocentro').append($('<option>', {
                    value: codfilial,
                    text: nomefilial
                }));
                $('#campocentro').selectpicker('refresh');
            }
        }
    });
}

function filtrarId() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltrarIdCombustivel',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idcentro=' + $('#campocentro').find('option:selected').text(),
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

function filtrarTabelaCartaoCar() {
    var placa = document.getElementById("campoplaca");
    var placaselecionada = placa.options[placa.selectedIndex].text;
    var oTable = $('#tabelaCartaoCar').dataTable();
    oTable.fnFilter(placaselecionada);
}

function inserirCartao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirCartaoCar',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').val()
                + '&iddescricao=' + $('#campodescricao').find('option:selected').text()
                + '&idresponsavel=' + $('#camporesponsavel').val()
                + '&idstatus=' + $('#campostatus').find('option:selected').text()
                + '&idcentro=' + $('#campocentrocusto').val(),
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

function atualizarCartao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarCartaoCar',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').val()
                + '&iddescricao=' + $('#campodescricao').find('option:selected').text()
                + '&idresponsavel=' + $('#camporesponsavel').val()
                + '&idstatus=' + $('#campostatus').find('option:selected').text()
                + '&idcentro=' + $('#campocentrocusto').val(),
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

function excluirCartao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirCartaoCar',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text(),

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