$(document).ready(function () {
    var temp = localStorage.getItem('payment');
    temp = JSON.parse(temp);
    var places = "";
    $(temp.check).each(function (index, el) {
        places = places + el + " "
    });
    $('#places').val("You are buying places: " + places + ", the price is " + temp.number * 500);
});

function purchase() {
    var temp = localStorage.getItem('payment');
    temp = temp.substring(0, temp.length - 1);
    temp = temp + ",\"name\":\"" + $('#name').val() + "\"}";
    $.ajax({
        url: getContextPath() + "/post",
        method: 'POST',
        contentType: 'json',
        data: temp
    }).done(function () {
        location.href = getContextPath() + "/index";
    }).fail( function(){
        alert('Purchaiser error, maybe ticket already have been bought, please try again');
        location.href = getContextPath() + "/index";
    })
}

function getContextPath() {
    return location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}