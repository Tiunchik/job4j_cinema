$(document).ready(function loadPage() {
    $.ajax({
        url: getContextPath() + "/post",
        method: 'POST',
        contentType: 'json',
        data: JSON.stringify({'action': 'getHall'}),
        dataType: 'json'
    }).done(function (data) {
        $(data).each(function (index, element) {
            var line = '<td> Row: ' + data[index][0].row + '</td>';
            $(data[index]).each(function (ind, el) {
                line = line + createCell(el.row, el.place, el.holder);
            });
            $('#table_body').append('<tr>' + line + '</tr>');
        });
        var line = '<th></th>';
        for (var i = 0; i < data[0].length; i++) {
            line = line + '<th> Place: </th>';
        }
        $('#table_thead').append('<tr>' + line + '</tr>');
    })
});

setTimeout(function () {
    location.reload();
}, 30000);

function createCell(row, place, attr) {
    var out = '<td bgcolor="white" id="cell' + row + place + '">' + place + "  ";
    if (attr == 0) {
        out = out + '<input class="checkbox" type="checkbox" id="' + row + place + '" >';
    }
    out = out + '<input type="hidden" value="' + row + '" id="1attr' + row + place + '"></td>';
    out = out + '<input type="hidden" value="' + place + '" id="2attr' + row + place + '"></td>';
    return out;
}

function getContextPath() {
    return location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

function getPaymentPage() {
    var line = "{\"check\":[";
    var number = 0;
    $('input:checkbox:checked').each(function (index, el) {
        line = line + "\"" + $('#1attr' + el.id).val() + "-" + $('#2attr' + el.id).val() + "\",";
        number++;
    });
    if (number == 0) {
        alert('Please choose places')
    } else {
        line = line.substring(0, line.length - 1) + "]";
        var checkline = line;
        checkline = checkline + ",\"action\":\"checkplases\"}";
        $.ajax({
            url: getContextPath() + "/post",
            method: 'POST',
            contentType: 'json',
            data: checkline,
        }).done(function () {
            line = line + ",\"action\":\"payment\",";
            line = line + "\"number\":" + number + "}";
            localStorage.setItem('payment', line);
            location.href = getContextPath() + "/payment";
        }).fail(function () {
            alert('Purchaiser error, maybe ticket already have been bought, please try again');
            location.href = getContextPath() + "/index";
        })

    }
}