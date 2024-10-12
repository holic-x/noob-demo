package com.noob.design.factory.product;

/**
 * 简单工厂模式：一类工厂生产不同类的产品
 */
public class SimpleFactoryProductDemo {
    public static void main(String[] args) {
        /*
        ProductFactory productFactory = new ProductFactory();
        productFactory.getProduct("A").print();
        productFactory.getProduct("B").print();
         */
    }
}
/*
// 抽象产品角色
interface Product {
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

// 工厂类实现（类实现）
class ProductFactory {
    public Product getProduct(String productType) {
        // 根据不同的类型生产产品
        if("A".equals(productType)){
            return new AProduct();
        }else if("B".equals(productType)){
            return new BProduct();
        }else{
            return null;
        }
    }
}
*/