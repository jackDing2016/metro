package hello.service.impl;

import hello.constant.PressureTypeEnum;
import hello.service.PressureCalculateService;
import hello.service.PressureLevelResultService;
import hello.service.PressureLevelStatisticsService;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PressureLevelStatisticsServiceImpl implements PressureLevelStatisticsService {

    @Autowired
    private PressureLevelResultService pressureLevelResultService;

    @Autowired
    private PressureCalculateService pressureCalculateService;

    @Override
    public Map<String, String> calculateAvgLevelAndScore(String lineCodeOne, String lineCodeTwo,
                                                         Double weightOne, Double weightTwo, String stationNameCode,
                                                         PressureTypeEnum pressureTypeEnum) {

        List<Double> dataList1 =
                pressureLevelResultService.getDataList( lineCodeOne, stationNameCode,
                        pressureTypeEnum);

        List<Double> dataList2 =
                pressureLevelResultService.getDataList( lineCodeTwo, stationNameCode,
                        pressureTypeEnum);

        Double sum = 0.0;
        for ( Double douVal : dataList1) {
            sum += douVal;
        }

        Double sum2 = 0.0;
        for ( Double douVal : dataList2) {
            sum2 += douVal;
        }

        Double avgVal=
                ( sum * Double.valueOf( weightOne ) + sum2 * Double.valueOf( weightTwo  )) / ( dataList1.size() );
        String avgLevel = pressureCalculateService.calPlateformLevel( avgVal );
        String avgScore = pressureCalculateService.calPlateformScore( avgVal );

        Map<String, String> resultMap = new HashMap<>();

        resultMap.put( "avgLevel", avgLevel );
        resultMap.put( "avgScore", avgScore );


        return resultMap;
    }
}
