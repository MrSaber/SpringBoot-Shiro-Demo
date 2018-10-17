package com.mrsaber.shiro.config;

import com.mrsaber.shiro.Mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper mapper;
    /**
     * 认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authenticationInfo =new SimpleAuthorizationInfo();
        authenticationInfo.setRoles(new HashSet<>(mapper.getRoleListByUserId(1)));
        return authenticationInfo;
    }

    /**
     * 授权
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        try {
            if(!new String(token.getPassword()).equals("123"))
                return null;
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(),getName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return simpleAuthenticationInfo;
    }


    @Override
    public String getName() {
        return "ShiroRealm";
    }
}
