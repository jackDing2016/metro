package hello.service;

import hello.constant.PressureTypeEnum;
import hello.entity.PressureLevelResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
                             PressureTypeEnum pressureTypeEnum);

}
