package com.noob.algorithm.common150.q238;

import java.util.Arrays;

/**
 * 238 除自身以外的乘积
 */
public class Solution2 {
    // 左右乘积法：分别计算指定元素左侧和右侧的乘积，然后进行相乘
    public int[] productExceptSelf(int[] nums) {
        // 定义结果
        int[] res = new int[nums.length];

        // 左乘积
        int[] left = new int[nums.length]; // 存储当前元素的左侧乘积（除自身）
        Arrays.fill(left, 1);
        int[] right = new int[nums.length];// 存储当前元素的右侧乘积（除自身）
        Arrays.fill(right, 1);

        // 左侧乘积计算
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
            System.out.print(left[i] + "-");
        }
        System.out.println("-----------");
        // 右侧乘积计算
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
            System.out.print(right[i] + "-");
        }

        // 再次遍历，得到乘积（每个元素除自身的乘积=left*right）
        for (int i = 0; i < nums.length; i++) {
            res[i] = left[i] * right[i];
        }

        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        Solution2 solution = new Solution2();
        int[] res = solution.productExceptSelf(nums);
    }

}
