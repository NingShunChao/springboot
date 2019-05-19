package com.superman.shiro.service.impl;

import com.superman.shiro.entity.SysUserEntity;
import com.superman.shiro.mapper.SysUserEntityMapper;
import com.superman.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 11:03
 * @Description:
 */
@Service
public class SysUserImplService implements SysUserService {

    @Resource
    SysUserEntityMapper sysUserEntityMapper;

    @Override
    public SysUserEntity findSysUser(String username) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername(username);
        return sysUserEntityMapper.selectOne(sysUserEntity);
    }
}
