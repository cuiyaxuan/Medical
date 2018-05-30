$(function () {
    initTablePatientManage();
});

function initTablePatientManage() {
    $('#table_doctor_patient').DataTable({
        ajax: {  //ajax方式向后台发送请求
            "type": "POST",
            "async": false,
            "url": contextPath + "/doctor/listAllPatient",
            "dataType": "json",
            "dataSrc": "result",
            "data": {}
        },
        columns: [//对接收到的json格式数据进行处理，data为json中对应的key
            {
                "data": "pname",
            },
            {
                "data": "psex",
                "render": function(data,type,row,meta){
                    if(data==1) {
                        return data = '男';
                    }
                    return data = '女';
                }
            },
            {"data": "page"},
            {"data": "porigin"},
            {
                "data": "pmarriage",
                "render": function(data,type,row,meta){
                    if(data==1) {
                        return data = '是';
                    }
                    return data = '否';
                }
            },
            {"data": "pbirthplace"},
            {"data": "pworkplace"},
            {"data": "pwork"},
            {"data": "pliveplace"},
            {"data": "phistory"},
            {"data": "padmissiontime"},
            {"data": "phistorytime"},
            {"data": null}
        ],
        columnDefs: [{
            targets: 12,
            render: function (data, type, row, meta) {
                return '<div class="doc-dropdown-justify-js">\n' +
                    '  <div class="am-dropdown doc-dropdown-js" style="min-width: 100px">\n' +
                    '    <button class="am-btn am-btn-danger am-dropdown-toggle">操 作  <span class="am-icon-caret-down"></span></button>\n' +
                    '    <div class="am-dropdown-content" style="padding: 0 !important;">' +
                    '  <ul class="am-list am-list-border" style="margin-bottom: 0 !important;">\n' +
                    '    <li><a href="javaScript:void(0)" onclick="initDeletePatientModal(' + data.id + ')">出院</a></li>\n' +
                    '    <li><a href="javaScript:void(0)" onclick="initUpdatePatientModal(' + data.id + ')">修改</a></li>\n' +
                    '    <li><a href="javaScript:void(0)" onclick="initAddPatientRecordModal(' + data.id + ')">新增病历</a></li>\n' +
                    '  </ul>' +
                    '</div>\n' +
                    '  </div>\n' +
                    '</div>'
            }
        },
            {"orderable": false, "targets": 12}
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
        fnInitComplete: function (oSettings, json) {
            $('#table_record_scanned').addClass('table-layout-fixed');
            $('#table_record_scanned td:not(:last-of-type)').addClass("text-one-line");
            $('.doc-dropdown-js').dropdown({justify: '.doc-dropdown-justify-js'});
        }
    });
}

function initAddModal() {
    var html = '<form class="am-form tpl-form-line-form" id="patient-form">\n' +
        '        <div class="am-g">\n' +
        '            <div class="am-u-sm-12 am-u-end">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">入院时间 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <p><input type="text" class="am-form-field" id="patient-dataTimePicker" placeholder="入院时间" data-am-datepicker="{theme: \'success\'}" readonly/></p>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pname" transmit="true" class="tpl-form-input"  placeholder="姓名">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">性别</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
/*        '                        <input type="text" name="psex" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +*/
        '<select name="psex" transmit="true">' +
        '<option value="1">男</option>' +
        '<option value="2">女</option>' +
        '</select>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">年龄 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="page" transmit="true" class="tpl-form-input"  placeholder="年龄">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">籍贯 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="porigin" transmit="true" class="tpl-form-input"  placeholder="籍贯">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">婚否 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '<select name="pmarriage" transmit="true">' +
        '<option value="1">是</option>' +
        '<option value="2">否</option>' +
        '</select>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">出生地 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pbirthplace" transmit="true" class="tpl-form-input"  placeholder="出生地">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">工作单位 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pworkplace" transmit="true" class="tpl-form-input"  placeholder="工作单位">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">职业</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pwork" transmit="true" class="tpl-form-input"  placeholder="职业">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">户口</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pliveplace" transmit="true" class="tpl-form-input"  placeholder="户口">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">供史者</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text"name="phistory" transmit="true" class="tpl-form-input"  placeholder="供史者">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </form>' +
        '<div class="center-button">' +
        '<button type="button" onclick="pageUtils.closeModal()" class="am-btn am-btn-danger">取消</button>\n' +
        '<button type="button" onclick="addNewPatient()" class="am-btn am-btn-success">确定</button>'
    '</div>';

    pageUtils.showModal('新增病人', html);
    $('#patient-dataTimePicker').datepicker({format: 'yyyy-mm-dd', endDate: new Date()});
}

function addNewPatient() {
    var json = commonSerializeForm("patient-form");
    var time = commonUtils.convertDateFromString($('#patient-dataTimePicker').val());
    json.padmissiontime = time;
    console.log(json);
    $.ajax({
        type: "post",
        url: contextPath + "/doctor/add",
        data: json,
        success: function (result) {
            pageUtils.closeModal();
            alert("添加成功");
            initTablePatientManage();

        },
        error: function () {

        }
    });


}

function deletePatient(id) {
    $.ajax({
        type: "post",
        url: contextPath + "/doctor/delete",
        data: {id: id},
        async: false,
        success: function (result) {
            alert("出院成功！");
            initTablePatientManage();
        },
        error: function () {

        }
    });
}

function initDeletePatientModal(id) {
    var r = confirm("确定出院？");
    if(r==true){
        deletePatient(id);
    }
}

function updatePatient(id) {
    var json = commonSerializeForm("patient-form");
    var time = commonUtils.convertDateFromString($('#patient-update-dataTimePicker').val());
    json.padmissiontime = time;
    json.id = id;
    $.ajax({
        type: "post",
        url: contextPath + "/doctor/update",
        data: json,
        success: function (result) {
            pageUtils.closeModal();
            alert("编辑成功！");
            initTablePatientManage();
        },
        error: function () {

        }
    });
}

function initUpdatePatientModal(id) {
    var html = '<form class="am-form tpl-form-line-form" id="patient-form">\n' +
        '        <div class="am-g">\n' +
        '            <div class="am-u-sm-12 am-u-end">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">入院时间 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <p><input type="text" class="am-form-field" id="patient-update-dataTimePicker" placeholder="入院时间" data-am-datepicker="{theme: \'success\'}" readonly/></p>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pname" transmit="true" class="tpl-form-input"  placeholder="姓名">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">性别</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        /*        '                        <input type="text" name="psex" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +*/
        '<select name="psex" transmit="true">' +
        '<option value="1">男</option>' +
        '<option value="2">女</option>' +
        '</select>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">年龄 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="page" transmit="true" class="tpl-form-input"  placeholder="年龄">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">籍贯 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="porigin" transmit="true" class="tpl-form-input"  placeholder="籍贯">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">婚否 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '<select name="pmarriage" transmit="true">' +
        '<option value="1">是</option>' +
        '<option value="2">否</option>' +
        '</select>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">出生地 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pbirthplace" transmit="true" class="tpl-form-input"  placeholder="出生地">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">工作单位 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pworkplace" transmit="true" class="tpl-form-input"  placeholder="工作单位">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">职业</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pwork" transmit="true" class="tpl-form-input"  placeholder="职业">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">户口</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pliveplace" transmit="true" class="tpl-form-input"  placeholder="户口">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-12">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">供史者</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text"name="phistory" transmit="true" class="tpl-form-input"  placeholder="供史者">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </form>' +
        '<div class="center-button">' +
        '<button type="button" onclick="pageUtils.closeModal()" class="am-btn am-btn-danger">取消</button>\n' +
        '<button type="button" onclick="updatePatient(' + id + ')" class="am-btn am-btn-success">确定</button>'
    '</div>';
    pageUtils.showModal('编辑病人', html);
    $('#patient-update-dataTimePicker').datepicker({format: 'yyyy-mm-dd', endDate: new Date()});
    $.ajax({
        type: "post",
        url: contextPath + "/doctor/getOnePatientById",
        data: {id: id},
        success: function (data) {
            console.log(data);
            $.each(data.result, function (name, value) {
                $('#patient-form input[name=' + name + ']').val(value);
                if(name==="psex") {
                    $('#patient-form select[name=' + name + ']').val(value);
                }
                if(name==="pmarriage") {
                    $('#patient-form select[name=' + name + ']').val(value);
                }
            })
        },
        error: function () {

        }
    });
}

function initAddPatientRecordModal(id) {
    var html = '<form class="am-form tpl-form-line-form" id="patient-record-form">\n' +
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
        // '                        <input type="text"  class="tpl-form-input"  placeholder="部门">\n' +
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
        '                </select>'+
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
        '<button type="button" onclick="addNewPatientRecord(' + id + ')" class="am-btn am-btn-success">确定</button>'
    '</div>';
    pageUtils.showModal('添加病历', html);
}

function addNewPatientRecord(pid) {
    var json = commonSerializeForm("patient-record-form");
    json.pid = pid;
    $.ajax({
        type: "post",
        url: contextPath + "/doctor/addRecord",
        data: json,
        success: function (result) {
            pageUtils.closeModal();
            alert("添加成功");
            initTablePatientManage();

        },
        error: function () {

        }
    });
}

