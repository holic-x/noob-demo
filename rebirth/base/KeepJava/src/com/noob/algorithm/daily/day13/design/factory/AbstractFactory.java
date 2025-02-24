package com.noob.algorithm.daily.day13.design.factory;

/**
 * 抽象工厂（创建多类工厂，支持每类工厂灵活创建不同的产品）
 */
public class AbstractFactory {

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
        Product createProductA();

        Product createProductB();

        Product createOtherProduct();
    }

    // ④ 具体工厂角色
    // A 工厂
    static class AFactory implements Factory {

        @Override
        public Product createProductA() {
            System.out.println("AFactory create A-Product");
            return new AProduct();
        }

        @Override
        public Product createProductB() {
            System.out.println("AFactory create B-Product");
            return new BProduct();
        }

        @Override
        public Product createOtherProduct() {
            return null;
        }
    }

    // B 工厂
    static class BFactory implements Factory {
        @Override
        public Product createProductA() {
            System.out.println("BFactory create A-Product");
            return new AProduct();
        }

        @Override
        public Product createProductB() {
            System.out.println("BFactory create A-Product");
            return new BProduct();
        }

        @Override
        public Product createOtherProduct() {
            return null;
        }
    }

    public static void main(String[] args) {
        // 创建A工厂
        AFactory aFactory = new AFactory();
        Product pa1 = aFactory.createProductA();
        pa1.fun();
        Product pb1 = aFactory.createProductB();
        pb1.fun();

        // 创建B工厂
        BFactory bFactory = new BFactory();
        Product pa2 = bFactory.createProductA();
        pa2.fun();
        Product pb2 = bFactory.createProductB();
        pb2.fun();
    }
}
