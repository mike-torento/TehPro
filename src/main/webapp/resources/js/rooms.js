$(document).ready(function() {
    validatePermissions();
    initLeftSidePanelActions();


    initUserUIData();
    initRoomCreationForm();
    loadRooms();
    initJoinToRoomButtons();



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
    var time;
    var players;
    var rounds;
    var room_id;
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
        values: [24],
        slide: function(event, ui) {
            //  $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
            console.log(ui.values[0]);
            $('#selected-round-count').html(ui.values[0]);
            rounds = ui.values[0];
        }
    });
    $('#create-room-btn').click(function() {
        var room = {};
        room.name = $('#RoomName').val();
        room.players = [USER.login];
        room.players_count = players;
        room.round_time = time;
        room.rounds_count = rounds;
        room.id = ID_COUNTER; //костыль
        ID_COUNTER++;
        //console.log(CREATED_ROOM);
        //TODO отправить на сервер
        ROOMS.push(room); //тут принимать комнату с айди и его писать
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
        '<li><i class="glyphicon glyphicon-user"></i>' + room.numberOfPlayers + '/4 игроков</li>' +
        '</ul>' +
        '  <button class="join-to-room-bt btn btn btn-success" data-toggle="modal" data-target="#room-connection-modal">Присоединиться</button>' +
        '</div>' +
        '</div>';

    return roomBlock;


}

function initJoinToRoomButtons() {
    $('.join-to-room-bt').click(function() {
        var roomId = $(this).siblings('.room-parameters').attr('room-id');
        var selectedRoom = getRoomById(roomId);
        var roomParams = '<p> Время хода: ' + selectedRoom.round_time + '</p>' +
            '<p> Количество ходов: ' + selectedRoom.rounds_count + '</p>' +
            '<p> Количество игроков: ' + selectedRoom.players_count + '</p>' +
            '<h5>Игроки в комнате:</h5>' +
            '<ul>';
        for (var i = 0; i < selectedRoom.players.length; i++) {
            roomParams += '<li>' + selectedRoom.players[i] + '</li>';
        }
        roomParams += '</ul>';

        $('#room-name-modal').html(selectedRoom.name + " ");

        $('#room-parameters').html(roomParams);
        $('#connect-btn').click(function() {
            //тут отправляем запрос пользователя на подключение к игре
            console.log(selectedRoom);
        });
    });
}

function getRoomById(id) {
    for (var i = 0; i < ROOMS.length; i++) {
        if (ROOMS[i].id == id) return ROOMS[i];
    }
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