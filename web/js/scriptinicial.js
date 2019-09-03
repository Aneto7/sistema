$(document).ready(function () {
    var perfillog = $('#perfillogado').text();
    numeroPendentes();
    filtrarValores();
    filtrarValoresLabour();
    filtrarValoresOperational();
    filtrarValoresBusiness();
    filtrarGraficos();
    filtrarHC();
    filtrarOutsource();
    filtrarCell();
    filtrarCar();
    filtrarKm();
    filtrarWood();
    filtrarPower();
    filtrarFuel();
//    listarCustoTon();

    $('#mes').on('change', function () {
        filtrarAnoMes();
        filtrarValores();
        filtrarValoresLabour();
        filtrarValoresOperational();
        filtrarValoresBusiness();
    });

    $('#campoano').on('change', function () {
        filtrarAnoMes();
        filtrarValores();
        filtrarValoresLabour();
        filtrarValoresOperational();
        filtrarValoresBusiness();
    });

    $('#campoarea').on('change', function () {
        if (perfillog !== "Supervisor" && perfillog !== "Operacional") {
            filtrarRegiaoOrcamento();
        }
        if (perfillog !== "Operacional") {
            filtrarFilialOrcamento();
        }
        filtrarPlataformaOrcamento();
        filtrarMagnitudeOrcamento();

        filtrarValores();
        filtrarValoresLabour();
        filtrarValoresOperational();
        filtrarValoresBusiness();
        filtrarGraficos();
        filtrarHC();
        filtrarOutsource();
        filtrarCell();
        filtrarCar();
        filtrarKm();
        filtrarWood();
        filtrarPower();
        filtrarFuel();
    });

    $('#campoplataforma').on('change', function () {
        if (perfillog !== "Supervisor" && perfillog !== "Operacional") {
            filtrarRegiaoOrcamento();
        }
        if (perfillog !== "Operacional") {
            filtrarFilialOrcamento();
        }
        filtrarMagnitudeOrcamento();
        filtrarValores();
        filtrarValoresLabour();
        filtrarValoresOperational();
        filtrarValoresBusiness();
        filtrarGraficos();
        filtrarHC();
        filtrarOutsource();
        filtrarCell();
        filtrarCar();
        filtrarKm();
        filtrarWood();
        filtrarPower();
        filtrarFuel();
    });

    $('#camporegiao').on('change', function () {
        filtrarFilialOrcamento();
        filtrarMagnitudeOrcamento();
        filtrarValores();
        filtrarValoresLabour();
        filtrarValoresOperational();
        filtrarValoresBusiness();
        filtrarGraficos();
        filtrarHC();
        filtrarOutsource();
        filtrarCell();
        filtrarCar();
        filtrarKm();
        filtrarWood();
        filtrarPower();
        filtrarFuel();
    });

    $('#campofilial').on('change', function () {
        filtrarMagnitudeOrcamento();
        filtrarValores();
        filtrarValoresLabour();
        filtrarValoresOperational();
        filtrarValoresBusiness();
        filtrarGraficos();
        filtrarHC();
        filtrarOutsource();
        filtrarCell();
        filtrarCar();
        filtrarKm();
        filtrarWood();
        filtrarPower();
        filtrarFuel();
    });

    $('#campomagnitude').on('change', function () {
        filtrarValores();
        filtrarValoresLabour();
        filtrarValoresOperational();
        filtrarValoresBusiness();
        filtrarGraficos();
    });

    $('#md_checkbox_33').on('change', function () {
        filtrarGraficos();
    });
    $('#md_checkbox_34').on('change', function () {
        filtrarGraficos();
    });
    $('#md_checkbox_35').on('change', function () {
        filtrarGraficos();
    });
});

function filtrarValores() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroDashboardInicial',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var realmes = Math.round((parseInt($('#mes').find('option:selected').val()) + 12));

            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[$('#mes').find('option:selected').val()];
            var realmes1 = orcfiltrado[i].split("%")[realmes];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];

            var percentualmes = Math.round((parseFloat(realmes1) / parseFloat(orcmes1)) * 100);
            $('#totalmensal').val(percentualmes);

            var percentualacumulado = Math.round((parseFloat(realacum) / parseFloat(orcacum)) * 100);
            $('#totalacum').val(percentualacumulado);
        }
    });
}

function filtrarValoresLabour() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroDashboardInicial',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo=' + $('#labour').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var orcmes = Math.round(parseInt($('#mes').find('option:selected').val()));
            var realmes = Math.round((parseInt($('#mes').find('option:selected').val()) + 12));
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[orcmes];
            var realmes1 = orcfiltrado[i].split("%")[realmes];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];

            var percentualmes = Math.round((parseFloat(realmes1) / parseFloat(orcmes1)) * 100);
            $('#labourmensal').val(percentualmes);

            var percentualacumulado = Math.round((parseFloat(realacum) / parseFloat(orcacum)) * 100);
            $('#labouracum').val(percentualacumulado);
        }
    });
}

