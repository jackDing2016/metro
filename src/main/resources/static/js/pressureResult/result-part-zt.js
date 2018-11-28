$(document).ready(function () {

    $.ajax({
        type: 'post',
        url: '/pressureCalculation/calculateResult/1',
        // data: JSON.stringify( submitData ),
        // data : $()
        dataType : 'json',
        data: JSON.stringify(
            {
                lineCodeStr : $( '#lineCodeStr' ).val(),
                stationNameCode : $( '#stationNameCode' ).val()
            }
        ),
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['8:00-8:15', '8:15-8:30', '8:30-8:45', '8:45-9:00', '9:00-9:15','9:15-9:30', '9:30-9:45', '9:45-10:00']
                },
                yAxis: {
                    gridIndex:0,
                    type: 'category',
                    data:['E','D','C','B','A'],
                    axisLine:{

                    },
                    boundaryGap:false
                },
                grid:{
                    left:'5%',
                    right:'5%',
                },
                title: {
                    text: '站台口压力等级'
                },
                legend: {
                    data:['2号线（工作日）','11号线（工作日）','综合压力等级'],
                    top:'7%'
                },
                tooltip : {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    },
                    formatter: function (params,ticket,callback) {
                        console.log(params)
                        var res =  params[0].name;
                        console.log(" name"+params[0].name);

                        for (var i = 0, l = params.length; i < l; i++) {
                            res += '<br/>' + params[i].seriesName + ' : ' + params[i].value;
                        }
                        return res;
                    }
                },

                series: [
                    {
                    // data: ['A', 'B',  'A',  'D',  'A', 'C', 'A','E'],
                    data : data.line2,
                    type: 'line',
                    areaStyle:{},
                    name:'2号线（工作日）',
                    itemStyle : {
                        normal : {
                            lineStyle:{
                                width:5,//折线宽度

                            }
                        }
                    }



                },
                    {
                        // data: ['A', 'B',  'A',  'D',  'A', 'C', 'A','E'],
                        data : data.line11,
                        type: 'line',
                        areaStyle:{},
                        name:'11号线（工作日）',
                        itemStyle : {
                            normal : {
                                lineStyle:{
                                    width:5,//折线宽度

                                }
                            }
                        }



                    }
                ] };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });



});