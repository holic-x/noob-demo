package com.noob.zookeeper.curator.demo.x_discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceCache;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.InstanceSerializer;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * zookeeper-curator-x-discover 示例
 */
public class ZookeeperCuratorDiscovery {

    private ServiceDiscovery<ServerInfo> serviceDiscovery;

    private ServiceCache<ServerInfo> serviceCache;

    private CuratorFramework client;

    private String root;

    // 此处JsonInstanceSerializer是将ServerInfo序列化成Json
    private InstanceSerializer serializer = new JsonInstanceSerializer<>(ServerInfo.class);

    // 构造器
    public ZookeeperCuratorDiscovery(ZooKeeperConfig config) throws Exception {
        // 初始化root
        this.root = config.getPath();

        // ① 创建并启动 Curator客户端
        client = CuratorFrameworkFactory.newClient(config.getHostPort(), new ExponentialBackoffRetry(config.getBaseSleepTimeMs(), config.getMaxRetries()));
        client.start(); // 启动Curator客户端
        client.blockUntilConnected();  // 阻塞当前线程，等待连接成功

        // ② 创建并启动 ServiceDiscovery
        serviceDiscovery = ServiceDiscoveryBuilder
                .builder(ServerInfo.class)
                .client(client) // 依赖Curator客户端
                .basePath(root) // 管理的Zk路径
                .watchInstances(true) // 当ServiceInstance加载
                .serializer(serializer) // 序列化
                .build();
        serviceDiscovery.start(); // 启动ServiceDiscovery

        // ③ 创建ServiceCache，监Zookeeper相应节点的变化，也方便后续的读取
        serviceCache = serviceDiscovery.serviceCacheBuilder()
                .name(root)
                .build();
        serviceCache.start(); // 启动ServiceCache
    }

    // 服务注册：将ServiceInfo对象注册到ZK
    public void registerRemote(ServerInfo serverInfo) throws Exception {
        // 将ServerInfo对象转换成ServiceInstance对象
        ServiceInstance<ServerInfo> thisInstance = ServiceInstance.<ServerInfo>builder()
                .name(root)
                .id(UUID.randomUUID().toString()) // 随机生成的UUID
                .address(serverInfo.getHost()) // host
                .port(serverInfo.getPort()) // port
                .payload(serverInfo) // payload
                .build();

        // 将ServiceInstance写入到Zookeeper中
        serviceDiscovery.registerService(thisInstance);
    }

    // 服务发现：查找注册到ZK的服务节点列表
    public List<ServerInfo> queryRemoteNodes() {
        List<ServerInfo> ServerInfoDetails = new ArrayList<>();
        // 查询 ServiceCache 获取全部的 ServiceInstance 对象
        List<ServiceInstance<ServerInfo>> serviceInstances = serviceCache.getInstances();
        serviceInstances.forEach(serviceInstance -> {
            // 从每个ServiceInstance对象的playload字段中反序列化得到ServerInfo实例
            ServerInfo instance = serviceInstance.getPayload();
            ServerInfoDetails.add(instance);
        });
        return ServerInfoDetails;
    }
}



