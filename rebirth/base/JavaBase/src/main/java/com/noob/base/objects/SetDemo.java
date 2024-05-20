package com.noob.base.objects;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Set集合的普通应用
class SetCommon{

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("JAVA");
        set.add("PYTHON");
        set.add("MYSQL");
        set.add("JAVA");
        set.add("REDIS");
        System.out.println("SET集合长度：" + set.size());
        // 打印集合元素
        set.forEach(obj -> System.out.println(obj));
    }

}

// 定义课程类
class Course{
    private String name;
    private String code;
    public Course(String name, String code){
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    // 通过重写hashCode解决对象校验不相等“异常”（进一步验证为何重写equals方法的时候要重写hashCode）
    @Override
    public int hashCode() {
        // return -1;
        // 调整为对比name、age的hash是否相等
        return Objects.hash(name,code);
    }

    @Override
    public String toString() {
        return "name:" + name + " code:" + code;
    }

    // 重写equals方法（只要code和name相同，则认为是同一个对象）
    @Override
    public boolean equals(Object obj) {
        // 引用相同返回true
        if(this == obj){
            return true;
        }
        // 判断参数是否为null
        if(obj == null || getClass() != obj.getClass() ){
            return false;
        }
        // 引用不同进一步判断code、name
        Course course = (Course) obj;
        return course.getCode().equals(this.getCode()) && course.getName().equals(this.getName());
    }

    public static void main(String[] args) {
        Course c1 = new Course("JAVA","001");
        Course c2 = new Course("PYTHON","002");
        Course c3 = new Course("MYSQL","003");
        Course c4 = new Course("REDIS","004");
        Course c5 = new Course("JAVA","001");
        Set<Course> set = new HashSet<Course>(){};
        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(c4);
        set.add(c5);
        System.out.println("SET集合大小："+ set.size());
        set.forEach(obj -> System.out.println(obj));
    }
}

/**
 * Set demo：验证为什么重写equals方法时一定要重写hashCode
 */
public class SetDemo {





}
