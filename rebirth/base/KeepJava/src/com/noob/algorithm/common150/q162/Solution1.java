package com.noob.algorithm.common150.q162;

/**
 * 162 寻找峰值
 */
public class Solution1 {


    public int findPeakElement(int[] nums) {
        int len = nums.length;
        // 临界条件判断
        // 只有1个元素，元素本身就是峰值
        if (len == 0 || len == 1) {
            return 0;
        }
        // 有2个元素，返回比较大的元素索引
        if (len == 2) {
            return nums[0] < nums[1] ? 1 : 0;
        }
        // 首、尾节点判断
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }

        // 其他情况：循环遍历检索
        for (int i = 0; i < len - 2; i++) {
            int first = nums[i];
            int second = nums[i + 1];
            int third = nums[i + 2];
            // 判断当前序列是否为峰值
            if (second > first && second > third) {
                return i + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,1,3,5,6,4};
        int[] nums = new int[]{1, 2, 1};
        Solution1 solution1 = new Solution1();
        solution1.findPeakElement(nums);
    }
}
