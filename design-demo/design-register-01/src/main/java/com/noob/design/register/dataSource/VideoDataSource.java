package com.noob.design.register.dataSource;

/**
 * 引入新数据源信息：Video数据源
 */
public class VideoDataSource implements DataSource{
    @Override
    public void search(String searchParam) {
        System.out.println("video search : " + searchParam);
    }
}
