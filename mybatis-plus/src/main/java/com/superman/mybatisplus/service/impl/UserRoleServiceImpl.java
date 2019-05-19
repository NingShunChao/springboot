package com.superman.mybatisplus.service.impl;

import com.superman.mybatisplus.entity.UserRole;
import com.superman.mybatisplus.dao.UserRoleMapper;
import com.superman.mybatisplus.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author Ningsc
 * @since 2019-05-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
