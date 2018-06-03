$(function () {
    initTableRecordDeblock();
});

function initTableRecordDeblock() {
    $('#table_record_deblock').DataTable({
        ajax: {  //ajax方式向后台发送请求
            "type": "POST",
            "async":false,
            "url":contextPath+"/record/listAllRecordByState",
            "dataType" : "json",
            "dataSrc": "result",
            "data":{state:2}
        },
        columns : [//对接收到的json格式数据进行处理，data为json中对应的key
            {"data":"pname"},
            {"data":"rcomplain"} ,
            {"data":"rpresent"},
            {"data":"rhistory"},
            {"data":"rperson"},
            {"data":"rmarriage"},
            {"data":"rfamily"},
            {"data":"d_name"},
            {"data":"state"},
            {"data":null}
        ],
        columnDefs:[{
            targets: 9,
            render: function (data, type, row, meta) {
                return '<div class="doc-dropdown-justify-js">\n' +
                    '  <div class="am-dropdown doc-dropdown-js" style="min-width: 100px">\n' +
                    '    <button class="am-btn am-btn-danger am-dropdown-toggle">操作 <span class="am-icon-caret-down"></span></button>\n' +
                    '    <div class="am-dropdown-content" style="padding: 0 !important;">' +
                    '  <ul class="am-list am-list-border" style="margin-bottom: 0 !important;">\n' +
                    '    <li><a href="javaScript:void(0)" onclick="openRecordHtml('+data.id+')">查看详情</a></li>\n' +
                    '    <li><a href="javaScript:void(0)" onclick="deBlockRecordById('+data.id+','+data.userloginid+')">解封</a></li>\n' +
                    '  </ul>' +
                    '</div>\n' +
                    '  </div>\n' +
                    '</div>'
            }
        },
            { "orderable": false, "targets": 9 }
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
            $('#table_record_deblock').addClass('table-layout-fixed');
            $('#table_record_deblock td:not(:last-of-type)').addClass("text-one-line");
            $('.doc-dropdown-js').dropdown({justify: '.doc-dropdown-justify-js'});
        }
    });
}

function deBlockRecordById(id,userloginid) {
    var r = confirm("确认解封？");
    if(r==true){
        $.ajax({
            method:'post',
            url:contextPath+'/record/deBlockRecordById',
            data:{id: id,userLoginId:userloginid},
            async:false,
            success:function (data) {
                alert(data.message);
                initTableRecordDeblock();
            },
            error:function () {
            }
        })
    }
}

function openRecordHtml(id) {
    sessionStorage.setItem("recordDetailId", id);
    sessionStorage.setItem("contextPath", contextPath);
    window.open(contextPath+"/html/recordDetail/recordDetail.html");
}