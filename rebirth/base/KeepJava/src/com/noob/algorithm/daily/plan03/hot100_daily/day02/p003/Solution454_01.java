package com.noob.algorithm.daily.plan03.hot100_daily.day02.p003;

/**
 * 🟡 454 四数相加II - https://leetcode.cn/problems/4sum-ii/description/
 * 概要：给定4个整数数组(数组长度都是n)计算有多少个元组(i,j,k,l)满足有效范围内n1[i]+n2[j]+n3[k]+n4[l]=0
 */
public class Solution454_01 {

    /**
     * 思路分析：给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足 a+b+c+d=0
     * 暴力思路：四层for循环处理
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 满足条件的多元组个数
        int ans = 0;
        int n = nums1.length; // 4个数组长度都是n，此处取nums1
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                for (int c = 0; c < n; c++) {
                    for (int d = 0; d < n; d++) {
                        int curSum = nums1[a] + nums2[b] + nums3[c] + nums4[d];
                        if (curSum == 0) {
                            ans++;
                        }
                    }
                }
            }
        }

        // 返回结果
        return ans;
    }

}
