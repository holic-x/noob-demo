package com.noob.base.interfaces;


// 服务接口定义
interface MyService{
    void doSth();
}

// 不同服务实现
class ServiceOne implements MyService{
    @Override
    public void doSth() {
        System.out.println("ServiceOne");
    }
}
class ServiceTwo implements MyService{
    @Override
    public void doSth() {
        System.out.println("ServiceTwo");
    }
}

// 定义工厂方法，初始化不同服务
class ServiceFactory{
    public MyService getMyService(MyService myService) {
        return myService;
    }
}

/**
 * 工厂方法模式与接口：demo构建
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        ServiceFactory sf = new ServiceFactory();
        sf.getMyService(new ServiceOne()).doSth();
        sf.getMyService(new ServiceTwo()).doSth();
    }
}
