package com.noob.algorithm.common150.q014;

import java.util.Arrays;

/**
 * 14 最长公共前缀
 */
public class Solution2 {

    /**
     * 思路：先对字符串数组进行排序，然后只需要比较最短和最长字符串这两个元素的最大前缀
     */
    public String longestCommonPrefix(String[] strs) {

        String maxPrefix = "";

        // 对字符串数组进行排序
        Arrays.sort(strs);

        // 获取最短和最长的两个元素（首位）
        String minStr = strs[0];
        String maxStr = strs[strs.length - 1];

        maxPrefix = minStr; // 理想情况下maxPrefix是最小的字符串

        // 定义游标位置（记录最大前缀的位置）
        int idx = 0;
        for (int i = 0; i <= minStr.length(); i++) {
            // 如果最大的字符串包括了这个前缀部分，则可以继续后移
            if (maxStr.startsWith(minStr.substring(0, idx))) {
                idx++;
            } else {
                // 不包括说明不符合
                return minStr.substring(0, idx - 1);
            }
        }

        return maxPrefix;
    }

}
