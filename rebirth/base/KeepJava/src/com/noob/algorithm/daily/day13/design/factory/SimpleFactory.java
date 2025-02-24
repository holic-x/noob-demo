package com.noob.algorithm.daily.day13.design.factory;

/**
 * 简单工厂模式:一个工厂可以根据不同产品类型生产多种产品
 */
public class SimpleFactory {

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

    // ③ 简单工厂（一个工厂创建所有类型的产品）
    static class ProductFactory {
        public Product getProduct(String type) {
            Product p = null;
            switch (type) {
                case "A":
                    p = new AProduct();
                    break;
                case "B":
                    p = new BProduct();
                    break;
                default:
                    p = null; // ... other product
                    break;
            }
            return p;
        }
    }

    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();
        Product pa = factory.getProduct("A");
        pa.fun();
        Product pb = factory.getProduct("B");
        pb.fun();
    }
}
