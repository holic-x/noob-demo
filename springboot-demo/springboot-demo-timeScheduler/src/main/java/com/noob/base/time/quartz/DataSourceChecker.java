package com.noob.base.time.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * 数据源校验（应用启动的时候初始化执行，检验数据源是否正常装配）
 */
@Component
public class DataSourceChecker implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("dataSource:" + dataSource);
    }
}
