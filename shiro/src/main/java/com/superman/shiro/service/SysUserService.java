package com.superman.shiro.service;

import com.superman.shiro.entity.SysUserEntity;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 11:01
 * @Description: 系统用户信息
 */
public interface SysUserService {

    /**
     * @Author Ningsc
     * @Description  通过用户名查找用户
     * @Date 11:02 2019/5/19
     * @Param [username]
     * @return com.superman.shiro.entity.SysUserEntity
     * @throw
     */
    SysUserEntity findSysUser(String username);
}
