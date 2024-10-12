package com.noob.design.factory.product;

/**
 * 抽象工厂方法：
 * 不同类工厂生产产品，每类工厂可以自定义生产指定类产品
 */
public class AbstractFactoryProductDemo {
    public static void main(String[] args) {
        new ProductFactory001().createProductA().print();
        new ProductFactory001().createProductB().print();
        new ProductFactory002().createProductA().print();
        new ProductFactory002().createProductB().print();
    }
}

// 抽象产品角色
interface Product{
    public void print();
}

// 具体产品角色
class AProduct implements Product{
    @Override
    public void print() {
        System.out.println("i am AProduct");
    }
}

class BProduct implements Product{
    @Override
    public void print() {
        System.out.println("i am BProduct");
    }
}

// 抽象工厂角色
interface ProductFactory{
    // 定义创建不同类产品的方法
    public AProduct createProductA();
    public BProduct createProductB();
}

// 具体产品角色
class ProductFactory001 implements ProductFactory{
    @Override
    public AProduct createProductA() {
        System.out.println("i am ProductFactory001");
        return new AProduct();
    }

    @Override
    public BProduct createProductB() {
        System.out.println("i am ProductFactory001");
        return new BProduct();
    }
}

class ProductFactory002 implements ProductFactory{

    @Override
    public AProduct createProductA() {
        System.out.println("i am ProductFactory002");
        return new AProduct();
    }

    @Override
    public BProduct createProductB() {
        System.out.println("i am ProductFactory002");
        return new BProduct();
    }
}