function filtrarValoresOperational() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroDashboardInicial',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo=' + $('#operational').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var orcmes = Math.round(parseInt($('#mes').find('option:selected').val()));
            var realmes = Math.round((parseInt($('#mes').find('option:selected').val()) + 12));
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[orcmes];
            var realmes1 = orcfiltrado[i].split("%")[realmes];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];

            var percentualmes = Math.round((parseFloat(realmes1) / parseFloat(orcmes1)) * 100);
            $('#operationalmensal').val(percentualmes);

            var percentualacumulado = Math.round((parseFloat(realacum) / parseFloat(orcacum)) * 100);
            $('#operationalacum').val(percentualacumulado);
        }
    });
}

function filtrarValoresBusiness() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroDashboardInicial',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo=' + $('#business').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var orcmes = Math.round(parseInt($('#mes').find('option:selected').val()));
            var realmes = Math.round((parseInt($('#mes').find('option:selected').val()) + 12));
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[orcmes];
            var realmes1 = orcfiltrado[i].split("%")[realmes];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];

            var percentualmes = Math.round((parseFloat(realmes1) / parseFloat(orcmes1)) * 100);
            $('#businessmensal').val(percentualmes);

            var percentualacumulado = Math.round((parseFloat(realacum) / parseFloat(orcacum)) * 100);
            $('#businessacum').val(percentualacumulado);
        }
    });
}

function filtrarFilialOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../ServletOrcamentoFilial',
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
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            $("#campofilial").empty();
            filiaisfiltradas = dados.split(";");
            $('#campofilial').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < filiaisfiltradas.length; i++) {
                var codfilial = filiaisfiltradas[i].split("%")[0];
                var nomefilial = filiaisfiltradas[i].split("%")[1];
                $('#campofilial').append($('<option>', {
                    value: codfilial,
                    text: nomefilial
                }));
                $('#campofilial').selectpicker('refresh');
            }
        }
    });
}

function filtrarPlataformaOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../ServletOrcPlataforma',
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
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na plataforma');
            }
        },
        success: function (dados) {
            $("#campoplataforma").empty();
            plataformasfiltradas = dados.split(";");
            $('#campoplataforma').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < plataformasfiltradas.length; i++) {
                var codplataforma = plataformasfiltradas[i].split("-")[0];
                var nomeplataforma = plataformasfiltradas[i].split("-")[1];
                $('#campoplataforma').append($('<option>', {
                    value: codplataforma,
                    text: nomeplataforma
                }));
            }
            $('#campoplataforma').selectpicker('refresh');
        }
    });
}

function filtrarRegiaoOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../ServletOrcRegiao',
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
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na regiao');
            }
        },
        success: function (dados) {
            $("#camporegiao").empty();
            regiaofiltradas = dados.split(";");
            $('#camporegiao').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < regiaofiltradas.length; i++) {
                var codregiao = regiaofiltradas[i].split("/")[0];
                var nomeregiao = regiaofiltradas[i].split("/")[1];
                $('#camporegiao').append($('<option>', {
                    value: codregiao,
                    text: nomeregiao
                }));
            }
            $('#camporegiao').selectpicker('refresh');
        }
    });
}

function filtrarMagnitudeOrcamento() {
    $.ajax({
        type: 'GET',
        url: '../ServletOrcMagnitude',
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
                + '&idjustificativa=' + $('#campojustificativa').text(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor na magnitude');
            }
        },
        success: function (dados) {
            $("#campomagnitude").empty();
            magnitudefiltradas = dados.split(";");
            $('#campomagnitude').append($('<option>', {
                value: 0,
                text: ""
            }));
            for (var i = 0; i < magnitudefiltradas.length; i++) {
                var codmagnitude = magnitudefiltradas[i].split("-")[0];
                var nomemagnitude = magnitudefiltradas[i].split("-")[1];
                $('#campomagnitude').append($('<option>', {
                    value: codmagnitude,
                    text: nomemagnitude
                }));
            }
            $('#campomagnitude').selectpicker('refresh');
        }
    });
}

var data = [], totalPoints = 110;
var updateInterval = 320;
var realtime = 'on';

