package com.noob.demo.createObj;

public class Employee implements Cloneable{

    private String empName;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
