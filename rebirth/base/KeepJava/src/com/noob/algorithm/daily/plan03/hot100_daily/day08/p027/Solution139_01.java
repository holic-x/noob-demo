package com.noob.algorithm.daily.plan03.hot100_daily.day08.p027;

import java.util.List;

/**
 * 🟡 139 单词拆分 - https://leetcode.cn/problems/word-break/description/
 */
public class Solution139_01 {
    /**
     * 思路分析：
     * 物品：wordDict
     * 背包：s
     * 递推思路: 如果(0,i)可切割，则引入节点j (0,j)(j,i)也可被切割
     * dp[i] = dp[j] + wordDict.contains(subStr(j,i))
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        // dp[] dp[i] 表示(0,i)的位置可切割
        int n = s.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // 初始化可切割

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }

        return dp[n];
    }


}
