package hello.service;

import hello.constant.PressureTimeTypeEnum;
import hello.constant.PressureTypeEnum;
import hello.entity.PressureLevelResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jack
 * @since 2018-11-27
 */
public interface PressureLevelResultService extends IService<PressureLevelResult> {

    List<Double> getDataList(String lineCode, String stationNameCode,
                             PressureTypeEnum pressureTypeEnum, PressureTimeTypeEnum pressureTimeTypeEnum);

    List<String> getDataLevelList(String lineCode, String stationNameCode,
                             PressureTypeEnum pressureTypeEnum, PressureTimeTypeEnum pressureTimeTypeEnum);

    Map<String, Object> getDataListMap( String[] lineCodeArr,
                                     String stationNameCode, PressureTypeEnum pressureTypeEnum,
                                        PressureTimeTypeEnum pressureTimeTypeEnum);

    Map<String, Object> getDataLevelListMap( String[] lineCodeArr,
                                        String stationNameCode, PressureTypeEnum pressureTypeEnum,
                                             PressureTimeTypeEnum pressureTimeTypeEnum);

}