function filtrarGraficos() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroDashboardInicial',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            var oilprices = [[1, orcmes1], [2, orcmes2], [3, orcmes3], [4, orcmes4], [5, orcmes5], [6, orcmes6], [7, orcmes7], [8, orcmes8], [9, orcmes9], [10, orcmes10], [11, orcmes11], [12, orcmes12]];
            var exchangerates = [[1, realmes1], [2, realmes2], [3, realmes3], [4, realmes4], [5, realmes5], [6, realmes6], [7, realmes7], [8, realmes8], [9, realmes9], [10, realmes10], [11, realmes11], [12, realmes12]];

            function euroFormatter(v, axis) {
                return v.toFixed(axis.tickDecimals) + '€';
            }

            $.plot('#multiple_axis_chart', [
                {data: oilprices, label: 'Orçado', color: '#FF4081'},
                {data: exchangerates, label: 'Realizado', color: '#40C4FF'}
            ], {
                xaxes: [{mode: 'number'}],
                yaxes: [{min: 0}, {
                        alignTicksWithAxis: 1,
                        position: 'right',
                        tickFormatter: euroFormatter
                    }],
                grid: {
                    hoverable: true,
                    autoHighlight: false,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                },
                legend: {position: 'sw'}
            });

            //====================================================================================================

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat((mes1volplan) / orcmes1)]);
            barChartData.push([2, parseFloat((mes2volplan) / orcmes2)]);
            barChartData.push([3, parseFloat((mes3volplan) / orcmes3)]);
            barChartData.push([4, parseFloat((mes4volplan) / orcmes4)]);
            barChartData.push([5, parseFloat((mes5volplan) / orcmes5)]);
            barChartData.push([6, parseFloat((mes6volplan) / orcmes6)]);
            barChartData.push([7, parseFloat((mes7volplan) / orcmes7)]);
            barChartData.push([8, parseFloat((mes8volplan) / orcmes8)]);
            barChartData.push([9, parseFloat((mes9volplan) / orcmes9)]);
            barChartData.push([10, parseFloat((mes10volplan) / orcmes10)]);
            barChartData.push([11, parseFloat((mes11volplan) / orcmes11)]);
            barChartData.push([12, parseFloat((mes12volplan) / orcmes12)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat((mes1volreal) / realmes1)]);
            barChartData1.push([2, parseFloat((mes2volreal) / realmes2)]);
            barChartData1.push([3, parseFloat((mes3volreal) / realmes3)]);
            barChartData1.push([4, parseFloat((mes4volreal) / realmes4)]);
            barChartData1.push([5, parseFloat((mes5volreal) / realmes5)]);
            barChartData1.push([6, parseFloat((mes6volreal) / realmes6)]);
            barChartData1.push([7, parseFloat((mes7volreal) / realmes7)]);
            barChartData1.push([8, parseFloat((mes8volreal) / realmes8)]);
            barChartData1.push([9, parseFloat((mes9volreal) / realmes9)]);
            barChartData1.push([10, parseFloat((mes10volreal) / realmes10)]);
            barChartData1.push([11, parseFloat((mes11volreal) / realmes11)]);
            barChartData1.push([12, parseFloat((mes12volreal) / realmes12)]);

            var data = [
                {
                    label: "Planejado",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Realizado",
                    data: barChartData1,
                    color: '#40C4FF'
                }
            ];


            $.plot('#bar_chart', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}


function filtrarHC() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroHC',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat(orcmes1 / mes1volplan)]);
            barChartData.push([2, parseFloat(orcmes2 / mes2volplan)]);
            barChartData.push([3, parseFloat(orcmes3 / mes3volplan)]);
            barChartData.push([4, parseFloat(orcmes4 / mes4volplan)]);
            barChartData.push([5, parseFloat(orcmes5 / mes5volplan)]);
            barChartData.push([6, parseFloat(orcmes6 / mes6volplan)]);
            barChartData.push([7, parseFloat(orcmes7 / mes7volplan)]);
            barChartData.push([8, parseFloat(orcmes8 / mes8volplan)]);
            barChartData.push([9, parseFloat(orcmes9 / mes9volplan)]);
            barChartData.push([10, parseFloat(orcmes10 / mes10volplan)]);
            barChartData.push([11, parseFloat(orcmes11 / mes11volplan)]);
            barChartData.push([12, parseFloat(orcmes12 / mes12volplan)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat(realmes1 / mes1volreal)]);
            barChartData1.push([2, parseFloat(realmes2 / mes2volreal)]);
            barChartData1.push([3, parseFloat(realmes3 / mes3volreal)]);
            barChartData1.push([4, parseFloat(realmes4 / mes4volreal)]);
            barChartData1.push([5, parseFloat(realmes5 / mes5volreal)]);
            barChartData1.push([6, parseFloat(realmes6 / mes6volreal)]);
            barChartData1.push([7, parseFloat(realmes7 / mes7volreal)]);
            barChartData1.push([8, parseFloat(realmes8 / mes8volreal)]);
            barChartData1.push([9, parseFloat(realmes9 / mes9volreal)]);
            barChartData1.push([10, parseFloat(realmes10 / mes10volreal)]);
            barChartData1.push([11, parseFloat(realmes11 / mes11volreal)]);
            barChartData1.push([12, parseFloat(realmes12 / mes12volreal)]);

            var data = [
                {
                    label: "Planejado",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Realizado",
                    data: barChartData1,
                    color: '#40C4FF'
                }
            ];

            $.plot('#bar_chart1', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}

