$(function () {
    initTableRecordDoctor();
});

function initTableRecordDoctor() {
    $('#table_doctor_record').DataTable({
        ajax: {  //ajax方式向后台发送请求
            "type": "POST",
            "async": false,
            "url": contextPath + "/doctor/listAllRecord",
            "dataType": "json",
            "dataSrc": "result",
        },
        columns: [//对接收到的json格式数据进行处理，data为json中对应的key
            {"data": "pname"},
            {"data": "rcomplain"},
            {"data": "rpresent"},
            {"data": "rhistory"},
            {"data": "rperson"},
            {"data": "rmarriage"},
            {"data": "rfamily"},
            {"data": "d_name"},
            {"data": "state"},
            {"data": "pass"},
            {"data": null}
        ],
        columnDefs: [{
            targets: 10,
            render: function (data, tclassype, row, meta) {
                return '<div class="doc-dropdown-justify-js">\n' +
                    '  <div class="am-dropdown doc-dropdown-js" style="min-width: 100px">\n' +
                    '    <button class="am-btn am-btn-danger am-dropdown-toggle">操作 <span class="am-icon-caret-down"></span></button>\n' +
                    '    <div class="am-dropdown-content" style="padding: 0 !important;">' +
                    '  <ul class="am-list am-list-border" style="margin-bottom: 0 !important;">\n' +
                    '    <li><a href="javaScript:void(0)" onclick="initUpdatePatientRecordModal(' + data.pid + ','+data.id+')">修改</a></li>\n' +
                    '    <li><a href="javaScript:void(0)" onclick="commonUtils.downloadRecord('+data.id+')">下载病历</a></li>\n' +
                    '  </ul>' +
                    '</div>\n' +
                    '  </div>\n' +
                    '</div>'
            }
        },
            {"orderable": false, "targets": 10}
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
        destroy: true,
        autoWidth: false,
        // "sScrollX": "100%",
        // "bScrollCollapse": true,
        fnInitComplete: function (oSettings, json) {
            $('#table_doctor_record').addClass('table-layout-fixed');
            $('#table_doctor_record td:not(:last-of-type)').addClass("text-one-line");
            $('.doc-dropdown-js').dropdown({justify: '.doc-dropdown-justify-js'});
        }
    });

}

/**
 * 加载修改modal
 */
function initUpdatePatientRecordModal(pid,id) {
    var html = '<form class="am-form tpl-form-line-form" id="doctor-record-form">\n' +
        '<input name="pid" transmit="true" hidden>' +
        '        <div class="am-g">\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">主诉</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="rcomplain" transmit="true" class="tpl-form-input"  placeholder="主诉">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">现病史 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="rpresent" transmit="true" class="tpl-form-input"  placeholder="现病史">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">既往史 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="rhistory" transmit="true" class="tpl-form-input"  placeholder="既往史">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">个人史 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="rperson" transmit="true" class="tpl-form-input"  placeholder="个人史">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">婚育史 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="rmarriage" transmit="true" class="tpl-form-input"  placeholder="婚育史">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">家族史 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="rfamily" transmit="true" class="tpl-form-input"  placeholder="家族史">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">部门 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '<select name="rdepartment" transmit="true" class="patient-department-select" data-am-selected>\n' +
        '                    <option value="0" selected>所有科室</option>\n' +
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
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">是否传染</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '<select name="rinfaction" transmit="true">' +
        '<option value="1">是</option>' +
        '<option value="2">否</option>' +
        '</select>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </form>' +
        '<div class="center-button">' +
        '<button type="button" onclick="pageUtils.closeModal()" class="am-btn am-btn-danger">取消</button>\n' +
        '<button type="button" onclick="updatePatientRecord(' + id + ')" class="am-btn am-btn-success">确定</button>'
    '</div>';
    pageUtils.showModal('编辑病历', html);
    $.ajax({
        method: 'post',
        url: contextPath + '/doctor/getOneRecordByPid',
        data: {id: pid},
        success: function (data) {
            console.log(data);
            $.each(data.result, function (name, value) {
                $('#doctor-record-form input[name=' + name + ']').val(value);
                if (name == "rdepartment" || name == "rinfaction") {
                    $('#doctor-record-form select[name=' + name + ']').val(value);
                }
            })
        },
        error: function () {

        }
    })
}

function updatePatientRecord(id) {
    var json = commonSerializeForm("doctor-record-form");
    json.id = id;

    $.ajax({
        type: "post",
        url: contextPath + "/doctor/updateRecord",
        data: json,
        success: function (result) {
            pageUtils.closeModal();
            alert("编辑成功！");
            initTableRecordDoctor();
        },
        error: function () {

        }
    });
}

function downloadRecord(recordId) {
    $ajax(contextPath+'record/downloadRecordWord',{
        id:recordId
    },function (res) {
        if(res==="200") {
            pageUtils.showAlert("提示", res.message);
        }
    },function (err) {
        pageUtils.showAlert('提示', res.message);
    })
}