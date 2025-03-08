package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.strategy;

/**
 * 营销活动支持 - 调用不同的营销算法策略
 */
public class StrategySaleDemo {

    // ① 抽象策略
    static interface DiscountStrategy {
        // 打折算法（sourcePrice原价、num折扣相关数据信息）
        public int discount(int sourcePrice, int num);
    }

    // ② 具体策略(直减、满减、打折、0元购)
    static class ZjDiscountDiscountStrategy implements DiscountStrategy {
        @Override
        public int discount(int sourcePrice, int num) {
            return (sourcePrice - num) > 0 ? sourcePrice - num : 0;
        }
    }

    static class MjDiscountStrategy implements DiscountStrategy {
        @Override
        public int discount(int sourcePrice, int num) {
            // 满200减num
            return sourcePrice >= 200 ? sourcePrice - num : sourcePrice;
        }
    }

    static class DzDiscountStrategy implements DiscountStrategy {
        @Override
        public int discount(int sourcePrice, int num) {
            return (int) (sourcePrice * (1 - num / 100.00));
        }
    }

    // ③ 环境定义
    static class Context {
        // 定义策略
        DiscountStrategy discountStrategy;

        // 构造器初始化策略
        Context(DiscountStrategy discountStrategy) {
            this.discountStrategy = discountStrategy;
        }

        // 定义方法（调用不同的算法策略）
        public int getDiscountPrice(int sourcePrice, int num) {
            return discountStrategy.discount(sourcePrice, num);
        }
    }

    public static void main(String[] args) {
        // 选择策略
        MjDiscountStrategy mj = new MjDiscountStrategy();
        // 初始化环境
        Context context = new Context(mj);
        // 调用算法
        System.out.println(context.getDiscountPrice(100,20));
        System.out.println(context.getDiscountPrice(300,20));
    }
}
