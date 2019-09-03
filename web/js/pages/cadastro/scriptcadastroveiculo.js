$(document).ready(function () {

    numeroPendentes();

    $('#campofilialveiculo').on('change', function () {
        filtrarTabelaVeiculo();
    });
    $('#campoempresa').on('change', function () {
        filtrarTabelaVeiculo();
    });
    $('#campotipoveiculo').on('change', function () {
        filtrarModeloVeiculo();
        filtrarTabelaVeiculo();

    });
    $('#campomodeloveiculo').on('change', function () {
        filtrarTabelaVeiculo();
    });
    $('#campocondutorcad').on('change', function () {
        filtrarTabelaVeiculo();
    });
    $('#campostatuscad').on('change', function () {
        filtrarTabelaVeiculo();
    });

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaVeiculo',
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
                        var locador = itemfiltradas[i].split("%")[1];
                        var valor = itemfiltradas[i].split("%")[2];
                        var unidade = itemfiltradas[i].split("%")[3];
                        var empresa = itemfiltradas[i].split("%")[4];
                        var ccsoja = itemfiltradas[i].split("%")[5];
                        var ccmilho = itemfiltradas[i].split("%")[6];
                        var inicio = itemfiltradas[i].split("%")[7];
                        var fim = itemfiltradas[i].split("%")[8];
                        var tipo = itemfiltradas[i].split("%")[9];
                        var modelo = itemfiltradas[i].split("%")[10];
                        var classificacao = itemfiltradas[i].split("%")[11];
                        var cartao = itemfiltradas[i].split("%")[12];
                        var condutor = itemfiltradas[i].split("%")[13];
                        var status = itemfiltradas[i].split("%")[14];
                        var id = itemfiltradas[i].split("%")[15];
                        $('#campoplaca').val(placa);
                        $('#campolocador').val(locador);
                        $('#campovalorlocacao').val(valor);
                        $('#campofilialveiculo').val($('option:contains("' + unidade + '")').val());
                        $('#campofilialveiculo').selectpicker('refresh');
                        $('#campoempresa').val($('option:contains("' + empresa + '")').val());
                        $('#campoempresa').selectpicker('refresh');
                        $('#campoccsoja').val(ccsoja);
                        $('#campoccmilho').val(ccmilho);
                        $('#campodatainicio').val(inicio);
                        $('#campodatafim').val(fim);
                        $('#campotipoveiculo').val($('option:contains("' + tipo + '")').val());
                        $('#campotipoveiculo').selectpicker('refresh');
                        $('#campomodeloveiculo').val($('option:contains("' + modelo + '")').val());
                        $('#campomodeloveiculo').selectpicker('refresh');
                        $('#campoclassificacao').val($('option:contains("' + classificacao + '")').val());
                        $('#campoclassificacao').selectpicker('refresh');
                        $('#campocartao').val(cartao);
                        $('#campocondutorcad').val($('option:contains("' + condutor + '")').val());
                        $('#campocondutorcad').selectpicker('refresh');
                        $('#campostatuscad').val($('option:contains("' + status + '")').val());
                        $('#campostatuscad').selectpicker('refresh');
                        $('#campoid').val($('option:contains("' + id + '")').val());
                        $('#campoid').selectpicker('refresh');
                    }
                }
            });
        }
    });
});

function limparVeiculo() {
    $("#campoplaca").val("");
    $('#campolocador').val("");
    $("#campovalorlocacao").val("");
    $("#campofilialveiculo").val("");
    $('#campofilialveiculo').selectpicker('refresh');
    $("#campoempresa").val("");
    $('#campoempresa').selectpicker('refresh');
    $("#campoccsoja").val("");
    $("#campoccmilho").val("");
    $("#campodatainicio").val("");
    $("#campodatafim").val("");
    $("#campotipoveiculo").val("");
    $('#campotipoveiculo').selectpicker('refresh');
    $("#campomodeloveiculo").val("");
    $('#campomodeloveiculo').selectpicker('refresh');
    $("#campoclassificacao").val("");
    $("#campoclassificacao").selectpicker('refresh');
    $("#campocartao").val("");
    $("#campocondutorcad").val("");
    $("#campocondutorcad").selectpicker('refresh');
    $("#campostatuscad").val("");
    $("#campostatuscad").selectpicker('refresh');
    var oTable = $('#tabelaVeiculo').dataTable();
    oTable.fnFilter("");

    window.location.reload();
}

