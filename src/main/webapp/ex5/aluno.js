$(document).ready(function () {
    $('#calcular').on('click', function () {
        var nome = $('#nome').val();
        var p1 = $('#p1').val();
        var p2 = $('#p2').val();

        $.ajax({
            url: '../webresources/aluno/json-and-status-code',
            method: 'GET',
            data: {
                nome: nome,
                p1: p1,
                p2: p2
            },
            success: function (data, textStatus, jqXHR) {
                $('#media').val(data.media);
                $('#aprovado').val(data.aprovado ? 'Sim' : 'Não');
            },
            statusCode: {
                400: function (jqXHR, textStatus, errorThrown) {
                    var data = JSON.parse(jqXHR.responseText);
                    alert(data.erro);
                },
                404: function (jqXHR, textStatus, errorThrown) {
                    alert("WS não encontrado. Verifique se colocou números para p1 e p2.");
                }
            }
        });
    });
});
