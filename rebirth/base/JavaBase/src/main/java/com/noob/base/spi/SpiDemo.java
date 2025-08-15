package com.noob.base.spi;

import com.noob.base.spi.impl.MysqlStorage;
import com.noob.base.spi.impl.OracleStorage;
import com.noob.base.spi.impl.RedisStorage;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Spi demo 测试（模拟业务模块）：动态加载实现类
 */
public class SpiDemo {
    public static void main(String[] args) {
        // 传统方式调用服务实现接口
        System.out.println("1.传统方式调用服务实现接口");
        MysqlStorage mysqlStorage = new MysqlStorage();
        mysqlStorage.search("hello");
        RedisStorage redisStorage = new RedisStorage();
        redisStorage.search("hello");
        // 引入新的接口实现
        OracleStorage oracleStorage = new OracleStorage();
        oracleStorage.search("hello");

        // SPI机制下动态加载服务接口(从业务代码中解耦，实现动态插拔)
        System.out.println("2.SPI机制下动态加载服务接口(从业务代码中解耦，实现动态插拔)");
        // 使用ServiceLoader动态加载指定接口的实现类
        ServiceLoader<DataStorage> loader = ServiceLoader.load(DataStorage.class);
        // 使用迭代器迭代实现类信息
        Iterator<DataStorage> iterator = loader.iterator();
        while (iterator.hasNext()) {
            DataStorage storage = iterator.next();
            // 指定对应实现类指定的操作
            storage.search("keep");
        }
    }
}
