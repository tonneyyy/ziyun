/*后台管理首页*/
var projectName="";

$(function () {

    projectName=$("body").attr("data-project");

    loadSell();

});

//1、加载最近6个月销售额
function loadSell(){

   //设定外观和显示样式
    var barOptions = {
        series: {
            //柱状图
            bars: {
                show: true,
                barWidth: 0.6,   //柱的宽度0.1-1之间
                stack: true,
                fill: true,      //是否填充
                //颜色 从下往下颜色变化
                fillColor: {
                    colors: [{
                        opacity: 0.8
                    }, {
                        opacity: 0.8
                    }]
                },
                numbers: {
                    //显示数值，还可以自定义function(val){ return "自定义格式"}
                    show:function(val){
                        return val+"(万元)";
                    }
                }
            }
        },
        //x轴设定
        xaxis: {
            tickDecimals: 0
        },
        colors: ["#1ab394"],
        //背景格
        grid: {
            color: "#999999",
            hoverable: true,
            clickable: true,
            tickColor: "#D4D4D4",
            borderWidth:0
        },
        //显示图示说明
        legend: {
            show: false
        },
        //移上去以后是否显示提示消息
        tooltip: true,
        //提示消息的内容
        tooltipOpts: {
            content: "月份: %x, 销售额: %y(万元)"
        }
    };


    //ajax的操作
    $.ajax({
        type:"GET",
        url:projectName+"/admin/sell/6/month",
        dataType:"json",
        success:function(resp){

            //后台ajax返回数据
            var barData = {
                label: "bar",
                data: resp
            };
            $.plot($("#flot-bar-sell"), [barData], barOptions);

        }
    });



}