package com.noob.algorithm.common150.q88;

import java.util.Arrays;

/**
 * 88 合并两个有序数组
 */
public class Solution2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 因为题目中nums1作为返回的结果，说明可以直接将nums2加入到nums1中，直接排序即可
        int cur = 0;
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[cur++];
        }
        // 对nums1进行排序
        Arrays.sort(nums1);
    }
}
