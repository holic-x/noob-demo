package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于搜索平台的多数据源注册器参考
 */
public class DataSourceRegisterDemo {

    // ① 注册对象
    // 抽象注册对象（数据源）
    static interface DataSource {
        public void search(String param);
    }

    // 具体注册对象（不同的数据源:文章、爬虫、用户数据库）
    static class PostDataSource implements DataSource {
        @Override
        public void search(String param) {
            System.out.println("post search:" + param);
        }
    }

    static class UserDataSource implements DataSource {
        @Override
        public void search(String param) {
            System.out.println("user search:" + param);
        }
    }

    // ② 注册器
    static class DataSourceRegister {
        // a.定义全局缓存存储已注册对象
        static Map<String, DataSource> map = new HashMap<>();

        // b.初始化
        static {
            map.put("post", new PostDataSource());
            map.put("user", new UserDataSource());
        }

        // c.提供注册方法（注册数据源）
        public void register(String uniqueType, DataSource dataSource) {
            map.put(uniqueType, dataSource);
        }

        // d.根据类型获取注册对象（数据源对象）
        public DataSource getDataSource(String type) {
            return map.get(type);
        }
    }

    public static void main(String[] args) {
        // 构建注册器
        DataSourceRegister dsr = new DataSourceRegister();
        dsr.getDataSource("user").search("hello");
        dsr.getDataSource("post").search("world");

        // 新增数据源
        dsr.register("video", new VideoDataSource());
        dsr.getDataSource("video").search("new video");
    }

    // 补充新数据源
    static class VideoDataSource implements DataSource {
        @Override
        public void search(String param) {
            System.out.println("video search:" + param);
        }
    }

}