function filtrarOutsource() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroOutsource',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat(orcmes1)]);
            barChartData.push([2, parseFloat(orcmes2)]);
            barChartData.push([3, parseFloat(orcmes3)]);
            barChartData.push([4, parseFloat(orcmes4)]);
            barChartData.push([5, parseFloat(orcmes5)]);
            barChartData.push([6, parseFloat(orcmes6)]);
            barChartData.push([7, parseFloat(orcmes7)]);
            barChartData.push([8, parseFloat(orcmes8)]);
            barChartData.push([9, parseFloat(orcmes9)]);
            barChartData.push([10, parseFloat(orcmes10)]);
            barChartData.push([11, parseFloat(orcmes11)]);
            barChartData.push([12, parseFloat(orcmes12)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat(realmes1)]);
            barChartData1.push([2, parseFloat(realmes2)]);
            barChartData1.push([3, parseFloat(realmes3)]);
            barChartData1.push([4, parseFloat(realmes4)]);
            barChartData1.push([5, parseFloat(realmes5)]);
            barChartData1.push([6, parseFloat(realmes6)]);
            barChartData1.push([7, parseFloat(realmes7)]);
            barChartData1.push([8, parseFloat(realmes8)]);
            barChartData1.push([9, parseFloat(realmes9)]);
            barChartData1.push([10, parseFloat(realmes10)]);
            barChartData1.push([11, parseFloat(realmes11)]);
            barChartData1.push([12, parseFloat(realmes12)]);

            var barChartData2 = [];

            barChartData2.push([1, parseFloat(mes1volreal)]);
            barChartData2.push([2, parseFloat(mes2volreal)]);
            barChartData2.push([3, parseFloat(mes3volreal)]);
            barChartData2.push([4, parseFloat(mes4volreal)]);
            barChartData2.push([5, parseFloat(mes5volreal)]);
            barChartData2.push([6, parseFloat(mes6volreal)]);
            barChartData2.push([7, parseFloat(mes7volreal)]);
            barChartData2.push([8, parseFloat(mes8volreal)]);
            barChartData2.push([9, parseFloat(mes9volreal)]);
            barChartData2.push([10, parseFloat(mes10volreal)]);
            barChartData2.push([11, parseFloat(mes11volreal)]);
            barChartData2.push([12, parseFloat(mes12volreal)]);

            var data = [
                {
                    label: "Employment Realizado",
                    data: barChartData2,
                    color: '#FFC107'
                },
                {
                    label: "Outsourced Orçado",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Outsourced Realizado",
                    data: barChartData1,
                    color: '#40C4FF'
                }
            ];


            $.plot('#bar_chart2', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}

function filtrarCell() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroCell',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat(orcmes1 / mes1volreal)]);
            barChartData.push([2, parseFloat(orcmes2 / mes2volreal)]);
            barChartData.push([3, parseFloat(orcmes3 / mes3volreal)]);
            barChartData.push([4, parseFloat(orcmes4 / mes4volreal)]);
            barChartData.push([5, parseFloat(orcmes5 / mes5volreal)]);
            barChartData.push([6, parseFloat(orcmes6 / mes6volreal)]);
            barChartData.push([7, parseFloat(orcmes7 / mes7volreal)]);
            barChartData.push([8, parseFloat(orcmes8 / mes8volreal)]);
            barChartData.push([9, parseFloat(orcmes9 / mes9volreal)]);
            barChartData.push([10, parseFloat(orcmes10 / mes10volreal)]);
            barChartData.push([11, parseFloat(orcmes11 / mes11volreal)]);
            barChartData.push([12, parseFloat(orcmes12 / mes12volreal)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat(realmes1 / mes1volreal)]);
            barChartData1.push([2, parseFloat(realmes2 / mes2volreal)]);
            barChartData1.push([3, parseFloat(realmes3 / mes3volreal)]);
            barChartData1.push([4, parseFloat(realmes4 / mes4volreal)]);
            barChartData1.push([5, parseFloat(realmes5 / mes5volreal)]);
            barChartData1.push([6, parseFloat(realmes6 / mes6volreal)]);
            barChartData1.push([7, parseFloat(realmes7 / mes7volreal)]);
            barChartData1.push([8, parseFloat(realmes8 / mes8volreal)]);
            barChartData1.push([9, parseFloat(realmes9 / mes9volreal)]);
            barChartData1.push([10, parseFloat(realmes10 / mes10volreal)]);
            barChartData1.push([11, parseFloat(realmes11 / mes11volreal)]);
            barChartData1.push([12, parseFloat(realmes12 / mes12volreal)]);

            var data = [
                {
                    label: "Valor orçado pelo número de celulares",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Valor realizado pelo número de celulares",
                    data: barChartData1,
                    color: '#40C4FF'
                }
            ];

            $.plot('#bar_chart3', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}

