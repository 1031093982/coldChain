package com.coldchain.demo.config;

import com.coldchain.demo.mapper.ItblAccount;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Relam类
 */
public class ShrioUserRealm extends AuthorizingRealm{
    @Autowired
    ItblAccount itblAccount;
    @Override
    //执行授权逻辑
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    @Override
    //执行认证逻辑
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        int  Cname =itblAccount.PselectCbyAccount(token.getUsername());
        String pwd = itblAccount.PselectPbyAccount(token.getUsername());
        System.out.println("数据库的密码"+pwd);
        System.out.println("前台的密码"+token.getPassword());
        if(Cname!=1){
            return null;
        }
        return new SimpleAuthenticationInfo("需要返回方法的数据",pwd,"shiro的名字");
    }
}
