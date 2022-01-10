package cn.hanwei.baize.daqserver.service.impl;

import cn.hanwei.baize.daqserver.entity.User;
import cn.hanwei.baize.daqserver.mapper.UserMapper;
import cn.hanwei.baize.daqserver.service.IUserService;
import org.hibernate.id.GUIDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService  iUserService;
    @Test
    void getAllUser() {
        List<User> usrs= iUserService.getAllUser();
    }
    @Test
    void getUser() {
        String id="24755734240428066";
        User usr= iUserService.getUser(id);
    }

    @Test
    void findUserByName() {
        String userNam="org";
        User user=iUserService.findUserByName(userNam);
    }
    @Test
    void insert() {
        User usr=new User();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        usr.setOid(uuid);
        usr.setName("测试用户");
        usr.setCode("testUser7");
        usr.setMail("lo@qq.com");
        usr.setCreateTime(LocalDateTime.now());
        usr.setCreateUser("admin");
        usr.setIsAdmin(false);
        usr.setIsDefaultUser(false);
        usr.setIsEnable(true);
        usr.setIsTenantAdmin(true);
        usr.setPassword("test");
        usr.setPhone("1300000000");
        usr.setUpdateTime(LocalDateTime.now());
        byte [] data=new byte[]{0x01,0x03, 0x1f};
        usr.setUserImage(data);
        String rtn= iUserService.insert(usr);

    }

    @Test
    void update() {
        String id="24755734240428066";
        User usr= iUserService.getUser(id);
        usr.setRemark("java测试修改");
        String rtn=iUserService.update(usr);
    }

    @Test
    void delete() {
        String id="5907073c37ad4f35990637270037f7f5";
        String rtn= iUserService.delete(id);
    }

}