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
            "async":false,
            "url":contextPath+"/role/listUserRole",
            "dataType" : "json",
            "dataSrc": "result"
        },
        columns : [//对接收到的json格式数据进行处理，data为json中对应的key
            {"data":"realname"},
            {"data":"sex"} ,
            {"data":"d_name"},
            {"data":"csType"},
            {"data":"csRole"},
            {"data":null}
        ],
        columnDefs:[{
            targets: 5,
            render: function (data, type, row, meta) {
                return '<a type="button" class="am-btn am-btn-primary am-btn-xs data-change" onclick="updateUserRole(' + data.id + ','+data.role+')">修 改</a><br>';
            }
        },
            { "orderable": false, "targets": 5 }
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
function updateUserRole(id,role) {
    var confirmContent = "";
    role == 1 ? confirmContent = "您确认将该普通用户更改为管理员吗？" : confirmContent = "您确认将该管理员更改为普通用户吗？";
    var $confirm = $('#my-confirm');
    $confirm.find('.am-modal-hd').html('提示');
    $confirm.find('.am-modal-bd').html(confirmContent);
    $confirm.modal({
        relatedTarget: this,
        onConfirm: function(options) {
            changeUserRole(id,role);
        },
        // closeOnConfirm: false,
        onCancel: function() {
        }
    });
}

/**
 * confirm yes 事件，修改用户权限
 */
function changeUserRole(id,role) {
    $.ajax({
        method: "post",
        data:{id:id, role: role},
        url: contextPath + "/role/updateUserRole",
        success: function (data) {
            pageUtils.showAlert('提示', data.message);
            initRoleTable();
        },
        error:function () {
            pageUtils.showAlert('提示', 'ajax Error!');
        }
    })
}