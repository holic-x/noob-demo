package com.noob.design.facade;

/**
 * 门面模式：用于定义统一接口，访问一组子系统的功能
 */
public class FacadeDemo {

    public static void main(String[] args) {
        // 通过门面来访问功能，不需要关注内部的子系统细节
        Facade facade = new Facade();
        facade.doSomething();
    }

}

class Facade{
    public void doSomething(){
        System.out.println("do something");
        // 可能还会根据不同情况调用不同子系统/子服务的功能
    }
}

