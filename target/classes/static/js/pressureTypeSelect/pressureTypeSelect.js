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


    // 权重
    var percent = '?per2Val=' + per2Val + '&&per11Val=' + per11Val;



    $( '#plateformResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/1' + percent;
    } );

    $( '#escalatorResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/2' + percent;
    } );

    $( '#gateResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/3?' + percent;
    } );

    $( '#entranceResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/4?' + percent;
    } );

    $( '#transferPassageResBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureCalculationResultPage/5?' + percent;
    } );

    $( '#resultNewBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureAllTypeStatisticsPage' + percent;
    } );

    $( '#resultImgsampleBtn' ).click( function () {
        window.location.href = '/pressureCalculation/toPressureImgtatisticsPage';
    } );





});