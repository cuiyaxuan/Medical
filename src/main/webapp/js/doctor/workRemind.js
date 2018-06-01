$(function () {
    initRemindList();
});
function initRemindList() {
    $.ajax({
        method:'post',
        url: contextPath + '/doctor/listWorkMind',
        data:{},
        success:function (data) {
            var $list = $('#doctor-work-remind');
            console.log(data);
            var html='';
            $.each(data.result, function (index, obj) {
                html += '            <li onclick="deleteRemind('+obj.id+')" style="cursor: pointer">\n' +
                    '                <div class="cosB">\n' +
                    obj.gmtCreate +
                    '                </div>\n' +
                    '                <div class="cosA">\n' +
                    '                                <span class="cosIco label-info">\n' +
                    '                        <i class="am-icon-bullhorn icon-top"></i>\n' +
                    '                      </span>\n' +
                    '                    <span> ' + obj.message + '</span>\n' +
                    '                </div>\n' +
                    '            </li>            ';
            });
            $list.html(html);
        },
        error:function () {

        }
    })
}

function deleteRemind(id) {
    var r = confirm("确认删除？");
    if(r===true) {
        $ajax(contextPath + '/doctor/deleteWorkMind',{
            id:id
        },function (res) {
            if(res.state==="200") {
                pageUtils.showAlert('提示', res.message);
                initRemindList();
            }
        },function (error) {
            pageUtils.showAlert('提示', '删除失败！');
        })
    }
}