function filtrarCar() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroCar',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat(orcmes1 / mes1volreal)]);
            barChartData.push([2, parseFloat(orcmes2 / mes2volreal)]);
            barChartData.push([3, parseFloat(orcmes3 / mes3volreal)]);
            barChartData.push([4, parseFloat(orcmes4 / mes4volreal)]);
            barChartData.push([5, parseFloat(orcmes5 / mes5volreal)]);
            barChartData.push([6, parseFloat(orcmes6 / mes6volreal)]);
            barChartData.push([7, parseFloat(orcmes7 / mes7volreal)]);
            barChartData.push([8, parseFloat(orcmes8 / mes8volreal)]);
            barChartData.push([9, parseFloat(orcmes9 / mes9volreal)]);
            barChartData.push([10, parseFloat(orcmes10 / mes10volreal)]);
            barChartData.push([11, parseFloat(orcmes11 / mes11volreal)]);
            barChartData.push([12, parseFloat(orcmes12 / mes12volreal)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat(realmes1 / mes1volreal)]);
            barChartData1.push([2, parseFloat(realmes2 / mes2volreal)]);
            barChartData1.push([3, parseFloat(realmes3 / mes3volreal)]);
            barChartData1.push([4, parseFloat(realmes4 / mes4volreal)]);
            barChartData1.push([5, parseFloat(realmes5 / mes5volreal)]);
            barChartData1.push([6, parseFloat(realmes6 / mes6volreal)]);
            barChartData1.push([7, parseFloat(realmes7 / mes7volreal)]);
            barChartData1.push([8, parseFloat(realmes8 / mes8volreal)]);
            barChartData1.push([9, parseFloat(realmes9 / mes9volreal)]);
            barChartData1.push([10, parseFloat(realmes10 / mes10volreal)]);
            barChartData1.push([11, parseFloat(realmes11 / mes11volreal)]);
            barChartData1.push([12, parseFloat(realmes12 / mes12volreal)]);

            var data = [
                {
                    label: "Valor orçado pelo número de carros",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Valor realizado pelo número de carros",
                    data: barChartData1,
                    color: '#40C4FF'
                }
            ];

            $.plot('#bar_chart4', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}

function filtrarKm() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroKm',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat(orcmes1 / mes1volreal)]);
            barChartData.push([2, parseFloat(orcmes2 / mes2volreal)]);
            barChartData.push([3, parseFloat(orcmes3 / mes3volreal)]);
            barChartData.push([4, parseFloat(orcmes4 / mes4volreal)]);
            barChartData.push([5, parseFloat(orcmes5 / mes5volreal)]);
            barChartData.push([6, parseFloat(orcmes6 / mes6volreal)]);
            barChartData.push([7, parseFloat(orcmes7 / mes7volreal)]);
            barChartData.push([8, parseFloat(orcmes8 / mes8volreal)]);
            barChartData.push([9, parseFloat(orcmes9 / mes9volreal)]);
            barChartData.push([10, parseFloat(orcmes10 / mes10volreal)]);
            barChartData.push([11, parseFloat(orcmes11 / mes11volreal)]);
            barChartData.push([12, parseFloat(orcmes12 / mes12volreal)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat(realmes1 / mes1volreal)]);
            barChartData1.push([2, parseFloat(realmes2 / mes2volreal)]);
            barChartData1.push([3, parseFloat(realmes3 / mes3volreal)]);
            barChartData1.push([4, parseFloat(realmes4 / mes4volreal)]);
            barChartData1.push([5, parseFloat(realmes5 / mes5volreal)]);
            barChartData1.push([6, parseFloat(realmes6 / mes6volreal)]);
            barChartData1.push([7, parseFloat(realmes7 / mes7volreal)]);
            barChartData1.push([8, parseFloat(realmes8 / mes8volreal)]);
            barChartData1.push([9, parseFloat(realmes9 / mes9volreal)]);
            barChartData1.push([10, parseFloat(realmes10 / mes10volreal)]);
            barChartData1.push([11, parseFloat(realmes11 / mes11volreal)]);
            barChartData1.push([12, parseFloat(realmes12 / mes12volreal)]);

            var data = [
                {
                    label: "Custo Orçado por KM",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Custo Realizado por KM",
                    data: barChartData1,
                    color: '#40C4FF'
                }
            ];

            $.plot('#bar_chart5', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}

