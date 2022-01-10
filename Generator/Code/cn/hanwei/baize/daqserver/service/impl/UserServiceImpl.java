package cn.hanwei.baize.daqserver.service.impl;

import cn.hanwei.baize.daqserver.entity.User;
import cn.hanwei.baize.daqserver.mapper.UserMapper;
import cn.hanwei.baize.daqserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lishiming
 * @since 2021-12-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
