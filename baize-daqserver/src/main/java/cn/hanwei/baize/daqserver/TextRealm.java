package cn.hanwei.baize.daqserver;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class TextRealm {
    public static void main(String[] args) {
        String java_home= System.getenv().get( "JAVA_HOME" );
        // 读取配置文件，初始化SecurityManager工厂
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        securityManager.setRealm(iniRealm);

        // 把securityManager实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        // 创建token令牌，用户名/密码
        UsernamePasswordToken token = new UsernamePasswordToken("csdn", "1234");
        // 得到当前执行的用户
        Subject currentUser = SecurityUtils.getSubject();
        try{
            // 身份认证
            currentUser.login(token);
            System.out.println("身份认证成功！");
        }catch(AuthenticationException e){
            e.printStackTrace();
            System.out.println("身份认证失败！");
        }
        // 退出
        currentUser.logout();
    }

}
