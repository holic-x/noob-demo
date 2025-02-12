package com.noob.algorithm.daily.archive.plan02.day01.p001;

/**
 * 🟢 977 有序数组的平方 - https://leetcode.cn/problems/squares-of-a-sorted-array/description/
 */
public class Solution977_02 {

    /**
     * 思路分析：数组本身按照非递减顺序排序，返回每个数字的平方组成的新数组
     * 双指针思路，定义两端指针进行相向遍历，绝对值较大的元素其平方和也较大，将绝对值较大的元素从后往前进行平方和填充
     * O（n）时间复杂度（需遍历整个数组），O（n）空间复杂度（需借助辅助集合存储结果集）
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        // 定义平方和结果集
        int[] res = new int[n];

        int left = 0, right = n - 1; // 定义双指针相向遍历，选择绝对值较大的元素进行填充（从后往前）
        int cur = n - 1; // 定义指针从后往前遍历填充平方和
        while (left <= right) {
            // 校验平方和
            if (Math.abs(nums[left]) > Math.abs(nums[right])) { // left 指向元素绝对值较大，优先选择填充
                res[cur] = nums[left] * nums[left];
                cur--;
                left++;
            } else {
                res[cur] = nums[right] * nums[right]; // right 指向元素绝对值较大，优先选择填充
                cur--;
                right--;
            }
        }

        // 返回结果集
        return res;
    }
}
