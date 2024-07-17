package com.noob.base.id;

public class SnowflakeDemo {

    public static void main(String[] args) {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1000);
        System.out.println( snowflakeIdWorker.nextId());
    }
}
