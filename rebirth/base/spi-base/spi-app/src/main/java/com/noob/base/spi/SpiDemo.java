package com.noob.base.spi;

import com.noob.base.spi.service.SpiInterfaceService;

import java.util.Iterator;
import java.util.ServiceLoader;

// 测试SPI机制
public class SpiDemo {
    public static void main(String[] args) {
        // 使用ServiceLoader动态加载
        ServiceLoader<SpiInterfaceService> serviceLoader = ServiceLoader.load(SpiInterfaceService.class);
        Iterator<SpiInterfaceService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            SpiInterfaceService spiInterfaceService = iterator.next();
            spiInterfaceService.printParam("参数");
        }
    }
}
