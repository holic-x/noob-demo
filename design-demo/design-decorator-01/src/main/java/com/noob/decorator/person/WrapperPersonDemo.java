package com.noob.decorator.person;

class WrapperPerson {
    /**
     * 装饰者模式：
     * 1.定义一个私有的装饰对象
     * 2.提供一个带参数的构造函数初始化该对象
     * 3.根据需求在原有功能的基础上扩展相应的功能
     */
    private Person p;
    public WrapperPerson(Person p)
    {
        this.p = p;
    }
    public void eat()
    {
        System.out.println("饭前动作....");
        p.eat();
        System.out.println("饭后动作....");
    }
}

public class WrapperPersonDemo {
    public static void main(String[] args) {
        WrapperPerson wp =  new WrapperPerson(new Person());
        wp.eat();
    }
}
