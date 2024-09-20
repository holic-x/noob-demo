package com.noob.decorator.person;

/**
 * Person 类
 */
class Person {
    public void eat(){
        System.out.println("P 人要吃饭...");
    }
}

/**
 * NewPerson：
 * 传统思路通过继承扩展Person的公共方法
 */
class NewPerson extends Person{
    // 通过继承并重写方法实现功能扩展
    @Override
    public void eat() {
        System.out.println("饭前动作....");
        super.eat();
        System.out.println("饭后动作....");
    }
}

public class PersonDemo {
    public static void main(String[] args) {
        // 普通Person
        Person person = new Person();
        person.eat();

        // NewPerson
        NewPerson newPerson = new NewPerson();
        newPerson.eat();
    }
}
