$(document).ready(function () {

    filtrarValores();
    numeroPendentes();

    $('#campoplaca').on('change', function () {
        filtrarCartao();
        filtrarCondutor();
        filtrarCentro();
        filtrarId();
        filtrarTabelaAbastecimento();
        filtrarValores();
    });
    $('#campocartao').on('change', function () {
        filtrarCondutor();
        filtrarCentro();
        filtrarId();
        filtrarTabelaAbastecimento();
        filtrarValores();
    });
    $('#campocondutor').on('change', function () {
        filtrarCentro();
        filtrarId();
        filtrarTabelaAbastecimento();
        filtrarValores();
    });
    $('#campocentro').on('change', function () {
        filtrarId();
        filtrarTabelaAbastecimento();
        filtrarValores();
    });
    $('#campoid').on('change', function () {
        filtrarTabelaAbastecimento();
        filtrarValores();
    });

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        console.log(itemsel);
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaCombustivel',
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
                        var cartao = itemfiltradas[i].split("%")[1];
                        var condutor = itemfiltradas[i].split("%")[2];
                        var centro = itemfiltradas[i].split("%")[3];
                        var km = itemfiltradas[i].split("%")[4];
                        var valor = itemfiltradas[i].split("%")[5];
                        var litros = itemfiltradas[i].split("%")[6];
                        var data = itemfiltradas[i].split("%")[7];
                        var id = itemfiltradas[i].split("%")[8];
                        console.log(id);
                        $('#campoplaca').val($('option:contains("' + placa + '")').val());
                        $('#campoplaca').selectpicker('refresh');
                        $('#campocartao').val($('option:contains("' + cartao + '")').val());
                        $('#campocartao').selectpicker('refresh');
                        $('#campocondutor').val($('option:contains("' + condutor + '")').val());
                        $('#campocondutor').selectpicker('refresh');
                        $('#campocentro').val($('option:contains("' + centro + '")').val());
                        $('#campocentro').selectpicker('refresh');
                        $('#campokm').val(km);
                        $('#campovalor').val(valor);
                        $('#campolitros').val(litros);
                        $('#campodata').val(data);
                        $('#campoid').val(id);
                        $('#campoid').selectpicker('refresh');
                    }
                    filtrarValores();
                }
            });
        }
    });

});

function limparCombustivel() {
    $("#campoplaca").val("");
    $('#campoplaca').selectpicker('refresh');
    $("#campocartao").val("");
    $('#campocartao').selectpicker('refresh');
    $("#campocondutor").val("");
    $('#campocondutor').selectpicker('refresh');
    $("#campocentro").val("");
    $('#campocentro').selectpicker('refresh');
    $("#campoid").val("");
    $('#campoid').selectpicker('refresh');
    $("#campokm").val("");
    $("#campovalor").val("");
    $("#campolitros").val("");
    $("#campodata").val("");
    var oTable = $('#tabelaAbastecimento').dataTable();
    oTable.fnFilter("");

    window.location.reload();
}

function filtrarCartao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroCartao',
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
            $("#campocartao").empty();
            cartaofiltradas = dados.split(";");
            $('#campocartao').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < cartaofiltradas.length; i++) {
                var codfilial = cartaofiltradas[i].split("-")[0];
                var nomefilial = cartaofiltradas[i].split("-")[1];
                $('#campocartao').append($('<option>', {
                    value: codfilial,
                    text: nomefilial
                }));
                $('#campocartao').selectpicker('refresh');
            }
        }
    });
}

function filtrarCondutor() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroCondutor',
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
            $("#campocondutor").empty();
            condutorfiltradas = dados.split(";");
            $('#campocondutor').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < condutorfiltradas.length; i++) {
                var codfilial = condutorfiltradas[i].split("-")[0];
                var nomefilial = condutorfiltradas[i].split("-")[1];
                $('#campocondutor').append($('<option>', {
                    value: codfilial,
                    text: nomefilial
                }));
                $('#campocondutor').selectpicker('refresh');
            }
        }
    });
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

function filtrarTabelaAbastecimento() {
    var placa = document.getElementById("campoplaca");
    var placaselecionada = placa.options[placa.selectedIndex].text;
    var cartao = document.getElementById("campocartao");
    var cartaoselecionado = cartao.options[cartao.selectedIndex].text;
    var condutor = document.getElementById("campocondutor");
    var condutorselecionado = condutor.options[condutor.selectedIndex].text;
    var km = document.getElementById("campokm").value;
    var valor = document.getElementById("campovalor").value;
    var litros = document.getElementById("campolitros").value;
    var data = document.getElementById("campodata").value;
    var id = document.getElementById("campoid").value;
    var oTable = $('#tabelaAbastecimento').dataTable();
    oTable.fnFilter(placaselecionada + " " + cartaoselecionado + " " + condutorselecionado + " "
            + km + " " + valor + " " + litros + " " + data + " " + id);
}


function filtrarValores() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroNumerosCombustivel',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idcentro=' + $('#campocentro').find('option:selected').text()
                + '&iddata=' + $('#campodata').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            combfiltrado = dados.split(";");
            var i = 0;
            var id = combfiltrado[i].split("%")[0];
            var total = combfiltrado[i].split("%")[1];
            var media = combfiltrado[i].split("%")[2];
            $('#campomediamensal').countTo({
                from: 0,
                to: total,
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
            $('#campoacumuladoanual').countTo({
                from: 0,
                to: media,
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

function inserirCombustivel() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirCombustivel',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idcentro=' + $('#campocentro').find('option:selected').text()
                + '&idkm=' + $('#campokm').val()
                + '&idvalor=' + $('#campovalor').val()
                + '&idlitros=' + $('#campolitros').val()
                + '&iddata=' + $('#campodata').val(),

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

function atualizarCombustivel() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarCombustivel',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcartao=' + $('#campocartao').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idcentro=' + $('#campocentro').find('option:selected').text()
                + '&idkm=' + $('#campokm').val()
                + '&idvalor=' + $('#campovalor').val()
                + '&idlitros=' + $('#campolitros').val()
                + '&iddata=' + $('#campodata').val()
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

function excluirCombustivel() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirCombustivel',
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