package com.superman.mybatisplus.service.impl;

import com.superman.mybatisplus.entity.Role;
import com.superman.mybatisplus.dao.RoleMapper;
import com.superman.mybatisplus.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Ningsc
 * @since 2019-07-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
