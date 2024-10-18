package com.noob.algorithm.common150.q55;

/**
 * 55 跳跃游戏
 */
public class Solution1 {
    // 思路：加油站加油概念，判断当前已走里程+当前可加油数(覆盖范围)能否支撑走到下一个节点
    public boolean canJump(int[] nums) {
        // 定义覆盖范围（最大覆盖范围：看最大覆盖范围能否支撑走到下一个节点）
        int max = nums[0]; // 初始化最大覆盖范围为第一个加油站可加油量
        for(int i = 1; i < nums.length; i++) {
            if(max < i) {
                return false; // 判断目前的覆盖范围能不能到达这个节点，如果不能则返回false
            }
            // 如果可以到达这个节点，则需要更新最大覆盖范围(一个是原来的max，一个是已走路程+可加油量，选择这两个中的最大值作为下一个可覆盖范围)
            max = Math.max(max, i + nums[i]);
            System.out.println(max);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(new Solution1().canJump(nums));
    }
}
