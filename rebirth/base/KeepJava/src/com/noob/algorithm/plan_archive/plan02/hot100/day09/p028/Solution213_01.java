package com.noob.algorithm.plan_archive.plan02.hot100.day09.p028;

/**
 * 🟡 213 打家劫舍II - https://leetcode.cn/problems/house-robber-ii/description/
 */
public class Solution213_01 {

    /**
     * 房屋围成1圈（即第0间房屋和第n-1间房屋相邻），相邻两间房子同时被偷会报警
     * 思路分析：不能连着偷两间房，求偷窃的最大金额
     * 思路：平展数组，确定偷窃范围
     * ① 偷0不偷n-1:偷窃范围为[0,n-2]
     * ② 不偷0偷n-1：偷窃范围为[1,n-1]
     */
    public int rob(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // 划分区域进行偷窃
        // ① 偷0不偷n-1:偷窃范围为[0,n-2]
        int max1 = robByRange(nums, 0, n - 2);

        // ② 不偷0偷n-1：偷窃范围为[1,n-1]
        int max2 = robByRange(nums, 1, n - 1);

        return Math.max(max1, max2);
    }

    // 根据指定范围确定偷窃的最大金额
    private int robByRange(int[] nums, int start, int end) {
        // 1.dp 定义（dp[i] 表示偷窃到第i间房屋可获得的最大金额（i范围取值[start,end]））
        int n = end - start + 1;

        // 特例判断
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[start];
        }
        if (n == 2) {
            return Math.max(nums[start], nums[end]); // nums[start],nums[start+1]
        }


        int[] dp = new int[n];

        /**
         * 2.dp 递推：相邻房屋不能连着偷
         * dp[i] = max{dp[i-2]+nums[i],dp[i-1]}
         */

        // 3.dp 初始化
        dp[0] = nums[start]; // 有效范围的第1间房屋
        dp[1] = Math.max(nums[start], nums[start + 1]); // 有效范围内的前两间房屋

        // 4.dp 构建
        int cur = 2; // 定义dp数组封装指针
        // 遍历剩余房屋
        for (int i = start + 2; i <= end; i++) {
            dp[cur] = Math.max(dp[cur - 2] + nums[i], dp[cur - 1]);
            cur++;
        }

        // 返回结果
        return dp[n - 1]; // 偷窃到最后一间房屋可获得最大金额
    }
}
