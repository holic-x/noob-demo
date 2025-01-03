package com.noob.rpc.simple.registry;

import org.apache.curator.x.discovery.ServiceInstance;

// 服务实例监听接口
public interface ServiceInstanceListener<T> {

    void onRegister(ServiceInstance<T> serviceInstance);

    void onRemove(ServiceInstance<T> serviceInstance);

    void onUpdate(ServiceInstance<T> serviceInstance);

    void onFresh(ServiceInstance<T> serviceInstance, ServerInfoEvent event);

    enum ServerInfoEvent {
        ON_REGISTER,
        ON_UPDATE,
        ON_REMOVE
    }

}