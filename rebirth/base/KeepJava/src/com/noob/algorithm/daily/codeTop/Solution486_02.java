package com.noob.algorithm.daily.codeTop;


/**
 * 🟡 486 预测赢家 -  https://leetcode.cn/problems/predict-the-winner/description/
 */
public class Solution486_02 {

    /**
     * 动态规划思路：
     * dp[i][j] 表示当剩下的数字是nums[i...j]（表示[i,j]范围）时，当前玩家与另一个玩家的最大净胜
     * 对于当前玩家有2种选择：
     * - ① 选择nums[i]: 则对手在[i+1,j]范围内选择最优策略，则对手的净胜分为 dp[i+1][j]，则当前玩家的净胜分为 nums[i] - dp[i+1][j]
     * - ② 选择nums[j]: 则对手在[i,j-1]范围内选择最优策略，则对手的净胜分为 dp[i][j-1]，则当前玩家的净胜分为 nums[j] - dp[i][j-1]
     * - 则当前玩家会从这两种策略种选择较大值 => dp[i][j] = max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1])
     */
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // 1.dp 定义：dp[i][j] 表示当剩下的数字是nums[i...j]时，当前玩家与另一个玩家的最大净胜
        int[][] dp = new int[n][n];

        // 2.dp 递推

        // 3.dp 初始化（当i==j的时候表示只有一个数字，则玩家只能选择这个数字）

        // 4.dp 构建（确定递推顺序）
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        // 返回结果
        return dp[0][n - 1] >= 0;
    }

}
