$(function () {
    initData();
    initSelectDepartment();
});
var dataAll = [];
var dataMonth=[]

function initInfectionChart() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('patient_chart'));

    var xData = function () {
        var data = [];
        for (var i = 1; i < 13; i++) {
            data.push(i + "月份");
        }
        return data;
    }();

    var yAxisData = ['原因1','原因2','原因3','原因4','原因5','原因6','原因7','原因8','原因9','原因10'];
    var option = {
        backgroundColor: '',
        title:[
            {text:"病人最多月份TOP5",x: '2%', y: '1%',textStyle:{color:"#000",fontSize:"14"}},
            {text:"病人年度趋势",x: '40%', y: '1%',textStyle:{color:"#000",fontSize:"14"}},
        ],
        grid: [
            {x: '50%', y: '7%', width: '45%', height: '90%'},
        ],
        tooltip: {
            formatter: '{b} ({c})'
        },
        xAxis: [
            {gridIndex: 0, axisTick: {show:false},axisLabel: {show:false},splitLine: {show:false},axisLine: {show:false }},
        ],
        yAxis: [
            {  gridIndex: 0, interval:0,data:xData.reverse(),
                axisTick: {show:false}, axisLabel: {show:true},splitLine: {show:false},
                axisLine: {show:true,lineStyle:{color:"#6173a3"}},
            }
        ],
        series: [
            {
                name: '病人最多月份TOP5',
                type: 'pie',
                radius : '30%',
                center: ['22%', '50%'],
                color:['#f47d72','#ec8464','#d24736','#a61d0d','#970003'],
                data:dataMonth/*[
                    {value:335, name:'10月份'},
                    {value:310, name:'5月份'},
                    {value:234, name:'11月份'},
                    {value:135, name:'4月份'},
                    {value:105, name:'6月份'},
                ]*/,
                labelLine:{normal:{show:false}},
                itemStyle: {normal: {label:{ show: true,  formatter: '{b} \n ({d}%)', textStyle:{color:'#B1B9D3'}} },},
            },
            {
                name: '病人年度趋势',
                type: 'bar',xAxisIndex: 0,yAxisIndex: 0,barWidth:'45%',
                itemStyle:{normal:{color:'#c23531'}},
                label:{normal:{show:true, position:"right",textStyle:{color:"#9EA7C4"}}},
                data: dataAll,
            },

        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function initSelectDepartment() {
    $('.patient-department-select').selected();
    $('.patient-age-select').selected();
    $('.patient-sex-select').selected();

}
function initData() {
    $.ajax({
        method:"post",
        url:contextPath+"/data/listAllPatientMonthData",
        data:{departmentId:$('.patient-department-select').val(),page:$('.patient-age-select').val(),psex:$('.patient-sex-select').val()},
        success:function (data) {
            console.log(data.result);
            if(data.state="200") {
                dataAll = data.result.reverse();
                listPatientMonthTop();
                initInfectionChart();
            }
        }
    })
}
function initSearch() {
    let department = $('.patient-department-select').val();
    let age = $('.patient-age-select').val();
    let sex = $('.patient-sex-select').val();
    if(department=="0"||age=="0"||sex=="0") {
        initData();
        return;
    }
    $.ajax({
        method: "post",
        url: contextPath + "/data/listPatientMonthDataBySex",
        data: {
            departmentId:department ,
            page: age,
            psex: sex,
        },
        success: function (data) {
            console.log(data.result);
            if (data.state = "200") {
                dataAll = data.result.reverse();
                initInfectionChart();
            }
        }
    });
}
function listPatientMonthTop() {
    $ajax(contextPath+'data/listPatientMonthTop',{

    },res=>{
        if (res.state = "200") {
            dataMonth = res.result;
        }
    },err=>{

    },false)
}