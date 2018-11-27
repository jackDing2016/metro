package hello.service;

import hello.constant.PressureTypeEnum;

import java.util.Map;

public interface PressureLevelStatisticsService {


    /**
     * 计算压力等级统计信息
     * @param lineCodeOne
     * @param lineCodeTwo
     * @param stationNameCode
     * @param pressureTypeEnum
     * @return
     */
    Map<String, String> calculateAvgLevelAndScore(String lineCodeOne, String lineCodeTwo,
            Double weightOne, Double weightTwo,
            String stationNameCode,  PressureTypeEnum pressureTypeEnum);



}
