$(function () {
    initRoleTable();
});

/**
 * 加载权限管理表格
 */
function initRoleTable() {
    $('#table_user_role').DataTable({
        ajax: {  //ajax方式向后台发送请求
            "type": "POST",
            "async": false,
            "url": contextPath + "/role/listUserRole",
            "dataType": "json",
            "dataSrc": "result"
        },
        columns: [//对接收到的json格式数据进行处理，data为json中对应的key
            {"data": "realname"},
            {"data": "sex"},
            {"data": "d_name"},
            {"data": "csType"},
            {"data": "csRole"},
            {"data": null}
        ],
        columnDefs: [{
            targets: 5,
            render: function (data, type, row, meta) {
                return '<a type="button" class="am-btn am-btn-primary am-btn-xs data-change" onclick="updateUserRole(' + data.id + ',' + data.role + ')">修 改</a><br>';
            }
        },
            {"orderable": false, "targets": 5}
        ],
        language: {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            }
        },
        destroy: true
    });
}

/**
 * 弹出confirm框 是否改变权限
 * @param result
 */
function updateUserRole(id, role) {
    var confirmContent = "";
    role == 1 ? confirmContent = "您确认将该普通用户更改为管理员吗？" : confirmContent = "您确认将该管理员更改为普通用户吗？";
    var $confirm = $('#my-confirm');
    $confirm.find('.am-modal-hd').html('提示');
    $confirm.find('.am-modal-bd').html(confirmContent);
    $confirm.modal({
        relatedTarget: this,
        onConfirm: function (options) {
            changeUserRole(id, role);
        },
        // closeOnConfirm: false,
        onCancel: function () {
        }
    });
}

/**
 * confirm yes 事件，修改用户权限
 */
function changeUserRole(id, role) {
    $.ajax({
        method: "post",
        data: {id: id, role: role},
        url: contextPath + "/role/updateUserRole",
        success: function (data) {
            pageUtils.showAlert('提示', data.message);
            initRoleTable();
        },
        error: function () {
            pageUtils.showAlert('提示', 'ajax Error!');
        }
    })
}

/**
 * 分配权限
 */
function initAddLoginUserModal() {
    let html = '<form class="am-form tpl-form-line-form" id="user-form">\n' +
        '        <div class="am-g">\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">用户名</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="userName" transmit="true" class="tpl-form-input"  placeholder="用户名">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">性别</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        /*        '                        <input type="text" name="psex" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +*/
        '<select name="sex" transmit="true">' +
        '<option value="1">男</option>' +
        '<option value="2">女</option>' +
        '</select>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">所属科室 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '<select  name="departmentid" transmit="true" class="patient-department-select" data-am-selected>\n' +
        '                    <option value="0" selected>请选择科室</option>\n' +
        '                    <option value="1">内科</option>\n' +
        '                    <option value="2">外科</option>\n' +
        '                    <option value="3">妇产科</option>\n' +
        '                    <option value="4">男科</option>\n' +
        '                    <option value="5">儿科</option>\n' +
        '                    <option value="6">五官科</option>\n' +
        '                    <option value="7">皮肤科</option>\n' +
        '                    <option value="8">传染病科</option>\n' +
        '                    <option value="9">肛肠科</option>\n' +
        '                </select>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">性别</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        /*        '                        <input type="text" name="psex" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +*/
        '<select name="type" transmit="true">' +
        '<option value="1">医生</option>' +
        '<option value="2">护士</option>' +
        '</select>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </form>' +
        '<div class="center-button">' +
        '<button type="button" onclick="pageUtils.closeModal()" class="am-btn am-btn-danger">取消</button>\n' +
        '<button type="button" onclick="addLoginUser()" class="am-btn am-btn-success">确定</button>'
    '</div>';
    pageUtils.showModal('分配账户', html);
    $('#common-modal').css({height: "50vh"})
}

function addLoginUser() {
    let json = commonSerializeForm("user-form");
    console.log(json);
    $ajax(contextPath + "user/add", json, function (res) {
        pageUtils.showAlert('提示', res.message);
        pageUtils.closeModal();
    }, function (err) {
    })
}