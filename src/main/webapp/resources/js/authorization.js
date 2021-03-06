$(document).ready(function(){
  var $authButton=$('#auth-button');
  $authButton.click(function(e){
    e.preventDefault();
      var $loginInput=$('#login-input');
    var login =$loginInput.val();
    if(!isValidLogin(login)){
      $loginInput.val("");
      $("#login-invalid-alert").removeClass("hidden");
      $("#login-undef-alert").addClass("hidden");
      $("#login-success-alert").addClass("hidden");
    }
    else{
      // var status = false;
      //тут аякс запрос на проверку логина
      //статус и данные достать из респонза
      var user_data = {};
       user_data.login=login;
      // $.post("url",data, function(dataResp){
      //     window.location="rooms.html"
      // });

      $.ajax({
        url: '/TP-1.0-SNAPSHOT/authorization',
        type: 'POST',
        async: false,
        dataType: 'json',
        data: JSON.stringify(user_data),
        contentType: "application/json",
        success: function (data) {
          if(data.status==="SUCCESS"){
            user_data.avatarID = data.avatar_id;
            user_data.login = data.login;
            localStorage.setItem("user", JSON.stringify(user_data));
            //редирект на страницу комнат
            $("#login-success-alert").removeClass("hidden");
            $("#login-undef-alert").addClass("hidden");
            $("#login-invalid-alert").addClass("hidden");
            setTimeout(function(){
              window.location="/TP-1.0-SNAPSHOT/resources/rooms.html";
            }, 1000);

          }
          else{
            $("#login-undef-alert").removeClass("hidden");
            $("#login-success-alert").addClass("hidden");
            $("#login-invalid-alert").addClass("hidden");
          }
        }
      });
      
    }
  });

  function isValidLogin(login){
    login = login.toLowerCase();
    for(var i=0; i<login.length;i++){
      if(login.charAt(i)>='а' & login.charAt(i)<='я'){
        return false;
      }
    }
    return true;
  }
});
