package com.noob.jvm;


class User{
    public static void main(String[] args) {
        /**
         * 分析：user是栈帧中的本地变量,user就是GC Root
         * 由于user=null,user与new User()对象断开了链接,所以对象会被回收
         */
        User user = new User();
        user = null;
    }
}

class StaticTest{
    public static User user;
    /**
     *  分析：栈帧中的本地变量t是一个GC Root；user是属于Java引用类型的静态变量，它是一个GC Root
     *  st对象给静态成员变量user赋值了变量的引用，因此user指向的对象不会被回收
     *  执行t=null后和new Test()断了链接，因此t对象会被回收
     */
    public static void main(String[] args) {
        StaticTest st = new StaticTest();
        System.out.println("1.StaticTest.user初始化前：" + StaticTest.user); // 未初始化默认为null
        st.user = new User();
        System.out.println("2.StaticTest.user初始化后：" + StaticTest.user); // 初始化后分配地址
        st = null;
        System.out.println("3.StaticTest.user初始化后且执行了t=null后：" + StaticTest.user); // StaticTest.user还是指向new User()，没有断开连接，所以其指向对象不会被回收
        StaticTest.user = null;
        System.out.println("4.StaticTest.user断开连接：" + StaticTest.user);// StaticTest.user断开连接，则其指向对象被回收
    }
}


 class FinalTest{
    public static final User user = new User();

     /**
      * 常量user引用的对象不会因为ft引用的对象被回收而回收
      */
     public static void main(String[] args) {
         FinalTest ft = new FinalTest();
         System.out.println("ft=null 执行前final变量：" + ft.user);
         ft = null;
         System.out.println("ft=null 执行前final变后：" + FinalTest.user);
     }
 }






public class GCRootsDemo {
}
