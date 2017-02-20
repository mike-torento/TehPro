var REQ={};
$(document).ready(function() {
    validatePermissions();



    gameLifeCycle(10);


    $.ajax({
        url: '/TP-dao/startgame',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: localStorage.getItem("session_id"),
        contentType: "application/json",
        success: function (data) {
            var bankController = data.action_data[0].bankController;
            var players = bankController.playersList;
            var player;
            for(var i=0;i<players.length;i++){
                if(players[i].user.login===JSON.parse(localStorage.getItem("user")).login){
                    player = players[i];
                    break;
                }
            }

            MAX_ESM = bankController.bank.countESM;
            MIN_ESM = 0;
            MAX_ESM_PRICE = player.money/MAX_ESM;
            MIN_ESM_PRICE = bankController.bank.minPriceForESM; //пере

            MAX_EGP = bankController.bank.countEGP;
            MIN_EGP = 0;
            MAX_EGP_PRICE = bankController.bank.maxPriceForEGP;
            MIN_EGP_PRICE = 1;


            for(var i=0; i<players.length;i++){
                drawPlayer(players[i]);
            }





            initSliders();
        }
    });


    
});

var MIN_EGP;
var MAX_EGP;

var MAX_EGP_PRICE;
var MIN_EGP_PRICE;

var MIN_ESM;
var MAX_ESM;

var MAX_ESM_PRICE;
var MIN_ESM_PRICE;

function drawPlayer(player){
    var template='<div class="player" id="'+player.user.login+
            +'<img src="img/'+player.user.avatarID+'.png"><div class="player-info"><h4>'+player.user.login+'</h4>'+
            +'<div class="player-parameters"> <div class="left-col">  Капитал: $'+player.money+' <br> ЕСМ: '+player.numberOfESM+' <br>  <br> </div>'+
            +' <div class="right-col">ЕГП: '+player.numberOfEGP+' <br> Фабрики: '+player.numberOfStandartFactories+' <br> Авто фабрики: '+player.numberOfUniversalFactories+' <br> </div>  </div>  </div>';
    $('#players').append(template);

}
function initSliders() {
    $("#egp-count-slider").slider({
        range: false,
        min: MIN_EGP,
        max: MAX_EGP,
        values: [MIN_EGP],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#egp-count').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });

    $("#egp-cost-slider").slider({
        range: false,
        min: MIN_EGP_PRICE,
        max: MAX_EGP_PRICE,
        values: [MIN_EGP_PRICE],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#egp-cost').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });

    $("#esm-count-slider").slider({
        range: false,
        min: MIN_ESM,
        max: MAX_ESM,
        values: [MIN_ESM],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#esm-count').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });

    $("#esm-cost-slider").slider({
        range: false,
        min: MIN_ESM_PRICE,
        max: MAX_ESM_PRICE,
        values: [MIN_ESM_PRICE],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#esm-cost').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });
}
function gameLifeCycle(seconds){
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