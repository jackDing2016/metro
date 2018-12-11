package hello.service.impl;

import hello.constant.PressureTimeTypeEnum;
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
                                                         PressureTypeEnum pressureTypeEnum, PressureTimeTypeEnum pressureTimeTypeEnum) {

        List<Double> dataList1 =
                pressureLevelResultService.getDataList( lineCodeOne, stationNameCode,
                        pressureTypeEnum, pressureTimeTypeEnum );

        List<Double> dataList2 =
                pressureLevelResultService.getDataList( lineCodeTwo, stationNameCode,
                        pressureTypeEnum, pressureTimeTypeEnum );

        Double sum = 0.0;
        for ( Double douVal : dataList1) {
            sum += douVal;
        }

        Double sum2 = 0.0;
        // 是否有两条线路
        if ( dataList2 != null ) {
            for ( Double douVal : dataList2) {
                sum2 += douVal;
            }
        }

        Double avgVal=
                ( sum * Double.valueOf( weightOne ) + sum2 * Double.valueOf(  weightTwo == null ? 0 : weightTwo  )) / ( dataList1.size() );

        String avgLevel = null;
        String avgScore = null;
        if ( pressureTypeEnum == PressureTypeEnum.PLATEFORM ) {
            avgLevel = pressureCalculateService.calPlateformLevel( avgVal );
            avgScore = pressureCalculateService.calPlateformScore( avgVal );
        }
        else if ( pressureTypeEnum == PressureTypeEnum.Escalator ) {
            avgLevel = pressureCalculateService.calEscalatorLevel( avgVal );
            avgScore = pressureCalculateService.calEscalatorScore( avgVal );
        }
        else if ( pressureTypeEnum == PressureTypeEnum.GATE  ) {
            avgLevel = pressureCalculateService.calGateLevel( avgVal );
            avgScore = pressureCalculateService.calGateScore( avgVal );
        }


        Map<String, String> resultMap = new HashMap<>();

        resultMap.put( "avgLevel", avgLevel );
        resultMap.put( "avgScore", avgScore );


        return resultMap;
    }
}
