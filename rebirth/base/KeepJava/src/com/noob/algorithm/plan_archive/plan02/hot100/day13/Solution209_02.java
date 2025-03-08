package com.noob.algorithm.plan_archive.plan02.hot100.day13;

/**
 * 🟡 209 长度最小的子数组 - https://leetcode.cn/problems/minimum-size-subarray-sum/
 */
public class Solution209_02 {

    /**
     * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组长度
     * 滑动窗口思路：
     * - 初始化窗口（curSum维护窗口元素和）
     * - 遍历数组元素，将窗口右节点指向元素加入窗口，
     * 如果当前窗口元素和持续满足curSum>=target则更新窗口长度并不断移出左窗口节点以尝试获取更短的子数组
     */
    public int minSubArrayLen(int target, int[] nums) {

        int minLen = Integer.MAX_VALUE;

        int n = nums.length;

        int curSum = 0; // 维护窗口内元素和

        // 定义滑动窗口
        int winLeft = 0;
        for (int winRight = 0; winRight < n; winRight++) {
            // 将元素移入窗口
            curSum += nums[winRight];
            // 校验curSum与target，如果持续满足curSum>=target则不断更新窗口并将其左窗口节点移出窗口
            while (curSum >= target) {
                minLen = Math.min(minLen, winRight - winLeft + 1); // 更新窗口长度
                // 将左窗口节点移窗口
                curSum -= nums[winLeft];
                winLeft++;
            }
        }

        // 返回结果
        return minLen == Integer.MAX_VALUE ? 0 : minLen;

    }

}
