package com.noob.rpc.serizliable.model;

import java.io.Serializable;

/**
 * Person 对象定义
 */
public class Person implements Serializable {

    private String id;

    private String name;

    public Person(){}

    public Person(String id,String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
