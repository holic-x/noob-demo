package com.noob.base.statickey;

// static修饰内部类：静态内部类
class StaticClass{
    // static关键字修饰被不累
    public static class InnerClass{
        // 内部类构造方法定义
        InnerClass(){
            System.out.println("静态内部类");
        }
        // 内部类方法
        public void InnerMethod(){
            System.out.println("静态内部方法");
        }
    }

    public static void main(String[] args) {
        // 通过StaticClass类名访问静态内部类
        InnerClass innerClass = new StaticClass.InnerClass();
        // 静态内部类（和普通类一样使用）
        innerClass.InnerMethod();
    }
}

// static修饰方法
class StaticMethod{
    // 定义静态方法
    public static void show(){
        System.out.println("show");
    }

    public static void main(String[] args) {
        // 方式1：通过类名直接访问静态方法
        StaticMethod.show();

        // 方式2：通过对象访问静态方法
        StaticMethod staticMethod = new StaticMethod();
        staticMethod.show();
    }
}

// static修饰变量
class StaticVariable{
    private static String name = "noob";
    public static void main(String[] args) {
        System.out.println(StaticVariable.name);
    }
}


// static修饰代码块(结合继承机制分析)
class Father{
    // 父类静态代码块
    static{
        System.out.println("father static");
    }
    // 父类构造方法
    public Father(){
        System.out.println("father constructor");
    }
}
class Son extends Father{
    // 子类静态代码块
    static{
        System.out.println("son static");
    }
    // 子类构造方法
    public Son(){
        System.out.println("son constructor");
    }
}

class StaticCodeBlock{
    public static void main(String[] args) {
        Son son = new Son();
    }
}






/**
 * Static 关键字 demo
 */
public class StaticDemo {

}
