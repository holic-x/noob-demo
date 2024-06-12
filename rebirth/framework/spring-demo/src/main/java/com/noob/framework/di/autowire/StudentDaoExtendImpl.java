package com.noob.framework.di.autowire;

public class StudentDaoExtendImpl implements StudentDao{
    @Override
    public void save(Student student) {
        System.out.println("extend");
    }
}
