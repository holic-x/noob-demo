package com.noob.base.spi.impl;

import com.noob.base.spi.DataStorage;

/**
 * SPI 接口服务实现：MysqlStorage
 */
public class MysqlStorage implements DataStorage {
    @Override
    public void search(String searchParam) {
        System.out.println("mysql search param:" + searchParam + "-success");
    }
}
