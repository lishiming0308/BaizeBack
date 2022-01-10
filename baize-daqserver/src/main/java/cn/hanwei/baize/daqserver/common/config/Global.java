package cn.hanwei.baize.daqserver.common.config;

import cn.hanwei.baize.daqserver.entity.User;
import cn.hanwei.baize.daqserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

/**
 * @author guidon
 * 2019/6/4  14:49
 * 注意：@value不能直接注入到静态变量中需要用一个非静态的set方法注入
 */
@Configuration
@PropertySource(value = "classpath:config/test.properties")
public class Global implements Serializable {
    public String softSaveAdminDir;
    public String frontUrl;

    public String getFrontUrl() {
        return frontUrl;
    }
    @Value("${front_url}")
    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getSoftSaveAdminDir() {
        return softSaveAdminDir;
    }
    @Value("${soft_save_admin_dir}")
    public void setSoftSaveAdminDir(String softSaveAdminDir) {
        this.softSaveAdminDir = softSaveAdminDir;
    }


}