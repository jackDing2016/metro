package hello.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hello.constant.TransferTypeEnum;
import hello.entity.TrafficData;
import hello.entity.TransferData;
import hello.dao.TransferDataMapper;
import hello.service.TransferDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jack;
 * @since 2018-11-26
 */
@Service
public class TransferDataServiceImpl extends ServiceImpl<TransferDataMapper, TransferData> implements TransferDataService {

    @Override
    public List<Integer> getDataList(String lineCode, String stationName,
                                               TransferTypeEnum transferTypeEnum) {

        TransferData transferDataQuery = new TransferData();
        transferDataQuery.setStationName( stationName );

        if ( transferTypeEnum == TransferTypeEnum.TRANSFER_IN )
            transferDataQuery.setToLineCode( lineCode );
        else if ( transferTypeEnum == TransferTypeEnum.TRANSFER_OUT )
            transferDataQuery.setFromLineCode( lineCode );

        QueryWrapper<TransferData> queryWrapper =
                new QueryWrapper<>( transferDataQuery );

        // 排序字段不作为查询条件
        queryWrapper.orderByAsc( false, "data_order" );

        List<TransferData> transferDataList = list( queryWrapper );

        List<Integer> dataList = new ArrayList<>();
        for ( TransferData transferData : transferDataList ) {
            dataList.add( transferData.getTransferNum() );
        }

        return dataList;
    }
}
