package com.superman.mybatisplus.util;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 21:35
 * @Description: mybatis-plus自动生成代码，Entity、Mapper、Mapper XML、Service、Controller
 */
public class CodeGenerator {

    //生成文件所在项目路径
    private static String baseProjectPath = "/Users/ningshunchao/programDirectory/back/springboot/mybatis-plus";
    //基本包名
    private static String basePackage="com.superman.mybatisplus";
    //作者
    private static String authorName="Ningsc";
    //要生成的表名
    private static String[] tables= {"sys_user","sys_user_role","sys_role_permission","sys_role","sys_permission"};
    //table前缀
    private static String prefix="sys_";
    //数据库配置四要素
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
    private static String username = "root";
    private static String password = "root";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(baseProjectPath + "/src/main/java");//输出目录
        gc.setAuthor(authorName);
        gc.setOpen(false);//生成后打开文件夹
        gc.setFileOverride(true);// 是否覆盖文件
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        //自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        /*dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                //    return DbColumnType.BOOLEAN;
                // }
                return super.processTypeConvert(fieldType);
            }
        });*/
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent(basePackage);//自定义包路径
        pc.setController("controller");// 这里是控制器包名，默认 web
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };


        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // 自定义文件输出配置,自定义配置会被优先输出(非必须)
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return baseProjectPath + "/src/main/resources/mapper/"  + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        //指定模板引擎 默认是VelocityTemplateEngine ，需要引入相关引擎依赖
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);// 关闭默认 xml 生成，调整生成 至 根目录
        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别，模板名称也可自定义如下配置：
        // .setController("...");
        // .setEntity("templates/entity2.java");
        // .setMapper("...");
        // .setXml("...");
        // .setService("...");
        // .setServiceImpl("...");
        mpg.setTemplate(templateConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");//自定义实体父类 默认BaseEntity
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");// 自定义 controller 父类 默认BaseController
        //strategy.setSuperEntityColumns("id");// 自定义实体，公共字段
        //strategy.setTableFillList(tableFillList);
        //strategy.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper");// 自定义 mapper 父类 默认BaseMapper
        //strategy.setSuperServiceClass("com.baomidou.demo.TestService");// 自定义 service 父类 默认IService
        //strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");// 自定义 service 实现类父类 默认ServiceImpl
        //strategy.setCapitalMode(true);//全局大写命名
        //strategy.setDbColumnUnderline(true);//全局下划线命名
        strategy.setTablePrefix(new String[]{prefix});// 此处可以修改为您的表前缀
        strategy.setInclude(tables); // 需要生成的表
        //strategy.setExclude(new String[]{"表名"}); // 排除生成的表
        strategy.setEntityColumnConstant(false);// 【实体】是否生成字段常量（默认 false）
        strategy.setEntityBuilderModel(false);// 【实体】是否为构建者模型（默认 false）
        strategy.setEntityLombokModel(true);// 【实体】是否为lombok模型（默认 false）
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);// Boolean类型字段是否移除is前缀处理
        strategy.setRestControllerStyle(true);//controller为rest风格
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        mpg.execute();
    }


}
