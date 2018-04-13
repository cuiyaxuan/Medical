/*主页公共方法的js*/
$(function () {
    $('#main-content').load('./../html/home/home.html');
});

if(sessionStorage.getItem("userRole")==null) {
    window.location.href = contextPath+"/html/errorPage/error.html";
    console.log(sessionStorage.getItem("userRole"));
}
var pageUtils = {
    showModal: function (p_title, p_content) {
        var $modal = $("#common-modal");
        $modal.find(".am-popup-title").empty().html(p_title);
        $modal.find(".am-popup-bd").empty().html(p_content);
        $("#common-modal").modal();
    },
    showConfirm: function (p_title, p_content,yes_event) {
        var $confirm = $('#my-confirm');
        $confirm.find('.am-modal-hd').html(p_title);
        $confirm.find('.am-modal-bd').html(p_content);
        $confirm.modal({
            relatedTarget: this,
            onConfirm: function(options) {
                yes_event;
            },
            // closeOnConfirm: false,
            onCancel: function() {
            }
        });
    },
    closeModal: function () {
        $("#common-modal").modal('close');
    },
    returnHomePage: function () {
        $('.nav-link[data-html="home"]').click();
    }
};
function switchPage(th) {
    $('.nav-link').removeClass('active');
    $('.tpl-left-nav-item a').removeClass('active');
    $(th).addClass("active");
    var type=$(th).parents('.tpl-left-nav-item').attr('data-type');
    var htmlPage = $(th).attr('data-html');
    var $main = $('#main-content');
    $main.load("../../html/" + type + "/" + htmlPage + ".html");
}