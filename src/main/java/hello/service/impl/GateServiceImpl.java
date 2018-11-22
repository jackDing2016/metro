package hello.service.impl;

import hello.entity.Gate;
import hello.dao.GateMapper;
import hello.service.GateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jack
 * @since 2018-10-24
 */
@Service
public class GateServiceImpl extends ServiceImpl<GateMapper, Gate> implements GateService {

}
