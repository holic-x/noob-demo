package com.noob.framework.di.autowire;

public class StudentDaoImpl implements StudentDao{

    @Override
    public void save(Student student) {
        System.out.println("mod save:" + student.getStuName());
    }
}
