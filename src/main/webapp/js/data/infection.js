$(function () {
    selectAllInfection();
    initSelectDepartment();
});
var dataAll = [];
var MonthTop = [];
var DepartTop = [];
function initInfectionChart() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('infection_chart'));
    var xData = function () {
        var data = [];
        for (var i = 1; i < 13; i++) {
            data.push(i + "月份");
        }
        return data;
    }();
    var yAxisData = ['原因1', '原因2', '原因3', '原因4', '原因5', '原因6', '原因7', '原因8', '原因9', '原因10'];
    var option = {
        backgroundColor: '',
        title: [
            {text: "爆发月份TOP5", x: '2%', y: '1%', textStyle: {color: "#000", fontSize: "14"}},
            {text: "年度传染病趋势", x: '40%', y: '1%', textStyle: {color: "#000", fontSize: "14"}},
            {text: "处理传染病最多科室TOP5", x: '2%', y: '50%', textStyle: {color: "#000", fontSize: "14"}},
        ],
        grid: [
            {x: '50%', y: '7%', width: '45%', height: '90%'},
        ],
        tooltip: {
            formatter: '{b} ({c})'
        },
        xAxis: [
            {
                gridIndex: 0,
                axisTick: {show: false},
                axisLabel: {show: false},
                splitLine: {show: false},
                axisLine: {show: false}
            },
        ],
        yAxis: [
            {
                gridIndex: 0, interval: 0, data: xData.reverse(),
                axisTick: {show: false}, axisLabel: {show: true}, splitLine: {show: false},
                axisLine: {show: true, lineStyle: {color: "#6173a3"}},
            }
        ],
        series: [
            {
                name: '爆发月份TOP5',
                type: 'pie',
                radius: '30%',
                center: ['22%', '25%'],
                color: ['#86c9f4', '#4da8ec', '#3a91d2', '#005fa6', '#315f97'],
                data: MonthTop,
                labelLine: {normal: {show: false}},
                itemStyle: {normal: {label: {show: true, formatter: '{b} \n ({d}%)', textStyle: {color: '#B1B9D3'}}},},
            },
            {
                name: '处理传染病最多科室TOP5',
                type: 'pie',
                radius: '30%',
                center: ['22%', '75%'],
                color: ['#86c9f4', '#4da8ec', '#3a91d2', '#005fa6', '#315f97'],
                labelLine: {normal: {show: false}},
                data: DepartTop/*[
                    {value: 335, name: '传染病科'},
                    {value: 310, name: '内科'},
                    {value: 234, name: '男科'},
                    {value: 135, name: '外科'},
                    {value: 135, name: '儿科'},
                ]*/,
                itemStyle: {normal: {label: {show: true, formatter: '{b} \n ({d}%)', textStyle: {color: '#B1B9D3'}}},},
            },
            {
                name: '年度传染病趋势',
                type: 'bar', xAxisIndex: 0, yAxisIndex: 0, barWidth: '45%',
                itemStyle: {normal: {color: '#86c9f4'}},
                label: {normal: {show: true, position: "right", textStyle: {color: "#9EA7C4"}}},
                data: dataAll,
            },

        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function initSelectDepartment() {
    $('.infection-department-select').selected();
    $('.infection-department-select').on("change", function () {
        var departmentId = $(this).val();
        console.log(departmentId);
        if (departmentId != 0) {
            $.ajax({
                method: 'post',
                url: contextPath + "/data/listAllInfectionDataByDepartment",
                data: {departmentId: departmentId},
                async: false,
                success: function (data) {
                    dataAll = data.result.reverse();
                    console.log(data.result);
                    initInfectionChart();
                },
                error: function () {

                }
            });
            $.ajax({
                method: 'post',
                url: contextPath + "/data/getMonthTop",
                data: {departmentId: departmentId},
                async: false,
                success: function (data) {
                    MonthTop = data.result;
                    initInfectionChart();
                },
                error: function () {

                }
            });

        } else {
            selectAllInfection();
        }

    });
}

function selectAllInfection() {
    $.ajax({
        method: 'post',
        url: contextPath + "/data/listAllInfectionData",
        data: {},
        async: false,
        success: function (data) {
            dataAll = data.result.reverse();
        },
        error: function () {

        }
    });
    listAllMonthTop();
    listAllDepartmentTop();
    initInfectionChart();
}

function listAllMonthTop() {

    $.ajax({
        method: 'post',
        url: contextPath + "/data/listAllMonthTop",
        data: {},
        async: false,
        success: function (data) {
            MonthTop = data.result;
        },
        error: function () {

        }
    });
}

function listAllDepartmentTop() {
    $ajax(contextPath + 'data/listDepartmentTop', {}, function (res) {
        if (res.state === "200") {
            console.log(res.result);
            DepartTop = res.result;
        }
    }, err => {

    }, false);
}