function filtrarWood() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroWood',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat(mes1volplan / mes1volreal)]);
            barChartData.push([2, parseFloat(mes2volplan / mes2volreal)]);
            barChartData.push([3, parseFloat(mes3volplan / mes3volreal)]);
            barChartData.push([4, parseFloat(mes4volplan / mes4volreal)]);
            barChartData.push([5, parseFloat(mes5volplan / mes5volreal)]);
            barChartData.push([6, parseFloat(mes6volplan / mes6volreal)]);
            barChartData.push([7, parseFloat(mes7volplan / mes7volreal)]);
            barChartData.push([8, parseFloat(mes8volplan / mes8volreal)]);
            barChartData.push([9, parseFloat(mes9volplan / mes9volreal)]);
            barChartData.push([10, parseFloat(mes10volplan / mes10volreal)]);
            barChartData.push([11, parseFloat(mes11volplan / mes11volreal)]);
            barChartData.push([12, parseFloat(mes12volplan / mes12volreal)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat(realmes1 / mes1volreal)]);
            barChartData1.push([2, parseFloat(realmes2 / mes2volreal)]);
            barChartData1.push([3, parseFloat(realmes3 / mes3volreal)]);
            barChartData1.push([4, parseFloat(realmes4 / mes4volreal)]);
            barChartData1.push([5, parseFloat(realmes5 / mes5volreal)]);
            barChartData1.push([6, parseFloat(realmes6 / mes6volreal)]);
            barChartData1.push([7, parseFloat(realmes7 / mes7volreal)]);
            barChartData1.push([8, parseFloat(realmes8 / mes8volreal)]);
            barChartData1.push([9, parseFloat(realmes9 / mes9volreal)]);
            barChartData1.push([10, parseFloat(realmes10 / mes10volreal)]);
            barChartData1.push([11, parseFloat(realmes11 / mes11volreal)]);
            barChartData1.push([12, parseFloat(realmes12 / mes12volreal)]);

            var barChartData2 = [];

            barChartData2.push([1, parseFloat(realmes1 / mes1volplan)]);
            barChartData2.push([2, parseFloat(realmes2 / mes2volplan)]);
            barChartData2.push([3, parseFloat(realmes3 / mes3volplan)]);
            barChartData2.push([4, parseFloat(realmes4 / mes4volplan)]);
            barChartData2.push([5, parseFloat(realmes5 / mes5volplan)]);
            barChartData2.push([6, parseFloat(realmes6 / mes6volplan)]);
            barChartData2.push([7, parseFloat(realmes7 / mes7volplan)]);
            barChartData2.push([8, parseFloat(realmes8 / mes8volplan)]);
            barChartData2.push([9, parseFloat(realmes9 / mes9volplan)]);
            barChartData2.push([10, parseFloat(realmes10 / mes10volplan)]);
            barChartData2.push([11, parseFloat(realmes11 / mes11volplan)]);
            barChartData2.push([12, parseFloat(realmes12 / mes12volplan)]);

            var barChartData3 = [];

            barChartData3.push([1, parseFloat(mes1volplan)]);
            barChartData3.push([2, parseFloat(mes2volplan)]);
            barChartData3.push([3, parseFloat(mes3volplan)]);
            barChartData3.push([4, parseFloat(mes4volplan)]);
            barChartData3.push([5, parseFloat(mes5volplan)]);
            barChartData3.push([6, parseFloat(mes6volplan)]);
            barChartData3.push([7, parseFloat(mes7volplan)]);
            barChartData3.push([8, parseFloat(mes8volplan)]);
            barChartData3.push([9, parseFloat(mes9volplan)]);
            barChartData3.push([10, parseFloat(mes10volplan)]);
            barChartData3.push([11, parseFloat(mes11volplan)]);
            barChartData3.push([12, parseFloat(mes12volplan)]);

            var barChartData4 = [];

            barChartData4.push([1, parseFloat(mes1volreal)]);
            barChartData4.push([2, parseFloat(mes2volreal)]);
            barChartData4.push([3, parseFloat(mes3volreal)]);
            barChartData4.push([4, parseFloat(mes4volreal)]);
            barChartData4.push([5, parseFloat(mes5volreal)]);
            barChartData4.push([6, parseFloat(mes6volreal)]);
            barChartData4.push([7, parseFloat(mes7volreal)]);
            barChartData4.push([8, parseFloat(mes8volreal)]);
            barChartData4.push([9, parseFloat(mes9volreal)]);
            barChartData4.push([10, parseFloat(mes10volreal)]);
            barChartData4.push([11, parseFloat(mes11volreal)]);
            barChartData4.push([12, parseFloat(mes12volreal)]);

            var data = [
                {
                    label: "Indice de secagem",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Custo por volume secado",
                    data: barChartData1,
                    color: '#40C4FF'
                },
                {
                    label: "Custo por volume de lenha",
                    data: barChartData2,
                    color: '#FFC107'
                },
                {
                    label: "Volume de lenha",
                    data: barChartData3,
                    color: '#4CAF50'
                },
                {
                    label: "Volume secado",
                    data: barChartData4,
                    color: '#FF5722'
                }
            ];

            $.plot('#bar_chart6', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}

