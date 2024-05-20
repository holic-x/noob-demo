package com.noob.base.innerclass;

// 场景案例1：接口定义
interface Swim{
    void swim();
}
// 定义学生类实现Swim接口
class Student implements Swim{

    @Override
    public void swim() {
        System.out.println("student swim");
    }

    public static void main(String[] args) {
        // 创建Student对象调用接口方法
        Student s = new Student();
        s.swim();
    }
}

class AnonymousStudent{
    public static void main(String[] args) {
        new Swim(){
            @Override
            public void swim() {
                System.out.println("匿名内部类 swim");
            }
        }.swim();
    }
}









// 外部类定义
class AnonymousOuterClass {



}


/**
 * 匿名内部类 demo
 */
public class AnonymousInnerClassDemo {


}
