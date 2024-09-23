package com.noob.design.register.dataSource;

public class PostDataSource implements DataSource{
    @Override
    public void search(String searchParam) {
        System.out.println("post search: " + searchParam);
    }
}
