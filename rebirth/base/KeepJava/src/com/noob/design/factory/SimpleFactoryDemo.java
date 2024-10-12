package com.noob.design.factory;

/**
 * 简单工厂方法模式：
 * 例如"司机今天开什么车"的案例场景(例如工厂生产什么产品)
 * - 抽象产品角色
 * - 实际产品角色
 * - 工厂类（使用产品）
 */
public class SimpleFactoryDemo {
    public static void main(String[] args) {
        // 测试
        Driver driver = new Driver();
        Car c1 = driver.doDrive("BMW");
        Car c2 = driver.doDrive("Audi");
        c1.drive();
        c2.drive();
    }
}

// 抽象产品角色（接口）
interface Car {
    public void drive();
}

// 实际产品角色（子类实现）
class BMW implements Car {
    @Override
    public void drive() {
        System.out.println("BMW didi...");
    }
}

class Audi implements Car {
    @Override
    public void drive() {
        System.out.println("Audi didi...");
    }
}

// 工厂类角色（使用者，也可以理解为工厂要生产什么产品）
class Driver {
    // 模拟开车
    public Car doDrive(String driverType) {
        // 根据开车类型选定要开什么车
        if ("BMW".equals(driverType)) {
            return new BMW();
        } else if ("Audi".equals(driverType)) {
            return new Audi();
        } else {
            // 无指定
            return null;
        }
    }
}

