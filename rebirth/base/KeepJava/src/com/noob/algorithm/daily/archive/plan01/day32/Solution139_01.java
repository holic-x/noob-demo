package com.noob.algorithm.daily.archive.plan01.day32;

import java.util.Arrays;
import java.util.List;

/**
 * 🟡 139 单词拆分 - https://leetcode.cn/problems/word-break/submissions/594278972/
 */
public class Solution139_01 {

    /**
     * 动态规划：0-k 的子字符串可以被拆分（dp[k]可以被拆分），则dp[j]也可以被拆分，且从j+1到K的位置也能被拆分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        // 1.dp 定义：dp[k]表示0-k的子字符串可以被拆分
        boolean[] dp = new boolean[n + 1];

        /**
         * 2.dp 推导：如果dp[k]可以被拆分，则(0,j)、(j+1,k)也可以被切割
         */

        // 3.dp 初始化：初始化dp[0]为true，其余全为false
        Arrays.fill(dp, false);
        dp[0] = true;

        // 4.dp 构建
        for (int i = 1; i <= n; i++) {
            // 处理（0，i）的情况,寻找合适的切割位置
            for (int j = 0; j < i; j++) {
                // 如果dp[k]可以被拆分，则(0,j)、(j+1,k)也可以被切割,以此推导dp[k]
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        // 返回结果
        return dp[n];
    }

}