function filtrarPower() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroPower',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat(orcmes1 / mes1volreal)]);
            barChartData.push([2, parseFloat(orcmes2 / mes2volreal)]);
            barChartData.push([3, parseFloat(orcmes3 / mes3volreal)]);
            barChartData.push([4, parseFloat(orcmes4 / mes4volreal)]);
            barChartData.push([5, parseFloat(orcmes5 / mes5volreal)]);
            barChartData.push([6, parseFloat(orcmes6 / mes6volreal)]);
            barChartData.push([7, parseFloat(orcmes7 / mes7volreal)]);
            barChartData.push([8, parseFloat(orcmes8 / mes8volreal)]);
            barChartData.push([9, parseFloat(orcmes9 / mes9volreal)]);
            barChartData.push([10, parseFloat(orcmes10 / mes10volreal)]);
            barChartData.push([11, parseFloat(orcmes11 / mes11volreal)]);
            barChartData.push([12, parseFloat(orcmes12 / mes12volreal)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat(realmes1 / mes1volreal)]);
            barChartData1.push([2, parseFloat(realmes2 / mes2volreal)]);
            barChartData1.push([3, parseFloat(realmes3 / mes3volreal)]);
            barChartData1.push([4, parseFloat(realmes4 / mes4volreal)]);
            barChartData1.push([5, parseFloat(realmes5 / mes5volreal)]);
            barChartData1.push([6, parseFloat(realmes6 / mes6volreal)]);
            barChartData1.push([7, parseFloat(realmes7 / mes7volreal)]);
            barChartData1.push([8, parseFloat(realmes8 / mes8volreal)]);
            barChartData1.push([9, parseFloat(realmes9 / mes9volreal)]);
            barChartData1.push([10, parseFloat(realmes10 / mes10volreal)]);
            barChartData1.push([11, parseFloat(realmes11 / mes11volreal)]);
            barChartData1.push([12, parseFloat(realmes12 / mes12volreal)]);

            var data = [
                {
                    label: "Custo orçado por energia usada",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Custo realizado por energia usada",
                    data: barChartData1,
                    color: '#40C4FF'
                }
            ];

            $.plot('#bar_chart7', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}

function filtrarFuel() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroFuel',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");
            var i = 0;
            var orccod = orcfiltrado[i].split("%")[0];
            var orcmes1 = orcfiltrado[i].split("%")[1];
            var orcmes2 = orcfiltrado[i].split("%")[2];
            var orcmes3 = orcfiltrado[i].split("%")[3];
            var orcmes4 = orcfiltrado[i].split("%")[4];
            var orcmes5 = orcfiltrado[i].split("%")[5];
            var orcmes6 = orcfiltrado[i].split("%")[6];
            var orcmes7 = orcfiltrado[i].split("%")[7];
            var orcmes8 = orcfiltrado[i].split("%")[8];
            var orcmes9 = orcfiltrado[i].split("%")[9];
            var orcmes10 = orcfiltrado[i].split("%")[10];
            var orcmes11 = orcfiltrado[i].split("%")[11];
            var orcmes12 = orcfiltrado[i].split("%")[12];
            var realmes1 = orcfiltrado[i].split("%")[13];
            var realmes2 = orcfiltrado[i].split("%")[14];
            var realmes3 = orcfiltrado[i].split("%")[15];
            var realmes4 = orcfiltrado[i].split("%")[16];
            var realmes5 = orcfiltrado[i].split("%")[17];
            var realmes6 = orcfiltrado[i].split("%")[18];
            var realmes7 = orcfiltrado[i].split("%")[19];
            var realmes8 = orcfiltrado[i].split("%")[20];
            var realmes9 = orcfiltrado[i].split("%")[21];
            var realmes10 = orcfiltrado[i].split("%")[22];
            var realmes11 = orcfiltrado[i].split("%")[23];
            var realmes12 = orcfiltrado[i].split("%")[24];
            var orcacum = orcfiltrado[i].split("%")[25];
            var realacum = orcfiltrado[i].split("%")[26];
            var mes1volplan = orcfiltrado[i].split("%")[27];
            var mes2volplan = orcfiltrado[i].split("%")[28];
            var mes3volplan = orcfiltrado[i].split("%")[29];
            var mes4volplan = orcfiltrado[i].split("%")[30];
            var mes5volplan = orcfiltrado[i].split("%")[31];
            var mes6volplan = orcfiltrado[i].split("%")[32];
            var mes7volplan = orcfiltrado[i].split("%")[33];
            var mes8volplan = orcfiltrado[i].split("%")[34];
            var mes9volplan = orcfiltrado[i].split("%")[35];
            var mes10volplan = orcfiltrado[i].split("%")[36];
            var mes11volplan = orcfiltrado[i].split("%")[37];
            var mes12volplan = orcfiltrado[i].split("%")[38];
            var mes1volreal = orcfiltrado[i].split("%")[39];
            var mes2volreal = orcfiltrado[i].split("%")[40];
            var mes3volreal = orcfiltrado[i].split("%")[41];
            var mes4volreal = orcfiltrado[i].split("%")[42];
            var mes5volreal = orcfiltrado[i].split("%")[43];
            var mes6volreal = orcfiltrado[i].split("%")[44];
            var mes7volreal = orcfiltrado[i].split("%")[45];
            var mes8volreal = orcfiltrado[i].split("%")[46];
            var mes9volreal = orcfiltrado[i].split("%")[47];
            var mes10volreal = orcfiltrado[i].split("%")[48];
            var mes11volreal = orcfiltrado[i].split("%")[49];
            var mes12volreal = orcfiltrado[i].split("%")[50];
            var acumvolplan = orcfiltrado[i].split("%")[51];
            var acumvolreal = orcfiltrado[i].split("%")[52];

            //BAR CHART ==========================================================================================
            var barChartData = [];

            barChartData.push([1, parseFloat(orcmes1 / mes1volreal)]);
            barChartData.push([2, parseFloat(orcmes2 / mes2volreal)]);
            barChartData.push([3, parseFloat(orcmes3 / mes3volreal)]);
            barChartData.push([4, parseFloat(orcmes4 / mes4volreal)]);
            barChartData.push([5, parseFloat(orcmes5 / mes5volreal)]);
            barChartData.push([6, parseFloat(orcmes6 / mes6volreal)]);
            barChartData.push([7, parseFloat(orcmes7 / mes7volreal)]);
            barChartData.push([8, parseFloat(orcmes8 / mes8volreal)]);
            barChartData.push([9, parseFloat(orcmes9 / mes9volreal)]);
            barChartData.push([10, parseFloat(orcmes10 / mes10volreal)]);
            barChartData.push([11, parseFloat(orcmes11 / mes11volreal)]);
            barChartData.push([12, parseFloat(orcmes12 / mes12volreal)]);

            var barChartData1 = [];

            barChartData1.push([1, parseFloat(realmes1 / mes1volreal)]);
            barChartData1.push([2, parseFloat(realmes2 / mes2volreal)]);
            barChartData1.push([3, parseFloat(realmes3 / mes3volreal)]);
            barChartData1.push([4, parseFloat(realmes4 / mes4volreal)]);
            barChartData1.push([5, parseFloat(realmes5 / mes5volreal)]);
            barChartData1.push([6, parseFloat(realmes6 / mes6volreal)]);
            barChartData1.push([7, parseFloat(realmes7 / mes7volreal)]);
            barChartData1.push([8, parseFloat(realmes8 / mes8volreal)]);
            barChartData1.push([9, parseFloat(realmes9 / mes9volreal)]);
            barChartData1.push([10, parseFloat(realmes10 / mes10volreal)]);
            barChartData1.push([11, parseFloat(realmes11 / mes11volreal)]);
            barChartData1.push([12, parseFloat(realmes12 / mes12volreal)]);

            var data = [
                {
                    label: "Custo orçado por energia usada",
                    data: barChartData,
                    color: '#FF4081'
                },
                {
                    label: "Custo realizado por energia usada",
                    data: barChartData1,
                    color: '#40C4FF'
                }
            ];

            $.plot('#bar_chart8', data, {
                series: {
                    stack: 0,
                    lines: {
                        show: true,
                        fill: true,
                        steps: false
                    }
                },
                grid: {
                    hoverable: true,
                    autoHighlight: true,
                    borderColor: '#f3f3f3',
                    borderWidth: 1,
                    tickColor: '#f3f3f3'
                }
            });
        }});
}

