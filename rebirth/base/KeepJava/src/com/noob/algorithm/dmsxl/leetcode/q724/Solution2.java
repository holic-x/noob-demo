package com.noob.algorithm.dmsxl.leetcode.q724;

/**
 * 🟢724 寻找数组的中心下标
 */
public class Solution2 {

    // 遍历：判断每个下标(空间换时间，分别计算每个元素的前缀和、后缀和)
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        // 分别定义每个元素位置的前缀和、后缀和数组
        int[] leftSum = new int[len], rightSum = new int[len];
        leftSum[0] = 0; // 第一个元素的左边没有元素，所以前缀和为0
        rightSum[len - 1] = 0; // 最后一个元素右边没有元素，所以后缀和为0
        // 正序遍历:计算前缀和
        for (int i = 1; i < len; i++) {
            leftSum[i] = leftSum[i - 1] + nums[i - 1];
        }
        // 逆序遍历：计算后缀和
        for (int i = len - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i + 1];
        }

        // 遍历元素，验证每个坐标是否满足对应位置的前缀和=后缀和
        for (int i = 0; i < len; i++) {
            if (leftSum[i] == rightSum[i]) {
                return i;
            }
        }

        // 没有匹配的内容
        return -1;
    }
}
