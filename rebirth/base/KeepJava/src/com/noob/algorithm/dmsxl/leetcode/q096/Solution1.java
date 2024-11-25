package com.noob.algorithm.dmsxl.leetcode.q096;

/**
 * 096 不同的二叉搜索树
 */
public class Solution1 {

    // 动态规划
    public int numTrees(int n) {
        // 1.dp定义（dp[i]表示i个节点构成的二叉搜索树的数量）
        int[] dp = new int[n + 1]; // 需计算到n，此处扩展到n+1长度

        /**
         * 2.dp推导
         * i个节点构成的二叉搜索树的数量 = 以每个节点作为根节点的二叉搜索树数量之和
         * 即 dp[i] += dp[j-1] * dp[i-j](左子树数量 * 右子树数量) 累加二叉搜索树之和
         */

        // 3.dp 初始化
        dp[0] = 1; // dp[0]表示子树元素个数为0的情况下课构成的二叉搜索树数量为1

        // 4.dp 构建
        for (int i = 1; i <= n; i++) { // 外层循环：确定i
            for (int j = 1; j <= i; j++) { // 内层循环：确定以哪个节点作为根节点（j∈[1,i],其中[1,j-1]用于构建左子树、[j+1,i]用于构建右子树）
                dp[i] += dp[j - 1] * dp[i - j]; // L左j-1个元素、R右i-j个元素 =》dp[i] += L * R
            }
        }

        // 返回结果
        return dp[n];
    }

}
