$(document).ready(function() {
    validatePermissions();
    initLeftSidePanelActions();


    initUserUIData();
    initRoomCreationForm();
    loadRooms();
    initJoinToRoomButtons();
    setInterval(function(){loadRooms();  initJoinToRoomButtons();}, 30000);





});
ID_COUNTER = 0;
var USER = {
    login: "myLogin",
    id: "2"
}
ROOMS = [{
        id: 761,
        name: "room1",
        room_status: "active", //ждет подкл, активна, не активна,
        players_count: "2",
        rounds_count: "3",
        round_time: "120",
        room_creator: "lytkin",
        players: ["login1", "login2", "login3"]
    },
    {
        id: 980,
        name: "room2",
        room_status: "active", //ждет подкл, активна, не активна,
        players_count: "1",
        rounds_count: "7",
        round_time: "60",
        room_creator: "lytkin",
        players: ["login1", "login2", "login3"]
    }
];

function initRoomCreationForm() {
    var CREATED_ROOM = {};
    var time=90;
    var players=4;
    var rounds=20;
    $("#time-slider").slider({
        range: false,
        min: 60,
        max: 120,
        values: [90],
        slide: function(event, ui) {
            //  $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
            console.log(ui.values[0]);
            $('#selected-time').html(ui.values[0]);
            time = ui.values[0];
        }
    });

    $("#player-slider").slider({
        range: false,
        min: 2,
        max: 4,
        values: [4],
        slide: function(event, ui) {
            //  $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
            console.log(ui.values[0]);
            $('#selected-players-count').html(ui.values[0]);
            players = ui.values[0];
        }
    });

    $("#round-slider").slider({
        range: false,
        min: 13,
        max: 120,
        values: [20],
        slide: function(event, ui) {
            //  $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
            console.log(ui.values[0]);
            $('#selected-round-count').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });
    $('#create-room-btn').click(function() {
        var action_data1 = {};
        action_data1.room_name = $('#RoomName').val();
        action_data1.players_count = players;
        action_data1.round_time = time;
        action_data1.round_count = rounds;

        var d = {
            login: JSON.parse(localStorage.getItem("user")).login,
            action: "CREATE_ROOM",
            action_data: action_data1
        }
        //console.log(CREATED_ROOM);
        //TODO отправить на сервер
        $.ajax({
            url: '/TP-dao/createroom',
            type: 'POST',
            async: false,
            dataType: 'json',
            data: JSON.stringify(d),
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                //pokazat
            }
        });
        // ROOMS.push(room); //тут принимать комнату с айди и его писать
        $('#room-creation-modal').hide();
        $('.modal-backdrop').hide();
        loadRooms();
        console.log(ROOMS);
        initJoinToRoomButtons();
    });
}

function loadRooms() {
    //тут загружаем список комнат
    var user1 = JSON.stringify(localStorage.getItem("user"));
    $.ajax({
        url: '/TP-dao/getrooms',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: localStorage.getItem("user"),
        contentType: "application/json",
        success: function (data) {
            console.log(data);
            var $roomsItems = $("#available-rooms-items");
            $roomsItems.empty();
            for (var i = 0; i < data.rooms.length; i++) {
                var roomView = convertToRoomView(data.rooms[i]);
                $roomsItems.append(roomView);
            }
        }
    });



}

function convertToRoomView(room) {

    var roomBlock = '<div class="room-list-item">' +
        '<h4>' + room.name + '</h4>' +
        '<div class="rooms-param-wrapper">' +
        '<ul class="room-parameters" room-id="' + room.sessionID + '">' +
        '<li><i class="glyphicon glyphicon-time"></i>' + room.timeOfSteps + ' секунд</li>' +
        '<li><i class="glyphicon glyphicon-repeat"></i>' + room.numberOfSteps + ' круга</li>' +
        '<li><i class="glyphicon glyphicon-user"></i>' + room.userList.length + '/'+room.numberOfPlayers+' игроков</li>' +
        '</ul>' +
        '  <button class="join-to-room-bt btn btn btn-success" data-toggle="modal" data-target="#room-connection-modal">Присоединиться</button>' +
        '</div>' +
        '</div>';

    return roomBlock;


}

