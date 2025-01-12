package com.noob.algorithm.daily.plan01.day23;

/**
 * 🟡 045 跳跃游戏II - https://leetcode.cn/problems/jump-game-ii/description/
 */
public class Sloution045_01 {

    /**
     * 所给样例本身可达终点，计算最小跳跃次数（每次以上一次选择的最大距离进行跳跃）
     */
    public int jump1(int[] nums) {

        int preJumpPoint = 0; // 记录上次指定的最大跳跃位置
        int step = 0; // 记录最少跳跃次数
        int maxCover = 0; // 最大覆盖距离

        for (int i = 0; i < nums.length - 1; i++) { // i∈[0,len-1) 避免最后增加一次不必要的一次跳跃
            maxCover = Math.max(maxCover, i + nums[i]); // 更新最大覆盖距离
            if (i == preJumpPoint) {
                step++; // 到达上次指定的最大覆盖距离，选择跳跃
                preJumpPoint = maxCover;
            }
        }

        // 返回结果
        return step;
    }

}