function numeroPendentes() {
    $.ajax({
        type: 'GET',
        url: '../ServletOrcPendente',
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
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            npendente = dados;
            document.getElementById('numeropendente').innerHTML = npendente + ' - Análises Pendentes';
            document.getElementById('notificacoes').innerHTML = npendente;
        }
    });
}

function filtrarAnoMes() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroAnoMes',
        data: 'idano=' + $('#campoano').val()
                + '&idmes=' + $('#mes').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            window.location.reload();
        }
    });
}

function listarCustoTon() {
    $.ajax({
        type: 'GET',
        url: '../ServletFiltroTabelaCustoTon',
        data: 'idmes=' + $('#mes').find('option:selected').val()
                + '&idano=' + $('#campoano').val()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idgrupo='
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idcifexp=' + $('#md_checkbox_33').is(':checked')
                + '&idcifrec=' + $('#md_checkbox_34').is(':checked')
                + '&idfob=' + $('#md_checkbox_35').is(':checked'),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor');
            }
        },
        success: function (dados) {
            orcfiltrado = dados.split(";");

            for (var i = 0; i < orcfiltrado.length - 1; i++) {
                var orccod = orcfiltrado[i].split("#")[0];
                var real = orcfiltrado[i].split("#")[1];
                var volume = orcfiltrado[i].split("#")[2];
                var custon = orcfiltrado[i].split("#")[3];
                var filial = orcfiltrado[i].split("#")[4];

                custon = custon.toLocaleString('pt-BR',{style: 'currency', currency:'BRA'});
                real = real.toLocaleString();
                
                real = Math.round(real*100)/100 
                volume = Math.round(volume*100)/100 
                custon = Math.round(custon*100)/100 

                var t = $('#tabelaCustoTon').DataTable();
                t.row.add([
                    filial,
                    real,
                    volume,
                    custon
                ]).draw(false);
            }
        }
    });
}