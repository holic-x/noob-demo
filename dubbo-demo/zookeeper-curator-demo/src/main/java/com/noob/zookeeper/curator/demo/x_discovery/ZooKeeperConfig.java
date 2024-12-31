package com.noob.zookeeper.curator.demo.x_discovery;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 连接 zk server的配置信息,在application.properties进行配置
 */
//@Component  不需要配置：@Component，在@Configuration已经包括了，无需多此一举
@Data
// 标志这是一个配置文件
@Configuration
// 配置文件信息前缀
@ConfigurationProperties(prefix = "zookeeper")
public class ZooKeeperConfig {
    private String hostPort; // zookeeper 集群地址
    private String path; // 管理的zk路径
    private String namespace; // 命名空间
    private int sessionTimeoutMs = 60000;
    private int connectionTimeoutMs = 15000;
    private int baseSleepTimeMs = 1000; // 重试间隔
    private int maxRetries = 3; // 最大重试次数
}