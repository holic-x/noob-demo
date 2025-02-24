package com.noob.algorithm.daily.day13.design.factory;

/**
 * 工厂方法模式:多类工厂创建产品，每类工厂创建对应类型的产品
 */
public class FactoryMethod {

    // ① 抽象产品角色
    static interface Product {
        void fun(); // 产品功能
    }

    // ② 具体产品角色
    static class AProduct implements Product {
        @Override
        public void fun() {
            System.out.println("product a");
        }
    }

    static class BProduct implements Product {
        @Override
        public void fun() {
            System.out.println("product b");
        }
    }

    // ③ 抽象工厂角色
    static interface Factory {
        Product createProduct();
    }

    // ④ 具体工厂角色
    // A 工厂创建A产品
    static class AFactory implements Factory {
        @Override
        public Product createProduct() {
            return new AProduct();
        }
    }

    // B 工厂创建B产品
    static class BFactory implements Factory {
        @Override
        public Product createProduct() {
            return new BProduct();
        }
    }

    // 工厂方法（多类工厂创建所有对应类型的产品）
    public static void main(String[] args) {
        AFactory aFactory = new AFactory();
        Product pa = aFactory.createProduct();
        pa.fun();

        BFactory bFactory = new BFactory();
        Product pb = bFactory.createProduct();
        pb.fun();
    }

}
