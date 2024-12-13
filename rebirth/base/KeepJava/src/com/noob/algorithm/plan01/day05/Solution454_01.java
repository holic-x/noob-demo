package com.noob.algorithm.plan01.day05;

import java.util.HashMap;

/**
 * 🟡454 四数相加II
 */
public class Solution454_01 {

    /**
     * 分组统计：两两拆分
     * ① 拆分为(x,y) (u,v) 分别统计两个组各自元素之和及其出现的次数
     * ② 基于①的统计结果，继续统计各组元素满足i+j=0的次数（map1.get(i) * map2.get(j)累加）
     */

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // ① 分组统计:Map<两两元素和，出现次数>
        HashMap<Integer, Integer> map1 = new HashMap();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int curSum = nums1[i] + nums2[j];
                map1.put(curSum, map1.getOrDefault(curSum, 0) + 1); // 两数和 出现次数统计
            }
        }
        HashMap<Integer, Integer> map2 = new HashMap();
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int curSum = nums3[i] + nums4[j];
                map2.put(curSum, map2.getOrDefault(curSum, 0) + 1); // 两数和 出现次数统计
            }
        }

        // ② 校验i+j=0并统计出现次数
        int res = 0;
        for (int i : map1.keySet()) {
            if (map2.containsKey(0 - i)) {
                res += map1.get(i) * map2.get(0 - i);
            }
        }
        // 返回结果
        return res;
    }

}
