package com.noob.algorithm.solution_archive.dmsxl.leetcode.q139;

import java.util.Arrays;
import java.util.List;

/**
 * 139 单词拆分
 */
public class Solution1 {

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        // 1.dp[i] 表示(0,i)的位置可拆分
        boolean[] dp = new boolean[n + 1];

        // 2.dp推导：如果(0,i)的位置可拆分，则在中间插入一个切割点的位置也是可拆分的，即(0,j)(j,i)是可拆分的

        // 3.初始化
        Arrays.fill(dp, false); // 初始化默认设定为不可拆分
        dp[0] = true; // 第一个为止设置为true（作为后续推导的基础）

        // 4.构建dp
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }

        // 返回结果
        return dp[n];
    }
}
