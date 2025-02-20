package com.noob.algorithm.daily.archive.plan02.hot100.day08.p027;

import java.util.*;

/**
 * 🟡 139 单词拆分 - https://leetcode.cn/problems/word-break/description/
 */
public class Solution139_01 {
    /**
     * 思路分析：利用字典wordDict中的一个或者多个单词拼接出s，单词可以重复使用（不要求字典中的单词全部使用完），如果可行返回true
     * 如果(0,i)可以被拆分，则引入一个中间点切割(0,j)(j,i)也可以被拆分
     * 转化为完全背包问题即s为背包容量，wordDict为物品
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int m = wordDict.size(), n = s.length() + 1;
        // 1.dp 定义：dp[j] 表示(0,j)的位置可进行拆分
        boolean[] dp = new boolean[n];

        /**
         * 2.dp 递推：如果(0,i)可以被拆分，则引入一个中间点切割(0,j)(j,i)也可以被拆分
         * 即 dp[i] = dp[j] &&  wordDict.contains(subStr) (此处截取字符串范围为(j,i))
         */

        // 3.dp 初始化
        Arrays.fill(dp, false); // 默认区间不可拆分
        dp[0] = true; // 初始化第1个可被拆分，作为后续推导的基础

        // 4.dp 构建（遍历顺序：完全背包问题）
        for (int i = 1; i < n; i++) { // (0,n)范围 校验
            for (int j = 0; j < i; j++) { // 从(0,i)范围选择切割点j
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }

        // 返回结果
        return dp[n-1];
    }

}
