package com.noob.optional;

import java.util.Optional;

/**
 * @description:
 * @author：holic-x
 * @date: 2019/4/8
 * @Copyright： 无所事事是薄弱意志的避难所
 */

class Student {
    String name;
    Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

public class OptionalDemo1 {
    public static void main(String[] args) {
        Student stu1 = null;
        //ofNullable方法：获取一个Optional对象，Optional封装的值对象可以是null也可以不是null
        Optional<Student> os1 = Optional.ofNullable(stu1);
        System.out.println(os1);

        // of方法:获取一个Optional对象，封装的是非null值的对象
        Student stu2 = new Student("h",18);
        Optional<Student> os2 = Optional.of(stu2);
        System.out.println(os2);

        // of方法:如果封装一个null值对象，则抛出空指针异常
        Optional<Student> os3 = Optional.of(stu1); // stu1为null,抛出空指针异常

    }
}
