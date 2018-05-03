$(function () {
    initTableRecordSeal();
});

function initTableRecordSeal() {
    $('#table_record_seal').DataTable({
        ajax: {  //ajax方式向后台发送请求
            "type": "POST",
            "async":false,
            "url":contextPath+"/record/listAllRecordByState",
            "dataType" : "json",
            "dataSrc": "result",
            "data":{state:1}
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
                return '<a type="button" class="am-btn am-btn-primary am-btn-xs" onclick="">查看详情</a>' +
                    '<a type="button" class="am-btn am-btn-success am-btn-xs" onclick="sealRecordById('+data.id+')">封存<i class="am-icon-cloud-download"></i></a>';
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
        autoWidth: false
    });
}

function sealRecordById(id) {
    var $confirm = $('#my-confirm');
    $confirm.find('.am-modal-hd').html("提示");
    $confirm.find('.am-modal-bd').html("确认封存该病历？");
    var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
    var $cancelBtn = $confirm.find('[data-am-modal-cancel]');
    $confirmBtn.off('click.confirm.modal.amui').on('click', function() {
        $.ajax({
            method:'post',
            url:contextPath+'/record/sealRecordById',
            data:{id: id},
            async:false,
            success:function (data) {
                alert(data.message);
                initTableRecordSeal();
                pageUtils.closeConfirm();
            },
            error:function () {
            }
        })
    });
    $confirm.modal();
}