package hello.service;

import hello.constant.TimeIntervalTypeEnum;
import hello.constant.TrafficTypeEnum;
import hello.constant.TransferTypeEnum;
import hello.entity.TransferData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jack
 * @since 2018-11-26
 */
public interface TransferDataService extends IService<TransferData> {

    /**
     * 换入客流数据
     * @param lineCode
     * @param stationName
     * @return
     */
    List<Integer> getDataList(String lineCode, String stationName,
                                        TransferTypeEnum transferTypeEnum);

}
