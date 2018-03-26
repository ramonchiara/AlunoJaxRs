$(document).ready(function () {
    $('#calcular').on('click', function () {
        var nome = $('#nome').val();
        var p1 = $('#p1').val();
        var p2 = $('#p2').val();

        $.ajax({
            url: `../webresources/aluno/${nome}/${p1}/${p2}`,
            method: 'GET',
            success: function (data, textStatus, jqXHR) {
                $('#mensagem').val(data);
            }
        });
    });
});
