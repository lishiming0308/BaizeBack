package cn.hanwei.baize.baizeappgateway;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;



import java.util.Map;

@Component
public class MyErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options); // 获取 Spring Boot 默认提供的错误信息
        errorAttributes.put("msg", "出错了！出错了！"); // 添加一个自定义的错误信息
        errorAttributes.remove("error"); // 移除一个默认的错误信息
        return errorAttributes;
    }
}
