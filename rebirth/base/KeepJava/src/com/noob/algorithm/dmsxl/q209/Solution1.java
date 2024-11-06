package com.noob.algorithm.dmsxl.q209;

/**
 * 209 长度最小的子数组
 */
public class Solution1 {

    // 滑动窗口：左侧收缩右侧扩展
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;

        int min = Integer.MAX_VALUE; // 最小值

        // 定义滑动窗口边界指针
        int left = 0, right = 0;

        // 定义当前滑动窗口的元素之和
        int curSum = 0; // 初始化为0，逐步移入新元素

        // 当窗口移动到右侧则遍历结束
        while (right < len) { // left < len && right < len
            // 引入元素
            curSum += nums[right];
            while (curSum >= target) {
                // 如果当前窗口内元素之和大于等于target，则记录这个满足条件窗口的数组长度的最小值
                min = Math.min(min, right - left + 1);
                // 不断移出左侧元素，直到curSum<target，为加入下一个新元素做准备
                curSum = curSum - nums[left++];
            }
            // right 继续往前(因为前面需要用到原来的right计算长度，此处将right移动放置在最后)
            right++;
        }
        // 返回满足条件的最小长度
        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
