$(function () {
    initRecordData();
    $('.patient-department-select').selected();
    $('.patient-department-select').on("change", function () {
        var departmentId = $(this).val();
        console.log(departmentId);
        if (departmentId != 0) {
            $.ajax({
                method: 'post',
                url: contextPath + "/data/getRecordData",
                data: {departmentId: departmentId,year:2018},
                async: false,
                success: function (data) {
                    defaultState = data.result.defaultState;
                    passState = data.result.passState;
                    rejectState = data.result.rejectState;
                    // for (var i = 0; i <12; i++) {
                    //     sum.push(parseInt(defaultState[i]) + parseInt(passState[i]) + parseInt(rejectState[i]));
                    // }
                    initDataRecordChart();
                },
                error: function () {

                }
            });

        } else {
            initRecordData();
        }

    });
});
var sum = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
var defaultState=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
var passState=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
var rejectState=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
function initDataRecordChart() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('data-record-chart'));

    var xData = function () {
        var data = [];
        for (var i = 1; i < 13; i++) {
            data.push(i + "月份");
        }
        return data;
    }();

    var option = {
        backgroundColor: "",
        "title": {
            "text": "本年病历质量情况",
            "subtext": "BY Wang Xinyu",
            x: "4%",
            textStyle: {
                color: '#000',
                fontSize: '22'
            },
            subtextStyle: {
                color: '#90979c',
                fontSize: '16',

            },
        },
        "tooltip": {
            "trigger": "axis",
            "axisPointer": {
                "type": "shadow",
                textStyle: {
                    color: "#fff"
                }

            },
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        "grid": {
            "borderWidth": 0,
            "top": 110,
            "bottom": 95,
            textStyle: {
                color: "#90979c"
            }
        },
        "legend": {
            // x: '4%',
            // top: '11%',
            textStyle: {
                color: '#90979c',
            },
            "data": ['未审核', '已通过', '未通过']
        },


        "calculable": true,
        "xAxis": [{
            "type": "category",
            "axisLine": {
                lineStyle: {
                    color: '#90979c'
                }
            },
            "splitLine": {
                "show": false
            },
            "axisTick": {
                "show": false
            },
            "splitArea": {
                "show": false
            },
            "axisLabel": {
                "interval": 0,

            },
            "data": xData,
        }],
        "yAxis": [{
            "type": "value",
            "splitLine": {
                "show": false
            },
            "axisLine": {
                lineStyle: {
                    color: '#90979c'
                }
            },
            "axisTick": {
                "show": false
            },
            "axisLabel": {
                show:false
            },
            "splitArea": {
                "show": false
            },

        }],
        "dataZoom": [{
            "show": true,
            "height": 30,
            "xAxisIndex": [
                0
            ],
            bottom: 30,
            "start": 12,
            "end": 80,
            handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
                color: "#d3dee5",

            },
            textStyle: {
                color: "#90979c"
            },
            borderColor: "#90979c"


        }, {
            "type": "inside",
            "show": true,
            "height": 15,
            "start": 1,
            "end": 35
        }],
        "series": [
            {
                "name": "未审核",
                "type": "bar",
                "stack": "总量",
                "barMaxWidth": 35,
                "barGap": "10%",
                "itemStyle": {
                    "normal": {
                        "color": "rgba(255,144,128,1)",
                        "label": {
                            "show": true,
                            "textStyle": {
                                "color": "#90979c"
                            },
                            "position": "insideTop",
                            formatter: function (p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                "data": defaultState,
            },
            {
                "name": "已通过",
                "type": "bar",
                "stack": "总量",
                "itemStyle": {
                    "normal": {
                        "color": "rgba(0,191,183,1)",
                        "barBorderRadius": 0,
                        "label": {
                            "show": true,
                            "position": "right",
                            formatter: function (p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                "data":passState
            },
            {
                "name": "未通过",
                "type": "bar",
                "stack": "总量",
                "itemStyle": {
                    "normal": {
                        "color": "#00D4FF",
                        "barBorderRadius": 0,
                        "label": {
                            "show": true,
                            "position": "left",
                            formatter: function (p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                "data":rejectState
            },
            {
                "name": "总数",
                "type": "line",
                "stack": "总量",
                symbolSize: 10,
                symbol: 'circle',
                "itemStyle": {
                    "normal": {
                        "color": "rgba(252,230,48,1)",
                        "barBorderRadius": 0,
                        "label": {
                            "show": true,
                            "position": "top",
                            formatter: function (p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                "data": sum
            },
        ]
    }

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function initRecordData() {
    //图表中有的数据
    //获取数据
    $.ajax({
        method: "post",
        url: contextPath + "/data/listRecordData",
        data: {year:"2018"},
        async:false,
        success: function (data) {
            console.log(data);
            defaultState = data.result.defaultState;
            passState = data.result.passState;
            rejectState = data.result.rejectState;
            // for (var i = 0; i <12; i++) {
            //     sum.push(parseInt(defaultState[i]) + parseInt(passState[i]) + parseInt(rejectState[i]));
            // }
            console.log(sum);
            initDataRecordChart();
        },
        error: function () {
        }
    });
}