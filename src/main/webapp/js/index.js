$(function () {
    $('.nav-link.active').click();
});
var USER_ROLE=sessionStorage.getItem('userRole');
function initDataTables() {
    $('#table_id_example').on( 'init.dt', function () {
        //加载完成后的事件
        initRole();
    } ).DataTable({
        "ajax": {  //ajax方式向后台发送请求
            "type": "POST",
            "async":false,
            "url":contextPath+"/Data/listAll",
            "dataType" : "json",
            "dataSrc": "result"
        },
        "columns" : [//对接收到的json格式数据进行处理，data为json中对应的key
            {"data":"id"},
            {"data" : "team"} ,
            {"data" : "name"},
            {"data": "color"},
            {"data": "no"},
            {"data": "playerName"},
            {"data": "sNumber"},
            {"data": "mNumber"},
            {"data": "lNumber"},
            {"data": "xlNumber"},
            {"data": "xxlNumber"},
            {"data": "xxxlNumber"},
            {"data": "meno"},
            {"data":null}
        ],
        columnDefs:[{
            targets: 13,
            render: function (data, type, row, meta) {
                return '<a type="button" class="am-btn am-btn-primary am-btn-xs data-change" id="data-change" onclick="initUpdateData('+data.id+')">修 改</a><br>' +
                    '<a type="button" type="button" class="am-btn am-btn-danger am-btn-xs data-delete" id="data-delete" onclick="deleteData('+data.id+')">删 除</a>';
            }
        },
            { "orderable": false, "targets": 13 }
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
function initOperationLogs() {
    $('#table_operationLogs').on('init.dt', function () {
        $('#table_operationLogs').find("thead th:last-of-type").remove();
    }).DataTable({
        "ajax": {  //ajax方式向后台发送请求
            "type": "POST",
            "async": false,
            "url": contextPath+"/logs/getAll",
            "dataType": "json",
            "dataSrc": "result"
        },
        "columns": [//对接收到的json格式数据进行处理，data为json中对应的key
            {"data": "id"},
            {"data": "time"},
            {"data": "username"},
            {"data": "details"}
        ],
        "order": [[ 1, "desc" ]],
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
        "bAutoWidth": true,
        "destroy":true
    });
}
function initRole(){
    switch (USER_ROLE){
        case "1":
            break;
        case "2":
            $('#addClassify').attr("disabled", true);
            $("#addPro").attr("disabled",true);
            $(".data-change").attr("disabled",true);
            $(".data-delete").attr("disabled",true);
            break;
        case "3":
            $('#addClassify').attr("disabled", true);
            $("#addPro").attr("disabled",true);
            $(".data-delete").attr("disabled",true);
    }
    /*$.ajax({
        type:'post',
        async:false,
        url:contextPath+'/Login/getRole',
        success:function(result){
          var data =result.result;
          if(data=="1"){
              $("#addPro").setAttribute("disabled",true);
              $("#data-change").setAttribute("disabled",true);
              $("#data-delete").setAttribute("disabled",true);
          }
          if(data=="2"){
              $("#addPro").setAttribute("disabled",true);
          }
        },
        error:function () {
            alert("出错了！");
        }
    });*/
}
function initUpdateData(id) {
    var html = '<div class="am-g">' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-teamName" class="am-form-field am-radius" placeholder="团队名" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><select id="classfiy-select" data-am-selected>' +
        '</select>' +
        '<input id="classify-select-value" type="text" value="1" hidden></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-color" class="am-form-field am-radius" placeholder="颜色" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-no" class="am-form-field am-radius" placeholder="No" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-playerName" class="am-form-field am-radius" placeholder="球员名" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-meno" class="am-form-field am-radius" placeholder="MENO" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-m" class="am-form-field am-radius" placeholder="m" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-s" class="am-form-field am-radius" placeholder="s" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-l" class="am-form-field am-radius" placeholder="l" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-xl" class="am-form-field am-radius" placeholder="xl" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-xxl" class="am-form-field am-radius" placeholder="xxl" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-xxxl" class="am-form-field am-radius" placeholder="xxxl" /></p>' +
        '</div>' +
        '<div class="am-u-sm-12">' +
        '<button type="button" class="am-btn am-btn-success" style="margin: 0 auto;display: block" onclick="updateData('+id+')">提交</button>' +
        '</div>' +
        '</div>';
    pageUtils.showModal("修改数据", html);
    if(USER_ROLE==3){
        $("#data-teamName").attr("disabled", true);
        $("#data-color").attr("disabled", true);
        $("#data-no").attr("disabled", true);
        $("#data-playerName").attr("disabled", true);
        $("#data-meno").attr("disabled", true);
    }
    $.ajax({
        method: "post",
        url: contextPath+"/Data/listDataByID",
        data: {id: id},
        success: function (data) {
            $("#data-teamName").val(data.result.team);
            $("#data-goodID").val(data.result.goodid);
            $("#data-color").val(data.result.color);
            $("#data-no").val(data.result.no);
            $("#data-playerName").val(data.result.playerName);
            $("#data-meno").val(data.result.meno);
            $("#data-m").val(data.result.mNumber);
            $("#data-s").val(data.result.sNumber);
            $("#data-l").val(data.result.lNumber);
            $("#data-xl").val(data.result.xlNumber);
            $("#data-xxl").val(data.result.xxlNumber);
            $("#data-xxxl").val(data.result.xxxlNumber);
            $.ajax({
                method:"post",
                url:contextPath+"/good/getAll",
                success:function (res) {
                    var html="";
                    $.each(res.result, function (indexInArray, value) {
                        html+='<option value="'+value.id+'">'+value.name+'</option>'
                    });
                    var $selected = $('#classfiy-select');
                    $selected.append(html);
                    $selected.find('option').eq(data.result.goodid-1).attr('selected', true);
                    if(USER_ROLE==3){
                        $selected.attr("disabled", true);
                    }
                    $selected.selected();
                    $("#classify-select-value").attr("value",data.result.goodid);
                    $selected.on('change', function() {
                        $("#classify-select-value").attr("value",$(this).val());
                    });
                }
            })
        }
    });

}
function initAddData() {
    var html = '<div class="am-g">' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-teamName" class="am-form-field am-radius" placeholder="团队名" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><select id="classfiy-select" data-am-selected>' +
        '</select>' +
        '<input id="classify-select-value" type="text" value="1" hidden></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-color" class="am-form-field am-radius" placeholder="颜色" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-no" class="am-form-field am-radius" placeholder="No" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-playerName" class="am-form-field am-radius" placeholder="球员名" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="text" id="data-meno" class="am-form-field am-radius" placeholder="MENO" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-m" class="am-form-field am-radius" placeholder="m" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-s" class="am-form-field am-radius" placeholder="s" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-l" class="am-form-field am-radius" placeholder="l" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-xl" class="am-form-field am-radius" placeholder="xl" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-xxl" class="am-form-field am-radius" placeholder="xxl" /></p>' +
        '</div>' +
        '<div class="am-u-sm-6">' +
        '<p><input type="number" id="data-xxxl" class="am-form-field am-radius" placeholder="xxxl" /></p>' +
        '</div>' +
        '<div class="am-u-sm-12">' +
        '<button type="button" class="am-btn am-btn-success" style="margin: 0 auto;display: block" onclick="addData()">提交</button>' +
        '</div>' +
        '</div>';
    pageUtils.showModal("新增数据", html);
    $.ajax({
        method:"post",
        url:contextPath+"/good/getAll",
        success:function (data) {
            var html="";
            $.each(data.result, function (indexInArray, value) {
                html+='<option value="'+value.id+'">'+value.name+'</option>'
            });
            var $selected = $('#classfiy-select');
            $selected.append(html);
            $selected.selected();
            $selected.on('change', function() {
                $("#classify-select-value").attr("value",$(this).val());
            });
        }
    })
}
function updateData(id) {
    var teamName = $("#data-teamName").val();
    var goodID = $("#classify-select-value").val();
    var color = $("#data-color").val();
    var no = $("#data-no").val();
    var playerName = $("#data-playerName").val();
    var meno = $("#data-meno").val();
    var m = $("#data-m").val();
    var s = $("#data-s").val();
    var l = $("#data-l").val();
    var xl = $("#data-xl").val();
    var xxl = $("#data-xxl").val();
    var xxxl = $("#data-xxxl").val();
    $.ajax({
        method:"post",
        url:contextPath+"/Data/update",
        data:{id:id,team:teamName,goodid:goodID,color:color,no:no,playerName:playerName,sNumber:s,mNumber:m,
            lNumber:l,xlNumber:xl,xxlNumber:xxl,xxxlNumber:xxxl,meno:meno},
        success:function (data) {
            alert("修改数据成功！");
            pageUtils.closeModal();
            initDataTables();
            initOperationLogs();
        }
    })
}
function addData() {
    var teamName = $("#data-teamName").val();
    var goodID = $("#classify-select-value").val();
    var color = $("#data-color").val();
    var no = $("#data-no").val();
    var playerName = $("#data-playerName").val();
    var meno = $("#data-meno").val();
    var m = $("#data-m").val();
    var s = $("#data-s").val();
    var l = $("#data-l").val();
    var xl = $("#data-xl").val();
    var xxl = $("#data-xxl").val();
    var xxxl = $("#data-xxxl").val();
    $.ajax({
        method:"post",
        url:contextPath+"/Data/add",
        data:{team:teamName,goodid:goodID,color:color,no:no,playerName:playerName,sNumber:s,mNumber:m,
            lNumber:l,xlNumber:xl,xxlNumber:xxl,xxxlNumber:xxxl,meno:meno},
        success:function (data) {
            alert("新增数据成功！");
            pageUtils.closeModal();
            initDataTables();
            initOperationLogs();
        },
        error:function (jqXHR, textStatus, errorThrown) {
            alert('发生错误!' + textStatus);
        }
    })
}
function deleteData(id) {
    var $confirm = $('#my-confirm');
    $confirm.find('.am-modal-hd').html("数据删除");
    $confirm.find('.am-modal-bd').html("确认删除么亲？");
    $confirm.modal({
        relatedTarget: this,
        onConfirm: function(options) {
            $.ajax({
                method:"post",
                url:contextPath+"/Data/delete",
                data:{id:id},
                success:function (data) {
                    alert("删除数据成功！");
                    pageUtils.closeModal();
                    initDataTables();
                    initOperationLogs();
                }
            })
        },
        // closeOnConfirm: false,
        onCancel: function() {
        }
    });

}
function initClassify() {
    var html = '<div class="am-g">' +
        '<div class="am-u-sm-12">' +
        '<p><input type="text" id="classify-input" class="am-form-field am-radius" placeholder="输入新增分类" /></p>' +
        '</div>' +
        '<div class="am-u-sm-12">' +
        '<button type="button" class="am-btn am-btn-success" style="margin: 0 auto;display: block" onclick="addClassify()">提交</button>' +
        '</div>' +
        '</div>';
    pageUtils.showModal("新增分类", html);
}
function addClassify() {
    var $new_classfiy = $("#classify-input").val();
    $.ajax({
        method:"post",
        url:contextPath+"/good/add",
        data:{name:$new_classfiy},
        success:function (data) {
            alert("新增分类成功！");
            pageUtils.closeModal();
            initOperationLogs();
        },
        error:function (jqXHR, textStatus, errorThrown) {
            alert('发生错误!' + textStatus);
        }
    })
}

function returnLogin() {
    window.location.href = "http://localhost:8080/";
}