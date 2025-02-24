package com.noob.algorithm.solution_archive.leetcode.common150.q228;

import java.util.*;

/**
 * 228 汇总区间
 */
public class Solution1 {

    /**
     * 题目数组本身有序，不需额外排序，此处可将题意转化为分拆数组中的连续区间段
     */
    public List<String> summaryRanges(int[] nums) {
        // 定义响应结果
        List<String> res = new ArrayList<>();

        int i = 0;
        int n = nums.length;
        while (i < n) {
            int left = i;
            i++;
            while (i < n && (nums[i] - nums[i - 1] == 1)) {
                i++;
            }
            int right = i - 1; // 连续序列断开，区间取值为[left,right]=[start,i-1]

            // 构建区间结果（判断left、right）
            StringBuffer sb = new StringBuffer();
            if (left == right) {
                // 说明只有一个元素
                sb.append(nums[left]);
            } else if (left < right) {
                // 说明是一个区间
                sb.append(nums[left]).append("-->").append(nums[right]);
            }
            // 将区间结果加入结果集合
            res.add(sb.toString());
        }

        // 返回结果
        return res;
    }
}
