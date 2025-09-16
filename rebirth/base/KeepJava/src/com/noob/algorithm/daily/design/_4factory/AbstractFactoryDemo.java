package com.noob.algorithm.daily.design._4factory;

// 抽象产品
interface Product {
    void use();
}

// 具体产品
class ConcreteProduct implements Product {

    @Override
    public void use() {
        System.out.println("concrete product");
    }
}

// 抽象工厂
interface AbstractFactory {
    public Product createProduct(String type);
}

// 具体工厂
class ConcreteFactory implements AbstractFactory {
    @Override
    public Product createProduct(String type) {
        if (type.equals("concrete")) {
            return new ConcreteProduct();
        }
        return null;
    }
}

public class AbstractFactoryDemo {

    public static void main(String[] args) {
        ConcreteFactory concreteFactory = new ConcreteFactory();
        concreteFactory.createProduct("concrete").use();
    }

}
