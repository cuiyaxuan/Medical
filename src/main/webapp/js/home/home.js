$(function () {
    linkRecord();
    initHomeLogs();
    initTodayRecord();
});

/**
 * 跳转到病例管理
 */
function linkRecord() {
    $('.linkRecord').on('click', function () {
        $('ul.tpl-left-nav-menu li[data-type="medicalRecord"] a[data-html="recordScanned"]').click();
    });
}

/**
 * 加载日志管理的 iScroller
 */
function initIScollerLogs() {
    var myScroll = new IScroll('#wrapper', {
        scrollbars: true,
        mouseWheel: true,
        interactiveScrollbars: true,
        shrinkScrollbars: 'scale',
        preventDefault: false,
        fadeScrollbars: true
    });
}

/**
 * 加载主页的日志管理
 */
function initHomeLogs() {
    var $logs_ul = $('.home-logs');
    $.ajax({
        method:"post",
        url:contextPath+"/logs/getAll",
        data:{},
        success:function (data) {
            var html = "";
            $.each(data.result, function (index, obj) {
                html += '<li><span>' + obj.username + '在' + obj.time + obj.details + '</span></li>';
            });
            $logs_ul.html(html);
            initIScollerLogs();
        },
        error:function () {
            alert("ajax Error!");
        }
    })
}

/**
 * 加载今日病历
 */
function initTodayRecord() {
    var $recordSigned = $('.record-signed');
    var $recordNotSigned = $('.record-not-signed');
    $.ajax({
        method: "post",
        url: contextPath + "/record/listTodaySignedRecord",
        data: {},
        success: function (data) {
            if (data.result != null) {
                var html = "";
                $.each(data.result, function (index, obj) {
                    html += '<li>' +
                        '<div class="cosB">' + obj.gmt_create + '</div>' +
                        '<div class="cosA"><span>' + obj.rcomplain + '</span></div>' +
                        '</li>'
                });
                $recordSigned.html(html);
            }
        }
    });
    $.ajax({
        method: "post",
        url: contextPath + "/record/listTodayNotSignedRecord",
        data: {},
        success: function (data) {
            if (data.result != null) {
                var html = "";
                $.each(data.result, function (index, obj) {
                    html += '<li>' +
                        '<div class="cosB">' + obj.gmt_create + '</div>' +
                        '<div class="cosA"><span>' + obj.rcomplain + '</span></div>' +
                        '</li>'
                });
                $recordNotSigned.html(html);
            }
        }
    });
}