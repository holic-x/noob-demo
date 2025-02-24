package com.noob.algorithm.daily.day13.design.adapter;

import java.util.Map;

/**
 * 普通适配器: 接口适配
 * 场景：针对文章检索场景，不同的数据源检索接受处理的参数和响应结果不同
 */
public class SimpleAdapterDemo1 {

    // ① 定义不同的处理方法（例如不同的数据源的检索处理:例如此处拆分锦囊、知识库检索）
    static class JinNangDataSource {
        public Map<String, Object> search(String param) {
            System.out.println("JinNang search:" + param);
            return null;
        }
    }

    static class KnowledgeDataSource {
        public Map<String, Object> search(String param) {
            System.out.println("knowledge search:" + param);
            return null;
        }
    }

    // ② 定义适配器统一接收处理参数并响应结果
    static class DataSourceAdapter {
        public Map<String, Object> search(String dataSourceType, String param) {
            // 根据不同的类型引用相应的数据源进行检索并返回参数
            switch (dataSourceType) {
                case "JN":
                    return new JinNangDataSource().search(param);
                case "KN":
                    return new KnowledgeDataSource().search(param);
            }
            return null;
        }
    }
}
