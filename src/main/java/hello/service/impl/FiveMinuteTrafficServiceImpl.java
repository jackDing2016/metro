package hello.service.impl;

import hello.entity.FiveMinuteTraffic;
import hello.dao.FiveMinuteTrafficMapper;
import hello.service.FiveMinuteTrafficService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jack
 * @since 2018-11-22
 */
@Service
public class FiveMinuteTrafficServiceImpl extends ServiceImpl<FiveMinuteTrafficMapper, FiveMinuteTraffic> implements FiveMinuteTrafficService {

}
