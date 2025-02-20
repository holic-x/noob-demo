package com.noob.algorithm.leetcode.common150.q139;

import java.util.Arrays;
import java.util.List;

/**
 * 139 单词拆分
 */
public class Solution1 {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 1.dp[i]定义 表示(0,i)位置的字符串可以被拆分
        boolean[] dp = new boolean[s.length() + 1];
        // 2.初始化数组（dp[0]设为true（不具备含义） 其他默认设为false）
        Arrays.fill(dp, false);
        dp[0] = true;
        // 3.状态转移方程分析：如果(0,i)可被拆分，则中间引入一个点(0,j)(j,i)也能够被拆分
        // 4.初始化dp
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 构建dp元素
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        // 返回结果
        return dp[s.length()];
    }
}