function filtrarModeloVeiculo() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroModeloVeiculo',
        data: 'idplaca=' + $('#campoplaca').val()
                + '&idlocador=' + $('#campolocador').val()
                + '&idvalor=' + $('#campovalorlocacao').val()
                + '&idfilial=' + $('#campofilialveiculo').find('option:selected').text()
                + '&idempresa=' + $('#campoempresa').find('option:selected').text()
                + '&idccsoja=' + $('#campoccsoja').val()
                + '&idccmilho=' + $('#campoccmilho').val()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idtipo=' + $('#campotipoveiculo').find('option:selected').text()
                + '&idmodelo=' + $('#campomodeloveiculo').find('option:selected').text()
                + '&idclassificacao=' + $('#campoclassificacao').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').val()
                + '&idcondutor=' + $('#campocondutorcad').find('option:selected').text()
                + '&idstatus=' + $('#campostatuscad').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            $("#campomodeloveiculo").empty();
            modelofiltradas = dados.split(";");
            $('#campomodeloveiculo').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < modelofiltradas.length; i++) {
                var codfilial = modelofiltradas[i].split("-")[0];
                var nomefilial = modelofiltradas[i].split("-")[1];
                $('#campomodeloveiculo').append($('<option>', {
                    value: codfilial,
                    text: nomefilial
                }));
                $('#campomodeloveiculo').selectpicker('refresh');
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

function filtrarTabelaVeiculo() {
    var filial = document.getElementById("campofilialveiculo");
    var filialselecionada = filial.options[filial.selectedIndex].text;
    var empresa = document.getElementById("campoempresa");
    var empresaselecionada = empresa.options[empresa.selectedIndex].text;
    var tipo = document.getElementById("campotipoveiculo");
    var tiposelecionado = tipo.options[tipo.selectedIndex].text;
    var modelocad = document.getElementById("campomodeloveiculo");
    var modeloselecionado = modelocad.options[modelocad.selectedIndex].text;
    var classificacao = document.getElementById("campoclassificacao");
    var classificacaoselecionado = classificacao.options[classificacao.selectedIndex].text;
    var condutor = document.getElementById("campocondutorcad");
    var condutorselecionado = condutor.options[condutor.selectedIndex].text;
    var status = document.getElementById("campostatuscad");
    var statusselecionado = status.options[status.selectedIndex].text;
    var oTable = $('#tabelaVeiculo').dataTable();
    oTable.fnFilter(filialselecionada + " " + empresaselecionada + " " + tiposelecionado + " "
            + modeloselecionado + " " + classificacaoselecionado + " " + condutorselecionado + " " + statusselecionado);
}

function inserirVeiculo() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirVeiculo',
        data: 'idplaca=' + $('#campoplaca').val()
                + '&idlocador=' + $('#campolocador').val()
                + '&idvalor=' + $('#campovalorlocacao').val()
                + '&idfilial=' + $('#campofilialveiculo').find('option:selected').text()
                + '&idempresa=' + $('#campoempresa').find('option:selected').text()
                + '&idccsoja=' + $('#campoccsoja').val()
                + '&idccmilho=' + $('#campoccmilho').val()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idtipo=' + $('#campotipoveiculo').find('option:selected').text()
                + '&idmodelo=' + $('#campomodeloveiculo').find('option:selected').text()
                + '&idclassificacao=' + $('#campoclassificacao').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').val()
                + '&idcondutor=' + $('#campocondutorcad').find('option:selected').text()
                + '&idstatus=' + $('#campostatuscad').find('option:selected').text(),

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

function atualizarVeiculo() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarVeiculo',
        data: 'idplaca=' + $('#campoplaca').val()
                + '&idlocador=' + $('#campolocador').val()
                + '&idvalor=' + $('#campovalorlocacao').val()
                + '&idfilial=' + $('#campofilialveiculo').find('option:selected').text()
                + '&idempresa=' + $('#campoempresa').find('option:selected').text()
                + '&idccsoja=' + $('#campoccsoja').val()
                + '&idccmilho=' + $('#campoccmilho').val()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idtipo=' + $('#campotipoveiculo').find('option:selected').text()
                + '&idmodelo=' + $('#campomodeloveiculo').find('option:selected').text()
                + '&idclassificacao=' + $('#campoclassificacao').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').val()
                + '&idcondutor=' + $('#campocondutorcad').find('option:selected').text()
                + '&idstatus=' + $('#campostatuscad').find('option:selected').text()
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

function excluirVeiculo() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirVeiculo',
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