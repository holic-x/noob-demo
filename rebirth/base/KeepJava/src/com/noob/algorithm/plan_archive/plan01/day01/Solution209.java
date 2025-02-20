package com.noob.algorithm.plan_archive.plan01.day01;

/**
 * 🟡 209 长度最小的子数组
 * https://leetcode.cn/problems/minimum-size-subarray-sum/description/
 */
public class Solution209 {

    /**
     * 暴力思路：外层循环确定起点，内存循环确定确定终点，计算子数组和并校验最小长度
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen1(int target, int[] nums) {
        int len = nums.length;
        int minSubArrLen = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int curSum = 0;
            for (int j = i; j < len; j++) {
                curSum += nums[j];
                if (curSum >= target) {
                    minSubArrLen = Math.min(minSubArrLen, j - i + 1);
                }
            }
        }
        // 返回结果
        return minSubArrLen == Integer.MAX_VALUE ? 0 : minSubArrLen;
    }

    /**
     * 滑动窗口思路：
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int minSubArrLen = Integer.MAX_VALUE;
        // 定义滑动窗口的左右边界（滑动窗口相当于一个临时的子数组，用于查找子数组和大于等于target的长度）
        int left = 0, right = 0; // 窗口从0开始
        int curSum = 0; // 定义滑动窗口元素和
        while (left <= right && right < len) { // 滑动窗口不越界，且遍历指针不越界
            // 加入元素
            curSum += nums[right];
            // 判断curSum和target的关系
            while (curSum >= target) {
                // 记录满足条件的最小数组长度
                minSubArrLen = Math.min(minSubArrLen, right - left + 1); // int subArrSize = right - left + 1; 滑动窗口的大小（此处用坐标差值表示，注意right变化的影响）
                // 需要移除左边的元素直到curSum<target，才有可能加入下一个新元素进行校验
                curSum -= nums[left];
                left++;
            }
            // 右指针继续移动
            right++;
        }

        // 返回结果
        return minSubArrLen == Integer.MAX_VALUE ? 0 : minSubArrLen;
    }

    public static void main(String[] args) {
        Solution209 solution = new Solution209();
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(solution.minSubArrayLen(7, nums));
    }

}
