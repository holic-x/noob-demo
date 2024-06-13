package com.noob.framework.spi.springboot;

public class OracleDataBase implements DataBase{
    @Override
    public void initLink() {
        System.out.println("oracle database initLink");
    }
}
