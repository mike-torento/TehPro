var REQ={};
$(document).ready(function() {
    validatePermissions();
    initSliders();
    timer(10);
});

function initSliders() {
    $("#egp-count-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [24],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#egp-count').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });

    $("#egp-cost-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [24],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#egp-cost').html(ui.values[0] + "$");
            rounds = ui.values[0];
        }
    });

    $("#esm-count-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [24],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#esm-count').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });

    $("#esm-cost-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [24],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#esm-cost').html(ui.values[0] + "$");
            rounds = ui.values[0];
        }
    });
}
function timer(seconds){
    var currentTime = seconds;


    var intervalID =  setInterval(function(){
        $("#time>div").text(currentTime);
                currentTime-=1;
        if(currentTime==0){
            $("#time>div").text(currentTime);
            clearInterval(intervalID);
        }

            }, 1000);



}