package com.noob.algorithm.dmsxl.knapsack;


/**
 * 0-1 背包问题
 */
public class Solution4 {

    /**
     * 0-1 背包问题：每个物品只有1个
     *
     * @param bagWeight 背包容量
     * @param weight    物品重量
     * @param value     物品价值
     * @return
     */
    public int maxValue(int bagWeight, int[] weight, int[] value) {
        int m = weight.length; // 物品个数(物品种类：一个物品只有一个)
        int n = bagWeight; // 背包最大容量


        // 1.定义dp（dp[j]表示用背包容量为j的背包可以得到的最大物品价值）
        int[] dp = new int[n + 1]; // i 为指定物品，j 为指定背包容量(背包容量需要计算到n，因此此处扩展为n+1容量)

        /**
         * 2.dp[j]推导 实际上是基于二维数组的基础上去掉i维度概念
         * dp[j] = max{dp[j],dp[j-weight[i]]+value[i]}
         */

        // 3.dp初始化（对于dp[0]设为0，其他下标可以根据递推公式来进行初始化）

        // 4.构建dp(❌❌❌错误)
        for (int j = n; j>=0; j--) { // 外层背包容量（逆序遍历：用于保证一个物品只能被取1次）
            for (int i = 0; i < m; i++) { // 外层物品(从0开始遍历)
                if(j >= weight[i]){
                    // 如果j可以存放物品i，可以选择放|不放，从两个方案中选择价值最大的方案
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }

        print(dp);

        // 返回结果
        return dp[n];
    }

    public void print(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + "-");
        }
    }


    public static void main(String[] args) {
        // 测试数据
        int[] weight = new int[]{1, 3, 4};
        int[] value = new int[]{15, 20, 30};
        Solution4 solution1 = new Solution4();
        System.out.println(solution1.maxValue(4, weight, value));
    }
}
