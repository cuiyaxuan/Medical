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
            {"data": "pname"},
            {"data": "psex"},
            {"data": "page"},
            {"data": "porigin"},
            {"data": "pmarriage"},
            {"data": "pbirthplace"},
            {"data": "pworkplace"},
            {"data": "pwork"},
            {"data": "pliveplace"},
            {"data": "phistory"},
            {"data": "padmissiontime"},
            {"data": "phistorytime"},
            {"data":null}
        ],
        columnDefs: [{
                targets: 12,
                render: function (data, type, row, meta) {
                    return '<div class="doc-dropdown-justify-js">\n' +
                        '  <div class="am-dropdown doc-dropdown-js" style="min-width: 100px">\n' +
                        '    <button class="am-btn am-btn-danger am-dropdown-toggle">操 作<span class="am-icon-caret-down"></span></button>\n' +
                        '    <div class="am-dropdown-content">' +
                        '  <ul class="" >\n' +
                        '    <li><a href="javaScript:void(0)" onclick="initDeletePatientModal('+data.id+')">出院</a></li>\n' +
                        '    <li class="am-active"><a href="javaScript:void(0)" onclick="initUpdatePatientModal('+data.id+')">修改</a></li>\n' +
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
        fnInitComplete:function (oSettings, json) {
            $('#table_record_scanned').addClass('table-layout-fixed');
            $('#table_record_scanned td:not(:last-of-type)').addClass("text-one-line");
            $('.doc-dropdown-js').dropdown({justify: '.doc-dropdown-justify-js'});
        }
    });
}

function initAddModal() {
    var html = '<form class="am-form tpl-form-line-form" id="patient-form">\n' +
        '        <div class="am-g">\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pname" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">性别 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="psex" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">年龄 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="page" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">籍贯 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="porigin" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">婚否 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pmarriage" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">出生地 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pbirthplace" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">工作单位 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pworkplace" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">职业</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pwork" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">户口</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pliveplace" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">供史者</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text"name="phistory" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6 am-u-end">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">入院时间 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <p><input type="text" class="am-form-field" id="patient-dataTimePicker" placeholder="日历组件" data-am-datepicker="{theme: \'success\'}" readonly/></p>' +
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
    $('#patient-dataTimePicker').datepicker({format: 'yyyy-mm-dd',endDate:new Date()});
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
        data: {id:id},
        async:false,
        success: function (result) {
            alert("出院成功！");
            initTablePatientManage();
        },
        error: function () {

        }
    });
}
function initDeletePatientModal(id) {
    var $confirm = $('#my-confirm');
    $confirm.find('.am-modal-hd').html(p_title);
    $confirm.find('.am-modal-bd').html(p_content);
    $confirm.modal({
        relatedTarget: this,
        onConfirm: function (options) {
            deletePatient(id)
        },
        // closeOnConfirm: false,
        onCancel: function () {
        }
    });
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
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pname" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">性别 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="psex" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">年龄 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="page" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">籍贯 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="porigin" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">婚否 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pmarriage" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">出生地 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pbirthplace" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">工作单位 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pworkplace" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">职业</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pwork" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">户口</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" name="pliveplace" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">供史者</label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text"name="phistory" transmit="true" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6 am-u-end">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">入院时间 </label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <p><input type="text" name="padmissiontime" class="am-form-field" id="patient-update-dataTimePicker" placeholder="日历组件" data-am-datepicker="{theme: \'success\'}" readonly/></p>' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </form>' +
        '<div class="center-button">' +
        '<button type="button" onclick="pageUtils.closeModal()" class="am-btn am-btn-danger">取消</button>\n' +
        '<button type="button" onclick="updatePatient('+id+')" class="am-btn am-btn-success">确定</button>'
    '</div>';
    pageUtils.showModal('编辑病人', html);
    $('#patient-update-dataTimePicker').datepicker({format: 'yyyy-mm-dd',endDate:new Date()});
    $.ajax({
        type: "post",
        url: contextPath + "/doctor/getOnePatientById",
        data: {id:id},
        success: function (data) {
            console.log(data);
            $.each(data.result,function (name,value) {
                $('#patient-form input[name=' + name + ']').val(value);
            })
        },
        error: function () {

        }
    });
}

