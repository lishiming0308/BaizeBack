package cn.hanwei.baize.baizeappgateway.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @author lishiming
 * @description: 后台认证-->控制器
 * @date 2022-1-7
 */
@Api(tags  = "Odata服务")
@RestController
@RequestMapping("/appData")
@Slf4j
public class AppDataController {
    /**
     *
     * @return 获得Token
     */
    @ApiOperation(value="获得Token",notes = "")
    @GetMapping("/getToken")
    public  String GetToken(){
        log.trace("trace 级别日志");
        log.debug("debug 级别日志");
        log.info("info 级别日志");
        log.warn("warn 级别日志");
        log.error("error 级别日志");
        return  "TestToken";
    }
}
