/*主页公共方法的js*/
$(function () {
    $('#main-content').load('./../html/home/home.html');
    $('#my-confirm').on('closed.modal.amui', function () {
        $(this).removeData('amui.modal');
    });
});

if (sessionStorage.getItem("userRole") == null) {
    window.location.href = contextPath + "/html/errorPage/error.html";
    console.log(sessionStorage.getItem("userRole"));
}
var pageUtils = {
    showModal: function (p_title, p_content) {
        var $modal = $("#common-modal");
        $modal.find(".am-popup-title").empty().html(p_title);
        $modal.find(".am-popup-bd").empty().html(p_content);
        $("#common-modal").modal();
    },
    showConfirm: function (p_title, p_content, yes_event) {
        var $confirm = $('#my-confirm');
        $confirm.find('.am-modal-hd').html(p_title);
        $confirm.find('.am-modal-bd').html(p_content);
        $confirm.modal({
            relatedTarget: this,
            onConfirm: function (options) {
                yes_event;
            },
            // closeOnConfirm: false,
            onCancel: function () {
            }
        });
    },
    showAlert: function (p_title, p_content) {
        var $modal = $("#my-alert");
        $modal.find(".am-modal-hd").empty().html(p_title);
        $modal.find(".am-modal-bd").empty().html(p_content);
        $("#my-alert").modal();
    },
    showLoading(p_content){
        let $modal = $('#my-modal-loading');
        $modal.find('.am-modal-hd').html(p_content);
        $modal.modal();
    },
    closeConfirm: function () {
        var $confirm = $('#my-confirm');
        var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
        var $cancelBtn = $confirm.find('[data-am-modal-cancel]');
        $confirmBtn.off('click.confirm.modal.amui');
        $confirm.modal('close');
    },
    closeModal: function () {
        $("#common-modal").modal('close');
    },
    closeLoading(){
        $('#my-modal-loading').modal('close');
    },
    returnHomePage: function () {
        $('.nav-link[data-html="home"]').click();
    }
};
var commonUtils = {
    ellipsis: function (str) {
        //将str转化为10个字之后就是省略号
        var s = str;//要展示的字符串
        if (str.length > 10) {
            s = str.substring(0, 10) + "...";
        }
        return s;
    },
    convertDateFromString(dateString) {
        if (dateString) {
            var arr1 = dateString.split(" ");
            var sdate = arr1[0].split('-');
            var date = new Date(sdate[0], sdate[1] - 1, sdate[2]);
            return date;
        }
    },

};

function switchPage(th) {
    $('.nav-link').removeClass('active');
    $('.tpl-left-nav-item a').removeClass('active');
    $(th).addClass("active");
    var type = $(th).parents('.tpl-left-nav-item').attr('data-type');
    var htmlPage = $(th).attr('data-html');
    var $main = $('#main-content');
    $main.load("../../html/" + type + "/" + htmlPage + ".html");
}

function imgOnError() {
    var img = event.srcElement;
    img.src = "../img/error-headImg.jpg";
    img.onerror = null;
}

//封装了jquery的ajax
function $ajax(url, postData, succCallback, errorCallback, type, dataType){
    var type = type || "post";
    var dataType = dataType || "json";

    $.ajax({
        type: type,
        url: url,
        data: postData,
        dataType: dataType,

        beforeSend: function(){  //开始loading
            pageUtils.showLoading('数据正在传输，请稍后...')
        },

        success: function(res){
            succCallback(res);
        },
        error:function(res){
            errorCallback(res);
        },
        complete: function(){   //结束loading
            //$(".js_loading").remove();
            pageUtils.closeLoading();
        }
    });
}