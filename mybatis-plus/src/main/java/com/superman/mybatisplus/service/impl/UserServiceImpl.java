package com.superman.mybatisplus.service.impl;

import com.superman.mybatisplus.entity.User;
import com.superman.mybatisplus.dao.UserMapper;
import com.superman.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ningsc
 * @since 2019-05-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
