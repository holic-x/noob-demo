package com.oho.factory.simple;

/**
 * @description:
 * @author：holic-x
 */
public class SimpleFactoryDemo {
    /**
     * 简单工厂方法测试
     */
    public static void main(String[] args) {
        // 开车测试
        try {
            // 告诉司机今天开什么车
            Car car = Driver.driverCar("benz");
            // 执行开车指令
            car.drive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/**
 * 1.定义抽象产品角色
 */
interface Car {
    // 定义开车方法
    public void drive();
}

/**
 * 2.确定具体的产品角色：奔驰、宝马、奥迪
 */
class Benz implements Car {
    // 实现Car接口中的drive()方法
    @Override
    public void drive() {
        System.out.println("Benz driving...");
    }
}
class Bmw implements Car {
    // 实现Car接口中的drive()方法
    @Override
    public void drive() {
        System.out.println("Bmw driving...");
    }

}
class Audi implements Car {
    // 实现Car接口中的drive()方法
    @Override
    public void drive() {
        System.out.println("Audi driving...");
    }
}

/**
 * 3.定义工厂类角色
 */
class Driver {
    // 工厂方法.注意：返回类型为抽象产品角色
    public static Car driverCar(String s) throws Exception {
        // 判断逻辑，根据传入的参数返回具体的产品角色给 Client
        if (s.equalsIgnoreCase("Benz")){
            return new Benz();
        }
        else if (s.equalsIgnoreCase("Bmw")){
            return new Bmw();
        }
        else if (s.equalsIgnoreCase("Audi")){
            return new Audi();
        }
        else{
            throw new Exception();
        }
    }
}
