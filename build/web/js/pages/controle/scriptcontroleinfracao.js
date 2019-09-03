$(document).ready(function () {

    filtrarValores();
    numeroPendentes();
    filtrarInfracao();

    $('#campoplaca').on('change', function () {
        filtrarLocadora();
        filtrarCondutor();
        filtrarTabelaInfracao();
        filtrarValores();
        filtrarInfracao();
        $('#spinnerinfracao').val("1");
    });
    $('#campolocadora').on('change', function () {
        filtrarTabelaInfracao();
        filtrarValores();
        filtrarInfracao();
        $('#spinnerinfracao').val("1");
    });
    $('#campocondutor').on('change', function () {
        filtrarTabelaInfracao();
        filtrarValores();
        filtrarInfracao();
        $('#spinnerinfracao').val("1");
    });
    $('#campoid').on('change', function () {
        filtrarTabelaInfracao();
        filtrarValores();
        filtrarInfracao();
        $('#spinnerinfracao').val("1");
    });

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaInfracao',
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
                        var condutor = itemfiltradas[i].split("%")[2];
                        var pontos = itemfiltradas[i].split("%")[3];
                        var condutorident = itemfiltradas[i].split("%")[4];
                        var codigo = itemfiltradas[i].split("%")[5];
                        var data = itemfiltradas[i].split("%")[6];
                        var valor = itemfiltradas[i].split("%")[7];
                        var auto = itemfiltradas[i].split("%")[8];
                        var prazo = itemfiltradas[i].split("%")[9];
                        var chamado = itemfiltradas[i].split("%")[10];
                        var reembolso = itemfiltradas[i].split("%")[11];
                        var id = itemfiltradas[i].split("%")[12];
                        var descricao = itemfiltradas[i].split("%")[13];
                        var observacao = itemfiltradas[i].split("%")[14];
                        $('#campoplaca').val($('option:contains("' + placa + '")').val());
                        $('#campoplaca').selectpicker('refresh');
                        $('#campolocadora').val($('option:contains("' + locadora + '")').val());
                        $('#campolocadora').selectpicker('refresh');
                        $('#campocondutor').val($('option:contains("' + condutor + '")').val());
                        $('#campocondutor').selectpicker('refresh');
                        $('#campopontos').val($('option:contains("' + pontos + '")').val());
                        $('#campopontos').selectpicker('refresh');
                        $('#campocident').val($('option:contains("' + condutorident + '")').val());
                        $('#campocident').selectpicker('refresh');
                        $('#campocodigo').val(codigo);
                        $('#campodata').val(data);
                        $('#campovalor').val(valor);
                        $('#campoauto').val(auto);
                        $('#campoprazo').val(prazo);
                        $('#campochamado').val(chamado);
                        if (reembolso == 'false') {
                            $('#md_checkbox_10').prop('checked', false);
                        } else {
                            $('#md_checkbox_10').prop('checked', true);
                        }
                        $('#campoid').val(id);
                        $('#campoid').selectpicker('refresh');
                        $('#campodescricaomulta').val(descricao);
                        $('#campoobservacao').val(observacao);
                    }
                    filtrarValores();
                    filtrarInfracao();
                    $('#spinnerinfracao').val("1");
                }
            });
        }
    });
});

function limparInfracao() {
    $("#campoplaca").val("");
    $('#campoplaca').selectpicker('refresh');
    $("#campolocadora").val("");
    $('#campolocadora').selectpicker('refresh');
    $("#campocondutor").val("");
    $('#campocondutor').selectpicker('refresh');
    $("#campopontos").val("");
    $('#campopontos').selectpicker('refresh');
    $("#campocident").val("");
    $('#campocident').selectpicker('refresh');
    $("#campocodigo").val("");
    $("#campodata").val("");
    $("#campovalor").val("");
    $("#campoauto").val("");
    $("#campoprazo").val("");
    $("#campochamado").val("");
    $("#campoid").val("");
    $('#campoid').selectpicker('refresh');

    var oTable = $('#tabelaAbastecimento').dataTable();
    oTable.fnFilter("");

    window.location.reload();
}

