var REQ={};
var intervalID;
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
var ROUNDS;
var CURRENT_ROUND;

var X;
var Y;
var egp_selected={
    count:0,
    cost:0
};
var esm_selected={
    count:0,
    cost:0
};


var fabric_build=0;
var esm_refurbished=0;
var esm_refurbished_auto=0;
var fabric_upgraded=0;
$(document).ready(function() {
    $('#new-fabric').click(function(){
        fabric_build++;
        $(this).addClass('fabric-ok');
    });
    validatePermissions();
    initStartParams();
    initMyResources();
    disableButtons();
    gameLifeCycle(ROUND_TIME);


});


function disableButtons(){
    $('#egp-accept').attr('disabled', false);
    $('#egp-reset').attr('disabled', false);
    $('#esm-accept').attr('disabled', false);
    $('#esm-reset').attr('disabled', false);
    $("#egp-cost-slider").attr('disabled', false);
    $("#esm-cost-slider").attr('disabled', false);
}

function initMyResources(){
    // $('#simple-fabric>.fabric-counter').text(PLAYER.numberOfReadyStandartFactories+'/'+PLAYER.numberOfStandartFactories);
    // $('#auto-fabric>.fabric-counter').text(PLAYER.numberOfReadyUniversalFactories+'/'+PLAYER.numberOfUniversalFactories);
    // $('#builded-fabric>.fabric-counter').text(PLAYER.numberOfStandartFactories-PLAYER.numberOfReadyStandartFactories);
    // $('#upgraded-fabric>.fabric-counter').text(PLAYER.numberOfUniversalFactories-PLAYER.numberOfReadyUniversalFactories);
    $('#fabrics').empty();
    $('#fabrics').append('<div id="new-fabric" class="fabric" >+</div>');
    $('#new-fabric').click(function(){
        fabric_build++;
        $(this).addClass('fabric-ok');
    });
    for(var i=0;i<PLAYER.numberOfReadyStandartFactories;i++){

        $('#fabrics').append('<div class="fabric fabric-simple">Ф </div>');
    }

    $('.fabric-simple').click(
        function (event) {
            $('#fabric-selected').attr('id', '');
            $('#fabric-popup').remove();

            if(!$(this).hasClass('fabric-ok')) {
                var popup = $('<div id="fabric-popup">' +
                    '<div id="ref-esm" class="btn btn-success">Переработаь 1 ЕСМ</div>' +
                    '<div id="upgrade" class="btn btn-success">Улучшить</div>' +
                    '<div id="exit" class="btn btn-success">Закрыть</div>' +
                    '</div>');
                popup.css({'top': $(this).offset().top - 120 + 'px', 'left': $(this).offset().left - 45 + 'px'});
                $(this).attr('id', 'fabric-selected');
                $('body').append(popup);
                $('#ref-esm').click(function () {
                    esm_refurbished++;
                    setTimeout(function () {
                        $('#fabric-popup').remove();
                    }, 700);
                    $('#fabric-selected').addClass('fabric-ok');
                    $('#fabric-selected').css({'background-color':'#999999','z-index':'-9999'});
                    $('#fabric-selected').attr('id', '');
                });

                $('#upgrade').click(function () {
                    fabric_upgraded++;
                    setTimeout(function () {
                        $('#fabric-popup').remove();
                    }, 700);
                    $('#fabric-selected').addClass('fabric-ok');
                    $('#fabric-selected').css({'background-color':'#999999','z-index':'-9999'});
                    $('#fabric-selected').attr('id', '');

                });
                $('#exit').click(function(){
                    $('#fabric-popup').remove();
                    $('#fabric-selected').attr('id', '');
                });
            }
        }
    );


    for(var i=0;i<PLAYER.numberOfReadyUniversalFactories;i++){
        $('#fabrics').append('<div class="fabric fabric-auto"">ФА </div>');
    }

    $('.fabric-auto').click(
        function (event) {
            $('#fabric-popup').remove();
            $('#fabric-selected').attr('id', '');

            if(!$(this).hasClass('fabric-ok')) {
                var popup = $('<div id="fabric-popup">' +
                    '<div id="ref-esm" class="btn btn-success">Переработаь 1 ЕСМ</div>' +
                    '<div id="ref-esm2" class="btn btn-success">Переработаь 2 ЕСМ</div>' +
                    '<div id="exit" class="btn btn-success">Закрыть</div>' +
                    '</div>');
                popup.css({'top': $(this).offset().top - 120 + 'px', 'left': $(this).offset().left - 45 + 'px'});
                $(this).attr('id', 'fabric-selected');
                $('body').append(popup);
                $('#ref-esm').click(function () {
                    esm_refurbished_auto++;
                    setTimeout(function () {
                        $('#fabric-popup').remove();
                    }, 700);
                    $('#fabric-selected').addClass('fabric-ok');
                    $('#fabric-selected').css({'background-color':'#999999','z-index':'-9999'});
                    $('#fabric-selected').attr('id', '');
                });

                $('#ref-esm2').click(function () {
                    esm_refurbished_auto+=2;
                    setTimeout(function () {
                        $('#fabric-popup').remove();
                    }, 700);
                    $('#fabric-selected').addClass('fabric-ok');
                    $('#fabric-selected').css({'background-color':'#999999','z-index':'-9999'});
                    $('#fabric-selected').attr('id', '');

                });
                $('#exit').click(function(){
                    $('#fabric-popup').remove();
                    $('#fabric-selected').attr('id', '');
                });
            }
        }
    );

    //TODO Дописать строящиеся и улучшающиеся

    $('#money-counter').text('$'+PLAYER.money);
    $('#egp-counter').text('ЕГП: '+PLAYER.numberOfEGP);
    $('#esm-counter').text('ECМ: '+PLAYER.numberOfESM);

    $('#round>div').text(ROUNDS);
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
            $('#egp-count').text(ui.values[0]);
            rounds = ui.values[0];
            egp_selected.count=ui.values[0];

            if(egp_selected.count&&egp_selected.cost){
                $('#egp-reset').removeClass('disabled');
                $('#egp-accept').removeClass('disabled');
            }
        }
    });

    $("#egp-cost-slider").slider({
        range: false,
        min: MIN_EGP_PRICE,
        max: MAX_EGP_PRICE,
        values: [MIN_EGP_PRICE],
        slide: function (event, ui) {
            $('#egp-cost').text(ui.values[0]);
            rounds = ui.values[0];
            egp_selected.cost=ui.values[0];


            if(egp_selected.count&&egp_selected.cost){
                $('#egp-reset').removeClass('disabled');
                $('#egp-accept').removeClass('disabled');
            }
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
            esm_selected.count=ui.values[0];
            if(esm_selected.count&&esm_selected.cost){
                $('#esm-reset').removeClass('disabled');
                $('#esm-accept').removeClass('disabled');
            }
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
            esm_selected.cost=ui.values[0];
            if(esm_selected.count&&esm_selected.cost){
                $('#esm-reset').removeClass('disabled');
                $('#esm-accept').removeClass('disabled');
            }
        }
    });
}
function gameLifeCycle(seconds){
    var currentTime = seconds;


    intervalID =  setInterval(function(){
        $("#time>div").text(currentTime);
        currentTime-=1;
        if(currentTime<16){
            if(currentTime%2==0){
                $('#time').css({'background-color':'red'});
            }else{
                $('#time').css({'background-color':'#5597d0'});
            }
        }
        if(currentTime==0){
            $("#time>div").text(currentTime);
            clearInterval(intervalID);
        }

    }, 1000);



}

