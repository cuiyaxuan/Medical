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
        columnDefs: [
            {
                targets: 12,
                render: function (data, type, row, meta) {
                    return '<div class="doc-dropdown-justify-js">\n' +
                        '  <div class="am-dropdown doc-dropdown-js" style="min-width: 100px">\n' +
                        '    <button class="am-btn am-btn-danger am-dropdown-toggle">操作 <span class="am-icon-caret-down"></span></button>\n' +
                        '    <div class="am-dropdown-content">' +
                        '  <ul class="" >\n' +
                        '    <li><a href="#">查看详情</a></li>\n' +
                        '    <li class="am-active"><a href="#">下载</a></li>\n' +
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
        autoWidth: false
    });
}

function initAddModal() {
    var html = '<form class="am-form tpl-form-line-form">\n' +
        '        <div class="am-g">\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="am-u-sm-6">\n' +
        '                <div class="am-form-group">\n' +
        '                    <label for="user-name" class="am-u-sm-3 am-form-label">姓名 <span class="tpl-form-line-small-title">Name</span></label>\n' +
        '                    <div class="am-u-sm-9">\n' +
        '                        <input type="text" class="tpl-form-input"  placeholder="请输入标题文字">\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </form>';
    pageUtils.showModal('新增病人',html)
}