function filtrarLocadora() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroLocadora',
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
            $("#campolocadora").empty();
            locadorafiltradas = dados.split(";");
            $('#campolocadora').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < locadorafiltradas.length; i++) {
                var codfilial = locadorafiltradas[i].split("-")[0];
                var nomefilial = locadorafiltradas[i].split("-")[1];
                $('#campolocadora').append($('<option>', {
                    value: codfilial,
                    text: nomefilial
                }));
                $('#campolocadora').selectpicker('refresh');
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

function filtrarId() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltrarIdInfracao',
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

function filtrarTabelaInfracao() {
    var placa = document.getElementById("campoplaca");
    var placaselecionada = placa.options[placa.selectedIndex].text;
    var locadora = document.getElementById("campolocadora");
    var locadoraselecionado = locadora.options[locadora.selectedIndex].text;
    var condutor = document.getElementById("campocondutor");
    var condutorselecionado = condutor.options[condutor.selectedIndex].text;
    var pontos = document.getElementById("campopontos");
    var pontosselecionado = pontos.options[pontos.selectedIndex].text;
    var cindent = document.getElementById("campocident");
    var cindentselecionado = cindent.options[cindent.selectedIndex].text;
    var id = document.getElementById("campoid");
    var idselecionado = id.options[id.selectedIndex].text;
    var oTable = $('#tabelaInfracoes').dataTable();
    oTable.fnFilter(placaselecionada + " " + locadoraselecionado + " " + condutorselecionado + " "
            + pontosselecionado + " " + cindentselecionado + " " + idselecionado);
}


function filtrarValores() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroNumerosInfracao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcident=' + $('#campocident').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idpontos=' + $('#campopontos').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text(),
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
            var somavalores = combfiltrado[i].split("%")[1];
            var mediavalores = combfiltrado[i].split("%")[2];
            var somapontos = combfiltrado[i].split("%")[3];
            var mediapontos = combfiltrado[i].split("%")[4];
            $('#campovalormultas').countTo({
                from: 0,
                to: somavalores,
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
            $('#campomediavalormulta').countTo({
                from: 0,
                to: mediavalores,
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
            $('#campomultapontos').countTo({
                from: 0,
                to: somapontos,
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
            $('#campomediapontos').countTo({
                from: 0,
                to: mediapontos,
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

function inserirInfracao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirInfracao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idpontos=' + $('#campopontos').find('option:selected').val()
                + '&idcident=' + $('#campocident').find('option:selected').text()
                + '&idcodigo=' + $('#campocodigo').val()
                + '&iddata=' + $('#campodata').val()
                + '&idvalor=' + $('#campovalor').val()
                + '&idauto=' + $('#campoauto').val()
                + '&idprazo=' + $('#campoprazo').val()
                + '&idchamado=' + $('#campochamado').val()
                + '&idreembolso=' + $('#md_checkbox_10').is(':checked')
                + '&iddescmulta=' + $('#campodescricaomulta').val()
                + '&idobsmulta=' + $('#campoobservacao').val(),

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

function atualizarInfracao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarInfracao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idpontos=' + $('#campopontos').find('option:selected').val()
                + '&idcident=' + $('#campocident').find('option:selected').text()
                + '&idcodigo=' + $('#campocodigo').val()
                + '&iddata=' + $('#campodata').val()
                + '&idvalor=' + $('#campovalor').val()
                + '&idauto=' + $('#campoauto').val()
                + '&idprazo=' + $('#campoprazo').val()
                + '&idchamado=' + $('#campochamado').val()
                + '&idreembolso=' + $('#md_checkbox_10').is(':checked')
                + '&iddescmulta=' + $('#campodescricaomulta').val()
                + '&idobsmulta=' + $('#campoobservacao').val()
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

function excluirInfracao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirInfracao',
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

function filtrarInfracao() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInfracao',
        data: 'idplaca=' + $('#campoplaca').find('option:selected').text()
                + '&idcident=' + $('#campocident').find('option:selected').text()
                + '&idcondutor=' + $('#campocondutor').find('option:selected').text()
                + '&idpontos=' + $('#campopontos').find('option:selected').text()
                + '&idlocadora=' + $('#campolocadora').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            infracaofiltradas = dados.split(";");

            var i = $('#spinnerinfracao').val() - 1;
            var cont = infracaofiltradas.length;
            var codinfracao = infracaofiltradas[i].split("#")[0];
            var infracaolocadora = infracaofiltradas[i].split("#")[1];
            var infracaoplaca = infracaofiltradas[i].split("#")[2];
            var infracaocondutor = infracaofiltradas[i].split("#")[3];
            var infracaoauto = infracaofiltradas[i].split("#")[4];
            var infracaogravidade = infracaofiltradas[i].split("#")[5];
            var infracaoprazo = infracaofiltradas[i].split("#")[6];
            var infracaoobservacao = infracaofiltradas[i].split("#")[7];
            var infracaodescricao = infracaofiltradas[i].split("#")[8];

            document.getElementById('infracaonumero').innerHTML = cont;
            document.getElementById('infracaoplaca').innerHTML = infracaoplaca;
            document.getElementById('infracaolocadora').innerHTML = infracaolocadora;
            document.getElementById('infracaocondutor').innerHTML = infracaocondutor;
            document.getElementById('infracaoauto').innerHTML = infracaoauto;
            document.getElementById('infracaogravidade').innerHTML = infracaogravidade;
            document.getElementById('infracaoprazo').innerHTML = infracaoprazo;
            document.getElementById('infracaoobservacao').innerHTML = infracaoobservacao;
            document.getElementById('infracaodescricao').innerHTML = infracaodescricao;

        }
    });
}