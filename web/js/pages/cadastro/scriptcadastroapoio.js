$(document).ready(function () {
    numeroPendentes();
    $("#tabelahc").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabelavolume").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabelavolumeplan").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabelakm").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabelawood").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabeladry").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabelapower").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabelafuel").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabelacell").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });
    $("#tabelacar").DataTable({
        "paging": false,
        "ordering": false,
        "searching": false
    });

    $('#mes').on('change', function () {
        filtrarAnoMes();
        window.location.reload();
    });
    
    $('#campoano').on('change', function () {
        filtrarAnoMes();
        window.location.reload();
    });

    $('td').change(function () {
        var tablename = "#" + $(this).closest('table').attr('id');
        var table = $(tablename).DataTable();
        var data = table.row(this).data();

        $.ajax({
            type: 'GET',
            url: '../../ServletInserirApoio',
            data: 'idregiao=' + data[0]
                    + '&idfilial=' + data[1]
                    + '&idano=' + $('#campoano').val()
                    + '&idmes=' + $('#mes').find('option:selected').text()
                    + '&idtipo=' + $(this).closest('table').attr('name')
                    + '&idarea=' + $(this).closest('table td').attr('area')
                    + '&idpr=' + $(this).closest('table td').attr('planoureal')
                    + '&idplataforma=' + $(this).closest('table td').attr('plataforma')
                    + '&idvalor=' + $(this).text(),
            statusCode: {
                404: function () {
                    alert('Pagina não encontrada no filtro');
                },
                500: function () {
                    alert('erro no servidor')
                }
            },
            success: function (dados) {
            }
        });
    });
});

function filtrarAnoMes() {
    $.ajax({
        type: 'GET',
        url: '../../ServletFiltroAnoMes',
        data: 'idano=' + $('#campoano').val()
                + '&idmes=' + $('#mes').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor')
            }
        },
        success: function (dados) {
            console.log(dados);
            anomes = dados.split(";");
            var i = 0;
            var mes = anomes[i].split("#")[0];
            var ano = anomes[i].split("#")[1];
            $('#campoano').val(ano);
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
            console.log(dados);
            npendente = dados;
            document.getElementById('numeropendente').innerHTML = npendente + ' - Análises Pendentes';
            document.getElementById('notificacoes').innerHTML = npendente;
        }
    });
}