package com.noob.base.polymorphism;


class Wang{
    Wang(){
        System.out.println("调用前");
        say(); // 在父类的构造方法中调用say方法(say是一个多态方法)
        System.out.println("调用后");
    }
    public void say(){
        System.out.println("我的年龄是30岁");
    }
}

class XiaoWang extends Wang{
    private int age = 3;
    public XiaoWang(int age){
        this.age = age;
        System.out.println("小王的年龄是" + this.age);
    }
    // 重写write方法
    @Override
    public void say() {
        System.out.println("小王的实际年龄是" + this.age);
    }
}


/**
 * 多态
 */
public class PolDemo2 {
    public static void main(String[] args) {
        XiaoWang xiaoWang = new XiaoWang(10);
    }
}
