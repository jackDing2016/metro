$(document).ready(function () {

    $( '#resultImgsampleBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureImgtatisticsPage';
    } );


    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));



    // 指定图表的配置项和数据
    var option = {

        radar:
            {
                indicator: [
                    {name: '站台\n压力等级', max: 100},
                    {name: '楼扶梯\n压力等级', max: 100},
                    {name: '闸机\n压力等级', max: 100},
                    {name: '换乘通道\n压力等级', max: 100},
                    {name: '出入口\n压力等级', max: 100}
                ],

                center: ['50%','50%'],
                radius: '80%',
                name:{
                    formatter:function(v){
                        console.log(v);
                        return v;
                    }
                },
                triggerEvent:true
            },


        series:
            {
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: [
                    {
                        value: [ $( "#plateformAvgScore" ).text() ,$( "#escalatorAvgScore" ).text(),
                            $( "#gateAvgScore" ).text(), $( "#transferPassageAvgScore" ).text(),
                            $( "#entranceAvgScore" ).text() ]
                    }
                ]
            },


    };

    // option.series.data = [
    //     {
    //         value: [50,100,20,30,100],
    //
    //     }
    // ]

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    myChart.on('click', function (params) {
        if(params.name=='站台\n压力等级'){
            $('#div_id').html('<a class="div_close">x</a><p>站台压力等级</p><span>A</span><hr/><table class="press-de"><tr><td class="floatleft">客流压力分数值</td><td class="floatright">100</td></tr></table><a id="pre-01" data-toggle="modal" data-target="#pre-1-info">压力等级说明</a>');
            $('#div_id').fadeIn();
            $('#div_id').css({'right':'20px','top':'10px'});
        }
        else if(params.name=='楼扶梯\n压力等级'){
            $('#div_id').html('<a class="div_close">x</a><p>楼扶梯压力等级</p><span>B</span><hr/><table class="press-de"><tr><td class="floatleft">客流压力分数值</td><td class="floatright">100</td></tr></table><a id="pre-02" data-toggle="modal" data-target="#pre-2-info">压力等级说明</a>');
            $('#div_id').fadeIn();
            $('#div_id').css({'right':'35%','top':'20%'});
        }
        else if(params.name=='闸机\n压力等级'){
            $('#div_id').html('<a class="div_close">x</a><p>闸机压力等级</p><span>B</span><hr/><table class="press-de"><tr><td class="floatleft">客流压力分数值</td><td class="floatright">100</td></tr></table><a id="pre-03" data-toggle="modal" data-target="#pre-3-info">压力等级说明</a>');
            $('#div_id').fadeIn();
            $('#div_id').css({'right':'30%','bottom':'5%'});
        }
        else if(params.name=='换乘通道\n压力等级'){
            $('#div_id').html('<a class="div_close">x</a><p>换乘通道压力等级</p><span>B</span><hr/><table class="press-de"><tr><td class="floatleft">客流压力分数值</td><td class="floatright">100</td></tr></table><a id="pre-04" data-toggle="modal" data-target="#pre-4-info">压力等级说明</a>');
            $('#div_id').fadeIn();
            $('#div_id').css({'right':'10%','bottom':'5%'});
        }
        else if(params.name=='出入口\n压力等级'){
            $('#div_id').html('<a class="div_close">x</a><p>出入口压力等级</p><span>B</span><hr/><table class="press-de"><tr><td class="floatleft">客流压力分数值</td><td class="floatright">100</td></tr></table><a id="pre-05" data-toggle="modal" data-target="#pre-5-info">压力等级说明</a>');
            $('#div_id').fadeIn();
            $('#div_id').css({'right':'5%','top':'20%'});
        }
        else  {$('#div_id').fadeOut();};
        $('#pre-02').click(function(){
            $('#pre-2-info').fadeIn();
            $('#div_id').fadeOut();
        });
        $('#pre-01').click(function(){
            $('#pre-1-info').fadeIn();
            $('#div_id').fadeOut();
        });
        $('#pre-03').click(function(){
            $('#pre-3-info').fadeIn();
            $('#div_id').fadeOut();
        });
        $('#pre-04').click(function(){
            $('#pre-4-info').fadeIn();
            $('#div_id').fadeOut();
        });
        $('#pre-05').click(function(){
            $('#pre-5-info').fadeIn();
            $('#div_id').fadeOut();
        });
        $('.div_close').click(function(){
            $('#div_id').fadeOut();
        });
    });



});