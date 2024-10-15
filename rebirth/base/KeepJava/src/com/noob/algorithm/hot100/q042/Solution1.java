package com.noob.algorithm.hot100.q042;

/**
 * 42.接雨水
 */
public class Solution1 {

    /**
     * 思路:将接雨水转化为计算各个"桶"装的雨水之和
     * 即每个桶能装多少水是由其对应后缀最大值-前缀最大值-高度得到的？？？？
     */
    public int trap(int[] height) {
        // 定义结果
        int res = 0;

        // 1.计算数组元素的最大前缀（正序）
        int[] prev = new int[height.length];
        prev[0] = height[0]; // 初始化第一个元素
        for (int i = 1; i < height.length; i++) {
            prev[i] = Math.max(prev[i - 1], height[i]);
        }
        print(prev);

        // 2.计算数组元素的最大后缀（逆序）
        int[] suffix = new int[height.length];
        suffix[height.length - 1] = height[height.length - 1]; // 初始化第一个元素
        for (int i = height.length - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }
        print(suffix);

        // 3.计算每个桶接的雨水
        for (int i = 0; i < height.length; i++) {
            res += Math.min(suffix[i], prev[i]) - height[i];
        }

        // 返回结果
        return res;
    }

    // 打印数组
    public static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] nums = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(solution1.trap(nums));
    }
}
