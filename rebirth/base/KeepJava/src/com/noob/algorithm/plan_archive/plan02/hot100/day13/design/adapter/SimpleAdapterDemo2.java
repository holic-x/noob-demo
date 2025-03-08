package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 普通适配器: 接口适配
 * 场景：针对文章检索场景，不同的数据源检索接受处理的参数和响应结果不同
 */
public class SimpleAdapterDemo2 {

    // ① 定义不同的处理方法（例如不同的数据源的检索处理:文章检索、图片检索、视频检索等,根据检索要素返回不同的信息）
    // 泛型接口定义
    static interface DataSource<T> {
        public T search(String param);
    }

    // 实现类
    // -- 文本数据源
    static class TextDataSource implements DataSource<String> {
        @Override
        public String search(String param) {
            return "text search:" + param;
        }
    }

    static class Picture {
        int id;
        int name;
        int size;

        Picture() {
        }
    }

    // -- 图片数据源
    static class PictureDataSource implements DataSource<Picture> {
        @Override
        public Picture search(String param) {
            return new Picture();
        }
    }


    // ② 定义适配器统一接收处理参数并响应结果
    static class DataSourceAdapter {
        public Map<String, Object> search(String dataSourceType, String param) {
            // 根据不同的类型引用相应的数据源进行检索并返回参数
            Map<String, Object> map = new HashMap<>();
            switch (dataSourceType) {
                case "text":
                    map.put("data", new TextDataSource().search(param));
                case "picture":
                    map.put("data", new PictureDataSource().search(param));
            }
            return map;
        }
    }
}
