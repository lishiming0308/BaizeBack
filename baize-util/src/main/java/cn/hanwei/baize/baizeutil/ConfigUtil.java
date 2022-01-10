package cn.hanwei.baize.baizeutil;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

    public  void springUtil() {
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadAllProperties("config/test.properties");
            for (Object key : props.keySet()) {
                System.out.print(key + ":");
                System.out.println(props.get(key));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
