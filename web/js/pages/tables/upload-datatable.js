$(document).ready(function(){
    $('#tabelaorcamento').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": "scripts/server_processing.php"
    });
});