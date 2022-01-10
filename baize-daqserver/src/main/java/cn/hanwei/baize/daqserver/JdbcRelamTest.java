package cn.hanwei.baize.daqserver;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcRelamTest {

    static  DruidDataSource getDruidDataSource(){
         DruidDataSource druidDataSource = new DruidDataSource();{
            druidDataSource.setUrl("jdbc:postgresql://localhost:5432/Baize.System");
            druidDataSource.setUsername("postgres");
            druidDataSource.setPassword("admin");
        }
        return  druidDataSource;
    }
    public static void main(String[] args) throws SQLException {
        List<String> properties=new ArrayList<>();
        properties.add("admin");
        properties.add("test");
        String str=String.valueOf(properties);

        DruidDataSource druidDataSource=getDruidDataSource();
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);

        //创建自定义SQL
        String sql ="SELECT \"Password\" FROM \"User\" where \"Code\"= ?";

        jdbcRealm.setAuthenticationQuery(sql);

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("org", "5a445d710ae24cd276062b0c84850838");
        //登录认证
        subject.login(usernamePasswordToken);
        System.err.println(subject.isAuthenticated());//true
    }
}
