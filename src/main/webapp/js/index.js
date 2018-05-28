$(function () {
    getUserInfo();
});

/**
 * 初始化权限
 * @param role 1为普通用户 2是管理员
 */
function initRole(role) {
    if(role==1) {
        $('.tpl-left-nav-menu li[data-type="medicalRecord"]').hide();
        $('.tpl-left-nav-menu li[data-type="permission"]').hide();
    }
}

/**
 * 初始化类型
 * @param type 1为医生 2为护士
 */
function initType(type) {
    if(type==1) {
        $('.tpl-left-nav-menu li[data-type="doctor"]').show();
        $('.tpl-left-nav-menu li[data-type="nurse"]').hide();
    }else if(type==2) {
        $('.tpl-left-nav-menu li[data-type="doctor"]').hide();
        $('.tpl-left-nav-menu li[data-type="nurse"]').show();
    }
}

//查询用户信息
function  getUserInfo() {
    $.ajax({
            type: 'post',
            async: false,
            url: './Login/getUserInfo',
            success: function (data) {
                let state = data.state;
                if (state === "200") {
                    //获取role
                    sessionStorage.setItem('userRoleId', data.result.role);
                    sessionStorage.setItem('userTypeId', data.result.type);
                    sessionStorage.setItem('realName', data.result.realname);
                    initRole(data.result.role);
                    initType(data.result.type);
                    $('.realName').html(data.result.realname);
                } else {
                    alert(data.message);
                }
            },
            error: function (result) {
                alert("出错了")
            }
        }
    );
}

function returnLogin() {
    var r = confirm("是否退出本系统？");
    if (r==true)
    {
        window.location.href = "http://localhost:8080/";
    }
    else
    {
        pageUtils.showAlert('提示', '操作已取消！');
    }

}

//凉凉夜色为你思念成河

