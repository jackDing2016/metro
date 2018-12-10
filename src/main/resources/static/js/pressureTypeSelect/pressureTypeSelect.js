$(document).ready(function () {

    // 客流权重计算

    var lineCodeStr = $( '#lineCodeStr' ).val();

    // lineCodeStr = '2';

    var lineCodeArr = lineCodeStr.split( ',' );



    // 权重
    var percent = '';

    // 此站如果有多条线路
    if ( lineCodeArr.length > 1 ) {

        var num2Sum = Number( $( '[name=num2]:eq(0)' ).val() );
        var num11Sum = Number( $( '[name=num11]:eq(0)' ).val() );
        var per2Val, per11Val;
        var numSum = num2Sum + num11Sum;
        per2Val = num2Sum / numSum;
        per11Val = num11Sum / numSum;

        $( '[name=percent2]' ).val( per2Val );
        $( '[name=percent11]' ).val( per11Val );

        percent = '?per2Val=' + per2Val + '&&per11Val=' + per11Val;

    }
    else {

        per2Val = 1.00;
        $( '[name=percent2]' ).val( per2Val );

        percent = '?per2Val=' + per2Val;

        $.each( $( '[name=percent11]' ), function () {

            $( this ).parents( 'div:eq(1)' ).hide();

        } );

    }

    // param
    var param = '';



    // lineCodeStr
    var lineCodeStr = '&&lineCodeStr=' + $( '#lineCodeStr' ).val();

    // stationNameCode
    var stationNameCode = '&&stationNameCode=' + $( '#stationNameCode' ).val();

    // stationName
    var stationName = '&&stationName=' + $( '#stationName' ).val();

    param = percent + lineCodeStr + stationNameCode + stationName;

    $( '#plateformResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/1' + param;
    } );

    $( '#escalatorResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/2' + param;
    } );

    $( '#gateResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/3' + param;
    } );

    $( '#entranceResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/4' + param;
    } );

    $( '#transferPassageResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/5' + param;
    } );

    $( '#resultNewBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureAllTypeStatisticsPage' + param;
    } );

    $( '#resultImgsampleBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureImgtatisticsPage';
    } );


});