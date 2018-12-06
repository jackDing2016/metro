$(document).ready(function () {

    getData( 1 );

    $( '#weekdayBtn' ).click( function () {
        getData( 1 );

        $( this ).addClass( 'active' );
        $( '#holidayBtn' ).removeClass( 'active' );

    } );

    $( '#holidayBtn' ).click( function () {
        getData( 2 );

        $( this ).addClass( 'active' );
        $( '#weekdayBtn' ).removeClass( 'active' );

    } );

});

var getData = function ( pressureTimeType ) {

    var weightArr = [];
    $.each( $( '#weightTable > tbody' ).find( 'tr' ), function () {
        weightArr.push( $( this ).find( 'td:eq(1)' ).text() );
    } );

    var per2Val , per11Val = ''

    per2Val = weightArr[ 0 ];
    if ( weightArr.length > 1 )
        per11Val = weightArr[ 1 ];

    $.ajax({
        type: 'get',
        url: '/pressureCalculation/calculateResult/' + $( '#pressureTypeCode' ).val(),
        dataType : 'json',
        data: {
            lineCodeStr : $( '#lineCodeStr' ).val(),
            stationNameCode : $( '#stationNameCode' ).val(),
            per2Val : per2Val,
            per11Val : per11Val,
            pressureTimeType : pressureTimeType
        },
        contentType: "application/json; charset=utf-8",
        success: function ( data ) {

            // $( '#main' ).empty();

            // chart show begin
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
                    text: $( '#pressureTypeName' ).val() + '压力等级'
                },
                legend: {
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
                }

            };
            var optionLegendData = [];
            var optionSeries = [];

            var dataResultMap = data.dataResultMap;
            for ( var key in dataResultMap ) {
                console.log( 'key is '  );
                console.log( key);
                var dataTitle = key.substring( key.indexOf( 'e' ) + 1, key.length )+ '号线（工作日）' ;
                optionLegendData.push( dataTitle );

                var seriesData = {};
                seriesData.data = dataResultMap[ key ];
                seriesData.type = 'line';
                seriesData.name = dataTitle;
                seriesData.areaStyle = {};
                seriesData.itemStyle = {
                    normal : {
                        lineStyle:{
                            width:5,//折线宽度
                        }
                    }
                }
                optionSeries.push( seriesData );

            }
            option.legend.data = optionLegendData;
            option.series = optionSeries;
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            // chart show end

            // avg score and level show begin
            $( '#avgScore' ).text( data.avgScoreLevelMap.avgScore );
            $( '#avgLevel' ).text( data.avgScoreLevelMap.avgLevel );
            // avg score and level show end


        }
    });

}