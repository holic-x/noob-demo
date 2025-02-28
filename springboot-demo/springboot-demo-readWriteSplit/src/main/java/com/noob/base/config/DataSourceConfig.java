package com.noob.base.config;

import com.noob.base.datasource.DataSourceRouter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.noob.base.user.dao",
        entityManagerFactoryRef = "routingEntityManagerFactory",
        transactionManagerRef = "routingTransactionManager"
)
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("master", masterDataSource);
        dataSources.put("slave", slaveDataSource);

        DataSourceRouter router = new DataSourceRouter();
        router.setDefaultTargetDataSource(masterDataSource);
        router.setTargetDataSources(dataSources);
        return router;
    }

    @Primary
    @Bean(name = "routingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean routingEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("routingDataSource") DataSource routingDataSource) {
        return builder
                .dataSource(routingDataSource)
                .packages("com.noob.base.user.entity") // 实体类所在的包
                .persistenceUnit("routingPU")
                .build();
    }

    @Primary
    @Bean(name = "routingTransactionManager")
    public PlatformTransactionManager routingTransactionManager(
            @Qualifier("routingEntityManagerFactory") EntityManagerFactory routingEntityManagerFactory) {
        return new JpaTransactionManager(routingEntityManagerFactory);
    }
}