package com.noob.optional;

import java.util.Optional;

/**
 * @description:
 * @author：holic-x
 * @date: 2019/4/8
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class OptionalDemo2 {
    public static void main(String[] args) {
//        Student s = new Student("h",18);
        Student s = null;
        // 且如果封装的是一个null，那么通过get方法再次获取会抛出NoSuchElementException
        Optional<Student> optional = Optional.ofNullable(s);
        // 判断Optional所封装的对象是否不为空，如果不为空返回true,否则返回false
        if(optional.isPresent()){
            // get() 如果存在值，返回值，否则抛出NoSuchElementException
            Student student = optional.get();
            System.out.println(student);
        }else{
            System.out.println("Optional封装的对象为空");
        }
    }
}