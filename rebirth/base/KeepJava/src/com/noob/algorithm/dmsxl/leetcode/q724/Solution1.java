package com.noob.algorithm.dmsxl.leetcode.q724;

/**
 * 🟢724 寻找数组的中心下标
 */
public class Solution1 {

    // 遍历：判断每个下标
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 校验每个元素位置i是否符合中心坐标的定义，一旦找到直接返回，否则继续搜索
            int leftSum = 0; // 存储 i 左侧的元素总和
            for (int left = 0; left < i; left++) {
                leftSum += nums[left];
            }
            int rightSum = 0; // 存储 i 右侧的元素总和
            for (int right = i + 1; right < len; right++) {
                rightSum += nums[right];
            }

            // 判断leftSum==rightSum是否成立
            if (leftSum == rightSum) {
                return i; // 找到满足条件的中心坐标则直接返回
            }
        }
        // 没有匹配的内容
        return -1;
    }
}
