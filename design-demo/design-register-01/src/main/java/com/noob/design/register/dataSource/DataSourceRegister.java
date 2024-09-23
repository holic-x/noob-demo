package com.noob.design.register.dataSource;

import java.util.HashMap;

/**
 * 数据源注册器：统一管理数据源信息
 */
public class DataSourceRegister {

    // 数据源集合
    private static HashMap<String, DataSource> dataSources = new HashMap<String, DataSource>();

    // 初始化数据源信息
    static{
        dataSources.put("post", new PostDataSource());
        dataSources.put("user", new UserDataSource());
        dataSources.put("picture", new PictureDataSource());
    }

    // 注册数据源信息
    public static void register(String type,DataSource dataSource){
        dataSources.put(type, dataSource);
    }

    // 根据类型获取数据源信息
    public static DataSource getDataSource(String type){
        return dataSources.get(type);
    }

}
