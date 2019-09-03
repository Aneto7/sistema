$(document).ready(function () {

    filtrarValores();
    numeroPendentes();

    $('#campoplaca').on('change', function () {
        filtrarTabelaManutencao();
        filtrarValores();
        filtrarManutencao();
        $('#spinnermanutencao').val("1");
    });

    $('#campogrupomanutencao').on('change', function () {
        filtrarTabelaManutencao();
        filtrarValores();
        filtrarManutencao();
        $('#spinnermanutencao').val("1");
    });

    $('#campoorcamento').on('change', function () {
        filtrarTabelaManutencao();
        filtrarValores();
        filtrarManutencao();
        $('#spinnermanutencao').val("1");
    });

    $('#campoid').on('change', function () {
        filtrarTabelaManutencao();
        filtrarValores();
        filtrarManutencao();
        $('#spinnermanutencao').val("1");
    });

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaManutencao',
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
                        var grupo = itemfiltradas[i].split("%")[1];
                        var orcamento = itemfiltradas[i].split("%")[2];
                        var inicio = itemfiltradas[i].split("%")[3];
                        var fim = itemfiltradas[i].split("%")[4];
                        var valor = itemfiltradas[i].split("%")[5];
                        var carro = itemfiltradas[i].split("%")[6];
                        var id = itemfiltradas[i].split("%")[7];
                        var detalhe = itemfiltradas[i].split("%")[8];
                        $('#campoplaca').val($('option:contains("' + placa + '")').val());
                        $('#campoplaca').selectpicker('refresh');
                        $('#campogrupomanutencao').val($('option:contains("' + grupo + '")').val());
                        $('#campogrupomanutencao').selectpicker('refresh');
                        $('#campoorcamento').val($('option:contains("' + orcamento + '")').val());
                        $('#campoorcamento').selectpicker('refresh');
                        $('#campodatainicio').val(inicio);
                        $('#campodatafim').val(fim);
                        $('#campovalor').val(valor);
                        if (carro == 'false') {
                            $('#md_checkbox_10').prop('checked', false);
                        } else {
                            $('#md_checkbox_10').prop('checked', true);
                        }
                        $('#campoid').val(id);
                        $('#campoid').selectpicker('refresh');
                        $('#campodetalhemanutencao').val(detalhe);
                    }
                    filtrarValores();
                    filtrarManutencao();
                    $('#spinnermanutencao').val("1");
                }
            });
        }
    });

});

function limparManutencao() {
    $("#campoplaca").val("");
    $('#campoplaca').selectpicker('refresh');
    $("#campogrupomanutencao").val("");
    $('#campogrupomanutencao').selectpicker('refresh');
    $("#campoorcamento").val("");
    $('#campoorcamento').selectpicker('refresh');
    $("#campodatainicio").val("");
    $("#campodatafim").val("");
    $('#campovalor').val("");
    $("#campoid").val("");
    $('#campoid').selectpicker('refresh');
    $('#campodetalhemanutencao').val("");

    var oTable = $('#tabelaManutencao').dataTable();
    oTable.fnFilter("");

    window.location.reload();
}

function filtrarId() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltrarIdManutencao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idgrupomanutencao=' + $('#campogrupomanutencao').find('option:selected').text()
                + '&idorcamento=' + $('#campoorcamento').find('option:selected').text()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idvalor=' + $('#campovalor').val()
                + '&idcarroreserva=' + $('#md_checkbox_10').is(':checked')
                + '&iddetalhemanutencao=' + $('#campodetalhemanutencao').val()
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

function filtrarTabelaManutencao() {
    var placa = document.getElementById("campoplaca");
    var placaselecionada = placa.options[placa.selectedIndex].text;
    var grupomanutencao = document.getElementById("campogrupomanutencao");
    var grupomanutencaoselecionado = grupomanutencao.options[grupomanutencao.selectedIndex].text;
    var orcamento = document.getElementById("campoorcamento");
    var orcamentoselecionado = orcamento.options[orcamento.selectedIndex].text;
    var id = document.getElementById("campoid");
    var idselecionado = id.options[id.selectedIndex].text;
    var oTable = $('#tabelaManutencao').dataTable();
    oTable.fnFilter(placaselecionada + " " + grupomanutencaoselecionado + " " + orcamentoselecionado + " " + idselecionado);
}


