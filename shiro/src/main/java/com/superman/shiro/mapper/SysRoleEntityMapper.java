package com.superman.shiro.mapper;

import com.superman.shiro.entity.SysRoleEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleEntityMapper extends Mapper<SysRoleEntity> {

   List<SysRoleEntity> findRoleByUsername(String username);
}