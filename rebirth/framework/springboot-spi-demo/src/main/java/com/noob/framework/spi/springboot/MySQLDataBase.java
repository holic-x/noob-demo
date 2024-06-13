package com.noob.framework.spi.springboot;

public class MySQLDataBase implements DataBase{
    @Override
    public void initLink() {
        System.out.println("mysql database initLink");
    }
}
