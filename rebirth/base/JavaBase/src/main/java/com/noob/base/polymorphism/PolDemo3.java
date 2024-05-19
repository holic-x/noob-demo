package com.noob.base.polymorphism;

// 父类：动物类定义
class Animal{
    public void say(){
        System.out.println("Animal");
    }
}

class Dog extends Animal{

    // 子类重写say方法
    @Override
    public void say(){
        System.out.println("Dog say....");
    }

    // 子类自定义扩展的makeNoise方法
    public void makeNoise(){
        System.out.println("Dog makeNoise");
    }
}

public class PolDemo3 {
    public static void main(String[] args) {
        // 向上转型：父类声明，子类赋值
        Animal a = new Dog();
        a.say(); // 编译时引用对象是父类Animal对象，因此不能调用父类中没有声明的方法（例如子类的makeNoise）

        // 向下转型：当使用多态并访问子类独有的属性或者方法，必须进行向下转型。如果转换类型不合法则会出现类型转换异常 java.lang.ClassCastException
        Animal[] animals = {new Animal(),new Dog()};
//        ((Dog)animals[0]).makeNoise(); // 向下转型失败（抛出类型转换异常）
        ((Dog)animals[1]).makeNoise();


        if(animals[0] instanceof Dog){
            ((Dog)animals[0]).makeNoise(); // 向下转型失败（抛出类型转换异常）
        }else{
            System.out.println("类型不匹配");
        }
    }
}
