package com.superman.mybatisplus.service.impl;

import com.superman.mybatisplus.entity.Permission;
import com.superman.mybatisplus.dao.PermissionMapper;
import com.superman.mybatisplus.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Ningsc
 * @since 2019-07-16
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
