$(document).ready(function () {
    $('#calcular').on('click', function () {
        var nome = $('#nome').val();
        var p1 = $('#p1').val();
        var p2 = $('#p2').val();

        $.ajax({
            url: '../webresources/aluno/json',
            method: 'GET',
            data: {
                nome: nome,
                p1: p1,
                p2: p2
            },
            success: function (data, textStatus, jqXHR) {
                $('#media').val(data.media);
                $('#aprovado').val(data.aprovado ? 'Sim' : 'Não');
            }
        });
    });
});
