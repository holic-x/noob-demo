package com.noob.base.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Apple实体定义
 class Apple{
     private int price;
     public String name;

     // 无参构造函数
     public Apple(){}

     public Apple(int price) {
         this.price = price;
     }

     public int getPrice() {
         return price;
     }

     public void setPrice(int price) {
         this.price = price;
     }
}


 class ReflectAPI{

     // 1.获取反射中的Class对象
     public static void testGetReflectClass() throws ClassNotFoundException {

         // 方式1：通过Class.forName("类全路径");获取
         Class clz1 = Class.forName("com.noob.base.reflect.Apple");
         System.out.println(clz1);

         // 方式2：使用.class方法（适用于在编译前就知道操作的Class）
         Class clz2 = Apple.class;
         System.out.println(clz2);

         // 方式3：使用类对象的getClass()方法
          String str = new String("hello");
          Class clz3 = str.getClass();
          System.out.println(clz3);
     }

     // 2.通过反射创建类对象
     public static void testGetClassObject() throws Exception {

         // 方式1：通过Class对象的newInstance()方法
         Class clz1 = Apple.class;
         Apple apple = (Apple)clz1.newInstance();

         // 方式2：通过Constructor对象的newInstance()方法
         Class clz2 = Apple.class;
         Constructor constructor = clz2.getConstructor();
         Apple appleObj = (Apple)constructor.newInstance();
     }

     // 3.通过反射获取类属性、方法、构造器
     public static void testGetReflectMoreInfo() throws Exception {

         Class clz = Apple.class;
         // 获取类属性(getFields、getDeclaredFields)
         Field[] fields = clz.getFields();
         for (Field field : fields) {
             System.out.println(field.getName());
         }

         // 获取方法(getMethods、getDeclaredMethods)
         Method[] methods = clz.getDeclaredMethods();
         for (Method method : methods) {
             System.out.println(method.getName());
         }

         // 获取构造器(getConstructors、getDeclaredConstructors)
         Constructor[] constructors = clz.getDeclaredConstructors();
         for (Constructor constructor : constructors) {
             System.out.println(constructor.getName());
         }
     }

     public static void main(String[] args) throws Exception {
         testGetReflectClass();
         testGetClassObject();
         testGetReflectMoreInfo();
     }

 }















/**
 * 反射 demo
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        // 正常创建对象调用
        Apple apple = new Apple(100);
        System.out.println("apple price:" + apple.getPrice());

        /**
         * 使用反射调用
         */
        // 加载指定类
        Class clazz = Class.forName("com.noob.base.reflect.Apple");
        // 加载指定类的方法
        Method setPriceMethod = clazz.getMethod("setPrice",int.class);
        Method getPriceMethod = clazz.getMethod("getPrice");

        // 加载指定类的构造方法,并通过构造方法创建实例(如果重载了构造函数，则需显示指定构造函数，否则此处查找无参构造函数失败)
        Constructor constructor = clazz.getConstructor();
        Object appleObj = constructor.newInstance();
        // 通过invoke实现方法调用
        setPriceMethod.invoke(appleObj,100);
        System.out.println("reflect apple price:" + getPriceMethod.invoke(appleObj));

        // 通过指定构造函数构建对象
        Constructor paramsConstructor = clazz.getConstructor(int.class);
        Object paramsObj = paramsConstructor.newInstance(99);
        System.out.println("reflect apple price:" + getPriceMethod.invoke(paramsObj));
    }
}
