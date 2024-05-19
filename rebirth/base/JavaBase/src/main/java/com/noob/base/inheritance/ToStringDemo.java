package com.noob.base.inheritance;


class T1{
    private String name;
    private int age;
    public T1(String name, int age){
        this.name = name;
        this.age = age;
    }
}

class T2{
    private String name;
    private int age;
    public T2(String name, int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "T1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * toString方法demo
 */
public class ToStringDemo {
    public static void main(String[] args) {
        T1 t1 = new T1("Jack", 18);
        System.out.println(t1.toString());

        T2 t2 = new T2("Noob", 20);
        System.out.println(t2.toString());
    }
}
