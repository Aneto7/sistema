$(document).ready(function () {
//    filtrarValores();
//    inserirValores();
//    listarClientes();
//    listarProduto();
//    listarFornecedor();
//    pegarano();
//    getMorris('line', 'graficolinha');

    function pegarano() {
        var now = new Date;
        var anoatual = now.getFullYear();
        $('#ano').val(anoatual);
        return inserirValores(), getMorris('line', 'graficolinha');
    }

    $('#cliente').on('change', function () {
        inserirValores();
        upGraficos();
    });
    $('#produto').on('change', function () {
        inserirValores();
        upGraficos();
    });
    $('#fornecedor').on('change', function () {
        inserirValores();
        upGraficos();
    });

    $('#ano').on('change', function () {
        inserirValores();
        upGraficos();
    });


});


function getMorris(type, element) {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroNumeros',
        data: {
            idano: 2019,
            idcliente: $('#cliente').find('option:selected').text(),
            idproduto: $('#produto').find('option:selected').text(),
            idfornecedor: $('#fornecedor').find('option:selected').text()
        },
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var ano = $('#ano').val();
            var receitajan = orcfiltrado[i].split("#")[0];
            var receitafev = orcfiltrado[i].split("#")[1];
            var receitamar = orcfiltrado[i].split("#")[2];
            var receitaabr = orcfiltrado[i].split("#")[3];
            var receitamai = orcfiltrado[i].split("#")[4];
            var receitajun = orcfiltrado[i].split("#")[5];
            var receitajul = orcfiltrado[i].split("#")[6];
            var receitaago = orcfiltrado[i].split("#")[7];
            var receitaset = orcfiltrado[i].split("#")[8];
            var receitaout = orcfiltrado[i].split("#")[9];
            var receitanov = orcfiltrado[i].split("#")[10];
            var receitadez = orcfiltrado[i].split("#")[11];
            var despesajan = orcfiltrado[i].split("#")[12];
            var despesafev = orcfiltrado[i].split("#")[13];
            var despesamar = orcfiltrado[i].split("#")[14];
            var despesaabr = orcfiltrado[i].split("#")[15];
            var despesamai = orcfiltrado[i].split("#")[16];
            var despesajun = orcfiltrado[i].split("#")[17];
            var despesajul = orcfiltrado[i].split("#")[18];
            var despesaago = orcfiltrado[i].split("#")[19];
            var despesaset = orcfiltrado[i].split("#")[20];
            var despesaout = orcfiltrado[i].split("#")[21];
            var despesanov = orcfiltrado[i].split("#")[22];
            var despesadez = orcfiltrado[i].split("#")[23];
            var recejan = orcfiltrado[i].split("#")[24];
            var recefev = orcfiltrado[i].split("#")[25];
            var recemar = orcfiltrado[i].split("#")[26];
            var receabr = orcfiltrado[i].split("#")[27];
            var recemai = orcfiltrado[i].split("#")[28];
            var recejun = orcfiltrado[i].split("#")[29];
            var recejul = orcfiltrado[i].split("#")[30];
            var receago = orcfiltrado[i].split("#")[31];
            var receset = orcfiltrado[i].split("#")[32];
            var receout = orcfiltrado[i].split("#")[33];
            var recenov = orcfiltrado[i].split("#")[34];
            var recedez = orcfiltrado[i].split("#")[35];
            var despjan = orcfiltrado[i].split("#")[36];
            var despfev = orcfiltrado[i].split("#")[37];
            var despmar = orcfiltrado[i].split("#")[38];
            var despabr = orcfiltrado[i].split("#")[39];
            var despmai = orcfiltrado[i].split("#")[40];
            var despjun = orcfiltrado[i].split("#")[41];
            var despjul = orcfiltrado[i].split("#")[42];
            var despago = orcfiltrado[i].split("#")[43];
            var despset = orcfiltrado[i].split("#")[44];
            var despout = orcfiltrado[i].split("#")[45];
            var despnov = orcfiltrado[i].split("#")[46];
            var despdez = orcfiltrado[i].split("#")[47];
            var receita = orcfiltrado[i].split("#")[48];
            var despesa = orcfiltrado[i].split("#")[49];
            var rece = orcfiltrado[i].split("#")[50];
            var desp = orcfiltrado[i].split("#")[51];

            if (type === 'line') {
                Morris.Line({
                    element: element,
                    data: [{
                            'period': ano + '-12',
                            'licensed': receitadez,
                            'sorned': despesadez,
                            'planrec': recedez,
                            'plandesp': despdez
                        }, {
                            'period': ano + '-11',
                            'licensed': receitanov,
                            'sorned': despesanov,
                            'planrec': recenov,
                            'plandesp': despnov
                        }, {
                            'period': ano + '-10',
                            'licensed': receitaout,
                            'sorned': despesaout,
                            'planrec': receout,
                            'plandesp': despout
                        }, {
                            'period': ano + '-09',
                            'licensed': receitaset,
                            'sorned': despesaset,
                            'planrec': receset,
                            'plandesp': despset
                        }, {
                            'period': ano + '-08',
                            'licensed': receitaago,
                            'sorned': despesaago,
                            'planrec': receago,
                            'plandesp': despago
                        }, {
                            'period': ano + '-07',
                            'licensed': receitajul,
                            'sorned': despesajul,
                            'planrec': recejul,
                            'plandesp': despjul
                        }, {
                            'period': ano + '-06',
                            'licensed': receitajun,
                            'sorned': despesajun,
                            'planrec': recejun,
                            'plandesp': despjun
                        }, {
                            'period': ano + '-05',
                            'licensed': receitamai,
                            'sorned': despesamai,
                            'planrec': recemai,
                            'plandesp': despmai
                        }, {
                            'period': ano + '-04',
                            'licensed': receitaabr,
                            'sorned': despesaabr,
                            'planrec': receabr,
                            'plandesp': despabr
                        }, {
                            'period': ano + '-03',
                            'licensed': receitamar,
                            'sorned': despesamar,
                            'planrec': recemar,
                            'plandesp': despmar
                        }, {
                            'period': ano + '-02',
                            'licensed': receitafev,
                            'sorned': despesafev,
                            'planrec': recefev,
                            'plandesp': despfev
                        }, {
                            'period': ano + '-01',
                            'licensed': receitajan,
                            'sorned': despesajan,
                            'planrec': recejan,
                            'plandesp': despjan
                        }],
                    xkey: 'period',
                    ykeys: ['licensed', 'sorned', 'planrec', 'plandesp'],
                    labels: ['Receita Faturada', 'Despesa Faturada', 'A Receber', 'A Pagar'],
                    lineColors: ['rgb(33, 150, 243)', 'rgb(244, 67, 54)', 'rgb(105, 226, 255)', 'rgb(255, 89, 131)'],
                    lineWidth: 3
                });
            }
        }
    });
}

