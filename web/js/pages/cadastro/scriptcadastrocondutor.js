$(document).ready(function () {

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaCondutor',
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
                        var nome = itemfiltradas[i].split("%")[0];
                        var matricula = itemfiltradas[i].split("%")[1];
                        var centro = itemfiltradas[i].split("%")[2];
                        var ibutton = itemfiltradas[i].split("%")[3];
                        var cnh = itemfiltradas[i].split("%")[4];
                        var cargo = itemfiltradas[i].split("%")[5];
                        var ccnh = itemfiltradas[i].split("%")[6];
                        var gsub = itemfiltradas[i].split("%")[7];
                        var greg = itemfiltradas[i].split("%")[8];
                        var gdiv = itemfiltradas[i].split("%")[9];
                        var unidade = itemfiltradas[i].split("%")[10];
                        var empresa = itemfiltradas[i].split("%")[11];
                        var area = itemfiltradas[i].split("%")[12];
                        var status = itemfiltradas[i].split("%")[13];
                        var admissao = itemfiltradas[i].split("%")[14];
                        var defensiva = itemfiltradas[i].split("%")[15];
                        var vencimento = itemfiltradas[i].split("%")[16];
                        var poll = itemfiltradas[i].split("%")[17];
                        var trab = itemfiltradas[i].split("%")[18];
                        var desig = itemfiltradas[i].split("%")[19];
                        var form31 = itemfiltradas[i].split("%")[20];
                        var form32 = itemfiltradas[i].split("%")[21];
                        var aprovado = itemfiltradas[i].split("%")[22];
                        var id = itemfiltradas[i].split("%")[23];
                        console.log(poll);
                        console.log(form32);
                        $('#camponome').val(nome);
                        $('#campomatricula').val(matricula);
                        $('#campocentro').val(centro);
                        $('#campoibutton').val(ibutton);
                        $('#campocnh').val(cnh);
                        $('#campocargo').val(cargo);
                        $('#campocategoria').val($('option:contains("' + ccnh + '")').val());
                        $('#campocategoria').selectpicker('refresh');
                        $('#campogsub').val($('option:contains("' + gsub + '")').val());
                        $('#campogsub').selectpicker('refresh');
                        $('#campogreg').val($('option:contains("' + greg + '")').val());
                        $('#campogreg').selectpicker('refresh');
                        $('#campogdiv').val($('option:contains("' + gdiv + '")').val());
                        $('#campogdiv').selectpicker('refresh');
                        $('#campounidade').val($('option:contains("' + unidade + '")').val());
                        $('#campounidade').selectpicker('refresh');
                        $('#campoempresa').val($('option:contains("' + empresa + '")').val());
                        $('#campoempresa').selectpicker('refresh');
                        $('#campostatus').val($('option:contains("' + area + '")').val());
                        $('#campostatus').selectpicker('refresh');
                        $('#campoadminissao').val(admissao);
                        $('#campodefensiva').val(defensiva);
                        $('#campovencimentocnh').val(vencimento);
                        if (poll == 'false') {
                            $('#md_checkbox_70').prop('checked', false);
                        } else {
                            $('#md_checkbox_70').prop('checked', true);
                        }
                        if (trab == 'false') {
                            $('#md_checkbox_71').prop('checked', false);
                        } else {
                            $('#md_checkbox_71').prop('checked', true);
                        }
                        if (desig == 'false') {
                            $('#md_checkbox_72').prop('checked', false);
                        } else {
                            $('#md_checkbox_72').prop('checked', true);
                        }
                        if (form31 == 'false') {
                            $('#md_checkbox_73').prop('checked', false);
                        } else {
                            $('#md_checkbox_73').prop('checked', true);
                        }
                        if (form32 == 'false') {
                            $('#md_checkbox_74').prop('checked', false);
                        } else {
                            $('#md_checkbox_74').prop('checked', true);
                        }
                        if (aprovado == 'false') {
                            $('#md_checkbox_75').prop('checked', false);
                        } else {
                            $('#md_checkbox_75').prop('checked', true);
                        }
                        $('#campoid').val(id);
                        $('#campoid').selectpicker('refresh');
                    }
                }
            });
        }
    });
});

