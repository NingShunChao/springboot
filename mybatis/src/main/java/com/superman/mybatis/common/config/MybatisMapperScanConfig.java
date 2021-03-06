package com.superman.mybatis.common.config;

//import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 13:49
 * @Description: mapper的扫描类(使用通用mapper的时候，使用tk开头的MapperScannerConfigurer扫描配置类)
 */
@Configuration
//由于MabatisMapperScanConfig执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(DataSourceConfig.class)
public class MybatisMapperScanConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        //不使用tk通用mpper使用，org包开头的MapperScannerConfigurer扫描配置类
        //MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //使用tk通用mapper的时候，使用tk开头的MapperScannerConfigurer扫描配置类
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //注意这里的sqlSessionFactory就是DataSourceConfig里面的sqlSessionFactoryBean方法，注解bean的名字
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //接口路径，通过这些接口调用sql的配置，操作数据库
        mapperScannerConfigurer.setBasePackage("com.superman.mybatis.mapper");
        return mapperScannerConfigurer;
    }
}
