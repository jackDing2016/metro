package hello.service;

import hello.constant.PressureTimeTypeEnum;
import hello.constant.PressureTypeEnum;
import hello.entity.GateStatistics;
import hello.entity.PressureLevelResult;
import hello.entity.TransferPassageFlow;
import hello.param.StationAddParam;

import java.util.List;
import java.util.Map;

public interface PressureCalculateService {

    List<Double> calEntrance( String lineCode, String dataStr, String inputDataStr );

    /**
     *
     * @param lineCode
     * @param plateformArea
     * @param importNumber
     * @param transferIntoNumber
     * @param exportNumber
     * @param transferOutNumber
     * @param stationNameCode
     * @param headWay 发车间隔
     */
    void calPlateform(String lineCode, Double plateformArea,
                      List<Integer> importNumber, List<Integer> transferIntoNumber,
                      List<Integer> exportNumber, List<Integer> transferOutNumber,
                      String stationNameCode, Double headWay,
                      PressureTimeTypeEnum pressureTimeTypeEnum);

    /**
     * 计算压力等级的平均值
     * @param lineCodeFirst
     * @param lineCodeSecond
     * @param lineCodeWeightFirst
     * @param lineCodeSecondWeight
     * @param lineDataFirst
     * @param lineDataSecond
     * @param pressureTypeEnum
     */
    Map<String, Object>  calPressureLevelAvg(String lineCodeFirst, String lineCodeSecond,
                          Double lineCodeWeightFirst, Double lineCodeSecondWeight,
                          List<Double> lineDataFirst, List<Double> lineDataSecond,
                          PressureTypeEnum pressureTypeEnum, String stationNameCode);

    /**
     *
     * @param  lineCode line code
     * @param plateEscalatorNum 站台上楼梯和自动扶梯的组数
     * @param exportNumber 出站人数
     * @param transferOutNumber 换出人数
     * @param  walkSpeed 下车乘客在站台上的行走速度 0.5
     * @param  plateformLength 站台长度 140
     * @param  upEscalatorWidth 上行扶梯总宽度
     * @param  floorWidth   楼梯总宽度
     */
    void calEscalator( String lineCode, Integer plateEscalatorNum,
                       List<Integer> exportNumber, List<Integer> transferOutNumber,
                       Double walkSpeed, Double plateformLength,
                       Double upEscalatorWidth, Double floorWidth,
                       String stationNameCode,
                       PressureTimeTypeEnum pressureTimeTypeEnum );

    /**
     * 计算进/出闸
     * @param code
     * @param fiveMinNums 每五分钟进/出站人数
     * @param secNum    安检个数/ 出闸个数
     * @param avgSecTime    平均过检时间/平均过闸时间
     * @return
     */
    List<Double> calGateImportExport( String code, List<Integer> fiveMinNums,
                                Integer secNum, Double avgSecTime,
                                      PressureTimeTypeEnum pressureTimeTypeEnum);

    /**
     * 计算总的闸机结果
     * @param gateImportResultList
     * @param gateExportResultList
     * @param weitghEntrance
     * @param weightExit
     */
    void calGate( String lineCode, List<Double> gateImportResultList, List<Double> gateExportResultList,
                  Double weitghEntrance, Double weightExit, String stationNameCode,
                  PressureTimeTypeEnum pressureTimeTypeEnum);


    /**
     * 计算换乘通道
     * @param fromLineCode 从哪条线换乘 目前只支持一个车站两条线路的车站， 两个以上的车站如人民广场暂不支持
     * @param passageLength 换乘通道长度
     * @param tCommon   通畅情况下通过通道所用的时间
     * @param b 计算用
     * @param n 计算用
     */
    void calTransferPassage(String fromLineCode, Double passageLength,
                            Double tCommon, Double b,
                            Double n, List<TransferPassageFlow> transferPassageFlowList,
                            String stationNameCode);

    /**
     * 计算车站总的换乘通道压力等级
     * @param resultValueListFirst
     * @param resultValueListSecond
     * @param stationNameCode
     */
    List<PressureLevelResult> calAvgTransferPassage(List<Double> resultValueListFirst, List<Double> resultValueListSecond,
                                                    String stationNameCode);

    /**
     * 出入口压力等级换算
     * @param data
     * @return
     */
    String calLevel( Double data );

    /**
     * 出口压力等级计算
     * @param stationAddParam
     * @return
     */
    void  calEntranceAll( StationAddParam stationAddParam );

    Map<String, Object> getEntranceResultMap();

    Map<String, Object> getPlateformResultMap();

    Map<String, Object> getEscalatorResultMap();

    Map<String, Object> getGateResultMap();

    Map<String, Object> getTransferPassageResultMap();

    String calPlateformLevel( Double data );

    String calPlateformScore( Double data );

    String calTransferPassageLevel( Double data );


    String calGateLevel( Double data );

    String calGateScore( Double data );

    String calEscalatorScore( Double data );

    String calEscalatorLevel( Double data );

}
