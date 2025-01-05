package com.noob.rpc.simple.registry;

import org.apache.curator.x.discovery.ServiceInstance;

import java.util.List;

public interface Registry<T> {

    void registerService(ServiceInstance<T> service) throws Exception;

    void unregisterService(ServiceInstance<T> service) throws Exception;

    List<ServiceInstance<T>> queryForInstances(String name) throws Exception;

}