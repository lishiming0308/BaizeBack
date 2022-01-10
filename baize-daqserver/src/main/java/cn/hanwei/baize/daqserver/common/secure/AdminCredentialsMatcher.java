package cn.hanwei.baize.daqserver.common.secure;

import cn.hanwei.baize.baizeutil.CheckcodeUtil;
import cn.hanwei.baize.baizeutil.StringUtil;
import cn.hanwei.baize.daqserver.entity.User;
import cn.hanwei.baize.daqserver.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author zhen
 * @description:
 * @date 2019-06-06 10:20
 */
@Component
public class AdminCredentialsMatcher extends SimpleCredentialsMatcher {
    @Autowired
    private IUserService  userService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Session session = SecurityUtils.getSubject().getSession();
        String checkCode = (String) session.getAttribute(Constans.CHECK_CODE_TEXT);
        AdminToken mytoken = (AdminToken) token;
        if (StringUtil.isNotEmpty(checkCode)) {
            String code = mytoken.getCheckCode();
            if (StringUtil.isEmpty(code)) {
                throw new AuthenticationException(MyMessageSource.get("credentials_matcher_check_code_null"));
            }
            if (!Objects.equals(checkCode, code)) {
                setCheckcode(session);
                throw new AuthenticationException(MyMessageSource.get("credentials_matcher_check_code_match_error"));
            }
        }

        String userName = (String) token.getPrincipal();
        User admin = userService.findUserByName(userName); // 查询用户
        if (null == admin) {
            // 添加验证码并抛出异常
            setCheckcode(session);
            throw new UnknownAccountException(MyMessageSource.get("credentials_matcher_user_not_exist"));
        }
        mytoken = new AdminToken(admin.getCode(), admin.getPassword(), checkCode);
        boolean matches = super.doCredentialsMatch(mytoken, info);
        // 如果认证，则清除缓存中的验证码
        if (matches) {
            session.removeAttribute(Constans.CHECK_CODE_TEXT);
            session.removeAttribute(Constans.CHECK_CODE_BASE64);
            session.setAttribute(Constans.USER_NAME, userName);
            session.setTimeout(Constans.SESSION_TIMEOUT);
        } else { // 否则， 在session中加入验证码
            setCheckcode(session);
        }
        return matches;
    }

    private final void setCheckcode(Session session) {
        String text = CheckcodeUtil.createText();
        String base64Str = CheckcodeUtil.createBase64Str(text);
        session.setAttribute(Constans.CHECK_CODE_TEXT, text);
        session.setAttribute(Constans.CHECK_CODE_BASE64, base64Str);
    }
}
