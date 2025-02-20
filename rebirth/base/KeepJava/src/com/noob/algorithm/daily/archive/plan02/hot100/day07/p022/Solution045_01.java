package com.noob.algorithm.daily.archive.plan02.hot100.day07.p022;

/**
 * 🟡 045 - 跳跃游戏II - https://leetcode.cn/problems/jump-game-ii/description/
 */
public class Solution045_01 {

    /**
     * 思路分析：测试用例支持跳跃到末尾，此处获取跳跃的最小步数
     * 贪心思路：在有效的跳跃范围内选择可到达的最大距离进行跳跃
     */
    public int jump(int[] nums) {
        int cnt = 0; // 步数统计
        int jumpPointer = 0; // 上一跳指定的最大跳跃距离(跳跃目标)
        int curMaxCover = 0; // 目前的最大覆盖距离

        // 限定在有效的跳跃范围内进行跳跃
        for (int i = 0; i < nums.length - 1; i++) {
            curMaxCover = Math.max(curMaxCover, i + nums[i]);
            // 如果到达上一跳指定的最大覆盖距离则进行跳跃
            if (i == jumpPointer) {
                cnt++;
                jumpPointer = curMaxCover; // 更新
            }
        }

        // 返回最少跳跃步数
        return cnt;
    }
}
