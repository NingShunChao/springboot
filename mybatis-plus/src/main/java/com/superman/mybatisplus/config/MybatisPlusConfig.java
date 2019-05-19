package com.superman.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 21:26
 * @Description: mybatis-plus分页插件配置
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.superman.mybatisplus.dao")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");//设置方言类型
        return page;
    }
}
