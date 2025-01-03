package com.noob.rpc.simple.demo.test;

import com.noob.rpc.simple.proxy.RpcProxy;
import com.noob.rpc.simple.registry.ServerInfo;
import com.noob.rpc.simple.registry.ZookeeperRegistry;

public class Consumer {

    public static void main(String[] args) throws Exception {
        // 创建ZookeeperRegistry对象
        ZookeeperRegistry<ServerInfo> discovery = new ZookeeperRegistry<>();
        discovery.start();

        // 创建代理对象，通过代理调用远端Server
        DemoService demoService = RpcProxy.newInstance(DemoService.class, discovery);

        // 调用sayHello()方法，并输出结果
        String result = demoService.sayHello("hello");
        System.out.println(result);

        /*

        // 服务发现测试
        ZookeeperRegistry<ServerInfo> zcd = new ZookeeperRegistry<>();
        zcd.start();

        while(true){
            List<ServiceInstance<ServerInfo>> serverInfoList =  zcd.queryForInstances("demoService");
            for (ServiceInstance serverInfo : serverInfoList) {
                System.out.println("dddddddddddddddddd");
                System.out.println(serverInfo.getName() + serverInfo.getPort());
                Thread.sleep(3000);
            }
        }
         */
    }

}