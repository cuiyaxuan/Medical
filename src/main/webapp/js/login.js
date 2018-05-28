document.onkeydown = function (event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if (e && e.keyCode == 13) { // enter 键
        login();
    }
};

//登录
function login() {
    let name = $.trim($("#first-name").val());
    let pwd = $("#last-name").val();
    if (name == "") {
        alert("用户名不能为空");
    }
    $.ajax({
            type: 'post',
            async: false,
            url: './Login/login',
            data: {
                username: name,
                password: pwd
            },
            success: function (result) {
                let state = result.state;
                if (state === "200") {
                    sessionStorage.setItem('userRole', "1");
                    window.location.href = "index.jsp";
                } else {
                    alert("用户名或者密码错误!");
                }
            },
            error: function (result) {
                alert("出错了")
            }
        }
    );


}
