package hello.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hello.constant.*;
import hello.entity.*;
import hello.param.StationAddParam;
import hello.service.PressureCalculateService;
import hello.service.PressureLevelResultService;
import hello.service.TrafficDataService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class PressureCalculateServiceImpl implements PressureCalculateService {

    private static Map<String, Object> entranceMap = new HashMap<>();

    private static Map<String, Object> plateformMap = new HashMap<>();

    private static Map<String, Object> escalatorMap = new HashMap<>();

    private static Map<String, Object> gateMap = new HashMap<>();

    private static Map<String, Object> transferPassageMap = new HashMap<>();


    @Autowired
    private TrafficDataService trafficDataService;

    @Autowired
    private PressureLevelResultService pressureLevelResultService;


    @Override
    public Map<String, Object> getEntranceResultMap() {
        return entranceMap;
    }

    @Override
    public void calPlateform(String lineCode, Double plateformArea,
                             List<Integer> importNumber, List<Integer> transferIntoNumber,
                             List<Integer> exportNumber, List<Integer> transferOutNumber,
                             String stationNameCode, Double headWay,
                             PressureTimeTypeEnum pressureTimeTypeEnum) {

        List<String> resultList = new ArrayList<>();
        // 用于计算加权平均值
        List<Double> resultValList = new ArrayList<>();

        List<PressureLevelResult> pressureLevelResultList = new ArrayList<>();

        for ( int i = 0; i < 8; i++ ) {

            // 实际客流量
            Integer actualP =  importNumber.get(i) + exportNumber.get(i);

            // 有换乘的加上换乘
            if ( transferIntoNumber.size() > 0 )
                actualP += transferIntoNumber.get(i) + transferOutNumber.get(i);

            // 列车到站对数
            Double subWayN = 15 * 60 / headWay;

            double pPlateform = actualP /  ( subWayN *  plateformArea * 1.67);

            double pMax = pPlateform * 1.3;

            PressureLevelResult pressureLevelResult =
                    getPressureLevelResult(lineCode, stationNameCode, i, pMax,
                            calPlateformLevel( pMax ), PressureTypeEnum.PLATEFORM,
                            pressureTimeTypeEnum );

            pressureLevelResultList.add( pressureLevelResult );

        }


//        plateformMap.put( "line" + lineCode, resultList );
        // 用于计算加权平均值
//        plateformMap.put(  "line" + lineCode + "Val", resultValList );

        // 删除原来的 暂时从简
        savePressureResultData(lineCode, stationNameCode, pressureLevelResultList,
                PressureTypeEnum.PLATEFORM, pressureTimeTypeEnum);

    }

    private PressureLevelResult getPressureLevelResult(String lineCode, String stationNameCode,
                                                       int i, double pMax, String resultLevel, PressureTypeEnum pressureTypeEnum,
                                                       PressureTimeTypeEnum pressureTimeTypeEnum) {
        PressureLevelResult pressureLevelResult = new PressureLevelResult();
        pressureLevelResult.setDataOrder( i );
        pressureLevelResult.setLineCode( lineCode );
        pressureLevelResult.setStationName( stationNameCode );
        pressureLevelResult.setPressureType( pressureTypeEnum.getCode() );
        pressureLevelResult.setResultValue( pMax );
        pressureLevelResult.setResultLevel( resultLevel );
        pressureLevelResult.setPressureTimeType( pressureTimeTypeEnum.getCode() );
        return pressureLevelResult;
    }

    private void savePressureResultData(String lineCode, String stationNameCode,
                                        List<PressureLevelResult> pressureLevelResultList, PressureTypeEnum pressureTypeEnum,
                                        PressureTimeTypeEnum pressureTimeTypeEnum) {
        PressureLevelResult pressureLevelResultQuery =
                new PressureLevelResult();
        pressureLevelResultQuery.setLineCode( lineCode );
        pressureLevelResultQuery.setStationName( stationNameCode );
        pressureLevelResultQuery.setPressureType( pressureTypeEnum.getCode() );
        pressureLevelResultQuery.setPressureTimeType( pressureTimeTypeEnum.getCode() );
        QueryWrapper<PressureLevelResult> queryWrapper =
                new QueryWrapper<>(pressureLevelResultQuery);
        pressureLevelResultService.remove( queryWrapper );

        pressureLevelResultService.saveBatch( pressureLevelResultList );
    }


    @Override
    public String calPlateformLevel( Double data ) {
        String res = null;
        if ( data > 0.75 && data <= 1.0 )
            res = "A";
        else if ( data >0.6 && data <= 0.75 )
            res = "B";
        else if ( data >0.45 && data <= 0.6 )
            res = "C";
        else if ( data >0.3 && data <= 0.45 )
            res = "D";
        else if ( data >0.0 && data <= 0.3 )
            res = "E";
        else
            res = "C";
        return res;
    }

    @Override
    public String calEscalatorLevel( Double data ) {
        String res = null;

        if ( data > 0 )
            res = "E";
        else if ( data > 0 && data <= 450 )
            res = "D";
        else if ( data >450 && data <= 1800 )
            res = "C";
        else if ( data >1800 && data <= 3000 )
            res = "B";
        else if ( data >3000 )
            res = "A";
        else
            res = "C";
        return res;
    }

    @Override
    public Map<String, Object> getPlateformResultMap() {
        return plateformMap;
    }

    @Override
    public List<Double> calEntrance(String lineCode, String dataStr, String inputDataStr) {
        try {

            // develop environment
            String pythonPath = "E:\\Python27\\python";
            String pythonFilePath = "E:\\learn\\java\\jyni\\churukou1107.py";

            // server environment
//            String pythonPath = "python";
//            String pythonFilePath = "/root/churukou1107.py";

            String[] args1 = new String[] { pythonPath, pythonFilePath,
                    String.valueOf( dataStr ), String.valueOf( inputDataStr ) };


//            String[] args1 = new String[] { "python3", "/home/jack/develop/project/metro/pressurecalculate/churukou.py", String.valueOf( dataStr )};
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;

            String result = null;

//            List<String> dataList = new ArrayList<>();

            List<Double> resultValueList = new ArrayList<>();

            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if ( line.startsWith( "precent" ) ) {
                    result = line.split( "=" )[1];

//                    String level = calLevel( Double.valueOf( result ) );

//                    dataList.add( level );

                    resultValueList.add( Double.valueOf( result ) );

                }
            }
            in.close();
            proc.waitFor();
//            entranceMap.put( "line" + lineCode, dataList );

            return resultValueList;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        return 0.0;
        return null;
    }

    @Override
    public String calLevel(Double data) {

        String res = null;
        if ( data >= 0 && data <= 0.2 )
            res = "E";
        else if ( data >0.2 && data <= 0.3 )
            res = "D";
        else if ( data >0.3 && data <= 0.5 )
            res = "C";
        else if ( data >0.5 && data <= 0.8 )
            res = "B";
        else if ( data >0.8 && data <= 1 )
            res = "A";
        else
            res = "C";
        return res;
    }

    @Override
    public void calEntranceAll(StationAddParam stationAddParam) {

        List<Entrance> entrances = stationAddParam.getEntrances();

        String lineCode = stationAddParam.getPlateform().getLineCode() + "";

        String stationNameCode = stationAddParam.getPlateform().getStationNameCode();

        List<List<Double>> dataListList = new ArrayList<>();
        for ( Entrance entrance : entrances ) {

            StringBuffer inputArrSb = new StringBuffer();

            List<Integer> referenceDataList =
                trafficDataService.getDataList( TrafficTypeEnum.IMPORT, TimeIntervalTypeEnum.FIVE_MINUTE ,
                       3, 26,  lineCode, stationNameCode, PressureTimeTypeEnum.WEEKDAY );

            // reference data
            // String referenceData = "758,723,866,913,926,914,789,854,833,720,643,692,748,702,572,387,806,932,1042,953,1070,945,610,606";
            String referenceData = StringUtils.join( referenceDataList, "," );

            List<EntranceStatistics> entranceStatisticsList = entrance.getEntranceStatisticsList();
            for ( EntranceStatistics entranceStatistics : entranceStatisticsList ) {
                inputArrSb.append( entranceStatistics.getEnterPersonNum() + "," );
            }

            String inputData = inputArrSb.toString().substring( 0, inputArrSb.lastIndexOf( "," ) );

            dataListList.add( calEntrance( lineCode,  referenceData, inputData) );

        }

        List<PressureLevelResult> pressureLevelResultList = new ArrayList<>();
        Double entranceWeight = 1.0 / dataListList.size();
        for ( int i = 0; i < dataListList.get(0).size(); i++ ) {

            Double avgResultVal = 0.0;
            for ( int j = 0; j < dataListList.size(); j++ ) {
                avgResultVal += dataListList.get( j ).get( i ) * entranceWeight;
            }

            // 一共24个 每3个取1个 暂时从简
            if ( i % 3 == 0 ) {
                System.out.println( "balabalaba"+ i );
                PressureLevelResult pressureLevelResult = getPressureLevelResult(lineCode, stationNameCode,
                        i, avgResultVal, calLevel( avgResultVal ), PressureTypeEnum.ENTRANCE, PressureTimeTypeEnum.WEEKDAY);
                pressureLevelResultList.add( pressureLevelResult );
            }
        }

        // 删除原来的 暂时从简
        savePressureResultData(lineCode, stationNameCode,
                pressureLevelResultList, PressureTypeEnum.ENTRANCE, PressureTimeTypeEnum.WEEKDAY);

    }

    @Override
    public void calEscalator(String lineCode, Integer plateEscalatorNum,
                             List<Integer> exportNumber, List<Integer> transferOutNumber,
                             Double walkSpeed, Double plateformLength,
                             Double upEscalatorWidth, Double floorWidth,
                             String stationNameCode, PressureTimeTypeEnum pressureTimeTypeEnum) {


//        List<String> resultList = new ArrayList<>();

        List<PressureLevelResult> pressureLevelResultList = new ArrayList<>();

        for ( int i = 0; i < 8; i++ ) {

            Integer w = exportNumber.get(i);

            // 有换乘的加上换乘数据
            if ( transferOutNumber.size() > 0 )
                w += transferOutNumber.get(i);

            // 15min内，每组楼梯和扶梯服务的乘客数
            Integer q = w /  plateEscalatorNum;
            // 输入时间
            Double t0 = plateformLength / ( 2 * plateEscalatorNum *  walkSpeed );
            // 输入率
            Double lamda = 2 * w * walkSpeed / plateformLength;
            // 自动扶梯通过能力
            Double cEscalator = 2.25;
            // 楼梯通过能力
            Double cFloor = 0.89;

            // 排队系统输出率
            Double u = cEscalator * upEscalatorWidth / plateEscalatorNum + cFloor * floorWidth / plateEscalatorNum;

            // 输出时间
            Double t1 = w / ( plateEscalatorNum * u );

            // 最大排队乘客数Q = q1 - q0
            Double qMax = lamda * t0 - u * t0;

            // 排队中最大延误时间 ts
            Double ts = t1 - t0;

            // 平均排队乘客数Q
            Double qAvg = 0.5 * ( lamda - u ) * t0;

            // 排队乘客总的延误时间D

            Double result = qAvg * ts / 60;

            System.out.println( "排队乘客总的延误时间D " +  result );


            PressureLevelResult pressureLevelResult = getPressureLevelResult(lineCode, stationNameCode,
                    i, result, calEscalatorLevel( result ), PressureTypeEnum.Escalator,
                    pressureTimeTypeEnum);

            pressureLevelResultList.add( pressureLevelResult );

        }

//        escalatorMap.put( "line" + lineCode, resultList );

        // 删除原来的 暂时从简
        savePressureResultData(lineCode, stationNameCode,
                pressureLevelResultList, PressureTypeEnum.Escalator, pressureTimeTypeEnum);

    }

    @Override
    public Map<String, Object> getEscalatorResultMap() {
        return escalatorMap;
    }

    @Override
    public String calPlateformScore(Double data) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format( data * 100 );
    }


    @Override
    public List<Double> calGateImportExport(String code, List<Integer> fiveMinNums,
                                            Integer secNum, Double avgSecTime,
                                            PressureTimeTypeEnum pressureTimeTypeEnum ) {
        List<String> resultList = new ArrayList<>();
        // 用于计算加权平均值
        List<Double> resultValList = new ArrayList<>();

        // 平均过检时间1.5s
        Double ts = avgSecTime;

//        int count = 0;
//        Double resultMax = 0.0;
        for ( Integer q5min : fiveMinNums ) {

//            q5min = q5min / secNum;

            // 计算该五分钟内，客流达到率λ_5min
            Double lamda5min = q5min * 1.0 / ( 300 * secNum );

            // 计算五分钟内安检处的服务率μ_5min
            Double u5min = 1 / ts;

            Double result = lamda5min / u5min;

            resultValList.add( result );

            System.out.println( "export or / import result is " + result );

//            // 每3个取最大的放大list中
//            if ( count % 3 != 0 ) {
//                if ( result > resultMax ) {
//                    resultMax = result;
//                }
//
//                if ( count % 3 == 2 ) {
//                    resultValList.add( resultMax );
//                }
//
//            }
//            else {
//                resultMax = result;
//            }
//
//            count++;

        }

        System.out.println( "loop end" );
        return resultValList;
    }

    @Override
    public void calGate(String lineCode, List<Double> gateImportResultList,
                        List<Double> gateExportResultList,Double weitghEntrance,
                        Double weightExit, String stationNameCode,
                        PressureTimeTypeEnum pressureTimeTypeEnum) {


//        // 存等级
//        List<String> resultList = new ArrayList<>();
//        // 存值(用于计算各条线路的加权值)
//        List<Double> resultValList = new ArrayList<>();

        List<PressureLevelResult> pressureLevelResultList = new ArrayList<>();

        int count = 0;
        Double resultMax = 0.0;
        int dataOrder = 0;
        for ( int i = 0; i < gateImportResultList.size(); i++ ) {

            Double result = gateImportResultList.get(i) * weitghEntrance + gateExportResultList.get(i) * weightExit;

            System.out.println( "result is " + result );

            // 每3个取最大的放大list中
            if ( count % 3 != 0 ) {
                if ( result > resultMax ) {
                    resultMax = result;
                }

                if ( count % 3 == 2 ) {

//                    resultList.add( calPlateformLevel( resultMax ) );
//                    resultValList.add( resultMax );

                    PressureLevelResult pressureLevelResult = getPressureLevelResult(lineCode, stationNameCode,
                            dataOrder, resultMax, calGateLevel( resultMax ), PressureTypeEnum.GATE, pressureTimeTypeEnum);
                    dataOrder++;
                    pressureLevelResultList.add( pressureLevelResult );

                }

            }
            else {
                resultMax = result;
            }

            count++;

        }

//        gateMap.put( "line" + lineCode, resultList );
//        gateMap.put( "line" + lineCode + "Val", resultValList );

        // 删除原来的 暂时从简
        savePressureResultData(lineCode, stationNameCode,
                pressureLevelResultList, PressureTypeEnum.GATE, pressureTimeTypeEnum);


    }

    @Override
    public void calTransferPassage(String lineCode, Double passageLength,
                                   Double tCommon, Double b, Double n,
                                   List<TransferPassageFlow> transferPassageFlowList,
                                   String stationNameCode) {

        List<PressureLevelResult> pressureLevelResultList = new ArrayList<>();

        Integer dataOrder = 0;
        for ( TransferPassageFlow transferPassageFlow : transferPassageFlowList ) {

            Double result = tCommon + b * Math.pow( transferPassageFlow.getFlow(), n );

            result = passageLength / result;

            PressureLevelResult pressureLevelResult = getPressureLevelResult(lineCode, stationNameCode,
                    dataOrder, result, calTransferPassageLevel( result ), PressureTypeEnum.TRANSFER_PASSAGE,
                    PressureTimeTypeEnum.WEEKDAY);

            pressureLevelResultList.add( pressureLevelResult );

            dataOrder++;

        }

        // 删除原来的 暂时从简
        savePressureResultData(lineCode, stationNameCode,
                pressureLevelResultList, PressureTypeEnum.TRANSFER_PASSAGE, PressureTimeTypeEnum.WEEKDAY);

    }

    @Override
    public List<PressureLevelResult> calAvgTransferPassage(List<Double> resultValueListFirst,
                                      List<Double> resultValueListSecond, String stationNameCode) {

        List<PressureLevelResult> pressureLevelResultList = new ArrayList<>();

        if ( resultValueListFirst.size() > 0 && resultValueListSecond.size() > 0 ) {

            Integer dataOrder = 0;
            for ( int i = 0; i < resultValueListFirst.size(); i++ ) {

                if ( i % 3 == 0 ) {

                    Double result = ( resultValueListFirst.get( i ) + resultValueListSecond.get( i ) ) / 2;

                    PressureLevelResult pressureLevelResult = getPressureLevelResult( null, stationNameCode,
                            dataOrder, result, calTransferPassageLevel( result ), PressureTypeEnum.TRANSFER_PASSAGE,
                            PressureTimeTypeEnum.WEEKDAY);

                    pressureLevelResultList.add( pressureLevelResult );

                }

            }

            // 删除原来的 暂时从简
            savePressureResultData(null, stationNameCode,
                    pressureLevelResultList, PressureTypeEnum.TRANSFER_PASSAGE, PressureTimeTypeEnum.WEEKDAY);

        }

        return pressureLevelResultList;

    }

    @Override
    public Map<String, Object> getTransferPassageResultMap() {
        return transferPassageMap;
    }

    @Override
    public Map<String, Object> getGateResultMap() {
        return gateMap;
    }

    @Override
    public String calTransferPassageLevel( Double data ) {
        String res = null;
        if ( data > 0.0 && data <= 0.83 )
            res = "A";
        else if ( data >0.83 && data <= 1.09 )
            res = "B";
        else if ( data >1.09 && data <= 1.22 )
            res = "C";
        else if ( data >1.22 && data <= 1.32)
            res = "D";
        else if ( data >1.32  )
            res = "E";
        else
            res = "C";
        return res;
    }

    @Override
    public Map<String, Object> calPressureLevelAvg(String lineCodeFirst, String lineCodeSecond,
                                Double lineCodeWeightFirst, Double lineCodeSecondWeight,
                                List<Double> lineDataFirst, List<Double> lineDataSecond,
                                PressureTypeEnum pressureTypeEnum, String stationNameCode) {

        // 存等级
        List<String> resultList = new ArrayList<>();
        // 存值
        List<Double> resultValList = new ArrayList<>();

        boolean isHaveMutipleLine = true;

        // 只有一条线路
        if ( StringUtils.isEmpty( lineCodeSecond ) )
            isHaveMutipleLine = false;


        if ( lineDataFirst != null ) {

            for ( int i = 0; i < lineDataFirst.size(); i++ ) {
                Double result = lineDataFirst.get(i) * lineCodeWeightFirst;

                if ( isHaveMutipleLine )
                    result += lineDataSecond.get(i) * lineCodeSecondWeight;


                if ( pressureTypeEnum.getCode() == PressureTypeEnum.PLATEFORM.getCode() )
                    resultList.add( calPlateformLevel( result ) );
                else if ( pressureTypeEnum.getCode() == PressureTypeEnum.Escalator.getCode() )
                    resultList.add( calEscalatorLevel( result ) );
                else if ( pressureTypeEnum.getCode() == PressureTypeEnum.GATE.getCode() )
                    resultList.add( calGateLevel( result ) );
                else if ( pressureTypeEnum.getCode() == PressureTypeEnum.ENTRANCE.getCode() )
                    resultList.add( calLevel( result ) );
                else if ( pressureTypeEnum.getCode() == PressureTypeEnum.TRANSFER_PASSAGE.getCode() )
                    resultList.add( calTransferPassageLevel( result ) );

                resultValList.add( result );
            }

        }

        Map<String, Object> dataMap = new HashMap<>();

        // 存等级
//        dataMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond, resultList);

        dataMap.put( "dataList", resultList );

        // 存值
        dataMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond + MetroConstant.SUFFIX_LINE,
                    resultValList);

        return dataMap;

    }

    @Override
    public String calGateLevel(Double data) {
        String res = null;
        if ( data >= 1 )
            res = "A";
        else if ( data >= 0.6 && data < 1 )
            res = "B";
        else if ( data >= 0.3 && data < 0.6 )
            res = "C";
        else if ( data >=0.1 && data < 0.3 )
            res = "D";
        else if ( data > 0.1 && data < 0.0 )
            res = "E";
        else
            res = "C";
        return res;
    }

    @Override
    public String calGateScore(Double data) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format( data * 100 );
    }

    @Override
    public String calEscalatorScore(Double data) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format( data / 30 );
    }
}
