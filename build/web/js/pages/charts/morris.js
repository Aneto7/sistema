$(function () {
    getMorris('line', 'line_chart');
    getMorris('bar', 'bar_chart');
    getMorris('area', 'area_chart');
    getMorris('donut', 'donut_chart');
});


function getMorris(type, element) {
    var ano = $('#campoano').val();
    console.log(ano);
    $.ajax({
        type: 'GET',
        url: '../../ServletOrcDashboard',
        data: 'idregiao=' + $('#camporegiao').find('option:selected').text()
                + '&idplataforma=' + $('#campoplataforma').find('option:selected').text()
                + '&idmagnitude=' + $('#campomagnitude').find('option:selected').text()
                + '&idarea=' + $('#campoarea').find('option:selected').text()
                + '&idfilial=' + $('#campofilial').find('option:selected').text()
                + '&idano=' + $('#campoano').val()
                + '&idmes=' + $('#mes').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada no filtro');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            dadosfiltrados = dados.split(";");
            var meses = 12;
            for (var i = 0; i < meses; i++) {
                if (i < dadosfiltrados.length) {
                    window['idmes' + i] = dadosfiltrados[i].split("%")[0];
                    window['mes' + i] = dadosfiltrados[i].split("%")[1];
                    window['realmes' + i] = dadosfiltrados[i].split("%")[2];
                    window['bdgmes' + i] = dadosfiltrados[i].split("%")[3];
                    window['jusdentro' + i] = dadosfiltrados[i].split("%")[4];
                    window['jussub' + i] = dadosfiltrados[i].split("%")[5];
                    window['jussuper' + i] = dadosfiltrados[i].split("%")[6];
                    window['jussem' + i] = dadosfiltrados[i].split("%")[7];
                    window['jus115' + i] = dadosfiltrados[i].split("%")[8];
                    window['jus201' + i] = dadosfiltrados[i].split("%")[9];
                    window['jus322' + i] = dadosfiltrados[i].split("%")[10];
                    window['jus333' + i] = dadosfiltrados[i].split("%")[11];
                    window['jussoja' + i] = dadosfiltrados[i].split("%")[12];
                    window['jusmilho' + i] = dadosfiltrados[i].split("%")[13];
                } else {
                    window['idmes' + i] = 0;
                    window['mes' + i] = '';
                    window['realmes' + i] = 0;
                    window['bdgmes' + i] = 0;
                    window['jusdentro' + i] = 0;
                    window['jussub' + i] = 0;
                    window['jussuper' + i] = 0;
                    window['jussem' + i] = 0;
                    window['jus115' + i] = 0;
                    window['jus201' + i] = 0;
                    window['jus322' + i] = 0;
                    window['jus333' + i] = 0;
                    window['jussoja' + i] = 0;
                    window['jusmilho' + i] = 0;
                }
            }
            if (type === 'line') {
                data = new Date();
                mes = data.getMonth();
                Morris.Line({
                    element: element,
                    data: [{
                            'period': ano + '-' + idmes0,
                            'licensed': realmes0,
                            'sorned': bdgmes0
                        }, {
                            'period': ano + '-' + idmes1,
                            'licensed': realmes1,
                            'sorned': bdgmes1
                        }, {
                            'period': ano + '-' + idmes2,
                            'licensed': realmes2,
                            'sorned': bdgmes2
                        }, {
                            'period': ano + '-' + idmes3,
                            'licensed': realmes3,
                            'sorned': bdgmes3
                        }, {
                            'period': ano + '-' + idmes4,
                            'licensed': realmes4,
                            'sorned': bdgmes4
                        }, {
                            'period': ano + '-' + idmes5,
                            'licensed': realmes5,
                            'sorned': bdgmes5
                        }, {
                            'period': ano + '-' + idmes6,
                            'licensed': realmes6,
                            'sorned': bdgmes6
                        }, {
                            'period': ano + '-' + idmes6,
                            'licensed': realmes6,
                            'sorned': bdgmes6
                        }, {
                            'period': ano + '-' + idmes7,
                            'licensed': realmes7,
                            'sorned': bdgmes7
                        }, {
                            'period': ano + '-' + idmes8,
                            'licensed': realmes8,
                            'sorned': bdgmes8
                        }, {
                            'period': ano + '-' + idmes9,
                            'licensed': realmes9,
                            'sorned': bdgmes9
                        }, {
                            'period': ano + '-' + idmes10,
                            'licensed': realmes10,
                            'sorned': bdgmes10
                        }, {
                            'period': ano + '-' + idmes11,
                            'licensed': realmes11,
                            'sorned': bdgmes11
                        }],
                    xkey: 'period',
                    ykeys: ['licensed', 'sorned'],
                    labels: ['Realizado', 'Orçado'],
                    lineColors: ['rgb(233, 30, 99)', 'rgb(0, 188, 212)'],
                    lineWidth: 3
                });
            } else if (type === 'bar') {
                Morris.Bar({
                    element: element,
                    data: [{
                            x: ano + '-' + idmes11,
                            y: jusdentro11,
                            z: jussub11,
                            a: jussuper11,
                            b: jussem11
                        }, {
                            x: ano + '-' + idmes10,
                            y: jusdentro10,
                            z: jussub10,
                            a: jussuper10,
                            b: jussem10
                        }, {
                            x: ano + '-' + idmes9,
                            y: jusdentro9,
                            z: jussub9,
                            a: jussuper9,
                            b: jussem9
                        }, {
                            x: ano + '-' + idmes8,
                            y: jusdentro8,
                            z: jussub8,
                            a: jussuper8,
                            b: jussem8
                        }, {
                            x: ano + '-' + idmes7,
                            y: jusdentro7,
                            z: jussub7,
                            a: jussuper7,
                            b: jussem7
                        }, {
                            x: ano + '-' + idmes6,
                            y: jusdentro6,
                            z: jussub6,
                            a: jussuper6,
                            b: jussem6
                        }, {
                            x: ano + '-' + idmes5,
                            y: jusdentro5,
                            z: jussub5,
                            a: jussuper5,
                            b: jussem5
                        }, {
                            x: ano + '-' + idmes4,
                            y: jusdentro4,
                            z: jussub4,
                            a: jussuper4,
                            b: jussem4
                        }, {
                            x: ano + '-' + idmes3,
                            y: jusdentro3,
                            z: jussub3,
                            a: jussuper3,
                            b: jussem3
                        }, {
                            x: ano + '-' + idmes2,
                            y: jusdentro2,
                            z: jussub2,
                            a: jussuper2,
                            b: jussem2
                        }, {
                            x: ano + '-' + idmes1,
                            y: jusdentro1,
                            z: jussub1,
                            a: jussuper1,
                            b: jussem1
                        }, {
                            x: ano + '-' + idmes0,
                            y: jusdentro0,
                            z: jussub0,
                            a: jussuper0,
                            b: jussem0
                        }],
                    xkey: 'x',
                    ykeys: ['y', 'z', 'a', 'b'],
                    labels: ['Dentro do Orçamento', 'Orçamento Subestimado', 'Orçamento Superestimado', 'Sem Orçamento'],
                    barColors: ['rgb(233, 30, 99)', 'rgb(0, 188, 212)', 'rgb(0, 150, 136)', 'rgb(255, 152, 0)'],
                });
            } else if (type === 'area') {
                Morris.Area({
                    element: element,
                    data: [{
                            period: ano + '-' + idmes0,
                            333: jus3330,
                            322: jus3220,
                            201: jus2010,
                            115: jus1150
                        }, {
                            period: ano + '-' + idmes1,
                            333: jus3331,
                            322: jus3221,
                            201: jus2011,
                            115: jus1151
                        }, {
                            period: ano + '-' + idmes2,
                            333: jus3332,
                            322: jus3222,
                            201: jus2012,
                            115: jus1152
                        }, {
                            period: ano + '-' + idmes3,
                            333: jus3333,
                            322: jus3223,
                            201: jus2013,
                            115: jus1153
                        }, {
                            period: ano + '-' + idmes4,
                            333: jus3334,
                            322: jus3224,
                            201: jus2014,
                            115: jus1154
                        }, {
                            period: ano + '-' + idmes5,
                            333: jus3335,
                            322: jus3225,
                            201: jus2015,
                            115: jus1155
                        }, {
                            period: ano + '-' + idmes6,
                            333: jus3336,
                            322: jus3226,
                            201: jus2016,
                            115: jus1156
                        }, {
                            period: ano + '-' + idmes7,
                            333: jus3337,
                            322: jus3227,
                            201: jus2017,
                            115: jus1157
                        }, {
                            period: ano + '-' + idmes8,
                            333: jus3338,
                            322: jus3228,
                            201: jus2018,
                            115: jus1158
                        }, {
                            period: ano + '-' + idmes9,
                            333: jus3339,
                            322: jus3229,
                            201: jus2019,
                            115: jus1159
                        }, {
                            period: ano + '-' + idmes10,
                            333: jus33310,
                            322: jus32210,
                            201: jus20110,
                            115: jus11510
                        }, {
                            period: ano + '-' + idmes11,
                            333: jus33311,
                            322: jus32211,
                            201: jus20111,
                            115: jus11511
                        }],
                    xkey: 'period',
                    ykeys: ['333', '322', '201', '115'],
                    labels: ['333', '322', '201', '115'],
                    pointSize: 2,
                    hideHover: 'auto',
                    lineColors: ['rgb(233, 30, 99)', 'rgb(0, 188, 212)', 'rgb(0, 150, 136)', 'rgb(255, 152, 0)']
                });
            } else if (type === 'donut') {
                Morris.Donut({
                    element: element,
                    data: [{
                            label: 'SOJA',
                            value: jussoja0
                        }, {
                            label: 'MILHO',
                            value: jusmilho0
                        }],
                    colors: ['rgb(233, 30, 99)', 'rgb(0, 188, 212)'],
                    formatter: function (y) {
                        return y
                    }
                });
            }
        }
    });
}