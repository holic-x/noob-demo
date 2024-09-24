package com.noob.design.register.dataSource;

public class PictureDataSource implements DataSource{
    @Override
    public void search(String searchParam) {
        System.out.println("picture search: " + searchParam);
    }
}
