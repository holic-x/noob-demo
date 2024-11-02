package com.noob.algorithm.common150.q035;

/**
 * 035 搜索插入位置
 */
public class Solution1 {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // 中间节点的索引位置
            // 判断target在哪个区间
            if (target <= nums[mid]) {
                // target在左区间，right缩边
                right = mid - 1;
            } else {
                // target在右区间，left缩边
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        Solution1 solution1 = new Solution1();
        solution1.searchInsert(nums,3);
    }
}
