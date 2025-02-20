package com.noob.algorithm.solution_archive.dmsxl.knapsack.backpackMulti;


import java.util.ArrayList;
import java.util.List;

/**
 * 多重背包问题
 */
public class Solution1 {

    /**
     * 多重背包：每个物品最多有m件可用
     *
     * @param weight  物品重量
     * @param value   物品价值
     * @param num     物品数量
     * @param bagSize 背包容量
     * @return
     */
    public int maxValue(int[] weight, int[] value, int[] num, int bagSize) {

        // 将多重背包平展成01背包（也就是说将所有物品展开来）
        int len = num.length;
        List<Integer> newWeight = new ArrayList<>(); // 初始化
        List<Integer> newValue = new ArrayList<>(); // 初始化

        for (int i = 0; i < len; i++) {
            int curNum = num[i];
            // 展开weight
            for (int k = 0; k < curNum; k++) {
                newWeight.add(weight[i]); // 扩展weight数组
                newValue.add(value[i]); // 扩展value数组
            }
        }

        // 动态规划思路切换为：处理01背包问题
        // 1.dp[j] 表示装满容量为j的背包的最大价值
        int[] dp = new int[bagSize + 1];

        /**
         * 2.递推公式：
         * j<newWeight[i]: 继承上一状态
         * j>=newWeight[i]: dp[j] = max{dp[j],dp[j-newWeight[i]]+newValue[i]};
         */

        // 3.初始化
        dp[0] = 0; // 装满背包为0的最大价值为0

        // 4.构建dp (先物品后背包容量 + 背包逆序)
        for (int i = 0; i < newWeight.size(); i++) {
            for (int j = bagSize; j >= 0; j--) {
                if (j >= newWeight.get(i)) {
                    dp[j] = Math.max(dp[j], dp[j - newWeight.get(i)] + newValue.get(i));
                }
            }
        }

        // 返回结果
        return dp[bagSize];

    }

    public static void main(String[] args) {
        int[] weight = new int[]{1, 3, 4};
        int[] value = new int[]{15, 20, 30};
        int[] num = new int[]{2, 3, 2};
        int bagSize = 10;
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.maxValue(weight, value, num, bagSize));
    }

}
