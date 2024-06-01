package com.noob.base.spi.service.impl;

import com.noob.base.spi.service.SpiInterfaceService;

/**
 * SpiInterfaceService服务实现
 */
public class SpiServiceTwo implements SpiInterfaceService {
    @Override
    public void printParam(String param) {
        System.out.println("start SpiServiceTwo");
        System.out.println(param);
        System.out.println("end SpiServiceTwo");
    }
}
