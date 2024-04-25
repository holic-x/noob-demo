package com.noob.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.noob.demo.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // 就相当于创建了一个xml 文件 <beans></beans>
@ComponentScan("com.noob.demo") //<context:component‐scan base‐package="com.noob.demo" >
@PropertySource("classpath:db.properties")
public class MainConfiguration {

    @Value("${mysql.username}")
    private String name;
    @Value("${mysql.password}")
    private String password;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.driverClassName}")
    private String driverName;

    // <bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource"></bean>
    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName(name);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverName);
        return dataSource;
    }

    // init‐method="initByConfig" destroy‐method="destroyByConfig"
    @Bean(initMethod = "initByConfig", destroyMethod = "destroyByConfig")
    public User userconf() {
        return new User();
    }

}
