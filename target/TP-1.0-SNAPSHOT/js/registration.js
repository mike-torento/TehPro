$(document).ready(function(){
  var $authButton=$('#auth-button');
  $authButton.click(function(e){
    e.preventDefault();
      var $loginInput=$('#login-input');
    var login =$loginInput.val();
    if(!isValidLogin(login)){
      $loginInput.val("");
      $("#login-invalid-alert").removeClass("hidden");
      $("#login-already-exist-alert").addClass("hidden");
    }
    else{
      var status = false;
      //тут аякс запрос на регистрацию
      //статус и данные достать из респонза
      var data = {};
      data.login=;
      data.avatar_id=2;
      if(status){
        //редирект на страницу комнат
        $("#login-success-alert").removeClass("hidden");
        $("#login-already-exist-alert").addClass("hidden");
        $("#login-invalid-alert").addClass("hidden");
        setTimeout(function() {
            alert("REDIRECT!");
      }, 2000);
      }
      else{
        $("#login-already-exist-alert").removeClass("hidden");
        $("#login-success-alert").addClass("hidden");
        $("#login-invalid-alert").addClass("hidden");
      }
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
