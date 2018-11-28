$(document).ready(function () {

    // 客流权重计算
    var num2Sum = Number( $( '[name=num2]:eq(0)' ).val() );
    var num11Sum = Number( $( '[name=num11]:eq(0)' ).val() );
    var per2Val, per11Val;
    var numSum = num2Sum + num11Sum;
    per2Val = num2Sum / numSum;
    per11Val = num11Sum / numSum;

    $( '[name=percent2]' ).val( per2Val );
    $( '[name=percent11]' ).val( per11Val );

    // $.each( $( '[name=percent2]' ), function () {
    //     $( this ).val( per2Val );
    // } );
    //
    // $.each( $( '[name=percent11]' ), function () {
    //     $( this ).val( per11Val );
    // } );

    ;
    // param
    var param = '';

    // 权重
    var percent = '?per2Val=' + per2Val + '&&per11Val=' + per11Val;

    // lineCodeStr
    var lineCodeStr = '&&lineCodeStr=' + $( '#lineCodeStr' ).val();

    // stationNameCode
    var stationNameCode = '&&stationNameCode=' + $( '#stationNameCode' ).val();

    param = percent + lineCodeStr + stationNameCode;

    $( '#plateformResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/1' + param;
    } );

    $( '#escalatorResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/2' + param;
    } );

    $( '#gateResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/3?' + param;
    } );

    $( '#entranceResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/4?' + param;
    } );

    $( '#transferPassageResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/5?' + percent;
    } );

    $( '#resultNewBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureAllTypeStatisticsPage' + param;
    } );

    $( '#resultImgsampleBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureImgtatisticsPage';
    } );





});