function listarClientes() {
    $.ajax({
        type: 'GET',
        url: '../ServletListarCliente',
        data: 'idtipo=filtrar',
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            $("#cliente").empty();
            clientefiltrado = dados.split(";");
            $('#cliente').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < clientefiltrado.length - 1; i++) {
                var id = clientefiltrado[i].split("#")[0];
                var cpf = clientefiltrado[i].split("#")[1];
                var nome = clientefiltrado[i].split("#")[2];
                $('#cliente').append($('<option>', {
                    value: cpf,
                    text: nome
                }));
            }
            $("#cliente").selectpicker('refresh');
        }
    });
}

function listarProduto() {
    $.ajax({
        type: 'GET',
        url: '../ServletListarProdutoAtendimento',
        data: 'idtipo=filtrar',
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            console.log(dados);
            $("#produto").empty();
            servicofiltrado = dados.split(";");
            $('#produto').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < servicofiltrado.length; i++) {
                var id = servicofiltrado[i].split("#")[0];
                var produto = servicofiltrado[i].split("#")[1];
                $('#produto').append($('<option>', {
                    value: produto,
                    text: produto
                }));
            }
            $("#produto").selectpicker('refresh');
        }
    });
}

function listarFornecedor() {
    $.ajax({
        type: 'GET',
        url: '../ServletListarFornecedor',
        data: 'idtipo=filtrar',
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            $("#fornecedor").empty();
            fornecedorfiltrado = dados.split(";");
            $('#fornecedor').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < fornecedorfiltrado.length - 1; i++) {
                var id = fornecedorfiltrado[i].split("#")[0];
                var cnpj = fornecedorfiltrado[i].split("#")[1];
                var nome = fornecedorfiltrado[i].split("#")[2];
                $('#fornecedor').append($('<option>', {
                    value: cnpj,
                    text: nome
                }));
            }
            $("#fornecedor").selectpicker('refresh');
        }
    });
}

function upGraficos() {
    $('#graficolinha').empty();
    getMorris('line', 'graficolinha');
}