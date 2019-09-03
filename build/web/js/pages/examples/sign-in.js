$(function () {
    $('#sign_in').validate({
        highlight: function () {
            console.log(input);
            alert('erro no servidor');
        },
        unhighlight: function () {
            alert('erro no servidor');
        },
        errorPlacement: function () {
            alert('erro no servidor');

        }
    });
});