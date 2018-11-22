$(document).ready(function () {

    $( '#saveBtn' ).click(function () {


        var stationAddParam = getData();
        saveData( stationAddParam );

    });


    $( '#submitBtn' ).click( function () {

        var stationAddParam = getData();

        calculateData( stationAddParam );

    } );

    // 换乘通道数修改
    $ ( 'input[name=passageNumber]' ).change( function () {

        console.log( 'passageNumber changed!' );

        var sumPassageNumber = 0
        $.each( $ ( 'input[name=passageNumber]' ), function () {
            if ($(this).val() == '' )
                $(this).val(0);
            sumPassageNumber += Number(  $(this).val() );
        } );

        console.log( sumPassageNumber );

        $( this ).siblings( 'input[name=passageLength]' ).remove();

        var currentPassageNumber = Number(  $(this).val() );
        for ( var i = 0; i < currentPassageNumber; i++ ) {

            var traPasLenCom = $( '                                                        <div class="col-sm-2">\n' +
                '                                                            <input type="text" class="form-control input-danwei"  name="passageLength" value="" >m\n' +
                '                                                        </div>' );

            $( this ).parent().after( traPasLenCom );

        }

        $( '#transferPassageDiv' ).find( '.transferPassageStatisticsClass' ).remove();



        for ( var i = 0; i < sumPassageNumber; i++ ) {
            var traPassStaCom = $( '<div class="transferPassageStatisticsClass">\n' +
                '                                        <div class="float-left col-6 tongji">\n' +
                '                                            <table class="table table-striped table-bordered table-hover " >\n' +
                '                                                <thead>\n' +
                '                                                <tr id="thead1">\n' +
                '                                                    <th scope="col"></th>\n' +
                '                                                    <th scope="col">换乘通道1</th>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tbhead1">\n' +
                '                                                    <th scope="col">运营时间</th>\n' +
                '                                                    <th scope="col">换乘走行时间</th>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                </thead>\n' +
                '                                                <tbody>\n' +
                '                                                <tr id="tt1">\n' +
                '                                                    <th scope="row">06:00:00~06:59:59</th>\n' +
                '                                                    <td><input type="text" value="" name="walkTime" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt2">\n' +
                '                                                    <th scope="row">07:00:00~07:59:59</th>\n' +
                '                                                    <td><input type="text" value=""  name="walkTime"  class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt3">\n' +
                '                                                    <th scope="row">08:00:00~08:59:59</th>\n' +
                '                                                    <td><input type="text" value=""  name="walkTime"  class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt4">\n' +
                '                                                    <th scope="row">09:00:00~09:59:59</th>\n' +
                '                                                    <td><input type="text" value=""  name="walkTime"  class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt5">\n' +
                '                                                    <th scope="row">10:00:00~10:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt6">\n' +
                '                                                    <th scope="row">11:00:00~11:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt7">\n' +
                '                                                    <th scope="row">12:00:00~12:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt8">\n' +
                '                                                    <th scope="row">13:00:00~13:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt9">\n' +
                '                                                    <th scope="row">14:00:00~14:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt10">\n' +
                '                                                    <th scope="row">15:00:00~15:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt11">\n' +
                '                                                    <th scope="row">16:00:00~16:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt12">\n' +
                '                                                    <th scope="row">17:00:00~17:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt13">\n' +
                '                                                    <th scope="row">18:00:00~18:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt14">\n' +
                '                                                    <th scope="row">19:00:00~19:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt15">\n' +
                '                                                    <th scope="row">20:00:00~20:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt16">\n' +
                '                                                    <th scope="row">21:00:00~21:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt17">\n' +
                '                                                    <th scope="row">22:00:00~22:59:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt18">\n' +
                '                                                    <th scope="row">23:00:00~23:49:59</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '\n' +
                '                                                </tbody>\n' +
                '                                            </table>\n' +
                '                                        </div>\n' +
                '                                        <div class="float-left col-6  tongji">\n' +
                '                                            <table class="table table-striped table-bordered table-hover" >\n' +
                '                                                <thead>\n' +
                '                                                <tr id="thead2">\n' +
                '                                                    <th scope="col"></th>\n' +
                '                                                    <th scope="col">换乘通道1</th>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tbhead2">\n' +
                '                                                    <th scope="col">时间段</th>\n' +
                '                                                    <th scope="col">换乘通道换乘量</th>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                </thead>\n' +
                '                                                <tbody>\n' +
                '                                                <tr id="tt50">\n' +
                '                                                    <th scope="row">8:00-8:04</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt51">\n' +
                '                                                    <th scope="row">8:05-8:09</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt52">\n' +
                '                                                    <th scope="row">8:10-8:14</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt53">\n' +
                '                                                    <th scope="row">8:15-8:19</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt54">\n' +
                '                                                    <th scope="row">8:20-8:24</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt55">\n' +
                '                                                    <th scope="row">8:25-8:29</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt56">\n' +
                '                                                    <th scope="row">8:30-8:34</th>\n' +
                '                                                    <td><input type="text" value=""  name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt57">\n' +
                '                                                    <th scope="row">8:35-8:39</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt58">\n' +
                '                                                    <th scope="row">8:40-8:44</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt59">\n' +
                '                                                    <th scope="row">8:45-8:49</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt60">\n' +
                '                                                    <th scope="row">8:50-8:54</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt61">\n' +
                '                                                    <th scope="row">8:55-8:59</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt62">\n' +
                '                                                    <th scope="row">9:00-9:04</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt63">\n' +
                '                                                    <th scope="row">9:05-9:09</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt64">\n' +
                '                                                    <th scope="row">9:10-9:14</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt65">\n' +
                '                                                    <th scope="row">9:15-9:19</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt66">\n' +
                '                                                    <th scope="row">9:20-9:24</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt67">\n' +
                '                                                    <th scope="row">9:25-9:29</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt68">\n' +
                '                                                    <th scope="row">9:30-9:34</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt69">\n' +
                '                                                    <th scope="row">9:35-9:39</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt70">\n' +
                '                                                    <th scope="row">9:40-9:44</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt71">\n' +
                '                                                    <th scope="row">9:45-9:49</th>\n' +
                '                                                    <td><input type="text" value="" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt72">\n' +
                '                                                    <th scope="row">9:50-9:54</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                <tr id="tt73">\n' +
                '                                                    <th scope="row">9:55-9:59</th>\n' +
                '                                                    <td><input type="text" value="" name="flow" class="form-control"></td>\n' +
                '\n' +
                '                                                </tr>\n' +
                '                                                </tbody>\n' +
                '                                            </table>\n' +
                '                                        </div>\n' +
                '                                    </div>' )
            ;
            $( '#transferPassageDiv' ).append( traPassStaCom );
        }



    } );

});

