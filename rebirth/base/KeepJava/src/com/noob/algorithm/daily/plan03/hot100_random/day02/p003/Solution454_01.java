package com.noob.algorithm.daily.plan03.hot100_random.day02.p003;

import sun.text.normalizer.UBiDiProps;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟡 454 四数相加II - https://leetcode.cn/problems/4sum-ii/description/
 */
public class Solution454_01 {

    /**
     * 思路分析：给定4个整数数组(数组长度都是n)计算有多少个元组(i,j,k,l)满足有效范围内n1[i]+n2[j]+n3[k]+n4[l]=0
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {

        // 定义map1统计nums1+num2的和及该和出现的次数
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                int sum = n1 + n2;
                map1.put(sum, map1.getOrDefault(sum, 0) + 1);
            }
        }

        // 同理，计算num3+num4的和及该和出现的次数
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int n3 : nums3) {
            for (int n4 : nums4) {
                int sum = n3 + n4;
                map2.put(sum, map2.getOrDefault(sum, 0) + 1);
            }
        }

        // 计算满足条件的元组
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            Integer key = entry.getKey();
            if (map2.containsKey(0 - key)) {
                ans += map1.get(key) * map2.get(0 - key);
            }
        }

        // 返回结果
        return ans;
    }

}
