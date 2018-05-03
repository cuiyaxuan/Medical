
$(function () {
    initTablePatientManage();
});

function initTablePatientManage() {
    $('#table_nurse_patient').DataTable({
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
            {'data':null}
        ],
        columnDefs: [{
            targets: 12,
            render: function (data, type, row, meta) {
                console.log(data);
                return '<div class="doc-dropdown-justify-js">\n' +
                    '  <div class="am-dropdown doc-dropdown-js" style="min-width: 100px">\n' +
                    '    <button class="am-btn am-btn-danger am-dropdown-toggle">操 作<span class="am-icon-caret-down"></span></button>\n' +
                    '    <div class="am-dropdown-content">' +
                    '  <ul class="" >\n' +
                    '    <li><a href="javaScript:void(0)" onclick="initAddNewClericalModal('+data.id+')">添加护理文书</a></li>\n' +
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

function initAddNewClericalModal(id) {
    var html = '<form class="am-form tpl-form-line-form" id="nurse-clerical-form">\n' +
        '<input name="pid" transmit="true" hidden>' +
        '        <div class="am-g">\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class=" am-form-label">体温记录</label>\n' +
        '                        <input type="text" name="dtemp" transmit="true" class="tpl-form-input"  placeholder="体温记录">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class=" am-form-label">护理记录 </label>\n' +
        '                        <input type="text" name="dnursing" transmit="true" class="tpl-form-input"  placeholder="护理记录">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class=" am-form-label">医嘱记录 </label>\n' +
        '                        <input type="text" name="dadvice" transmit="true" class="tpl-form-input"  placeholder="医嘱记录">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class=" am-form-label">手术记录 </label>\n' +
        '                        <input type="text" name="doperation" transmit="true" class="tpl-form-input"  placeholder="手术记录">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6 am-u-end">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class=" am-form-label">所属科室 </label>\n' +
        '<select  name="departmentid" transmit="true" class="patient-department-select" data-am-selected>\n' +
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
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </form>' +
        '<div class="center-button">' +
        '<button type="button" onclick="pageUtils.closeModal()" class="am-btn am-btn-danger">取消</button>\n' +
        '<button type="button" onclick="addNewClerical('+id+')" class="am-btn am-btn-success">确定</button>'
    '</div>';
    pageUtils.showModal('添加护理文书', html);
    console.log(id);
    
}

function addNewClerical(pid) {
    var json = commonSerializeForm("nurse-clerical-form");
    json.pid = pid;
    $.ajax({
        type: "post",
        url: contextPath + "/nurse/add",
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