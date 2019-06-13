package com.superman.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superman.mybatisplus.dao.PermissionMapper;
import com.superman.mybatisplus.entity.Permission;
import com.superman.mybatisplus.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    PermissionService permissionService;

    @Resource
    PermissionMapper permissionMapper;

    @Test
    public void test1() {
        List<Permission> list = permissionService.list();
        list.forEach(permission -> {
            System.out.println(permission.toString());
        });
    }

    @Test
    public void test2(){
        List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<>());
        permissions.forEach(permission -> {
            System.out.println(permission.toString());
        });
    }

}
