$(document).ready(function () {
    var $authButton = $('#auth-button');
    $authButton.click(function (e) {
        e.preventDefault();
        var $loginInput = $('#login-input');
        var login = $loginInput.val();
        if (!isValidLogin(login)) {
            $loginInput.val("");
            $("#login-invalid-alert").removeClass("hidden");
            $("#login-already-exist-alert").addClass("hidden");
        } else {
            var status = false;
            //тут аякс запрос на регистрацию
            //статус и данные достать из респонза
            var user_data = {};
            user_data.login = login; // вернуть назад как было!!
            user_data.avatarID = 2;

            $.ajax({
                url: '/TP-dao/registration',
                type: 'POST',
                async: false,
                dataType: 'json',
                data: JSON.stringify(user_data),
                contentType: "application/json",
                success: function (data) {
                    if (data.status === "SUCCESS") {
                        //редирект на страницу комнат
                        $("#login-success-alert").removeClass("hidden");
                        $("#login-already-exist-alert").addClass("hidden");
                        $("#login-invalid-alert").addClass("hidden");
                        setTimeout(function () {
                            window.location = "/TP-dao/resources/rooms.html";
                        }, 2000);
                    } else {
                        $("#login-already-exist-alert").removeClass("hidden");
                        $("#login-success-alert").addClass("hidden");
                        $("#login-invalid-alert").addClass("hidden");
                    }
                }
            });


//            $.post('/TP-dao/registration', JSON.stringify(user_data), function (data) {
//                if (data.status === "SUCCESS") {
//                    //редирект на страницу комнат
//                    $("#login-success-alert").removeClass("hidden");
//                    $("#login-already-exist-alert").addClass("hidden");
//                    $("#login-invalid-alert").addClass("hidden");
//                    setTimeout(function () {
//                        window.location = "resources/rooms.html";
//                    }, 2000);
//                } else {
//                    $("#login-already-exist-alert").removeClass("hidden");
//                    $("#login-success-alert").addClass("hidden");
//                    $("#login-invalid-alert").addClass("hidden");
//                }
//            }, "json");


        }
    });

    function isValidLogin(login) {
        login = login.toLowerCase();
        for (var i = 0; i < login.length; i++) {
            if (login.charAt(i) >= 'а' & login.charAt(i) <= 'я') {
                return false;
            }
        }
        return true;
    }
});
