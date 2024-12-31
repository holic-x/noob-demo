package com.noob.zookeeper.curator.demo;

import com.noob.zookeeper.curator.demo.x_discovery.ServerInfo;
import com.noob.zookeeper.curator.demo.x_discovery.ZooKeeperConfig;
import com.noob.zookeeper.curator.demo.x_discovery.ZookeeperCuratorDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ZookeeperCuratorDemoApplication {

    @Autowired
    ZooKeeperConfig config;

    @Bean
    public ZookeeperCuratorDiscovery setZookeeperCuratorDiscovery() throws Exception {
        ZookeeperCuratorDiscovery zookeeperCuratorDiscovery = new ZookeeperCuratorDiscovery(config);
        return zookeeperCuratorDiscovery;
    }

    public static void main(String[] args) throws Exception {
        // SpringApplication.run(ZookeeperCuratorDemoApplication.class, args);

        ConfigurableApplicationContext ctx = SpringApplication.run(ZookeeperCuratorDemoApplication.class, args);

        // 注册服务
        ZookeeperCuratorDiscovery zcd = ctx.getBean(ZookeeperCuratorDiscovery.class);
        zcd.registerRemote(new ServerInfo("127.0.0.1",8081,"本地服务测试"));

        // 服务发现
        while(true){
            List<ServerInfo> serverInfoList =  zcd.queryRemoteNodes();
            for (ServerInfo serverInfo : serverInfoList) {
                System.out.println(serverInfo.host + ":" + serverInfo.port + "/" + serverInfo.descr);
                Thread.sleep(3000);
            }
        }
    }

}
