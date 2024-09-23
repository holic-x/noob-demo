package com.noob.design.register.dataSource;

public class UserDataSource implements DataSource{
    @Override
    public void search(String searchParam) {
        System.out.println("user search: " + searchParam);
    }
}