function limparCondutor() {
    $("#camponome").val("");
    $('#campomatricula').val("");
    $("#campocentro").val("");
    $("#campoibutton").val("");
    $('#campocnh').val("")
    $("#campocargo").val("");
    $("#campocategoria").val("");
    $('#campocategoria').selectpicker('refresh');
    $("#campogsub").val("");
    $('#campogsub').selectpicker('refresh');
    $("#campogreg").val("");
    $('#campogreg').selectpicker('refresh');
    $("#campogdiv").val("");
    $('#campogdiv').selectpicker('refresh');
    $("#campounidade").val("");
    $('#campounidade').selectpicker('refresh');
    $("#campoempresa").val("");
    $('#campoempresa').selectpicker('refresh');
    $("#campoarea").val("");
    $('#campoarea').selectpicker('refresh');
    $("#campostatus").val("");
    $("#campostatus").selectpicker('refresh');
    $("#campoadminissao").val("");
    $("#campodefensiva").val("");
    $("#campovencimentocnh").val("");
    $("#campoid").val("");
    $("#campoid").selectpicker('refresh');
    var oTable = $('#tabelacondutor').dataTable();
    oTable.fnFilter("");

    window.location.reload();
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

function inserirCondutor() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirCondutor',
        data: 'idnome=' + $('#camponome').val()
                + '&idmatricula=' + $('#campomatricula').val()
                + '&idcentro=' + $('#campocentro').val()
                + '&idibutton=' + $('#campoibutton').val()
                + '&idcnh=' + $('#campocnh').val()
                + '&idcargo=' + $('#campocargo').val()
                + '&idcategoria=' + $('#campocategoria').find('option:selected').text()
                + '&idgsub=' + $('#campogsub').find('option:selected').text()
                + '&idgreg=' + $('#campogreg').find('option:selected').text()
                + '&idgdiv=' + $('#campogdiv').find('option:selected').text()
                + '&idunidade=' + $('#campounidade').find('option:selected').text()
                + '&idempresa=' + $('#campoempresa').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idstatus=' + $('#campostatus').find('option:selected').text()
                + '&idadmissao=' + $('#campoadminissao').val()
                + '&iddefensiva=' + $('#campodefensiva').val()
                + '&idvencimento=' + $('#campovencimentocnh').val()
                + '&idpoll=' + $('#md_checkbox_70').is(':checked')
                + '&idtrab=' + $('#md_checkbox_71').is(':checked')
                + '&iddesig=' + $('#md_checkbox_72').is(':checked')
                + '&id31=' + $('#md_checkbox_73').is(':checked')
                + '&id32=' + $('#md_checkbox_74').is(':checked')
                + '&idaprovado=' + $('#md_checkbox_75').is(':checked')
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
//            console.log(dados);
//            window.location.reload();
        }
    });
}

function atualizarCondutor() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarCondutor',
        data: 'idnome=' + $('#camponome').val()
                + '&idmatricula=' + $('#campomatricula').val()
                + '&idcentro=' + $('#campocentro').val()
                + '&idibutton=' + $('#campoibutton').val()
                + '&idcnh=' + $('#campocnh').val()
                + '&idcargo=' + $('#campocargo').val()
                + '&idcategoria=' + $('#campocategoria').find('option:selected').text()
                + '&idgsub=' + $('#campogsub').find('option:selected').text()
                + '&idgreg=' + $('#campogreg').find('option:selected').text()
                + '&idgdiv=' + $('#campogdiv').find('option:selected').text()
                + '&idunidade=' + $('#campounidade').find('option:selected').text()
                + '&idempresa=' + $('#campoempresa').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idstatus=' + $('#campostatus').find('option:selected').text()
                + '&idadmissao=' + $('#campoadminissao').val()
                + '&iddefensiva=' + $('#campodefensiva').val()
                + '&idvencimento=' + $('#campovencimentocnh').val()
                + '&idpoll=' + $('#md_checkbox_70').is(':checked')
                + '&idtrab=' + $('#md_checkbox_71').is(':checked')
                + '&iddesig=' + $('#md_checkbox_72').is(':checked')
                + '&id31=' + $('#md_checkbox_73').is(':checked')
                + '&id32=' + $('#md_checkbox_74').is(':checked')
                + '&idaprovado=' + $('#md_checkbox_75').is(':checked')
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

function excluirCondutor() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirCondutor',
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