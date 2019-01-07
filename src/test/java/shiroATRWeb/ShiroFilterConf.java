package shiroATRWeb;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroFilterConf {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //过滤器要过滤的请求（是一个map）
        Map<String, String> map = new HashMap<>();
        //过滤方式  匿名过滤和认证过滤  匿名过滤不进行认证   认证过滤会先进行认证
        map.put("/user/loginUser", "anon");
        map.put("/main/main.jsp", "anon");
        //除了直接登录请求和主页请求不认证，其它都先进行认证
        map.put("/**", "authc");

        //自定义登录页面的位置，默认是webapp下
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //安全管理器
    @Bean
    public SecurityManager getSecurityManager(Realm realm, CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    //Realm   交给SecurityManager
    @Bean
    public Realm getRealm(CredentialsMatcher credentialsMatcher) {
        MyRealm3 myRealm3 = new MyRealm3();
        myRealm3.setCredentialsMatcher(credentialsMatcher);
        return myRealm3;
    }

    //凭证匹配器credentialsMatcher   交给Realm
    @Bean
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }


    //缓存EhCacheManager  交给SecurityManager
    @Bean
    public CacheManager getCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }


}
