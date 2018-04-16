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
            {"data": "phistorytime"}
        ],
        columnDefs: [
/*            {
                targets: 9,
                render: function (data, type, row, meta) {
                    return '<a type="button" class="am-btn am-btn-primary am-btn-xs" onclick="">查看详情</a>' +
                        '<a type="button" class="am-btn am-btn-success am-btn-xs" onclick="">封存<i class="am-icon-cloud-download"></i></a>';
                }
            },
            {"orderable": false, "targets": 9}*/
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