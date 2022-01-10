package cn.hanwei.baize.daqserver.service.impl;

import cn.hanwei.baize.daqserver.entity.User;
import cn.hanwei.baize.daqserver.mapper.UserMapper;
import cn.hanwei.baize.daqserver.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        List<User> rtn=new ArrayList<>();
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        rtn=userMapper.selectList(wrapper);
        return  rtn;
    }
    /**
     * 根据用户id获得用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public User getUser(String id) {
        User rtn= userMapper.selectById(id);
        return  rtn;
    }

    @Override
    public User findUserByName(String userName) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        //设置查询条件
        wrapper.eq("\"Code\"",userName);
        User rtn=userMapper.selectOne(wrapper);
        return  rtn;
    }

    @Override
    public String insert(User user) {
        String rtn="用户信息插入失败！";
        IdWorker idWorker=new IdWorker();
        String oid= Long.toString(idWorker.getId());
        user.setOid(oid);
        user.setId(null); //设置Id自动增长功能，通过数据库字段自动增长实现
       int res=  userMapper.insert(user);
       if(res>0){
           User addUser=getUser(user.getOid());
           user.setId(addUser.getId());
           rtn="用户插入成功！";
       }
       return rtn;
    }

    @Override
    public String update(User user) {
        String rtn="用户信息更新失败！";
        int res=  userMapper.updateById(user);
        if(res>0){
            rtn="用户信息更新成功！";
        }
        return rtn;
    }

    @Override
    public String delete(String id) {
        String rtn = "用户信息删除成功！";
        int res = userMapper.deleteById(id);
        if (res > 0) {
            rtn = "用户信息删除成功!";
        }
        return rtn;
    }
}
