package com.superman.shiro.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.superman.shiro.common.shirorealm.MyShiroRealm;
import com.superman.shiro.common.util.MD5Utils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
/**
* @Author: Ningsc
* @Date: 2019/7/15
* @Description:   权限配置：Apache Shiro核心通过Filter来实现，就好像SpringMvc通过DispachServlet来主控制一样
* @Param:
* @return:
*/
@Configuration
public class ShiroConfig {

    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:  SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
    * @Param:
    * @return:
    */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 将Realm注入到SecurityManager中。
        securityManager.setRealm(myShiroRealm());
        // 注入缓存对象。
        securityManager.setCacheManager(ehCacheManager());
        // 注入rememberMeManager;
        securityManager.setRememberMeManager(cookieRememberMeManager());

        return securityManager;
    }


    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:   shiro拦截器，设置shiro的过滤规则。用于控制哪些请求需要身份认证后才能继续执行，哪些不需要认证
    * @Param:
    * @return:
    */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 匹配原则是最上面的优先匹配
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*************身份验证相关****************/
        // logout：退出拦截器，主要属性：redirectUrl：退出成功后重定向的地址（/）;示例“/logout=logout”
        // authc：基于表单的拦截器；如“/**=authc”，如果没有登录会跳到相应的登录页面登录；主要属性：usernameParam：表单提交的用户名参数名（ username）； passwordParam：表单提交的密码参数名（password）；rememberMeParam：表单提交的密码参数名（rememberMe）； loginUrl：登录页面地址（/login.jsp）；successUrl：登录成功后的默认重定向地址；failureKeyAttribute：登录失败后错误信息存储key（shiroLoginFailure）
        // authcBasic：Basic HTTP身份验证拦截器，主要属性： applicationName：弹出登录框显示的信息（application）
        // user：用户拦截器，用户已经身份验证/记住我登录的都可；示例“/**=user”
        // anon：匿名拦截器，即不需要登录即可访问；一般用于静态资源过滤；示例“/static/**=anon”
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/doRegister", "anon");
        filterChainDefinitionMap.put("/doLogin", "anon");
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        /*************授权相关****************/
        // roles：角色授权拦截器，验证用户是否拥有所有角色；主要属性： loginUrl：登录页面地址（/login.jsp）；unauthorizedUrl：未授权后重定向的地址；示例“/admin/**=roles[admin]”
        // perms：权限授权拦截器，验证用户是否拥有所有权限；属性和roles一样；示例“/user/**=perms[“user:create”]”
        // port：端口拦截器，主要属性：port（80）：可以通过的端口；示例“/test= port[80]”，如果用户访问该页面是非80，将自动将请求端口改为80并重定向到该80端口，其他路径/参数等都一样
        // rest：rest风格拦截器，自动根据请求方法构建权限字符串（GET=read, POST=create,PUT=update,DELETE=delete,HEAD=read,TRACE=read,OPTIONS=read, MKCOL=create）构建权限字符串；示例“/users=rest[user]”，会自动拼出“user:read,user:create,user:update,user:delete”权限字符串进行权限匹配（所有都得匹配，isPermittedAll）
        // ssl：SSL拦截器，只有请求协议是https才能通过；否则自动跳转会https端口（443）；其他和port拦截器一样


//        shiroFilterFactoryBean.setLoginUrl("/login");
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:   配置自定义的Realm。在Shiro中，所有有关身份认证及授权管理数据源的获取与管理，都在Realm中进行。
    * @Param:
    * @return:
    */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        // 设置解密规则
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:  密码凭着管理器：因为我们的密码是加过密的，所以，如果要Shiro验证用户身份的话，需要告诉它我们用的是md5加密的。
     * 同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的盐
     * 。这样Shiro就能顺利的解密密码并验证用户名和密码是否正确了。
    * @Param:
    * @return:
    */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName(MD5Utils.ENCRYPT_TYPE);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(MD5Utils.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }
    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     *  只有开启了AOP才执行doGetAuthorizationInfo()，也就权限拦截
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public SimpleMappingExceptionResolver resolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/403");
        resolver.setExceptionMappings(properties);
        return resolver;
    }


    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:   授权只会进行一次，这样就避免了重复授权,将缓存对象注入到securityManager中
    * @Param:
    * @return:
    */
    @Bean
    public EhCacheManager ehCacheManager() {
        System.out.println("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:   cookie对象
    * @Param:
    * @return:
    */
    @Bean
    public SimpleCookie rememberMeCookie() {
        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:   cookie管理对象
    * @Param:
    * @return:
    */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        return manager;
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:   ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
    * @Param:
    * @return:
    */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
