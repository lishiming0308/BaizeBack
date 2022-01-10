package cn.hanwei.baize.daqserver.common.config;

import cn.hanwei.baize.daqserver.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GlobalTest {

    @Autowired
    private Global  global;
    @Test
    void getFrontUrl() {
       String url= global.getFrontUrl();
    }

    @Test
    void setFrontUrl() {
        String url=global.getFrontUrl();
        url=url+"test";
        global.setFrontUrl(url);
    }

    @Test
    void getSoftSaveAdminDir() {
    }

    @Test
    void setSoftSaveAdminDir() {
    }
}