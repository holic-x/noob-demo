package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟡 454 四数相加II - https://leetcode.cn/problems/4sum-ii/description/
 * 概要：给定4个整数数组(数组长度都是n)计算有多少个元组(i,j,k,l)满足有效范围内n1[i]+n2[j]+n3[k]+n4[l]=0
 */
public class Solution454_02 {

    /**
     * 思路分析：给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足 a+b+c+d=0
     * 哈希表辅助处理：map1+map2 降低多重循环嵌套内嵌度
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 满足条件的多元组个数
        int ans = 0;
        int n = nums1.length; // 4个数组长度都是n，此处取nums1

        // 处理nums1[a] + nums2[b] ，处理为Map<Integer,Integer> 表示每个组合之和的出现次数
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int curSum = num1 + num2;
                map1.put(curSum, map1.getOrDefault(curSum, 0) + 1);
            }
        }

        // 处理nums3[c] + nums4[d]，处理为Map<Integer,Integer> 表示每个组合之和的出现次数
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                int curSum = num3 + num4;
                map2.put(curSum, map2.getOrDefault(curSum, 0) + 1);
            }
        }

        // 组合处理结果：map1\map2 两者key组成满足x+y=0则可构成满足条件的四元组，则根据对应出现次数做乘积处理
        for (Map.Entry<Integer, Integer> entry1 : map1.entrySet()) {
            for (Map.Entry<Integer, Integer> entry2 : map2.entrySet()) {
                int sum = entry1.getKey() + entry2.getKey();
                if (sum == 0) {
                    ans += entry1.getValue() * entry2.getValue();
                }
            }
        }

        // 返回结果
        return ans;
    }

}
