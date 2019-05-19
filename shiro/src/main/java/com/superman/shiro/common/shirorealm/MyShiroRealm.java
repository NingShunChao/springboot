package com.superman.shiro.common.shirorealm;

import com.superman.shiro.entity.SysUserEntity;
import com.superman.shiro.mapper.SysPermissionEntityMapper;
import com.superman.shiro.mapper.SysRoleEntityMapper;
import com.superman.shiro.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 11:14
 * @Description:
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleEntityMapper sysRoleEntityMapper;
    @Autowired
    SysPermissionEntityMapper sysPermissionEntityMapper;


    /**
     * @Author Ningsc
     * @Description  授权
     * @Date 11:17 2019/5/19
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @throw
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUserEntity userInfo  = (SysUserEntity)principalCollection.getPrimaryPrincipal();
        //采用java8的函数式编程，替代了foreach
        sysRoleEntityMapper.findRoleByUsername(userInfo.getUsername()).stream().forEach(
                sysRole -> {
                    authorizationInfo.addRole(sysRole.getRole());//添加juese
                    sysPermissionEntityMapper.findPermissionByRoleId(sysRole.getId()).stream().forEach(
                            sysPermission -> {
                                authorizationInfo.addStringPermission(sysPermission.getPermission());//添加权限
                            }
                    );
                }
        );
        return authorizationInfo;
    }


    /**
     * @Author Ningsc
     * @Description   认证
     * @Date 11:17 2019/5/19
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @throw
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUserEntity userInfo = sysUserService.findSysUser(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            ////没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
