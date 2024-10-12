package com.noob.design.factory;

/**
 * 工厂方法模式：
 * 【案例场景】："什么司机开什么车"(相当于针对不同的工厂，生产不同的产品)
 * - 抽象产品角色
 * - 具体产品角色
 * - 抽象工厂角色
 * - 具体工厂角色
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        // 什么司机开什么车
        NewBMWDriver d1 = new NewBMWDriver();
        d1.doDrive().drive();
        NewAudiDriver d2 = new NewAudiDriver();
        d2.doDrive().drive();
    }

}

// 抽象产品角色
interface NewCar{
    public void drive();
}

// 具体产品角色
class NewBMWCar implements NewCar{
    @Override
    public void drive() {
        System.out.println("newBMWCar drive");
    }
}
class NewAudiCar implements NewCar{
    @Override
    public void drive() {
        System.out.println("newAudiCar drive");
    }
}

// 抽象工厂角色
interface NewDriver{
    public NewCar doDrive();
}

// 具体工厂角色（什么司机开什么车，实际就是消除if...else...）
class NewBMWDriver implements NewDriver{
    @Override
    public NewCar doDrive() {
        return new NewBMWCar();
    }
}
class NewAudiDriver implements NewDriver{
    @Override
    public NewCar doDrive() {
        return new NewAudiCar();
    }
}



