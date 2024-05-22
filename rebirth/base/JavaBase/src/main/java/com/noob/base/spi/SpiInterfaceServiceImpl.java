package com.noob.base.spi;

/**
 * SpiInterfaceService服务实现
 */
public class SpiInterfaceServiceImpl implements SpiInterfaceService {
    @Override
    public void printParam(String param) {
        System.out.println(param);
    }
}
