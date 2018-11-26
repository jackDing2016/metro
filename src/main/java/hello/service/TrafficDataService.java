package hello.service;

import hello.constant.TimeIntervalTypeEnum;
import hello.constant.TrafficTypeEnum;
import hello.entity.TrafficData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jack
 * @since 2018-11-23
 */
public interface TrafficDataService extends IService<TrafficData> {

    /**
     *
     * @param trafficTypeEnum
     * @param timeIntervalTypeEnum
     * @param startOrder
     * @param endOrder
     * @param lineCode
     * @return
     */
    List<Integer> getDataList(TrafficTypeEnum trafficTypeEnum, TimeIntervalTypeEnum timeIntervalTypeEnum,
                              Integer startOrder, Integer endOrder, String lineCode);

}
