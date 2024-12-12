package com.noob.algorithm.dmsxl.leetcode.q718;
import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 718 最长重复子数组
 */
public class Solution2 {

    /**
     * 动态规划
     */
    public int findLength(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int maxLen = 0;

        // 1.定义dp[][](dp[i][j]表示以下标`i-1`结尾的A、下标`j-1`结尾的B的最长重复子数组长度)
        int[][] dp = new int[n1 + 1][n2 + 1];

        /**
         * 2.dp 推导
         * dp[i][j] = dp[i-1][j-1]+1 (遍历从1位置开始)
         */

        // 3.dp 初始化（dp[0][j]和dp[i][j]本身为实际意义，但为了使推导公式有效，此处选择初始化为0）

        // 4.dp 构建（先A后B或者先B后A都可以）
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // 如果当前指向位置元素值相等则进行递推
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // 更新最值
                maxLen = Math.max(maxLen, dp[i][j]);

                // 打印dp
                System.out.println("---------------start---------------");
                PrintUtil.printMatrix(dp);
                System.out.println("---------------end---------------" + "\n");
            }
        }

        // 返回结果
        return maxLen;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 2, 1};
        int[] nums2 = new int[]{3, 2, 1, 4, 7};
        Solution2 solution2 = new Solution2();
        solution2.findLength(nums1, nums2);
    }

}
