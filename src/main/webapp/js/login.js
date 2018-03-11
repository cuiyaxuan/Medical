document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==13){ // enter 键
        login();
    }
};
//登录
function  login() {
    var name=$.trim($("#first-name").val());
    var pwd=$("#last-name").val();
    if(name==""){
        alert("用户名不能为空");
    }
    $.ajax({
          type:'post',
          async:false,
          url:'./Login/login',
          data:{
              name:name,
              password:pwd
          },
        success:function (result) {
              var data=result.result;
              if(data=="OK"){
                  sessionStorage.setItem('userRole', result.message);
                  window.location.href="index.jsp";
              }else {
                  alert("用户名或者密码错误!");
              }
        },
        error:function (result) {
              alert("出错了")
        }
        }
    );


}