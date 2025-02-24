package com.noob.algorithm.daily.day13.design.facade;

import java.util.Map;

/**
 * 门面模式：提供统一接口，用于访问一组子系统的功能（用于聚合接口）
 * 例如搜索场景中，前后端可能存在多个搜索接口交互场景，通过设置一个统一的搜索入口，然后由门面类负责接收请求并将其分发到子接口或者子系统进行处理
 * 其作用效果有点类似接口适配的概念，但其更多地是面向前台的聚合接口访问处理。而适配器模式更多的是接口定义规范，是对功能/服务提供方的约束处理
 */
public class FacadeDemo {

    // 定义搜索门面类
    static class SearchFacade {

        public Map<String, Object> doSearch(String type, String req) {
            switch (type) {
                // ......
            }
            return null;
        }

    }

    public static void main(String[] args) {
        // 使用门面类
        SearchFacade searchFacade = new SearchFacade();
        searchFacade.doSearch("1", "hello");
    }


}
