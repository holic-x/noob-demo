package com.oho.factory.factoryMethod;

/**
 * @description:
 * @author：holic-x
 */
public class FactoryMethodDemo {
    public static void main(String[] args) {
        Driver driver = new BenzDrive();
        Car car = driver.driveCar();
        car.drive();
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
 * 3.定义抽象工厂角色
 */
interface Driver {
    public Car driveCar();
}

/**
 * 4.定义具体工厂角色
 */
class BenzDrive implements Driver {
    @Override
    public Car driveCar() {
        return new Benz();
    }
}

class BmwDriver implements Driver {
    @Override
    public Car driveCar() {
        return new Bmw();
    }
}

class AudiDriver implements Driver {
    @Override
    public Car driveCar() {
        return new Audi();
    }
}