function initStartParams() {
    $.ajax({
        url: '/TP-1.0-SNAPSHOT/nextround',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: localStorage.getItem("session_id"),
        contentType: "application/json",
        success: function (data) {
            bankController = data.action_data[0].bankController;
            players = bankController.playersList;
            for (var i = 0; i < players.length; i++) {
                if (players[i].user.login === JSON.parse(localStorage.getItem("user")).login) {
                    PLAYER = players[i];
                    break;
                }
            }

            MAX_ESM = bankController.bank.countESM;
            MIN_ESM = 0;
            MAX_ESM_PRICE = PLAYER.money / MAX_ESM;
            MIN_ESM_PRICE = bankController.bank.minPriceForESM; //пере

            MAX_EGP = bankController.bank.countEGP;
            MIN_EGP = 0;
            MAX_EGP_PRICE = bankController.bank.maxPriceForEGP;
            MIN_EGP_PRICE = 1;
            ROUND_TIME = data.action_data[0].timeOfSteps;
            ROUNDS = data.action_data[0].numberOfSteps;

            $('#players').empty();
            for (var i = 0; i < players.length; i++) {
                drawPlayer(players[i]);
            }

            initSliders();
            drawBank(bankController);
        }
    });
}

function drawBank(bankController){
    var bank = bankController.bank;

    $('#esm-bank-count').empty();
    $('#esm-bank-price').empty();
    $('#egp-bank-count').empty();
    $('#egp-bank-price').empty();
    $('#bank-level').empty();

    $('#esm-bank-count').append('Покупка ЕСМ:'+ bank.minPriceForESM);
    $('#esm-bank-price').append('Количество ЕСМ:'+ bank.countESM);
    $('#egp-bank-count').append('Покупка ЕГП:'+ bank.maxPriceForEGP);
    $('#egp-bank-price').append('Количество ЕГП:'+ bank.countEGP);
    var a = bank.currentlevel;
    $('#bank-level').append('Текущий уровень: '+bank.currentlevel);
}

$('#end-round-btn').click(function(){
    clearInterval(intervalID);
    $("#time>div").text(0);
    var request = {};
    request.room_id=localStorage.getItem("session_id");
    request.login=JSON.parse(localStorage.getItem("user")).login;
    request.action_data={
        egp:egp_selected,
        esm: esm_selected,
        credit:0,
        process:{
            upgrade_fabric: fabric_upgraded,
            build_fabric: fabric_build,
            fabric: esm_refurbished,
            auto_fabric: esm_refurbished_auto
        }
    };

    fabric_build=0;
    esm_refurbished=0;
    esm_refurbished_auto=0;
    fabric_upgraded=0;
    egp_selected={
        count:0,
        cost:0
    };
    esm_selected={
        count:0,
        cost:0
    };
    $.ajax({
        url: '/TP-1.0-SNAPSHOT/collect',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: JSON.stringify(request) ,
        contentType: "application/json",
        success: function (data) {
            console.log('RSPNS!!');
            console.log(data);
        }
    });

    var statusIntervalID = setInterval(function(){
        $.ajax({
            url: '/TP-1.0-SNAPSHOT/check',
            type: 'POST',
            async: false,
            dataType: 'json',
            data: "data",
            contentType: "application/json",
            success: function (data) {
                var check = data.check;

                if(check==="READY"){
                    clearInterval(statusIntervalID);
                    //TODO startgame
                    initStartParams();
                    initMyResources();
                    disableButtons();
                    gameLifeCycle(ROUND_TIME);
                } else if(check==="FINISHED"){
                    console.log("игра завершена");
                    clearInterval(statusIntervalID);
                }
            }
        });
    },5000);
});
