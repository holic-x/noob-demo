package com.noob.base.paramtransfer;

// 传递引用类型参数案例1：数组类型
class ArrayDemo{
    public static void change(int[] array){
        // 将数组中的第一个元素变为0
        array[0] = 0;
    }

    public static void main(String[] args) {
        // 定义int类型数组
        int[] arr = {1,2,3,4,5};
        System.out.println("调用前：" + arr[0]);
        // 调用change方法
        change(arr);
        // 打印数组信息
        System.out.println("调用后：" + arr[0]);
    }
}

// 传递引用类型参数案例2：Java对象
class User{
    private String name;
    // 构造函数定义
    public User(String name) {
        this.name = name;
    }
    public static void swap(User ua,User ub){
        // 定义一个中间变量用于交换两者User对象
        User tUser = ua;
        ua = ub;
        ub = tUser;
        System.out.println("swap userA:" + ua.name);
        System.out.println("swap userB:" + ub.name);
    }

    public static void main(String[] args) {
        User userA = new User("小A");
        User userB = new User("小B");
        // 交换对象
        swap(userA,userB);
        System.out.println("main userA:" + userA.name);
        System.out.println("main userB:" + userB.name);
    }
}





/**
 * 引用类型
 */
public class ReferTypeDemo {

}
