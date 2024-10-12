package com.noob.design.factory.product;

/**
 * 工厂方法模式：多类工厂创建产品，每类工厂创建指定类产品
 */
public class FactoryMethodProductDemo {

    /*
    public static void main(String[] args) {
        // 什么工厂生产什么产品
        new AProductFactory().createProduct().print();
        new BProductFactory().createProduct().print();
    }
     */
}
/*
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
    public Product createProduct();
}

// 具体工厂角色
class AProductFactory implements ProductFactory{
    public Product createProduct() {
        return new AProduct();
    }
}
class BProductFactory implements ProductFactory{
    @Override
    public Product createProduct() {
        return new BProduct();
    }
}
 */