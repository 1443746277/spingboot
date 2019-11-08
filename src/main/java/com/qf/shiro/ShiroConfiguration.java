package com.qf.shiro;

import com.qf.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 注解注入bean
 */
@Configuration
public class ShiroConfiguration {
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean getShiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/loginPage");
        shiroFilterFactoryBean.setSuccessUrl("/userlist");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        //过滤器
        Map<String,String> map = new HashMap<String,String>();
        map.put("/loginPage","anon");
        map.put("/login","anon");
        map.put("/**","authc");
        map.put("/add","roles[role1]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
  @Bean(name="securityManager")
public SecurityManager getSecurityManager(MyRealm myRealm){
        SecurityManager securityManager = new DefaultWebSecurityManager(myRealm);
        return securityManager;

}

@Bean(name="myRealm")
public MyRealm getMyRealm(){
        return new MyRealm();
}






}
