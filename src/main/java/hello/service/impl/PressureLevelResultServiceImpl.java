package hello.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hello.constant.MetroConstant;
import hello.constant.PressureTimeTypeEnum;
import hello.constant.PressureTypeEnum;
import hello.entity.PressureLevelResult;
import hello.dao.PressureLevelResultMapper;
import hello.service.PressureLevelResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.map.HashedMap;
import org.python.antlr.ast.Str;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jack
 * @since 2018-11-27
 */
@Service
public class PressureLevelResultServiceImpl extends ServiceImpl<PressureLevelResultMapper, PressureLevelResult>
        implements PressureLevelResultService {

    @Override
    public List<Double> getDataList(String lineCode, String stationNameCode,
                                    PressureTypeEnum pressureTypeEnum, PressureTimeTypeEnum pressureTimeTypeEnum ) {

        List<PressureLevelResult> pressureLevelResultList =
                getPressureLevelResults(lineCode, stationNameCode, pressureTypeEnum, pressureTimeTypeEnum);

        List<Double> dataList = new ArrayList<>();

        if ( pressureLevelResultList != null ) {
            for ( PressureLevelResult pressureLevelResult : pressureLevelResultList ) {
                dataList.add( pressureLevelResult.getResultValue() );
            }
        }

        return dataList;
    }

    @Override
    public List<String> getDataLevelList(String lineCode, String stationNameCode,
                                         PressureTypeEnum pressureTypeEnum, PressureTimeTypeEnum pressureTimeTypeEnum) {
        List<PressureLevelResult> pressureLevelResultList =
                getPressureLevelResults(lineCode, stationNameCode, pressureTypeEnum, pressureTimeTypeEnum);

        List<String> dataList = new ArrayList<>();

        if ( pressureLevelResultList != null ) {
            for ( PressureLevelResult pressureLevelResult : pressureLevelResultList ) {
                dataList.add( pressureLevelResult.getResultLevel() );
            }
        }

        return dataList;
    }

    private List<PressureLevelResult> getPressureLevelResults(String lineCode, String stationNameCode,
                                                              PressureTypeEnum pressureTypeEnum, PressureTimeTypeEnum pressureTimeTypeEnum) {
        PressureLevelResult pressureLevelResultQuery =
                new PressureLevelResult();
        pressureLevelResultQuery.setLineCode( lineCode );
        pressureLevelResultQuery.setStationName( stationNameCode );
        pressureLevelResultQuery.setPressureType( pressureTypeEnum.getCode() );
        pressureLevelResultQuery.setPressureTimeType( pressureTimeTypeEnum.getCode() );

        QueryWrapper<PressureLevelResult> queryWrapper =
                new QueryWrapper<>( pressureLevelResultQuery );

        queryWrapper.orderByAsc( false, "data_order" );

        return list( queryWrapper );
    }

    @Override
    public Map<String, Object> getDataListMap(String[] lineCodeArr,
                                              String stationNameCode, PressureTypeEnum pressureTypeEnum,
                                              PressureTimeTypeEnum pressureTimeTypeEnum) {

        Map<String, Object> dataMap = new HashedMap();

        for ( int i = 0; i < lineCodeArr.length; i++ ) {

            dataMap.put(MetroConstant.PREFFIX_LINE + lineCodeArr[ i ] + MetroConstant.SUFFIX_LINE,
                    getDataList( lineCodeArr[ i ], stationNameCode,  pressureTypeEnum, pressureTimeTypeEnum ));

        }

        return dataMap;
    }

    @Override
    public Map<String, Object> getDataLevelListMap(String[] lineCodeArr, String stationNameCode,
                                                   PressureTypeEnum pressureTypeEnum, PressureTimeTypeEnum pressureTimeTypeEnum) {

        Map<String, Object> dataMap = new HashedMap();

        for ( int i = 0; i < lineCodeArr.length; i++ ) {

            dataMap.put(MetroConstant.PREFFIX_LINE + lineCodeArr[ i ],
                    getDataLevelList( lineCodeArr[ i ], stationNameCode,  pressureTypeEnum, pressureTimeTypeEnum));

        }

        // just for test
//        String[] resultArr = null;
//        if ( pressureTimeTypeEnum == PressureTimeTypeEnum.WEEKDAY ) {
//            resultArr = new String[]{"C", "B", "A", "C", "D", "A", "D", "C" };
//        }
//        else {
//            resultArr = new String[]{"D", "B", "B", "B", "B", "C", "E", "A" };
//        }
//        dataMap.put(MetroConstant.PREFFIX_LINE + "8", resultArr);



        return dataMap;
    }
}
