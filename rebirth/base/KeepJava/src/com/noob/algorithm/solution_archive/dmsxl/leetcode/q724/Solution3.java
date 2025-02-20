package com.noob.algorithm.solution_archive.dmsxl.leetcode.q724;

/**
 * 🟢724 寻找数组的中心下标
 */
public class Solution3 {

    // 两次遍历：totalSum、leftSum、rightSum
    public int pivotIndex(int[] nums) {
        int len = nums.length;

        // 定义数组元素总和
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        // 定义当前遍历元素左侧所有元素的总和
        int leftSum = 0;
        for (int i = 0; i < len; i++) {
            // 校验当前的rightSum
            int rightSum = totalSum - leftSum - nums[i];
            // 判断当前的leftSum是否等于rightSum
            if (leftSum == rightSum) {
                return i;
            }
            // 更新leftSum（继续下一轮校验）
            leftSum += nums[i];
        }

        // 没有匹配的内容
        return -1;
    }
}
