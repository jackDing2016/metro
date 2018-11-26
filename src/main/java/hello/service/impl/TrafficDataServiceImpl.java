package hello.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hello.constant.TimeIntervalTypeEnum;
import hello.constant.TrafficTypeEnum;
import hello.entity.TrafficData;
import hello.dao.TrafficDataMapper;
import hello.service.TrafficDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jack
 * @since 2018-11-23
 */
@Service
public class TrafficDataServiceImpl extends ServiceImpl<TrafficDataMapper, TrafficData> implements TrafficDataService {


    @Override
    public List<Integer> getDataList(TrafficTypeEnum trafficTypeEnum,
                                     TimeIntervalTypeEnum timeIntervalTypeEnum, Integer startOrder, Integer endOrder,
                                     String lineCode) {

        TrafficData trafficDataQuery = new TrafficData();
        trafficDataQuery.setTrafficType( Integer.parseInt( trafficTypeEnum.getCode() ) );
        trafficDataQuery.setTimeIntervalType( Integer.parseInt( timeIntervalTypeEnum.getCode() ) );
        trafficDataQuery.setLineCode( lineCode );
        QueryWrapper<TrafficData> wrapper = new QueryWrapper<>( trafficDataQuery );

        wrapper.between( "data_order", startOrder, endOrder );
        wrapper.orderByAsc( "data_order" );

        List< TrafficData > trafficDataList = list( wrapper );
        List<Integer> fiveMinuteTrafficDataList = new ArrayList<>();
        for ( TrafficData trafficData : trafficDataList ) {
            fiveMinuteTrafficDataList.add( trafficData.getTrafficNum() );
        }

        return fiveMinuteTrafficDataList;
    }
}
