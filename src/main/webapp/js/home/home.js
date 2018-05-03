$(function () {
    linkRecord();
    initHomeLogs();
    initTodayRecord();
    listDoctorSort();
    listRecord();
    selectAllInfection();
});
var dataAll = [];
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

function initHomeInfectionChart() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('tpl-echarts-A'));

    var xData = function () {
        var data = [];
        for (var i = 1; i < 13; i++) {
            data.push(i + "月份");
        }
        return data;
    }();
    var option = {
        xAxis: {
            type: 'category',
            data: xData
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: dataAll,
            type: 'bar'
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

/**
 * 显示医生排名
 */
function listDoctorSort() {
    var $docTable = $('#home-doctor-sort');
    $.ajax({
        method:"post",
        url:contextPath+"/index/listSomeDoctor",
        data:{},
        success(data){
            var docHtml = '';
            $.each(data.result, function (index, obj) {
                var type = '';
                if (obj.type = 1) {
                    type = '医生';
                } else {
                    type = '护士';
                }
                docHtml += '<tr>\n' +
                    '<td>\n' +
                    '<img src="../../img/headImg.png" alt="" class="user-pic">\n' +
                    '<a class="user-name" href="###">' + obj.realname + '</a>\n' +
                    '</td>\n' +
                    '<td>' + obj.d_name + '</td>\n' +
                    '<td>' + type + '</td>\n' +
                    '<td class="font-green bold">' + obj.score + '</td>\n' +
                    '</tr>'
            });
            $docTable.find('tbody').html(docHtml);
        },
        error(){
            alert('error!');
        }
    })
}

/**
 * 查询病历
 */
function listRecord() {
    $.ajax({
        method:"post",
        url:contextPath+"/index/listCountRecord",
        data:{},
        success:function (data) {
            var sum = 0;
            $.each(data.result,function (index,obj) {
                if(obj.rstate==1) {
                    $('.recordDefault').html(obj.number);
                }else if(obj.rstate==2) {
                    $('.recordSeal').html(obj.number);
                }
                sum += obj.number;
            })
            $('.recordAll').html(sum);
        }
    })
    $.ajax({
        method:"post",
        url:contextPath+"/index/listCountRejectRecord",
        data:{},
        success:function (data) {
            $('.recordReject').html(data.result);
        }
    })
}

/**
 * 查询传染病数据
 */
function selectAllInfection() {
    $.ajax({
        method: 'post',
        url: contextPath + "/data/listAllInfectionData",
        data: {},
        async: false,
        success: function (data) {
            dataAll = data.result;
            console.log(dataAll);
        },
        error: function () {

        }
    });
    initHomeInfectionChart();
}