package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

/**
 * 🔴 239 滑动窗口最大值 - https://leetcode.cn/problems/sliding-window-maximum/submissions/598655671/
 */
public class Solution239_01 {

    /**
     * 思路分析：
     * 给定整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
     * 返回 滑动窗口中的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int pt = 0; // 写入指针
        // 暴力统计法：计算每个窗口的最大值(窗口大小为k：j-i+1=k)
        for (int i = 0; i <= n - k; i++) {
            // 计算指定窗口的最大值
            ans[pt++] = getMax(nums, i, i + k - 1);
        }

        // 返回处理结果
        return ans;
    }

    /**
     * 计算指定区间[start,end]的max
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int getMax(int[] nums, int start, int end) {
        int max = nums[start];
        for (int i = start; i <= end; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        Solution239_01 s = new Solution239_01();
        s.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }


}
