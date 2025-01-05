package com.noob.rpc.simple.demo.test;

import com.noob.rpc.simple.factory.BeanManager;
import com.noob.rpc.simple.proxy.RpcProxy;
import com.noob.rpc.simple.registry.ServerInfo;
import com.noob.rpc.simple.registry.ZookeeperRegistry;
import com.noob.rpc.simple.transport.RpcServer;
import org.apache.curator.x.discovery.ServiceInstance;

import java.util.List;

public class Provider {
    public static void main(String[] args) throws Exception {
        // 创建DemoServiceImpl，并注册到BeanManager中
        BeanManager.registerBean("demoService", new DemoServiceImpl());

        // 创建ZookeeperRegistry，并将Provider的地址信息封装成ServerInfo对象注册到Zookeeper
        ZookeeperRegistry<ServerInfo> discovery = new ZookeeperRegistry<>();
        discovery.start();
        ServerInfo serverInfo = new ServerInfo("127.0.0.1", 20880);

        discovery.registerService(ServiceInstance.<ServerInfo>builder().name("demoService").payload(serverInfo).build());

        // 启动RpcServer，等待Client的请求
        RpcServer rpcServer = new RpcServer(20880);
        rpcServer.start();

        // 保持服务状态，让程序不要执行完就立刻退出
        Thread.sleep(100000000L);
    }
}