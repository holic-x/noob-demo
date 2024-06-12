package com.noob.framework.di.autowire;

public class StudentServiceImpl implements StudentService{

    private StudentDao studentDao;

    // setter方式注入
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void addUser(Student student) {
        studentDao.save(student);
    }
}
