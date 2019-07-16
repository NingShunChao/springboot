package com.superman.mybatisplus.util;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
* @Author: Ningsc
* @Date: 2019/7/16
* @Description:   mybatis-plus自动生成代码，Entity、Mapper、Mapper XML、Service、Controller
* @Param:
* @return:
*/
public class CodeGenerator {

    /**
     * 生成文件所在项目路径
     */
    private static String baseProjectPath = "/Users/ningshunchao/programDirectory/back/springboot/mybatis-plus";
    /**
     * 基本包名
     */
    private static String basePackage="com.superman.mybatisplus";
    /**
     * 作者
     */
    private static String authorName="Ningsc";
    /**
     * 要生成的表名
     */
    private static String[] tables= {"sys_user","sys_user_role","sys_role_permission","sys_role","sys_permission"};
    /**
     * table前缀
     */
    private static String prefix="sys_";
    /**
     * 数据库配置四要素
     */
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=true";
    private static String username = "root";
    private static String password = "root";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        /******************************全局配置********************************/
        GlobalConfig gc = new GlobalConfig();
        //输出目录
        gc.setOutputDir(baseProjectPath + "/src/main/java");
        // 作者
        gc.setAuthor(authorName);
        // 生成后打开文件夹
        gc.setOpen(false);
        // 是否覆盖文件
        gc.setFileOverride(true);
        // 开启 activeRecord 模式
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        //自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        //实体属性 Swagger注解
        gc.setSwagger2(true);

        autoGenerator.setGlobalConfig(gc);
        /******************************全局配置********************************/


        /******************************数据源配置********************************/
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);

        autoGenerator.setDataSource(dsc);
        /******************************数据源配置********************************/



        /******************************包配置********************************/
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        //自定义包路径
        pc.setParent(basePackage);
        /**
         * 包名
         */
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper");

        autoGenerator.setPackageInfo(pc);
        /******************************包配置********************************/


        /******************************指定模版引擎********************************/
        //指定模板引擎 默认是VelocityTemplateEngine ，需要引入相关引擎依赖
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        ///autoGenerator.setTemplateEngine(new BeetlTemplateEngine());
        // autoGenerator.setTemplateEngine(new VelocityTemplateEngine());

        /**
         * 自定义属性注入
         */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        /**
         * 自定义文件输出配置,自定义配置会被优先输出(非必须)
         */
        // 如果模板引擎是 freemarker
         String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         // String templatePath = "/templates/mapper.xml.vm";
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return baseProjectPath + "/src/main/resources/mapper/"  + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);


        autoGenerator.setCfg(cfg);
        /******************************指定模版引擎********************************/

        /******************************自定义代码模版********************************/
        TemplateConfig templateConfig = new TemplateConfig();
        // 关闭默认 xml 生成，调整生成 至 根目录
        templateConfig.setXml(null);
        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别，模板名称也可自定义如下配置：
        // .setController("...");
        // .setEntity("templates/entity2.java");
        // .setMapper("...");
        // .setXml("...");
        // .setService("...");
        // .setServiceImpl("...");
        autoGenerator.setTemplate(templateConfig);
        /******************************自定义代码模版********************************/


        /******************************策略配置********************************/
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
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
        // 此处可以修改为您的表前缀
        strategy.setTablePrefix(new String[]{prefix});
        // 需要生成的表
        strategy.setInclude(tables);
        //strategy.setExclude(new String[]{"表名"}); // 排除生成的表
        // 【实体】是否生成字段常量（默认 false）
        strategy.setEntityColumnConstant(false);
        // 【实体】是否为构建者模型（默认 false）
        strategy.setEntityBuilderModel(true);
        // 【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀处理
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 实体类属性是否添加注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        //controller为rest风格
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(false);


        autoGenerator.setStrategy(strategy);
        /******************************策略配置********************************/

        autoGenerator.execute();
    }


}