function initJoinToRoomButtons() {
    $('.join-to-room-bt').click(function() {
        var roomId = $(this).siblings('.room-parameters').attr('room-id');
        getRoomById(roomId);
        var ROOM_ID=roomId;
       var joinRoomID = setInterval(function(){
           getRoomById(ROOM_ID);
       },10000);
        $("#close-connect-to-room").click(function () {
            clearInterval(joinRoomID);
        });

    });
}
var ROOM_ID;
function getRoomById(id) {
    var room;
    $.ajax({
        url: '/TP-dao/updateroom',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: id,
        contentType: "application/json",
        success: function (data) {
            selectedRoom = data.action_data[0];
            var roomParams = '<p> Время хода: ' + selectedRoom.timeOfSteps + '</p>' +
                '<p> Количество ходов: ' + selectedRoom.numberOfSteps + '</p>' +
                '<p> Количество игроков: ' + selectedRoom.numberOfPlayers + '</p>' +
                '<h5>Игроки в комнате:</h5>' +
                '<ul>';
            for (var i = 0; i < selectedRoom.userList.length; i++) {
                roomParams += '<li>' + selectedRoom.userList[i].login + '</li>';
                if(selectedRoom.userList[i].login===JSON.parse(localStorage.getItem("user")).login){
                    $('#connect-btn').attr('disabled',true);
                    $('#connect-btn').css({"background-color":"grey !important"});
                    $('#connect-btn').text("Вы присоединены к комнате");
                    $("#connect-btn").addClass("hidden")
                }
                if(selectedRoom.userList[0].login===JSON.parse(localStorage.getItem("user")).login){
                    $("#start-btn").removeClass("hidden");
                    $("#start-btn").attr('disabled',true);
                    $("#connect-btn").addClass("hidden");

                }
                if(selectedRoom.userList.length===selectedRoom.numberOfPlayers){
                    $("#start-btn").attr('disabled',false);

                }
                if(selectedRoom.state==="ACTIVE"){ //когда игра началась
                    window.location="/TP-dao/resources/game.html"
                }
            }
            roomParams += '</ul>';

            $('#room-name-modal').html(selectedRoom.name + " ");

            $('#room-parameters').html(roomParams);
            $('#connect-btn').click(function() {
                //тут отправляем запрос пользователя на подключение к игре
                console.log(selectedRoom);

                $.ajax({
                    url: '/TP-dao/jointoroom',
                    type: 'POST',
                    async: false,
                    dataType: 'json',
                    data: JSON.stringify({login : JSON.parse(localStorage.getItem("user")).login, room_id:id}),
                    contentType: "application/json",
                    success: function (data) {
                        var selectedRoom =data.action_data[0];
                        var roomParams = '<p> Время хода: ' + selectedRoom.timeOfSteps + '</p>' +
                            '<p> Количество ходов: ' + selectedRoom.numberOfSteps + '</p>' +
                            '<p> Количество игроков: ' + selectedRoom.numberOfPlayers + '</p>' +
                            '<h5>Игроки в комнате:</h5>' +
                            '<ul>';
                        for (var i = 0; i < selectedRoom.userList.length; i++) {
                            roomParams += '<li>' + selectedRoom.userList[i].login + '</li>';
                        }
                        roomParams += '</ul>';

                        $('#room-name-modal').html(selectedRoom.name + " ");

                        $('#room-parameters').html(roomParams);
                        $('#connect-btn').attr('disabled',true);
                        $('#connect-btn').css({"background-color":"grey !important"});
                        $('#connect-btn').text("Вы присоединены к комнате");

                    }
                });

            });
        }
    });
    return room;
}
function   validatePermissions(){
    if(localStorage.user ===undefined){
        $("body").text("Доступ запрещен");
        setTimeout(function(){
            window.location="/TP-dao/resources/index.html";
        }, 3000);
    }
}
function initLeftSidePanelActions() {
    // $( "#left-side-panel" ).hide();
    var isOpenLeftSidePanel = false;

    $('#statistic-button-sb').click(function() {
        //тут опр контент для конкретной кнопки
        var content = "тут будет статистика игрока lorem";
        toggleLeftSidePanel(content);
    });
    $('#rules-button-sb').click(function() {
        //тут опр контент для конкретной кнопки
        var content = "тут будут правила игры";
        toggleLeftSidePanel(content);
    });

    $('#system-button-sb').click(function() {
        //тут опр контент для конкретной кнопки
        var content = "тут будут информация о системе";
        toggleLeftSidePanel(content);
    });

    $('#developers-button-sb').click(function() {
        //тут опр контент для конкретной кнопки
        var content = "тут будут информация о разработчиках";
        toggleLeftSidePanel(content);
    });

    $('#logout-button-sb').click(function() {
        //тут опр контент для конкретной кнопки
        //выход из профиля и возврат на экран входа в систему
        console.log("logout");
    });


    function toggleLeftSidePanel(data) {
        isOpenLeftSidePanel = !isOpenLeftSidePanel;
        if (isOpenLeftSidePanel) {
            $('#game-parameters').css({ "margin-left": "5px" });
            $("#left-side-panel").html(data);
            $("#left-side-panel").animate({ width: 'toggle' });
        } else {
            $("#left-side-panel").animate({ width: 'toggle' }, 'normal', function() {
                $('#game-parameters').css({ "margin-left": "55px" });
            });
        }
    }

}
function initUserUIData() {
    var user = JSON.parse(localStorage.getItem("user"));
    $("#profile-button-sb>img").attr("src", "img\\"+user.avatarID+".png");
    $("#profile-button-sb").attr("title", user.login);
}