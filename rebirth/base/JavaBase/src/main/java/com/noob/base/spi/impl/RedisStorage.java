package com.noob.base.spi.impl;

import com.noob.base.spi.DataStorage;

/**
 * SPI 接口服务实现：RedisStorage
 */
public class RedisStorage implements DataStorage {
    @Override
    public void search(String searchParam) {
        System.out.println("redis search param:" + searchParam + "-fail");
    }
}
