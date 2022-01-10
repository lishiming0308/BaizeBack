package cn.hanwei.baize.baizeappgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class BaizeAppgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaizeAppgatewayApplication.class, args);
    }

}
