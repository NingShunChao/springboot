package com.superman.shiro.service.impl;

import com.superman.shiro.common.util.MD5Utils;
import com.superman.shiro.entity.SysUserEntity;
import com.superman.shiro.mapper.SysUserEntityMapper;
import com.superman.shiro.service.SysUserService;
import org.apache.shiro.util.ByteSource;
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

    @Override
    public boolean registerData(String username, String password) {
        // 将用户名和密码作为盐值
        ByteSource salt = ByteSource.Util.bytes(username+password);

        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername(username);
        sysUserEntity.setPassword(MD5Utils.encrypt(password,salt));
        sysUserEntity.setState("1");

        SysUserEntity userEntity = findSysUser(username);
        if(userEntity != null){
            return false;
        }else {
            sysUserEntityMapper.insert(sysUserEntity);
            return  true;
        }
    }
}
