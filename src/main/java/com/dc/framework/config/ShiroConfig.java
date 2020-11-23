package com.dc.framework.config;

import com.dc.common.constant.CustomConstant;
import com.dc.framework.config.properties.SmsProperties;
import com.dc.framework.filter.CustomAuthenticationFilter;
import com.dc.framework.handler.CustomSessionManager;
import com.dc.framework.realm.CustomModularRealmAuthenticator;
import com.dc.framework.realm.CustomModularRealmAuthorizer;
import com.dc.framework.realm.SystemUserRealm;
import com.dc.framework.realm.WxUserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.AbstractSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@AutoConfigureAfter(ShiroLifecycleBeanPostProcessorConfig.class)
public class ShiroConfig {
    @Autowired
    private SmsProperties smsProperties;


    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());
        // 系统放行的请求
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/auth/getCode", "anon");
        // 第三方放行请求
        filterChainDefinitionMap.put("/open/auth/login", "anon");
        filterChainDefinitionMap.put("/open/item/list", "anon");
        filterChainDefinitionMap.put("/open/item/detail/*", "anon");
        // swagger放行
        filterChainDefinitionMap.put("/swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v3/api-docs", "anon");

        // 对所有请求进行认证
        filterChainDefinitionMap.put("/**", "customAuthenticationFilter");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //自定义过滤器
        HashMap<String, Filter> filters = new HashMap<>();
        filters.put("customAuthenticationFilter", customAuthenticationFilter());
        factoryBean.setFilters(filters);
        return factoryBean;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        manager.setAuthenticator(customModularRealmAuthenticator());
        manager.setAuthorizer(customModularRealmAuthorizer());
        List<Realm> realms = new ArrayList<>();
        realms.add(systemUserRealm());
        realms.add(wxUserRealm());
        manager.setRealms(realms);

        manager.setSessionManager(sessionManager());
        manager.setCacheManager(ehCacheManager());
        return manager;
    }

    /**
     * 系统用户Realm类
     */
    @Bean
    public SystemUserRealm systemUserRealm() {
        SystemUserRealm realm = new SystemUserRealm();
        //密文匹配的时候，这里需要设置credentialsMatcher()，否则无法匹配
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * 微信用户Realm类
     */
    @Bean
    public WxUserRealm wxUserRealm() {
        WxUserRealm realm = new WxUserRealm();
        return realm;
    }

    /**
     * 自定义的Realm管理，主要针对多realm
     *
     * @return
     */
    @Bean
    public CustomModularRealmAuthenticator customModularRealmAuthenticator() {
        CustomModularRealmAuthenticator authenticator = new CustomModularRealmAuthenticator();
        // 设置realm判断条件
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }

    @Bean
    public CustomModularRealmAuthorizer customModularRealmAuthorizer() {
        CustomModularRealmAuthorizer authenticator = new CustomModularRealmAuthorizer();
        // 设置realm判断条件
        return authenticator;
    }


    /**
     * 启用IOC容器中使用Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor attributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager());
        return attributeSourceAdvisor;
    }

    /**
     * shiro凭证匹配器
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        credentialsMatcher.setHashIterations(CustomConstant.ENCRYPTION_NUM);//散列的次数
        // true 密码加密用hex编码; false 用base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehcacheManager;
    }

    /**
     * 自定义sessionManager
     */
    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        // 会话过期时间，单位：毫秒(在无操作时开始计时)，默认30分钟
        sessionManager.setGlobalSessionTimeout(AbstractSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT * smsProperties.getTimeout());
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionDAO(sessionDao());
        return sessionManager;
    }

    @Bean("sessionDao")
    public SessionDAO sessionDao() {
        return new MemorySessionDAO();
    }

    /**
     * 自定义认证拦截器
     */
    public CustomAuthenticationFilter customAuthenticationFilter() {
        return new CustomAuthenticationFilter();
    }


}
