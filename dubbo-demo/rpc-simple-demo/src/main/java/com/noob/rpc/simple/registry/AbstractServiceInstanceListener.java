package com.noob.rpc.simple.registry;

import org.apache.curator.x.discovery.ServiceInstance;

// 服务实例接听接口实现
public abstract class AbstractServiceInstanceListener<T> implements ServiceInstanceListener<T> {

    public void onFresh(ServiceInstance<T> serviceInstance, ServerInfoEvent event) {
        switch (event) {
            case ON_REGISTER:
                onRegister(serviceInstance);
                break;
            case ON_UPDATE:
                onUpdate(serviceInstance);
                break;
            case ON_REMOVE:
                onRemove(serviceInstance);
                break;
        }
    }
}