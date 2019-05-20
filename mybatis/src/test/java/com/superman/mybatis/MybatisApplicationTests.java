package com.superman.mybatis;


import com.superman.mybatis.entity.SysPermissionEntity;
import com.superman.mybatis.entity.SysUserEntity;
import com.superman.mybatis.mapper.SysPermissionEntityMapper;
import com.superman.mybatis.mapper.SysUserEntityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Resource
    SysUserEntityMapper sysUserEntityMapper;

    @Resource
    SysPermissionEntityMapper sysPermissionEntityMapper;

    @Test
    public void tongmapperByTk() {
        List<SysUserEntity> sysUserEntities = sysUserEntityMapper.selectAll();
        for (int i = 0; i < sysUserEntities.size(); i++) {
            System.out.println(sysUserEntities.get(i).toString());
        }
    }

    @Test
    public void tongmapperByZIDINGYI() {
        List<SysPermissionEntity> sysPermissionEntities = sysPermissionEntityMapper.selectAll();
        for (int i = 0; i < sysPermissionEntities.size(); i++) {
            System.out.println(sysPermissionEntities.get(i).toString());
        }
    }

}
