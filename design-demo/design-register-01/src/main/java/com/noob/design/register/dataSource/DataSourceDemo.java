package com.noob.design.register.dataSource;

/**
 * 注册器模式应用：数据源注册
 * 场景案例(聚合搜索，不同的搜索来源的数据源注册、或不同类型数据源注册)
 * 1.本地数据源（数据库文章，可自行管理博客文章内容或基于爬虫机制进行文章爬取） 查询文章信息
 * 2.用户信息数据源 （查询文章信息）
 * 3.图片数据源（爬取bing图片信息）
 * 等....需根据不同数据源进行检索处理
 */
public class DataSourceDemo {

    public static void main(String[] args) {
        // 注册新数据源
        DataSourceRegister.register("video",new VideoDataSource());
        // 根据指定类型数据源进行检索
        DataSourceRegister.getDataSource("user").search("hello world");
    }

}
