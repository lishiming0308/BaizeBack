package cn.hanwei.baize.daqserver.service;

import cn.hanwei.baize.daqserver.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author lishiming
 * @since 2021-12-29
 */
public interface IUserService extends IService<User> {
    List<User> getAllUser();
    User getUser(String id);
    User findUserByName(String userName);
    String insert(User user);
    String  update(User user);
    String delete(String id);

}
