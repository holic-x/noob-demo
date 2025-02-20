package com.noob.algorithm.leetcode.common150.q209;

import java.util.HashSet;

/**
 * 209 长度最小的子数组
 */
public class Solution2 {

    public int minSubArrayLen(int target, int[] nums) {
        // 定义滑动窗口存储元素
        // HashSet<Integer> set = new HashSet<Integer>();
        int min = Integer.MAX_VALUE;

        // 定义指针确定窗口边界（left左边界，right指向当前要加入的元素位置）
        int left = 0, right = 0, curSum = 0;

        while (right < nums.length) {
           // 将当前元素加入
            curSum += nums[right];
            // 判断数据是否大于target，如果大于则记录min，并从左侧移入数据，直到curSum<target
            while (curSum >= target) {
                // 记录min
                min = Math.min(min,right - left + 1);
                // 从左侧逐步移出数据
                curSum -= nums[left];
                left++;
            }
            // right指向下一个元素(因为在while判断中会用到right，因此这个过程中要将right的变化放到最后，避免产生影响)
            right ++;
        }

        // 返回结果
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.minSubArrayLen(7, nums));
    }
}
