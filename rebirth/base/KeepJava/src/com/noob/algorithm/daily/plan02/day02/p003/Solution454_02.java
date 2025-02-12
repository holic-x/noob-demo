package com.noob.algorithm.daily.plan02.day02.p003;

/**
 * 🟡 454 四数相加II - https://leetcode.cn/problems/4sum-ii/description/
 */
public class Solution454_02 {

    /**
     * 思路分析：计算有多少个元组满足a+b+c+d=0
     * 暴力算法：多层嵌套 ❌ 超时
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        int n1 = nums1.length, n2 = nums2.length, n3 = nums3.length, n4 = nums4.length;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                for (int x = 0; x < n3; x++) {
                    for (int y = 0; y < n4; y++) {
                        int target = nums1[i] + nums2[j] + nums3[x] + nums4[y];
                        if (target == 0) {
                            res++;
                        }
                    }
                }
            }
        }
        // 返回结果
        return res;
    }

}
