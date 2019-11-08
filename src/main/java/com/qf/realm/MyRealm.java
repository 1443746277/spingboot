package com.qf.realm;

import com.qf.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
String username = (String) principalCollection.getPrimaryPrincipal();
    String rolename = userService.getRoleByUsername(username);
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    Set<String> roles= new HashSet<>();
    roles.add(rolename);
    simpleAuthorizationInfo.setRoles(roles);
return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
String  username = (String)authenticationToken.getPrincipal();
String password = userService.seletUserByName(username);
return new SimpleAuthenticationInfo(username,password,"myRealm");

    }
}
