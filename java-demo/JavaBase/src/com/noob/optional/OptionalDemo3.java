package com.noob.optional;

import java.util.Optional;

/**
 * @description:
 * @author：holic-x
 * @date: 2019/4/8
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class OptionalDemo3 {
    public static void main(String[] args) {
//        method1();
//        method2();
//        method3();
    }

    private static void method3() {
        //Student s = new Student("h",18);
        Student s = null;
        Optional<Student> optional = Optional.ofNullable(s);
        //ifPresent (Consumer<? super T> action):如果不为空，则使用该值执行给定的操作，否则不执行任何操作
        optional.ifPresent(student -> System.out.println(student));
    }

    private static void method2() {
        Student s = new Student("h",18);
        //Student s = null;
        Optional<Student> optional = Optional.ofNullable(s);
        //orElseGet(Supplier<? extends T> supplier):如果不为空，则返回具体的值，否则返回由括号中函数产生的结果
        Student student = optional.orElseGet(()-> new Student("xx" , 24));
        System.out.println(student);
    }

    private static void method1() {
        //Student s = new Student("h",18);
        Student s = null;
        Optional<Student> optional = Optional.ofNullable(s);
        //orElse(T other):如果不为空，则返回具体的值，否则返回参数中的值
        Student student = optional.orElse(new Student("xx", 24));
        System.out.println(student);
    }
}
