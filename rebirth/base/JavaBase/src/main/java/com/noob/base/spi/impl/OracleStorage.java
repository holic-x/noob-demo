package com.noob.base.spi.impl;

import com.noob.base.spi.DataStorage;

/**
 * SPI 接口服务实现：OracleStorage
 */
public class OracleStorage implements DataStorage {
    @Override
    public void search(String searchParam) {
        System.out.println("oracle search param:" + searchParam + "-new");
    }
}
