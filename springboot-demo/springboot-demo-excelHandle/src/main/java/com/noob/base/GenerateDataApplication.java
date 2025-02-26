package com.noob.base;

import com.noob.base.largeData.util.DataGeneratorToDB;
import com.noob.base.largeData.util.DataGeneratorToExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class GenerateDataApplication implements CommandLineRunner {

    @Autowired
    private DataGeneratorToDB dataGenerator;

    public static void main(String[] args) {
        SpringApplication.run(GenerateDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------start----------");
        long start = System.currentTimeMillis();

        // 生成 10 万条用户数据并插入数据库
        dataGenerator.generateAndInsertUsers();

        long end = System.currentTimeMillis();
        System.out.println("----------end----------");
        System.out.println("数据插入数据库耗时：" + (end-start)/1000 + "s");
    }
}