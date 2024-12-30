package com.noob.zookeeper.curator.demo.x_discovery.bak;

import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    /*

    // 环境信息类
    @Autowired
    private Environment environment;

    //Curator client
    @Autowired
    private CuratorFramework curatorFramework;
     */

    /**
     * 服务注册：将当前启动的服务信息(ip+port)进行注册到zk中。
     */
    /*
    public void register(){
        try {
            // address.getHostAddress(): 192.168.0.106
            InetAddress address = InetAddress.getLocalHost();
            ServiceInstance<ServiceDetail> instance = ServiceInstance.<ServiceDetail>builder()
                    .address(address.getHostAddress())//ip地址：192.168.0.106
                    .port(Integer.parseInt(environment.getProperty("local.server.port")))//port:8080
                    .name("userService") //服务的名称
                    .payload(new ServiceDetail("用户服务", 1))
                    .build();

            ServiceDiscovery<ServiceDetail> serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                    .client(curatorFramework)
                    //.serializer() //序列化方式
                    .basePath(ServiceDetail.REGISTER_ROOT_PATH)
                    .build();

            //服务注册
            serviceDiscovery.registerService(instance);
            serviceDiscovery.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     */


    /**
     * 服务发现：通过serviceDiscovery查询到服务
     */
    /*
    public void discovery(){
        try {
            ServiceDiscovery<ServiceDetail> serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                    .client(curatorFramework)
                    .basePath(ServiceDetail.REGISTER_ROOT_PATH)
                    .build();
            serviceDiscovery.start();

            //根据名称获取服务
            Collection<ServiceInstance<ServiceDetail>> services = serviceDiscovery.queryForInstances("userService");
            for(ServiceInstance<ServiceDetail> service : services) {
                System.out.print(service.getPayload()+" -- ");
                System.out.println(service.getAddress() + ":" + service.getPort());
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     */


}