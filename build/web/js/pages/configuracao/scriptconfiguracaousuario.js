$(document).ready(function () {

    numeroPendentes();
    $('#camposetor').on('change', function () {
        filtrarTabelaUsuario();
    });
    $('#campoperfil').on('change', function () {
        filtrarTabelaUsuario();
    });
    $('#camporegiao').on('change', function () {
        filtrarTabelaUsuario();
    });
    $('#campofilial').on('change', function () {
        filtrarTabelaUsuario();
    });
    $('#campoarea').on('change', function () {
        filtrarTabelaUsuario();
    });

    $('tr').click(function () {

        var itemsel = $(this).find('td[data-name]').data('name');
        if (itemsel > 0) {
            $.ajax({
                type: 'GET',
                url: '../../ServletConsultaItemTabelaUsuario',
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
                        var usuario = itemfiltradas[i].split("%")[0];
                        var nome = itemfiltradas[i].split("%")[1];
                        var email = itemfiltradas[i].split("%")[2];
                        var setor = itemfiltradas[i].split("%")[3];
                        var perfil = itemfiltradas[i].split("%")[4];
                        var regiao = itemfiltradas[i].split("%")[5];
                        var filial = itemfiltradas[i].split("%")[6];
                        var area = itemfiltradas[i].split("%")[7];
                        var id = itemfiltradas[i].split("%")[8];
                        $('#campousuario').val(usuario);
                        $('#camponome').val(nome);
                        $('#campoemail').val(email);
                        $('#camposetor').val($('option:contains("' + setor + '")').val());
                        $('#camposetor').selectpicker('refresh');
                        $('#campoperfil').val($('option:contains("' + perfil + '")').val());
                        $('#campoperfil').selectpicker('refresh');
                        $('#camporegiao').val($('option:contains("' + regiao + '")').val());
                        $('#camporegiao').selectpicker('refresh');
                        $('#campofilial').val($('option:contains("' + filial + '")').val());
                        $('#campofilial').selectpicker('refresh');
                        $('#campoarea').val($('option:contains("' + area + '")').val());
                        $('#campoarea').selectpicker('refresh');
                        $('#campoid').val($('option:contains("' + id + '")').val());
                        $('#campoid').selectpicker('refresh');
                    }
                }
            });
        }
    });

});
function limparUsuario() {
    $("#campousuario").val("");
    $('#camponome').val("");
    $("#campoemail").val("");
    $("#camposetor").val("");
    $('#camposetor').selectpicker('refresh');
    $("#campoperfil").val("");
    $('#campoperfil').selectpicker('refresh');
    $("#camporegiao").val("");
    $('#camporegiao').selectpicker('refresh');
    $("#campofilial").val("");
    $('#campofilial').selectpicker('refresh');
    $("#campoarea").val("");
    $('#campoarea').selectpicker('refresh');
    var oTable = $('#tabelausuario').dataTable();
    oTable.fnFilter("");
    window.location.reload();
}

function filtrarFilial() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroFilial',
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

function filtrarTabelaUsuario() {
    var setor = document.getElementById("camposetor");
    var setorselecionada = setor.options[setor.selectedIndex].text;
    var perfil = document.getElementById("campoperfil");
    var perfilselecionada = perfil.options[perfil.selectedIndex].text;
    var regiao = document.getElementById("camporegiao");
    var regiaoselecionada = regiao.options[regiao.selectedIndex].text;
    var filial = document.getElementById("campofilial");
    var filialselecionada = filial.options[filial.selectedIndex].text;
    var area = document.getElementById("campoarea");
    var areaselecionada = area.options[area.selectedIndex].text;
    var oTable = $('#tabelausuario').dataTable();
    oTable.fnFilter(setorselecionada + " " + perfilselecionada + " " + regiaoselecionada + " " + filialselecionada + " " + areaselecionada);
}

function inserirUsuario() {
    $.ajax({
        type: 'GET',
        url: '../../ServletInserirUsuario',
        data: 'idusuario=' + $('#campousuario').val()
                + '&idnome=' + $('#camponome').val()
                + '&idemail=' + $('#campoemail').val()
                + '&idsetor=' + $('#camposetor').find('option:selected').text()
                + '&idperfil=' + $('#campoperfil').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idsenha=' + $('#password').val
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

function atualizarUsuario() {
    $.ajax({
        type: 'GET',
        url: '../../ServletAtualizarUsuario',
        data: 'idusuario=' + $('#campousuario').val()
                + '&idnome=' + $('#camponome').val()
                + '&idemail=' + $('#campoemail').val()
                + '&idsetor=' + $('#camposetor').find('option:selected').text()
                + '&idperfil=' + $('#campoperfil').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idsenha=' + $('#password').val
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

function excluirUsuario() {
    $.ajax({
        type: 'GET',
        url: '../../ServletExcluirUsuario',
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