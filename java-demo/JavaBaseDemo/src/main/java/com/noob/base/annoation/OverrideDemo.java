package com.noob.base.annoation;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 15:03
 */
public class OverrideDemo {
    public static void main(String[] args) {
        Son son = new Son();
        son.fly();
    }
}

class Father{
    public void fly(){
        System.out.println("父类 fly方法");
    }
}

class Son extends Father{
    @Override
    public void fly(){
        System.out.println("子类 fly方法");
    }
}
