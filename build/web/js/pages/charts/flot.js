var data = [], totalPoints = 110;
var updateInterval = 320;
var realtime = 'on';

$(function () {
    //Real time ==========================================================================================
    var plot = $.plot('#real_time_chart', [getRandomData()], {
        series: {
            shadowSize: 0,
            color: 'rgb(0, 188, 212)'
        },
        grid: {
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        },
        lines: {
            fill: true
        },
        yaxis: {
            min: 0,
            max: 100
        },
        xaxis: {
            min: 0,
            max: 100
        }
    });

    function updateRealTime() {
        plot.setData([getRandomData()]);
        plot.draw();

        var timeout;
        if (realtime === 'on') {
            timeout = setTimeout(updateRealTime, updateInterval);
        } else {
            clearTimeout(timeout);
        }
    }

    updateRealTime();

    $('#realtime').on('change', function () {
        realtime = this.checked ? 'on' : 'off';
        updateRealTime();
    });
    //====================================================================================================

    //Tracking ===========================================================================================
    var sin = [], cos = [];
    for (var i = 0; i < 14; i += 0.1) {
        sin.push([i, Math.sin(i)]);
        cos.push([i, Math.cos(i)]);
    }

    var trackingData = [
        {
            data: sin,
            label: 'sin(x) = -0.00',
            color: '#E91E63'
        },
        {
            data: cos,
            label: 'cos(x) = -0.00',
            color: '#00BCD4'
        }
    ];

    var trackingPlot = $.plot('#tracking_chart', trackingData, {
        crosshair: {
            mode: 'x'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        },
        yaxis: {
            min: -1.2,
            max: 1.2
        }
    });

    var legends = $('#tracking_chart .legendLabel');

    legends.each(function () {
        $(this).css('width', $(this).width());
    });

    var updateLegendTimeout = null;
    var latestPosition = null;

    function updateLegend() {
        updateLegendTimeout = null;
        var pos = latestPosition;

        var axes = trackingPlot.getAxes();
        if (pos.x < axes.xaxis.min || pos.x > axes.xaxis.max ||
                pos.y < axes.yaxis.min || pos.y > axes.yaxis.max) {
            return;
        }

        var i, j, dataset = trackingPlot.getData();
        for (i = 0; i < dataset.length; ++i) {
            var series = dataset[i];

            for (j = 0; j < series.data.length; ++j) {
                if (series.data[j][0] > pos.x) {
                    break;
                }
            }

            var y, p1 = series.data[j - 1], p2 = series.data[j];

            if (p1 == null) {
                y = p2[1];
            } else if (p2 == null) {
                y = p1[1];
            } else {
                y = p1[1] + (p2[1] - p1[1]) * (pos.x - p1[0]) / (p2[0] - p1[0]);
            }

            legends.eq(i).text(series.label.replace(/=.*/, '= ' + y.toFixed(2)));
        }
    }

    $('#tracking_chart').bind('plothover', function (event, pos, item) {
        latestPosition = pos;
        if (!updateLegendTimeout) {
            updateLegendTimeout = setTimeout(updateLegend, 50);
        }
    });
    //====================================================================================================

    //MULTIPLE AXIS ======================================================================================
    var oilprices = [[1, 1], [2, 2], [3, 3], [4, 4], [5, 5], [6, 6], [7, 7], [8, 8], [9, 9], [10, 10], [11, 11], [12, 12]];
    var exchangerates = [[1, 1], [2, 2], [3, 3], [4, 4], [5, 5], [6, 6], [7, 7], [8, 8], [9, 9], [10, 10], [11, 11], [12, 12]];

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

    barChartData.push([1, 10]);
    barChartData.push([2, 20]);
    barChartData.push([3, 30]);
    barChartData.push([4, 40]);
    barChartData.push([5, 50]);
    barChartData.push([6, 60]);
    barChartData.push([7, 70]);
    barChartData.push([8, 10]);
    barChartData.push([9, 20]);
    barChartData.push([10, 30]);
    barChartData.push([11, 40]);
    barChartData.push([12, 50]);



    $.plot('#bar_chart', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });

    $.plot('#bar_chart1', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });

    $.plot('#bar_chart2', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });

    $.plot('#bar_chart3', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });

    $.plot('#bar_chart4', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });

    $.plot('#bar_chart5', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });

    $.plot('#bar_chart6', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });

    $.plot('#bar_chart7', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });

    $.plot('#bar_chart8', [barChartData], {
        series: {
            stack: 0,
            lines: {
                show: false,
                fill: true,
                steps: false
            },
            bars: {
                show: true,
                barWidth: 0.6
            },
            color: '#40C4FF'
        },
        grid: {
            hoverable: true,
            autoHighlight: false,
            borderColor: '#f3f3f3',
            borderWidth: 1,
            tickColor: '#f3f3f3'
        }
    });



    //====================================================================================================

    //PIE CHART ==========================================================================================
    var pieChartData = [], pieChartSeries = 4;
    var pieChartColors = ['#E91E63', '#03A9F4', '#FFC107', '#009688'];
    var pieChartDatas = [45, 17, 28, 10];

    for (var i = 0; i < pieChartSeries; i++) {
        pieChartData[i] = {
            label: 'Serie - ' + (i + 1),
            data: pieChartDatas[i],
            color: pieChartColors[i]
        }
    }
    $.plot('#pie_chart', pieChartData, {
        series: {
            pie: {
                show: true,
                radius: 1,
                label: {
                    show: true,
                    radius: 3 / 4,
                    formatter: labelFormatter,
                    background: {
                        opacity: 0.5
                    }
                }
            }
        },
        legend: {
            show: false
        }
    });
    function labelFormatter(label, series) {
        return '<div style="font-size:8pt; text-align:center; padding:2px; color:white;">' + label + '<br/>' + Math.round(series.percent) + '%</div>';
    }
    //====================================================================================================
});

function getRandomData() {
    if (data.length > 0)
        data = data.slice(1);

    while (data.length < totalPoints) {
        var prev = data.length > 0 ? data[data.length - 1] : 50, y = prev + Math.random() * 10 - 5;
        if (y < 0) {
            y = 0;
        } else if (y > 100) {
            y = 100;
        }

        data.push(y);
    }

    var res = [];
    for (var i = 0; i < data.length; ++i) {
        res.push([i, data[i]])
    }

    return res;
}