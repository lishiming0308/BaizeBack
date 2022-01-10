package cn.hanwei.baize.daqserver.common.secure;

import cn.hanwei.baize.daqserver.common.secure.util.PwdWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author zhen
 * @description: 平台管理员 realm
 * @date 2019-06-05 15:27
 */
public class AdminRealm extends AuthorizingRealm {

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");
        Session session = SecurityUtils.getSubject().getSession();
        session.setTimeout(Constans.SESSION_TIMEOUT);
        return  info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  {
        AdminToken mytoken = (AdminToken) token;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(mytoken.getUsername(), PwdWrapper.wraperMd5Pwd(new String(mytoken.getPassword()), mytoken.getUsername()), getName());
        return authenticationInfo;
    }
}
