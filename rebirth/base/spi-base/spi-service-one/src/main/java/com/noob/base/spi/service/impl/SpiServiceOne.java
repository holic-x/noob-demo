package com.noob.base.spi.service.impl;

import com.noob.base.spi.service.SpiInterfaceService;

/**
 * SpiInterfaceService服务实现
 */
public class SpiServiceOne implements SpiInterfaceService {
    @Override
    public void printParam(String param) {
        System.out.println("start SpiServiceOne");
        System.out.println(param);
        System.out.println("end SpiServiceOne");
    }
}
