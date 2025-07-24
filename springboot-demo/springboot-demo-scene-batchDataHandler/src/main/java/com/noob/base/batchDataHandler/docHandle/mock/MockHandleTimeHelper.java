package com.noob.base.batchDataHandler.docHandle.mock;

/**
 * 处理时间 mock
 */
public class MockHandleTimeHelper {

    /**
     * 模拟固定耗时：用于控制变量限定场景固定耗时模拟
     */
    public static void mockHandleTime(int mockTime) {
        try {
            Thread.sleep(mockTime);
        } catch (InterruptedException e) {
            // Thread.currentThread().interrupt();
            // 忽略中断异常
        }
    }

    /**
     * 模拟随机耗时（仿真实文件存储服务场景调用）：单文件处理延迟模拟器
     * 采用复合分布模拟真实场景的延迟特性
     */
    public static void mockHandleTime_bySingle(boolean isRandom) {
        // 基础延迟参数（单位：毫秒）
        final int BASE_DELAY = 200;
        final int IO_VARIANCE = 300;
        final double NETWORK_FLUCTUATION_PCT = 0.3;

        if (isRandom) {
            // 生成基础延迟+IO抖动
            int ioDelay = BASE_DELAY + (int) (Math.random() * IO_VARIANCE);

            // 30%概率增加网络波动
            if (Math.random() < NETWORK_FLUCTUATION_PCT) {
                ioDelay += (int) (Math.random() * 500);
            }

            // 10%概率触发异常延迟
            if (Math.random() < 0.1) {
                ioDelay *= 2;
            }

            try {
                Thread.sleep(Math.max(100, ioDelay));
            } catch (InterruptedException e) {
                // Thread.currentThread().interrupt();
                // 忽略中断异常
            }
        } else {
            try {
                Thread.sleep(BASE_DELAY);
            } catch (InterruptedException e) {
                // Thread.currentThread().interrupt();
                // 忽略中断异常
            }
        }
    }


    /**
     * 基于正态分布模拟真实场景的随机延迟
     * 包含基础延迟 + 波动范围 + 异常概率
     */
    public static void mockHandleTime_byBatch(boolean isRandom) {
        int baseDelay = 300; // 基础延迟300ms
        int stdDev = 200;    // 标准差控制波动范围
        double outlierProb = 0.05; // 5%概率出现异常延迟

        if (isRandom) {
            // 生成正态分布随机数
            double randomValue = generateNormalRandom();

            // 基础延迟 + 波动
            int delay = (int) (baseDelay + randomValue * stdDev);

            // 5%概率出现3倍延迟
            if (Math.random() < outlierProb) {
                delay *= 3;
            }

            // 确保最低延迟100ms
            delay = Math.max(100, delay);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                // Thread.currentThread().interrupt();
                // 忽略中断异常
            }
        } else {
            try {
                Thread.sleep(baseDelay);
            } catch (InterruptedException e) {
                // Thread.currentThread().interrupt();
                // 忽略中断异常
            }
        }
    }

    // 正态分布随机数生成器
    private static double generateNormalRandom() {
        // Box-Muller变换法
        double u1 = Math.random();
        double u2 = Math.random();
        return Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2 * Math.PI * u2);
    }

    public static void main(String[] args) {

        // 测试模拟耗时
        long startTime1 = System.currentTimeMillis();
        MockHandleTimeHelper.mockHandleTime_bySingle(true);
        long endTime1 = System.currentTimeMillis();
        System.out.println(String.format("1.本次操作耗时【%s】ms", endTime1 - startTime1));


        long startTime2 = System.currentTimeMillis();
        MockHandleTimeHelper.mockHandleTime_bySingle(false);
        long endTime2 = System.currentTimeMillis();
        System.out.println(String.format("2.本次操作耗时【%s】ms", endTime2 - startTime2));

        long startTime3 = System.currentTimeMillis();
        MockHandleTimeHelper.mockHandleTime_byBatch(true);
        long endTime3 = System.currentTimeMillis();
        System.out.println(String.format("3.本次操作耗时【%s】ms", endTime3 - startTime3));

        long startTime4 = System.currentTimeMillis();
        MockHandleTimeHelper.mockHandleTime_byBatch(false);
        long endTime4 = System.currentTimeMillis();
        System.out.println(String.format("4.本次操作耗时【%s】ms", endTime4 - startTime4));

        long startTime5 = System.currentTimeMillis();
        MockHandleTimeHelper.mockHandleTime(500);
        long endTime5 = System.currentTimeMillis();
        System.out.println(String.format("5.本次操作耗时【%s】ms", endTime5 - startTime5));

    }

}
