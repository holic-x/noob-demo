package com.noob.base.inheritance;



// 父类定义
class Father{
    public void speak(){
        System.out.println("father speak");
    }
}

// 子类定义
class Son extends Father{
    // 方法重写：子类继承父类并重写父类方法
    @Override
    public void speak() {
        // 调用父类speak方法
        super.speak();
        System.out.println("son speak");
    }
}

/**
 * 方法重写
 */
public class OverrideDemo {
    public static void main(String[] args) {
        Son son = new Son();
        son.speak();
    }
}
