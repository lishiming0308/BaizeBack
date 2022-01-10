package cn.hanwei.baize.daqserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan(basePackages = "cn.hanwei.baize.daqserver.mapper")
@ServletComponentScan("cn.hanwei.baize.daqserver")
@SpringBootApplication
public class BaizeApplication {

    public static void main(String[] args) {

        SpringApplication.run(BaizeApplication.class, args);
    }

}
