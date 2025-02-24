package com.noob.algorithm.solution_archive.dmsxl.knapsack.backpack01;


/**
 * 0-1 背包问题
 */
public class Solution1 {

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


        // 1.定义dp（dp[i][j]表示用背包容量为j的背包装物品i可以得到的最大物品价值）
        int[][] dp = new int[m][n + 1]; // i 为指定物品，j 为指定背包容量(背包容量需要计算到n，因此此处扩展为n+1容量)

        /**
         * 2.dp[i][j]推导
         * 当状态第i个物品时，需先判断当前容量可否装入物品i，在可装入物品i的前提下再去讨论放或者不放的情况
         * j<weight[i](当前容量j不足以装物品i)：直接延续上一状态的最大价值 dp[i][j] = dp[i-1][j]
         * j>=weight[i](当前容量j足以装物品i，可选择放|不放两种方案中可以获得最大价值的方案)：
         * - 不放：直接延续上一状态的最大价值 dp[i][j] = dp[i-1][j]
         * - 放：需要先空出存放当前物品的容量，然后判断剩余容量可获得的最大价值：dp[i][j] = dp[i-1][x] + value[i] = dp[i-1][j-weight[i]] + value[i]
         */

        // 3.dp初始化（首行、首列初始化）
        for (int j = 0; j <= n; j++) {
            // 判断当前背包容量是否可存放物品0
            dp[0][j] = (j >= weight[0]) ? value[0] : 0;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0; // 背包容量为0时不能存放任何物品
        }

        // 4.构建dp（按行填充：外层物品、内层背包容量）
        for (int i = 1; i < m; i++) { // 外层物品
            for (int j = 1; j <= n; j++) { // 内层背包容量
                if (weight[i] > j) {
                    // 如果j无法存放物品i
                    dp[i][j] = dp[i - 1][j]; // 延续上一背包状态
                } else {
                    // 如果j可以存放物品i，可以选择放|不放，从两个方案中选择价值最大的方案
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }

            }
        }

        // 在所有的方案中选择价值最大的方案(打印dp检查状态)
        int maxVal = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                maxVal = Math.max(maxVal, dp[i][j]);
            }
        }

        print(dp);

        // 返回结果
        return maxVal;
    }

    public void print(int[][] dp){
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "-");
            }
            System.out.println(); // 换行
        }
    }

    public static void main(String[] args) {
        // 测试数据
        int[] weight = new int[]{1, 3, 4};
        int[] value = new int[]{15, 20, 30};
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.maxValue(4, weight, value));
    }
}
