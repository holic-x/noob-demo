package com.noob.design.strategy;

/**
 * 营销活动支持
 * 不同的折扣方案实现的方法策略不同
 */
public class StrategySaleDemo {
    public static void main(String[] args) {
        int sourcePrice = 500;
        // 初始化环境并执行折扣策略
        System.out.println("原价为：" + sourcePrice + " 直减后：" + new DiscountContext(new ZjDiscountStrategy()).getDiscountPrice(sourcePrice, 100));
        System.out.println("原价为：" + sourcePrice + " 满减后：" + new DiscountContext(new MjDiscountStrategy()).getDiscountPrice(sourcePrice, 200));
        System.out.println("原价为：" + sourcePrice + " 打折后：" + new DiscountContext(new DzDiscountStrategy()).getDiscountPrice(sourcePrice, 50));
        System.out.println("原价为：" + sourcePrice + " 0元购后：" + new DiscountContext(new ZeroDiscountStrategy()).getDiscountPrice(sourcePrice, 0));
    }
}


// 抽象策略
interface DiscountStrategy {
    // sourcePrice 原价、num 对应不同的营销策略给它设定不同的业务定位（此处也可以定义一个类似dto概念，然后算法策略要用哪个值就取哪个）
    public int discount(int sourcePrice, int num);
}

// 具体策略(直减、满减、打折、0元购)
// 直减
class ZjDiscountStrategy implements DiscountStrategy {
    // 此处为直减（num表示的是直扣金额）
    @Override
    public int discount(int sourcePrice, int num) {
        // 直减
        return Math.max(sourcePrice - num, 0); // 如果折扣后金额小于0则取0
    }
}

// 满减
class MjDiscountStrategy implements DiscountStrategy {
    // 设定此处满300减（num表示的是满减金额）
    @Override
    public int discount(int sourcePrice, int num) {
        if (sourcePrice > 300) {
            return Math.max(sourcePrice - num, 0); // 如果折扣后金额小于0则取0
        }
        return sourcePrice;
    }
}

// 打折
class DzDiscountStrategy implements DiscountStrategy {
    // 设定此处为打折（num表示的是折扣比例，取值范围为0-100）
    @Override
    public int discount(int sourcePrice, int num) {
        if (num <= 0 || num > 100) {
            throw new RuntimeException("num值域错误");
        }
        return (int) (sourcePrice*(1-num/100.00)); // 此处需注意小数除法处理，暂定结果取整
    }
}

// 0元购
class ZeroDiscountStrategy implements DiscountStrategy {
    // 此处设定为0元购
    @Override
    public int discount(int sourcePrice, int num) {
        return 0;
    }
}

// 环境
class DiscountContext {
    // 定义算法策略并初始化环境
    private DiscountStrategy strategy;

    public DiscountContext(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    // 定义方法进行折扣操作
    public int getDiscountPrice(int sourcePrice, int num) {
        return strategy.discount(sourcePrice, num);
    }
}