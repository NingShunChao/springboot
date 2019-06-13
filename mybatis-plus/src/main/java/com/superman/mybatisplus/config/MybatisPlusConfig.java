package com.superman.mybatisplus.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 21:26
 * @Description: mybatis-plus分页插件配置
 */
@Configuration
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

    /**
     * @Author Ningsc
     * @Description   plus 的性能优化
     * @Date 23:40 2019/5/20
     * @Param []
     * @return com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor 
     * @throw 
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * @Author Ningsc
     * @Description  注册数据源（可有可无，springboot自动会读取application.yml中的配置）
     * @Date 23:42 2019/5/20
     * @Param []
     * @return javax.sql.DataSource
     * @throw
     */
    /*@Bean
    @ConfigurationProperties("spring.datasource" )
    public DataSource dataSource() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }*/
}
