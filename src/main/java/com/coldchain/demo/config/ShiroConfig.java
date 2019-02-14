package com.coldchain.demo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 */
@Configuration
public class ShiroConfig {
/**
 *创建ShrioFilterFactoryBean
 */
@Bean
public ShiroFilterFactoryBean getshiroffbean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    //设置安全管理器
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    //添加Shiro内置过滤器（实现权限相关的拦截器）
    Map<String,String> filterMap =new LinkedHashMap<>();
    //第一次匹配优先原则：即从头开始使用第一个匹配的url模式对应的拦截器链
    filterMap.put("/login","anon");
    filterMap.put("/register","anon");
    filterMap.put("/logout","logout");
    filterMap.put("/*","authc");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
    //修改拦截成功跳转页面
    shiroFilterFactoryBean.setLoginUrl("login");
    return shiroFilterFactoryBean;
}
/**
 *创建DefaultWebSecurityManager
 */
@Bean(name="securityManager")
public DefaultWebSecurityManager getDWSM(@Qualifier("userRealm") ShrioUserRealm userRealm){
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    //关联relam
    securityManager.setRealm(userRealm);
    return securityManager;
}
/**
 *创建Realm
 */
@Bean(name="userRealm")
public ShrioUserRealm getRealm(){
    return new ShrioUserRealm();
}
}
