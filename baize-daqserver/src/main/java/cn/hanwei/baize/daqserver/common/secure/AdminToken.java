package cn.hanwei.baize.daqserver.common.secure;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author zhen
 * @description: 管理员令牌
 * @date 2019-06-05 14:34
 */
public class AdminToken extends UsernamePasswordToken {

    private String checkCode;
    public AdminToken(String username, String password, String checkCode) {
        super(username, password);
        this.checkCode = checkCode;
    }
    public String getCheckCode() {
        return checkCode;
    }
}
