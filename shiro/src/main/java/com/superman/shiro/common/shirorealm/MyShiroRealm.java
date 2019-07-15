package com.superman.shiro.common.shirorealm;

import com.superman.shiro.common.util.MD5Utils;
import com.superman.shiro.entity.SysUserEntity;
import com.superman.shiro.mapper.SysPermissionEntityMapper;
import com.superman.shiro.mapper.SysRoleEntityMapper;
import com.superman.shiro.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @Author: Ningsc
* @Date: 2019/7/15
* @Description:  自定义域
* @Param:
* @return:
*/
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleEntityMapper sysRoleEntityMapper;
    @Autowired
    SysPermissionEntityMapper sysPermissionEntityMapper;

    /**
     * 认证对象
     */
    private SimpleAuthenticationInfo authenticationInfo = null;




    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:  授权
    * @Param:
    * @return:
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
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:  认证
     * 1.doGetAuthenticationInfo，获取认证消息，如果数据库中没有数，返回null，如果得到了正确的用户名和密码，返回指定类型的对象
     * 2.AuthenticationInfo 可以使用SimpleAuthenticationInfo实现类，封装正确的用户名和密码
     * 3.token参数 就是我们需要认证的token
    * @Param:
    * @return:
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户的输入的账号.
        String username = (String)authenticationToken.getPrincipal();
        String password =  new String((char[]) authenticationToken.getCredentials());
//        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //通过username从数据库中查找 User对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUserEntity userInfo = sysUserService.findSysUser(username);
        System.out.println("----->>userInfo="+userInfo.toString());
        if(userInfo != null){
            // 封装查询结果，返回给我们的调用
            // 获取盐值,和注册时使用的加盐方式一样
            ByteSource salt = ByteSource.Util.bytes(username+password);
            password = MD5Utils.encrypt(password,salt);
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            authenticationInfo = new SimpleAuthenticationInfo(userInfo, password,salt, getName());
        }else {
            // 如果没有查询到，抛出一个异常
            throw new AuthenticationException();
        }
        return authenticationInfo;
    }
}
