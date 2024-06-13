package com.noob.framework.spi.springboot;

import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

// SpringbootSPI机制测试
public class SpringbootSPITest {
    public static void main(String[] args) {
        List<DataBase> dataBaseList = SpringFactoriesLoader.loadFactories(DataBase.class, Thread.currentThread().getContextClassLoader());
        for (DataBase dataBase : dataBaseList) {
            System.out.println(dataBase);
            // 摘选Oracle数据库方法执行
            if(OracleDataBase.class.isInstance(dataBase)){
                OracleDataBase oracleDataBase = (OracleDataBase) dataBase;
                oracleDataBase.initLink();
            }
        }
    }
}
