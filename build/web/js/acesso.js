$(document).ready(function () {
//    $('#botaoacessar').on('click', function () {
//        acessarSistema();
//    });

    $('#logout').on('click', function () {
        logOut();
    });

    $('#login').on('action', function () {
        acessarSistema();
    });
});

function acessarSistema() {
    $.ajax({
        type: 'GET',
        url: 'ServletGravarUsuario',
        data: 'idusuario=' + $('#username').val()
                + '&idsenha=' + $('#password').val()
                + '&idtela=' + $('#nometela').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor mais um teste');
            }
        },
        success: function (dados) {
            dadosusuario = dados.split(";");
            var i = 0;

            var usuario = dadosusuario[i].split("%")[0];
            var area = dadosusuario[i].split("%")[1];
            var filial = dadosusuario[i].split("%")[2];
            var nome = dadosusuario[i].split("%")[3];
            var perfil = dadosusuario[i].split("%")[4];
            var regiao = dadosusuario[i].split("%")[5];
            var setor = dadosusuario[i].split("%")[6];
            var tela = dadosusuario[i].split("%")[7];
            var email = dadosusuario[i].split("%")[8];

            if (usuario === "null") {
                document.getElementById("inicio").innerHTML = '<div class="alert bg-pink alert-dismissible waves-effect" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>Usuário não encontrato, insira um usuário cadastrados</div>';
                $('#username').val("");
                $('#password').val("");
            } else {
                window.location.href = "pages/paginainicial.jsp";
            }
        }
    });
}

function logOut() {
    $.ajax({
        type: 'GET',
        url: 'ServletLogOut',
        data: 'idusuario=' + $('#username').val()
                + '&idsenha=' + $('#password').val()
                + '&idtela=' + $('#nometela').val(),
        statusCode: {
            404: function () {
                alert('Pagina não encontrada');
            },
            500: function () {
                alert('erro no servidor mais um teste');
            }
        },
        success: function (dados) {

        }
    });
}