$(function () {
    getUserInfo();
});

/**
 * 初始化权限
 * @param role 1为普通用户 2是管理员
 */
function initRole(role) {
    if (role == 1) {
        $('.tpl-left-nav-menu li[data-type="medicalRecord"]').hide();
        $('.tpl-left-nav-menu li[data-type="permission"]').hide();
    }
}

/**
 * 初始化类型
 * @param type 1为医生 2为护士
 */
function initType(type) {
    if (type == 1) {
        $('.tpl-left-nav-menu li[data-type="doctor"]').show();
        $('.tpl-left-nav-menu li[data-type="nurse"]').hide();
    } else if (type == 2) {
        $('.tpl-left-nav-menu li[data-type="doctor"]').hide();
        $('.tpl-left-nav-menu li[data-type="nurse"]').show();
    }
}

//查询用户信息
function getUserInfo() {
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

/**
 * 点击个人资料时弹出模态框
 */
function getUserInfoModal() {
    let html = '';
    $.ajax({
            type: 'post',
            async: false,
            url: './Login/getUserInfo',
            success: function (data) {
                let state = data.state;
                if (state === "200") {
                    html = '<form class="am-form tpl-form-line-form" id="user-info-form">\n' +
                        '        <div class="am-g">\n' +
                        '            <div class="am-u-sm-12">\n' +
                        '                <div class="am-form-group">\n' +
                        '                    <label for="user-name" class="am-u-sm-3 am-form-label">真实姓名</label>\n' +
                        '                    <div class="am-u-sm-9">\n' +
                        '                        <input type="text" name="realname" transmit="true" class="tpl-form-input form-input"  placeholder="真实姓名">\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>' +
                        '            <div class="am-u-sm-12">\n' +
                        '                <div class="am-form-group">\n' +
                        '                    <label for="user-name" class="am-u-sm-3 am-form-label">性别</label>\n' +
                        '                    <div class="am-u-sm-9">\n' +
                        '<select name="sex" transmit="true">' +
                        '<option value="1">男</option>' +
                        '<option value="2">女</option>' +
                        '</select>' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="am-u-sm-12">\n' +
                        '                <div class="am-form-group">\n' +
                        '                    <label for="user-name" class="am-u-sm-3 am-form-label">所属部门</label>\n' +
                        '                    <div class="am-u-sm-9">\n' +
                        '                        <input type="text" name="departmentname" transmit="true" class="tpl-form-input form-input" disabled placeholder="所属部门">\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>' +
                        '            <div class="am-u-sm-12">\n' +
                        '                <div class="am-form-group">\n' +
                        '                    <label for="user-name" class="am-u-sm-3 am-form-label">用户权限</label>\n' +
                        '                    <div class="am-u-sm-9">\n' +
                        '                        <input type="text" name="role" transmit="true" class="tpl-form-input form-input" disabled placeholder="用户权限">\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>' +
                        '            <div class="am-u-sm-12">\n' +
                        '                <div class="am-form-group">\n' +
                        '                    <label for="user-name" class="am-u-sm-3 am-form-label">用户身份</label>\n' +
                        '                    <div class="am-u-sm-9">\n' +
                        '                        <input type="text" name="type" transmit="true" class="tpl-form-input form-input" disabled placeholder="用户身份">\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>' +
                        '            <div class="am-u-sm-12">' +
                        '                <div class="am-form-group">\n' +
                        '                    <label for="user-name" class="am-u-sm-3 am-form-label">用户头像</label>\n' +
                        '                    <div class="am-u-sm-9" style="height: 200px">\n' +
                        '                         <img class="am-img-circle am-img-thumbnail" id="headImgUpload" style="cursor: pointer" src=""  alt="" width="200px" onerror="imgOnError()" onclick="uploadUserInfoImg()">\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>' +
                        '          </div>' +
                        '</form>'
                } else {
                    alert(data.message);
                }
            },
            error: function (result) {
                alert("出错了")
            }
        }
    );
    pageUtils.showModal('修改个人资料', html);
    getUserInfoByLoginId();
}

function getUserInfoByLoginId() {
    $.ajax({
            type: 'post',
            async: false,
            url: './user/getUserInfo',
            success: function (data) {
                let state = data.state;
                let result = data.result;
                if (state === "200") {
                    let $form = $('#user-info-form');
                    $form.find('input[name="realname"]').val(result.realname);
                    $form.find('select[name="sex"]').val(result.sex);
                    $form.find('input[name="departmentname"]').val(result.d_name);
                    $form.find('input[name="role"]').val(result.role===2?'管理员':'普通用户');
                    $form.find('input[name="type"]').val(result.type===2?'护士':'医生');
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
    if (r == true) {
        window.location.href = "http://localhost:8080/";
    }
    else {
        pageUtils.showAlert('提示', '操作已取消！');
    }

}

/**********************************图片上传 开始*********************************************/
function uploadUserInfoImg() {
    $("#doc-modal-1").modal({width: '600px'});
    'use strict';
    // 初始化
    var $image = $('#image');
    $image.cropper('destroy');
    $image.cropper({
        viewMode: 1,
        aspectRatio: 1,
        autoCropArea: 1,
        preview: '.up-pre-after',
        zoomRatio: 1
    });

    // 事件代理绑定事件
    $('.docs-buttons').on('click', '[data-method]', function () {

        var $this = $(this);
        var data = $this.data();
        var result = $image.cropper(data.method, data.option, data.secondOption);
        switch (data.method) {
            case 'getCroppedCanvas':
                if (result) {
                    // 显示 Modal
                    $('#cropped-modal').modal().find('.am-modal-bd').html(result);
                    $('#download').attr('href', result.toDataURL('image/jpeg'));
                }
                break;
        }
    });


    // 上传图片
    var $inputImage = $('#inputImage');
    var URL = window.URL || window.webkitURL;
    var blobURL;

    if (URL) {
        $inputImage.change(function () {
            var files = this.files;
            var file;

            if (files && files.length) {
                file = files[0];

                if (/^image\/\w+$/.test(file.type)) {
                    blobURL = URL.createObjectURL(file);
                    $image.one('built.cropper', function () {
                        // Revoke when load complete
                        URL.revokeObjectURL(blobURL);
                    }).cropper('reset').cropper('replace', blobURL);
                    $inputImage.val('');
                } else {
                    window.alert('Please choose an image file.');
                }
            }

            // Amazi UI 上传文件显示代码
            var fileNames = '';
            $.each(this.files, function () {
                fileNames += '<span class="am-badge">' + this.name + '</span> ';
            });
            $('#file-list').html(fileNames);
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }

    //绑定上传事件
    $('#up-btn-ok').off('click').on('click', function () {
        var $modal = $('#my-modal-loading');
        var $modal_alert = $('#my-alert');
        var img_src = $image.attr("src");
        if (img_src == "") {
            set_alert_info("没有选择上传的图片");
            $modal_alert.modal();
            return false;
        }

        $modal.modal();

        var canvas = $("#image").cropper('getCroppedCanvas');
        var data = canvas.toDataURL(); //转成base64
        var formData = new FormData();
        formData.append("imgBase64", data.toString());
        formData.append("fileName", "photo.png");
        $.ajax({
            url: contextPath + "/user/uploadImg",
            dataType: 'json',
            method: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function(data){
                $modal.modal('close');
                if("200"==data.state){
                    let result=data.result;
                    $("#doc-modal-1").modal('close');
                    $('#headImgUpload').attr('src', result);
                    $('#headImgFileUrl').val(result);
                    pageUtils.showAlert('提示','上传成功！');
                }else{
                    pageUtils.showAlert('提示','上传失败,请稍后重试');
                }
            },
            error: function(){
                $modal.modal('close');
                pageUtils.showAlert('提示','上传失败,请稍后重试');
                //console.log('Upload error');
            }
        });

    });

}
function rotateImgRight() {
    $("#image").cropper('rotate', 90);
}


function rotateImgLeft() {
    $("#image").cropper('rotate', -90);
}

function set_alert_info(content) {
    $("#alert_content").html(content);
}
/**********************************图片上传 结束*********************************************/
//凉凉夜色为你思念成河