var saveData =  function ( submitData ) {

    $.ajax({
        type: 'post',
        url: '/station/saveData',
        data: JSON.stringify( submitData ),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            alert( 'add success' );
        }
    });

}

var calculateData =  function ( submitData ) {

    $.ajax({
        type: 'post',
        url: '/station/calculateData',
        data: JSON.stringify(submitData),
        contentType: "application/json; charset=utf-8",
        dataType : 'text',
        success: function (data) {
            console.log( 111 );
            window.location.href = '/pressureCalculation/toPressureTypeSelectPage';
        }
    });
}

var getData = function () {

    var stationAddParam = {};

    // 站台基本信息
    var plateform = {};

    $.each( $( '.lift-container:visible' ).find( '#platefomBaseForm' ).serializeArray(), function (index, value ) {
        plateform[ value.name ] = value.value;
    } );

    // 线路
    var lineCode = $( '#myTabs' ).find( 'a.active' ).find( 'input' ).val();
    plateform.lineCode = lineCode;

    if ( checkAllObjectPropIsNotNull( plateform ) )
        stationAddParam.plateform = plateform;

    // 站台设备长宽
    var plateformDevices = [];

    $( '#floorLengthTr' ).find( 'input' );

    $.each( $( '#floorLengthTr' ).find( 'input' ), function ( index ) {

        if ( $( this ).val() ) {
            var plateformDevice = {
                deviceLength : $( this ).val()
            }

            plateformDevices.push( plateformDevice );
        }

    } );

    $.each( $( '#floorWidthTr' ).find( 'input' ), function ( index ) {

        if ( index < plateformDevices.length )
            plateformDevices[ index ].deviceWidth = $( this ).val();

    } );

    // 去除有属性为空的数据
    var handledPlateformDevices = [];
    plateformDevices.forEach( function (value) {
        if ( checkAllObjectPropIsNotNull( value ) )
            handledPlateformDevices.push( value );
    } );

    stationAddParam.plateformDevices = handledPlateformDevices;

    // 扶梯基本信息
    var escalator = {};

    $.each( $( '#escalatorForm' ).serializeArray(), function ( index, value ) {
        escalator[ value.name ] = value.value;
    } ) ;

    if ( checkAllObjectPropIsNotNull(escalator)  )
        stationAddParam.escalator = escalator;

    // 闸机基本信息
    var gate = {};

    $.each( $( 'form[name=gateForm]:visible' ).serializeArray(), function ( index, value ) {
        gate[ value.name ] = value.value;
    } ) ;

    if ( checkAllObjectPropIsNotNull( gate ))
        stationAddParam.gate = gate;

    // 闸机统计信息
    var gateStatisticsList = [];

    $.each( $( '#gateStatisticsTable' ).find( 'tr:gt(1)' ), function ( index ) {

        var gateStatistics = {};
        // console.log( $( this ) );
        $.each( $( this ).find( 'input' ).serializeArray(), function ( index, value ) {

            if ( value.name )
                gateStatistics[ value.name ] = value.value;
        } ) ;

        if ( checkAllObjectPropIsNotNull( gateStatistics ) )
            gateStatisticsList.push( gateStatistics );

    } );

    console.log( 'gateStatisticsList is ' );
    console.log( gateStatisticsList );
    stationAddParam.gateStatisticsList = gateStatisticsList;

    // 出入口基本信息和统计信息
    var entranceArr = [];

    $.each(  $( '.lift-container:visible' ).find( '.entranceDiv' ), function ( index, value ) {

        var entrance = {};
        console.log( 'form length' );
        console.log( $( this ).find( 'form' ).length );
        $.each( $( this ).find( 'form' ).serializeArray(), function ( index, value ) {

            entrance[ value.name ] = value.value;

        } );

        var entranceStatisticsList = [];

        $.each( $( this ).next( 'table' ).find( 'tr:gt(0)' ), function ( index, value ) {

            var entranceStatistics = {};

            // console.log( 'input length is ' );
            // console.log( $( this ).find( 'input' ).length );

            $.each( $( this ).find( 'input' ).serializeArray(), function ( index, value ) {
                if ( value.value && value.value != '' )
                    entranceStatistics[ value.name ] = value.value;
            } ) ;

            if ( checkAllObjectPropIsNotNull( entranceStatistics ) )
                entranceStatisticsList.push( entranceStatistics );


        } );

        entrance.entranceStatisticsList = entranceStatisticsList;

        console.log( 'entrance is ' );

        if ( checkAllObjectPropIsNotNull( entrance ) )
            entranceArr.push( entrance );

    } );

    stationAddParam.entrances = entranceArr;

    // 换乘通道基本信息
    var transferPassageArr = [];

    $.each( $( '#transferPassageBaseForm' ).find( 'input[name=passageLength]' ), function ( index, value ) {
        var transferPassage = {};
        transferPassage.passageLength = $( this ).val();

        if ( checkAllObjectPropIsNotNull( transferPassage ) )
            transferPassageArr.push( transferPassage );

    } )

    // 换乘通道统计信息

    // 通道行走时间
    var transferPassageUsetimeListArr = [];
    // 通道人流量
    var transferPassageFlowListArr = [];
    $.each( $( '.transferPassageStatisticsClass' ), function () {

        var transferPassageUsetimeList =[];
        var transferPassageFlowList = [];
        $.each( $( this ).find( '.tongji' ), function ( index, value ) {

            if ( index == 0 )
                $.each( $( this ) .find( 'tr:gt(1)' ), function ( index, value ) {
                    var transferPassageUsetime = {};
                    $.each( $( this ).find( 'input' ).serializeArray(), function ( index, value ) {
                        transferPassageUsetime[ value.name ] = value.value;
                    } );

                    if ( checkAllObjectPropIsNotNull( transferPassageUsetime ) )
                        transferPassageUsetimeList.push( transferPassageUsetime )
                } );

            if ( index == 1 )
                $.each( $( this ) .find( 'tr:gt(1)' ), function ( index, value ) {
                    var transferPassageFlow = {};
                    $.each( $( this ).find( 'input' ).serializeArray(), function ( index, value ) {
                        transferPassageFlow[ value.name ] = value.value;
                    } );

                    if ( checkAllObjectPropIsNotNull( transferPassageFlow ) )
                        transferPassageFlowList.push( transferPassageFlow )

                } );
        } );
        transferPassageUsetimeListArr.push( transferPassageUsetimeList );
        transferPassageFlowListArr.push( transferPassageFlowList );
    } );

    for ( var i = 0; i < transferPassageArr.length; i++  ) {
        transferPassageArr[ i ].transferPassageUsetimeList = transferPassageUsetimeListArr[ i ];
        transferPassageArr[ i ].transferPassageFlowList =  transferPassageFlowListArr[ i ];
    }

    console.log( 'new transferPassageArr is ' );
    console.log( transferPassageArr )
    stationAddParam.transferPassages = transferPassageArr;

    return stationAddParam;

}
