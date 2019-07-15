$(document).ready(function(){
    $.getJSON('/rate', function(data) {
        $('#rate').text(data.rates.UAH);
        $('#date').text(data.date);
    });
});