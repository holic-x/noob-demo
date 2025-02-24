package com.noob.algorithm.solution_archive.leetcode.hot100.q042;

/**
 * 42.接雨水
 */
public class Solution1 {

    /**
     * 思路:将接雨水转化为计算各个"桶"装的雨水之和
     * 思考每个桶的接水量如何计算：
     * l_max 表示当前桶的最大前缀（从前往后比较，包括其本身） 可以通过一次正序遍历获取
     * r_max 表示当前桶的最大后缀（从后往前比较，包括其本身） 可以通过一次倒序遍历获取
     * 当前桶的接水量为min(l_max[i],r_max[i])-height[i] 再次遍历计算结果
     */
    public int trap(int[] height) {
        // 定义返回结果
        int res = 0;

        // 存储数组长度值
        int len = height.length;

        // 1.正序遍历：计算每个桶的最大前缀（包括其本身）
        int[] l_max = new int[len];
        l_max[0] = height[0]; // 初始化第一个元素
        for (int i = 1; i < len; i++) {
            l_max[i] = Math.max(height[i], l_max[i - 1]); // 最大前缀：前一个元素存储了前[i-1]位置元素的最大前缀，此处将其和自身进行比较即可
        }

        // 2.倒序遍历：计算每个桶的最大后缀（包括其本身）
        int[] r_max = new int[len];
        r_max[len - 1] = height[len - 1]; // 从后往前初始化元素
        for (int i = len - 2; i >= 0; i--) {
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        }

        // 3.遍历数组：计算每个桶的接水量进行累加
        for (int i = 0; i < len; i++) {
            res += Math.min(l_max[i], r_max[i]) - height[i];
        }

        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] nums = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(solution1.trap(nums));
    }
}
