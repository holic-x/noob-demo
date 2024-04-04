package com.sz.demo.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class OracleCodeGenerator {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("");
        gc.setOutputDir("E:/dev-haha/workspace/mp-oracle-code/src/main/java");
        // 是否覆盖同名文件，默认是false
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // oracle数据库-数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setTypeConvert(new OracleTypeConvert() {
                               // 自定义数据库表字段类型转换
                               @Override
                               public DbColumnType processTypeConvert(String fieldType) {
                                   return super.processTypeConvert(fieldType);
                               }
                           });
        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        dsc.setUsername("CCB-WORK");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // 此处可以修改为您的表前缀
        strategy.setTablePrefix(new String[]{"mip_","eoas_","aps_"});
        //表名 字段名 是否使用下滑线命名
        strategy.setDbColumnUnderline(false) ;
        // 表名生成策略(数据库表映射到实体的命名策略)(nochange 无改变)
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
//        strategy.setInclude(new String[]{"MIP_USER","MIP_ROLE","MIP_AUTHORITY","MIP_USER_ROLE","MIP_ROLE_AUTHORITY"});
//        strategy.setInclude(new String[]{"EOAS_QUESTIONNAIRE","EOAS_QUESTION","EOAS_CHECK_RECORD","EOAS_ANSWER_RECORD","EOAS_HISTORY","EOAS_SYSTEM_CONFIG"});
//        strategy.setInclude(new String[]{"EOAS_ANNEX"});
        strategy.setInclude(new String[]{"APS_QUESTIONNAIRE","APS_QUESTION","APS_HISTORY","APS_CHECK_RECORD","APS_ANSWER_RECORD"});

        // 排除生成的表
        // strategy.setExclude(new String[]{"test"});
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.sz.mip.framework.rest");
        pc.setParent("com.sz.mip.aps");
//        pc.setModuleName("test");
        pc.setController("controller");
        pc.setEntity("model");
        pc.setService("service");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
//            }
//        };
//        mpg.setCfg(cfg);

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
//        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}