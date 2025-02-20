package com.noob.algorithm.solution_archive.dmsxl.leetcode.q135;


/**
 * 135 分发糖果
 */
public class Solution2 {
    /**
     * 动态规划 + 贪心思路
     * 将校验规则分为两条：左、右规则，计算分别满足这两条规则的情况下需要多少个糖果，要同时满足两个规则则需要选择两者较大的那个糖果数
     */
    public int candy(int[] nums) { // int[] ratings
        int n = nums.length;
        // 左规则：如果nums[i-1]<nums[i]成立，则i号学生会比i-1号学生多一个糖果
        int[] left = new int[n];
        left[0] = 1; // 初始化左规则：left[i] 表示每个学生满足左规则条件下所需的最少糖果数
        for (int i = 1; i < n; i++) {
            // 如果i评分比i-1大则比它多1个糖果即可，如果比i-1评分小则取最小为1
            left[i] = (nums[i] > nums[i - 1]) ? left[i - 1] + 1 : 1;
        }

        // 右规则：如果nums[i]>nums[i+1] 表示i号学生会比i+1号学生多一个糖果（因此此处需要先确认nums[i+1]所需的最少糖果数量，采用逆向遍历的思路）
        int[] right = new int[n];
        right[n - 1] = 1; // 初始化右规则：right[i] 表示每个学生满足右规则条件下所需的最少糖果数
        for (int i = n - 2; i >= 0; i--) { // 逆序遍历
            // 如果i评分比i+1大则比它多1个糖果即可，如果比i+1评分小则取最小为1
            right[i] = (nums[i] > nums[i + 1]) ? right[i + 1] + 1 : 1;
        }

        // 左右规则汇聚（要同时满足左右规则，因此选则两者之间的最大值即可）
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += Math.max(left[i], right[i]);
        }
        // 返回结果
        return cnt;
    }
}