function filtrarValores() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroNumerosManutencao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idgrupomanutencao=' + $('#campogrupomanutencao').find('option:selected').text()
                + '&idorcamento=' + $('#campoorcamento').find('option:selected').text()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idvalor=' + $('#campovalor').val()
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
            manutencaofiltrado = dados.split(";");
            var i = 0;
            var id = manutencaofiltrado[i].split("%")[0];
            var soma = manutencaofiltrado[i].split("%")[1];
            var media = manutencaofiltrado[i].split("%")[2];
            $('#campoultimamanutencao').countTo({
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
            $('#camposomamanutencao').countTo({
                from: 0,
                to: soma,
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

function inserirManutencao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirManutencao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idgrupomanutencao=' + $('#campogrupomanutencao').find('option:selected').text()
                + '&idorcamento=' + $('#campoorcamento').find('option:selected').text()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idvalor=' + $('#campovalor').val()
                + '&idcarroreserva=' + $('#md_checkbox_10').is(':checked')
                + '&iddetalhemanutencao=' + $('#campodetalhemanutencao').val()
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

function atualizarManutencao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarManutencao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idgrupomanutencao=' + $('#campogrupomanutencao').find('option:selected').text()
                + '&idorcamento=' + $('#campoorcamento').find('option:selected').text()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idvalor=' + $('#campovalor').val()
                + '&idcarroreserva=' + $('#md_checkbox_10').is(':checked')
                + '&iddetalhemanutencao=' + $('#campodetalhemanutencao').val()
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

function excluirManutencao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirManutencao',
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
                + '&idvalor=' + $('#campovalor').val()
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

function filtrarManutencao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletManutencao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idgrupomanutencao=' + $('#campogrupomanutencao').find('option:selected').text()
                + '&idorcamento=' + $('#campoorcamento').find('option:selected').text()
                + '&iddatainicio=' + $('#campodatainicio').val()
                + '&iddatafim=' + $('#campodatafim').val()
                + '&idvalor=' + $('#campovalor').val()
                + '&idcarroreserva=' + $('#md_checkbox_10').is(':checked')
                + '&iddetalhemanutencao=' + $('#campodetalhemanutencao').val()
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
            manutencaofiltradas = dados.split(";");

            var i = $('#spinnermanutencao').val() - 1;
            var cont = manutencaofiltradas.length;
            var codmanutencao = manutencaofiltradas[i].split("#")[0];
            var manutencaoplaca = manutencaofiltradas[i].split("#")[1];
            var manutencaogrupo = manutencaofiltradas[i].split("#")[2];
            var manutencaoorcamento = manutencaofiltradas[i].split("#")[3];
            var manutencaodatainicio = manutencaofiltradas[i].split("#")[4];
            var manutencaodatafim = manutencaofiltradas[i].split("#")[5];
            var manutencaovalor = manutencaofiltradas[i].split("#")[6];
            var manutencaocarroreserca = manutencaofiltradas[i].split("#")[7];
            var manutencaodetalhes = manutencaofiltradas[i].split("#")[8];

            document.getElementById('manutencaonumero').innerHTML = cont;
            document.getElementById('manutencaoplaca').innerHTML = manutencaoplaca;
            document.getElementById('manutencaoinicio').innerHTML = manutencaodatainicio;
            document.getElementById('manutencaofim').innerHTML = manutencaodatafim;
            document.getElementById('manutencaogrupoorcamento').innerHTML = manutencaogrupo;
            document.getElementById('manutencaovalor').innerHTML = manutencaovalor;
            document.getElementById('manutencaoorcamento').innerHTML = manutencaoorcamento;
            document.getElementById('manutencaodetalhe').innerHTML = manutencaodetalhes;

        }
    });
}