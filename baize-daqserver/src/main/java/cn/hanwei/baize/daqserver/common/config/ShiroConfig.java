package cn.hanwei.baize.daqserver.common.config;

import cn.hanwei.baize.daqserver.common.secure.AdminCredentialsMatcher;
import cn.hanwei.baize.daqserver.common.secure.AdminRealm;
import cn.hanwei.baize.daqserver.entity.User;
import cn.hanwei.baize.daqserver.service.IUserService;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zhen
 * @description: shiro 配置类
 * @date 2019-06-05 14:26
 */
@Configuration
@PropertySource(value = "classpath:config/shiro.properties")
public class ShiroConfig implements EnvironmentAware {

    @Value("${admin_login_url}")
    private String adminLoingUrl;

    private Environment env;

    /**
     * @return org.apache.shiro.cache.ehcache.EhCacheManager
     * @throws
     * @author shenzhen
     * @description: 配置缓存管理器
     * @date 2019-06-06 11:38
     */
    @Bean("ehCacheManagerShiro")
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return em;
    }

    /**
     * @param manager 安全管理器
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @throws
     * @author shenzhen
     * @description: 配置shiroFilter工厂类
     * @date 2019-06-06 11:38
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setLoginUrl(this.adminLoingUrl);
        bean.setSecurityManager(manager);
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        String anonUrl = env.getProperty("admin_anon");
        String authcUrl = env.getProperty("admin_authc");
        String[] anonUrlArr = anonUrl.split(",");
        String[] authcUrlArr = authcUrl.split(",");
        for (String url : anonUrlArr) {
            filterChainDefinitionMap.put(url, "anon"); //表示可以匿名访问
        }
        for (String url : authcUrlArr) {
            filterChainDefinitionMap.put(url, "authc"); //表示登录后才可访问
        }
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean("authFilter")
    public Filter authenticationFilter() {
        return new FormAuthenticationFilter() {
            @Override
            protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
                if (request instanceof HttpServletRequest) {
                    if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                        return true;
                    }
                }
                boolean rtn= super.isAccessAllowed(request, response, mappedValue);
                return  rtn;
            }

            @Override
            protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        };
    }
    @Bean(name = "securityManager")
    public SecurityManager securityManager(AdminRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setCacheManager(getEhCacheManager());
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        manager.setRealms(realms);
        return manager;
    }
    /**
     * @return com.hanwei.alco.admin.common.secure.AdminRealm
     * @author shenzhen
     * @description: TODO
     * @date 2019-06-05 16:09
     */
    @Bean(name = "adminRealm")
    public AdminRealm adminRealm(@Autowired AdminCredentialsMatcher adminCredentialsMatcher) {
        AdminRealm userRealm = new AdminRealm();
        userRealm.setCacheManager(getEhCacheManager()); //设置缓存管理器
        userRealm.setCredentialsMatcher(adminCredentialsMatcher);
        return userRealm;
    }
    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}

