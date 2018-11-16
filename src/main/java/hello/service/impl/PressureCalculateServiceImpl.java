package hello.service.impl;

import hello.constant.MetroConstant;
import hello.constant.PressureTypeEnum;
import hello.entity.*;
import hello.param.StationAddParam;
import hello.service.PressureCalculateService;
import jnr.ffi.annotations.In;
import org.python.antlr.ast.Str;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class PressureCalculateServiceImpl implements PressureCalculateService {

    private static Map<String, Object> entranceMap = new HashMap<>();

    private static Map<String, Object> plateformMap = new HashMap<>();

    private static Map<String, Object> escalatorMap = new HashMap<>();

    private static Map<String, Object> gateMap = new HashMap<>();

    private static Map<String, Object> transferPassageMap = new HashMap<>();

    @Override
    public Map<String, Object> getEntranceResultMap() {
        return entranceMap;
    }

    @Override
    public void calPlateform(String lineCode, Double plateformArea,
                             List<Integer> importNumber, List<Integer> transferIntoNumber,
                             List<Integer> exportNumber, List<Integer> transferOutNumber) {

        List<String> resultList = new ArrayList<>();
        // 用于计算加权平均值
        List<Double> resultValList = new ArrayList<>();


        for ( int i = 0; i < 8; i++ ) {

            double pPlateform =
                    (importNumber.get(i) + transferIntoNumber.get(i) + exportNumber.get(i) + transferOutNumber.get(i)) /
                            ( 4 *  plateformArea * 1.67);

            double pMax = pPlateform * 1.3;

            resultList.add( calPlateformLevel( pMax ) );
            resultValList.add( pMax );

        }


        plateformMap.put( "line" + lineCode, resultList );

        // 用于计算加权平均值
        plateformMap.put(  "line" + lineCode + "Val", resultValList );

    }

    @Override
    public String calPlateformLevel( Double data ) {
        String res = null;
        if ( data > 0 && data <= 0.2 )
            res = "A";
        else if ( data >0.2 && data <= 0.4 )
            res = "B";
        else if ( data >0.4 && data <= 0.6 )
            res = "C";
        else if ( data >0.6 && data <= 0.8 )
            res = "D";
        else if ( data >0.8 && data <= 1 )
            res = "E";
        else
            res = "C";
        return res;
    }

    private String calEscalatorLevel( Double data ) {
        String res = null;
        if ( data > 0 && data <= 25 )
            res = "E";
        else if ( data >25 && data <= 50 )
            res = "D";
        else if ( data >50 && data <= 100 )
            res = "C";
        else if ( data >100 && data <= 150 )
            res = "B";
        else if ( data >150 )
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
    public void calEntrance(String lineCode, String dataStr, String inputDataStr) {
        try {
            String[] args1 = new String[] { "E:\\Python27\\python", "E:\\learn\\java\\jyni\\churukou1107.py",
                    String.valueOf( dataStr ), String.valueOf( inputDataStr ) };
//            String[] args1 = new String[] { "python3", "/home/jack/develop/project/metro/pressurecalculate/churukou.py", String.valueOf( dataStr )};
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;

            String result = null;

            List<String> dataList = new ArrayList<>();

            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if ( line.startsWith( "precent" ) ) {
                    result = line.split( "=" )[1];

                    String level = calLevel( Double.valueOf( result ) );

                    dataList.add( level );

                }
            }
            in.close();
            proc.waitFor();
            entranceMap.put( "line" + lineCode, dataList );


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        return 0.0;
    }

    @Override
    public String calLevel(Double data) {

        String res = null;
        if ( data > 0 && data <= 0.2 )
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

        for ( Entrance entrance : entrances ) {

            StringBuffer inputArrSb = new StringBuffer();

            // reference data
            String referenceData = "758,723,866,913,926,914,789,854,833,720,643,692,748,702,572,387,806,932,1042,953,1070,945,610,606";

            List<EntranceStatistics> entranceStatisticsList = entrance.getEntranceStatisticsList();
            for ( EntranceStatistics entranceStatistics : entranceStatisticsList ) {
                inputArrSb.append( entranceStatistics.getEnterPersonNum() + "," );
            }

            String inputData = inputArrSb.toString().substring( 0, inputArrSb.lastIndexOf( "," ) );

            calEntrance( stationAddParam.getPlateform().getLineCode() + "",  referenceData, inputData);

        }

    }

    @Override
    public void calEscalator(String lineCode, Integer plateEscalatorNum,
                             List<Integer> exportNumber, List<Integer> transferOutNumber,
                             Double walkSpeed, Double plateformLength) {


        List<String> resultList = new ArrayList<>();

        for ( int i = 0; i < 8; i++ ) {

            Integer w = ( exportNumber.get(i) + transferOutNumber.get(i) );
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

            // 扶梯宽度
            Double escalatorLength = 1.0;

            // 楼梯宽度
            Double floorLength = 3.0;

            // 排队系统输出率
            Double u = cEscalator * escalatorLength + cFloor * floorLength;

            // 输出时间
            Double t1 = w / ( plateEscalatorNum * u );

            // 最大排队乘客数Q = q1 - q0
            Double qMax = lamda * t0 - u * t0;

            // 排队中最大延误时间 ts
            Double ts = t1 - t0;

            // 平均排队乘客数Q
            Double qAvg = 0.5 * ( lamda - u ) * t0;

            // 排队乘客总的延误时间D

            Double result = qAvg * ts;

            System.out.println( "排队乘客总的延误时间D " +  result );

            resultList.add( calEscalatorLevel( result ) );

        }

        escalatorMap.put( "line" + lineCode, resultList );

    }

    @Override
    public Map<String, Object> getEscalatorResultMap() {
        return escalatorMap;
    }

    @Override
    public String calPlateformScore(Double data) {
        String res = null;
        if ( data > 0 && data <= 0.2 )
            res = "A";
        else if ( data >0.2 && data <= 0.4 )
            res = "B";
        else if ( data >0.4 && data <= 0.6 )
            res = "C";
        else if ( data >0.6 && data <= 0.8 )
            res = "D";
        else if ( data >0.8 && data <= 1 )
            res = "E";
        else
            res = "C";
        return res;
    }

    @Override
    public List<Double> calGateImport(String lineCode, Double avgArrIntTime,
                              Double avgSecTime, Double secQueueArea,
                              List<GateStatistics> statisticsList) {

        // 平均到达间隔时间
        Double a = avgArrIntTime;
        // 平均过检时间
        Double c = avgSecTime;
        // 安检入口可供排队区域的面积
        Double s = secQueueArea;


        List<Double> resultList = new ArrayList<>();


        for ( GateStatistics gateStatistics : statisticsList ) {

            // 最大排队人数
            Integer b = gateStatistics.getMaxSecQueueNumber();

            // 最大排队人数持续的时间
            Double e = gateStatistics.getMaxSecQueueTime();

            // 每位乘客的平均排队时间
            Double t = 0.0;


            t = ( ( b - 1 ) * c + b * c + e * b * c / a ) / ( b + e / a );

            // 过闸效率指标q
            Double q = t / 60;

            // 密度指标p
            Double p = b*0.28/s;

            // 闸机客流压力综合评价模型建立 默认s1和s2为0.5
            Double result = 0.5 * q + 0.5 * p;

            resultList.add( result );

        }

        return resultList;

    }


    /**
     *
     * @param lineCode
     * @param exitGateNum 出站闸机数量
     * @param gateWidth 每个闸机的宽度
     * @param maxQueueLength 出站闸机后可供排队的长度
     * @param avgPassExitGateTime 平均过闸时间
     * @param avgArrIntTimeExp 平均到达间隔时间
     * @param statisticsList
     */
    @Override
    public List<Double> calGateExport(String lineCode, Integer exitGateNum,
                              Double gateWidth, Double maxQueueLength,
                              Double avgPassExitGateTime, Double avgArrIntTimeExp,
                              List<GateStatistics> statisticsList) {
        // 出站闸机数量
        Integer n = exitGateNum;
        // 每个闸机的宽度
        Double r = gateWidth;
        // 出站闸机后可供排队的长度
        Double d = maxQueueLength;
        // 平均到达间隔时间
        Double a = avgArrIntTimeExp;
        // 平均过闸时间
        Double c = avgPassExitGateTime;

        List<Double> resultList = new ArrayList<>();

        for ( GateStatistics gateStatistics : statisticsList ) {

            // 最大出闸排队人数数组str 12,12,166
            String maxExitNumberArrStr = gateStatistics.getMaxExitNumber();

            String[] maxExitNumberArr = maxExitNumberArrStr.split(",");

            // 最大出闸排队人数持续时间数组str
            String maxExitQueueTimeArrStr = gateStatistics.getMaxExitQueueTime();

            String[] maxExitQueueTimeArr = maxExitQueueTimeArrStr.split( "," );

            // 每位乘客的平均排队时间t
            Double tAvg = 0.0;
            for ( int i = 0; i < maxExitNumberArr.length; i++ ) {

                // 最大出闸排队人数
                Integer b = Integer.valueOf( maxExitNumberArr[i] );

                // 最大出闸排队人数持续时间
                Double e = Double.valueOf( maxExitQueueTimeArr[i] );


                // 每位乘客的平均排队时间t
                Double t = ( ( b - 1 ) * c + b * c + e * b * c / a ) / ( b + e / a );

                tAvg += t;

            }

            tAvg = tAvg / maxExitNumberArr.length;

            // 过闸效率指标k
            Double k = tAvg / 60;

            // 密度指标w TODO
            Double w = 0.0;


            // 闸机客流压力综合评价模型建立
            Double result = 0.5 * k;

            resultList.add( result );

        }

        return resultList;


    }

    @Override
    public void calGate(String lineCode, List<Double> gateImportResultList,
                        List<Double> gateExportResultList,Double weitghEntrance,
                        Double weightExit) {


        // 存等级
        List<String> resultList = new ArrayList<>();
        // 存值(用于计算各条线路的加权值)
        List<Double> resultValList = new ArrayList<>();


        for ( int i = 0; i < gateImportResultList.size(); i++ ) {
            Double result = gateImportResultList.get(i) * weitghEntrance + gateExportResultList.get(i) * weightExit;

            resultList.add( calPlateformLevel( result ) );
            resultValList.add( result );
        }

        gateMap.put( "line" + lineCode, resultList );
        gateMap.put( "line" + lineCode + "Val", resultValList );

    }

    @Override
    public void calTransferPassage(String lineCode, Double passageLength,
                                   Double tCommon, Double b, Double n,
                                   List<TransferPassageFlow> transferPassageFlowList) {

        // 存等级
        List<String> resultList = new ArrayList<>();
        // 存值(用于计算各条线路的加权值)
        List<Double> resultValList = new ArrayList<>();

        for ( TransferPassageFlow transferPassageFlow : transferPassageFlowList ) {

            Double result = tCommon + b * Math.pow( transferPassageFlow.getFlow(), n );

            result = passageLength / result;

            resultValList.add( result );
            resultList.add( calTransferPassageLevel( result ) );

        }

        transferPassageMap.put( "line" + lineCode, resultList);
        transferPassageMap.put( "line" + lineCode + "Val", resultValList );

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
    public void calPressureLevelAvg(String lineCodeFirst, String lineCodeSecond,
                                Double lineCodeWeightFirst, Double lineCodeSecondWeight,
                                List<Double> lineDataFirst, List<Double> lineDataSecond,
                                PressureTypeEnum pressureTypeEnum) {

        // 存等级
        List<String> resultList = new ArrayList<>();
        // 存值
        List<Double> resultValList = new ArrayList<>();


        if ( lineDataFirst != null && lineDataSecond != null ) {

            for ( int i = 0; i < lineDataFirst.size(); i++ ) {
                Double result = lineDataFirst.get(i) * lineCodeWeightFirst +
                        lineDataSecond.get(i) * lineCodeSecondWeight;

                if ( pressureTypeEnum.getCode() == PressureTypeEnum.PLATEFORM.getCode() )
                    resultList.add( calPlateformLevel( result ) );
                else if ( pressureTypeEnum.getCode() == PressureTypeEnum.Escalator.getCode() )
                    resultList.add( calEscalatorLevel( result ) );
                else if ( pressureTypeEnum.getCode() == PressureTypeEnum.GATE.getCode() )
                    resultList.add( calPlateformLevel( result ) );
                else if ( pressureTypeEnum.getCode() == PressureTypeEnum.ENTRANCE.getCode() )
                    resultList.add( calLevel( result ) );
                else if ( pressureTypeEnum.getCode() == PressureTypeEnum.TRANSFER_PASSAGE.getCode() )
                    resultList.add( calTransferPassageLevel( result ) );

                resultValList.add( result );
            }

        }


        if ( pressureTypeEnum.getCode() == PressureTypeEnum.PLATEFORM.getCode() ) {
            // 存等级
            plateformMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond, resultList);
            // 存值
            plateformMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond + MetroConstant.SUFFIX_LINE,
                    resultValList);
        }
        else if ( pressureTypeEnum.getCode() == PressureTypeEnum.Escalator.getCode() ) {
            // 存等级
            escalatorMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond, resultList);
            // 存值
            escalatorMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond + MetroConstant.SUFFIX_LINE,
                    resultValList);
        }
        else if ( pressureTypeEnum.getCode() == PressureTypeEnum.GATE.getCode() ) {
            // 存等级
            gateMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond, resultList);
            // 存值
            gateMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond + MetroConstant.SUFFIX_LINE,
                    resultValList);
        }
        else if ( pressureTypeEnum.getCode() == PressureTypeEnum.ENTRANCE.getCode() ) {
            // 存等级
            entranceMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond, resultList);
            // 存值
            entranceMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond + MetroConstant.SUFFIX_LINE,
                    resultValList);
        }
        else if ( pressureTypeEnum.getCode() == PressureTypeEnum.TRANSFER_PASSAGE.getCode() ) {
            // 存等级
            transferPassageMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond, resultList);
            // 存值
            transferPassageMap.put( MetroConstant.PREFFIX_LINE + "_" + lineCodeFirst + "_" + lineCodeSecond + MetroConstant.SUFFIX_LINE,
                    resultValList);
        }

    }


}
