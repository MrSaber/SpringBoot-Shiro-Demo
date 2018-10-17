package com.mrsaber.shiro.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroRealm shiroRealm()
    {
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition()
    {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        //所有路径由注解进行管理
        //chainDefinition.addPathDefinition("/**","anon");
        chainDefinition.addPathDefinition("/login.html","anon");
        return chainDefinition;
    }

    /**
     * 核心安全事务管理器
     * @param shiroRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(ShiroRealm shiroRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }
}
