/*主页公共方法的js*/
$(function () {

});
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
    }
};
function switchPage(page,th) {
    $('.tpl-content-wrapper').children().hide();
    $('.nav-link').removeClass('active');
    $(th).addClass("active");
    switch (page){
        case 1:
            $('#index').show();
            break;
        case 2:
            $('#dataTable').show();
            break;
        case 3:
            $('#operationLogs').show();
            break;
        case 4:
            $('#dataManage').show();
            break;
        case 5:
            $('#customerManage').show();
            break;
        default:
            break;
    }
}