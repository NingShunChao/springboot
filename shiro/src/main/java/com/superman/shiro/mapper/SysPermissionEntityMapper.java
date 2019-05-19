package com.superman.shiro.mapper;

import com.superman.shiro.entity.SysPermissionEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysPermissionEntityMapper extends Mapper<SysPermissionEntity> {

    List<SysPermissionEntity> findPermissionByRoleId(Integer roleId);
}