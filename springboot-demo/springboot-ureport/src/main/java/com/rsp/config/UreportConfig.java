package com.rsp.config;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ImportResource("classpath:ureport-console-context.xml")// 引入ureport-console-context.xml配置文件
@Configuration
public class UreportConfig implements BuildinDatasource {
    @Resource
    DataSource dataSource;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Bean //定义ureport的启动servlet
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean ureportServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new UReportServlet());
        servletRegistrationBean.addUrlMappings("/ureport/*");
        return servletRegistrationBean;
    }

    // 构建数据源名称
    @Override
    public String name() {
        return "customUReportDatasource";
    }

    // 构建数据源连接
    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Ureport 数据源 获取连接失败！");
            e.printStackTrace();
        }
        return null;
    }

}
