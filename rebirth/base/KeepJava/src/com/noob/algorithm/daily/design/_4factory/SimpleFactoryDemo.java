package com.noob.algorithm.daily.design._4factory;

// 产品A
class ProductA {

}

// 产品B
class ProductB{

}

// A工厂
class FactoryA{
    public ProductA createProduct(){
        return new ProductA();
    }
}

// B工厂
class FactoryB{
    public FactoryB createProduct(){
        return new FactoryB();
    }
}

public class SimpleFactoryDemo {

    public static void main(String[] args) {
        // A 工厂创建A产品
        FactoryA factoryA = new FactoryA();
        factoryA.createProduct();

        // B 工厂创建B产品
        FactoryB factoryB = new FactoryB();
        factoryB.createProduct();
    }

}
