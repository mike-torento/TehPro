var REQ={};
var players;
var PLAYER;
var bankController;
var ROUND_TIME;
var user_action={};
var MIN_EGP;
var MAX_EGP;

var MAX_EGP_PRICE;
var MIN_EGP_PRICE;

var MIN_ESM;
var MAX_ESM;

var MAX_ESM_PRICE;
var MIN_ESM_PRICE;
$(document).ready(function() {
    validatePermissions();






    $.ajax({
        url: '/TP-1.0-SNAPSHOT/startgame',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: localStorage.getItem("session_id"),
        contentType: "application/json",
        success: function (data) {
            bankController = data.action_data[0].bankController;
            players = bankController.playersList;
            for(var i=0;i<players.length;i++){
                if(players[i].user.login===JSON.parse(localStorage.getItem("user")).login){
                    PLAYER = players[i];
                    break;
                }
            }

            MAX_ESM = bankController.bank.countESM;
            MIN_ESM = 0;
            MAX_ESM_PRICE = PLAYER.money/MAX_ESM;
            MIN_ESM_PRICE = bankController.bank.minPriceForESM; //пере

            MAX_EGP = bankController.bank.countEGP;
            MIN_EGP = 0;
            MAX_EGP_PRICE = bankController.bank.maxPriceForEGP;
            MIN_EGP_PRICE = 1;
            ROUND_TIME = data.action_data[0].timeOfSteps;

            for(var i=0; i<players.length;i++){
                drawPlayer(players[i]);
            }

            initSliders();
        }
    });
    initMyResources();
    gameLifeCycle(ROUND_TIME);
    
});

function initMyResources(){
        $('#simple-fabric>.fabric-counter').text(PLAYER.numberOfReadyStandartFactories+'/'+PLAYER.numberOfStandartFactories);
        $('#auto-fabric>.fabric-counter').text(PLAYER.numberOfReadyUniversalFactories+'/'+PLAYER.numberOfUniversalFactories);
        $('#builded-fabric>.fabric-counter').text(PLAYER.numberOfStandartFactories-PLAYER.numberOfReadyStandartFactories);
        $('#upgraded-fabric>.fabric-counter').text(PLAYER.numberOfUniversalFactories-PLAYER.numberOfReadyUniversalFactories);
        $('#money-counter').text('$'+PLAYER.money);
        $('#egp-counter').text('ЕГП: '+PLAYER.numberOfEGP);
        $('#esm-counter').text('ECМ: '+PLAYER.numberOfESM);
}

function drawPlayer(player){

    var userDiv = $(' <div class="player"></div>');
    userDiv.append('<img src="img/'+player.user.avatarID+'.png">');
    var playerInfo = $('<div class="player-info"></div>');
    playerInfo.append('<h4>'+player.user.login+'</h4>');

    var leftCol = $(' <div class="left-col"></div>');
    leftCol.append('Капитал: $');
    leftCol.append(player.money);
    leftCol.append('<br>');

    leftCol.append('ЕГП: ');
    leftCol.append(player.numberOfEGP);
    leftCol.append('<br>');

    leftCol.append('ЕСМ: ');
    leftCol.append(player.numberOfESM);
    leftCol.append('<br>');

    playerInfo.append(leftCol);

    var rightCol = $(' <div class="right-col"></div>');

    rightCol.append('Фабрики: ');
    rightCol.append(player.numberOfStandartFactories);
    rightCol.append('<br>')

    rightCol.append('Авт. фабрики: ');
    rightCol.append(player.numberOfUniversalFactories);
    rightCol.append('<br>');

    rightCol.append('Кредит: ');
    rightCol.append(player.loan);
    rightCol.append('<br>');

    playerInfo.append(leftCol);
    playerInfo.append(rightCol);




    userDiv.append(playerInfo);
    $('#players').append(userDiv);

}
function initSliders() {
    $("#egp-count-slider").slider({
        range: false,
        min: MIN_EGP,
        max: MAX_EGP,
        values: [MIN_EGP],
        slide: function (event, ui) {
            console.log(ui.values[0]);
            $('#egp-count').text(ui.values[0]);
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
            $('#egp-cost').text(ui.values[0]);
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
            $('#esm-count').text(ui.values[0]);
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
            $('#esm-cost').text(ui.values[0]);
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