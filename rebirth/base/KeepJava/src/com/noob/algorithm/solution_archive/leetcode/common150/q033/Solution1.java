package com.noob.algorithm.solution_archive.leetcode.common150.q033;

import java.util.Arrays;

/**
 * 33 搜索旋转数组
 */
public class Solution1 {

    /**
     * 思路：找轴点+分段二分
     */
    public int search(int[] nums, int target) {

        int findPoint = getPoint(nums);
        if (findPoint == -1) {
            // 不存在轴点（无旋转），可以直接对整个数组进行二分
            return binarySearch(nums, target);
        }

        // 存在轴点的情况处理（分段二分）
        int[] leftNums = Arrays.copyOfRange(nums, 0, findPoint + 1);
        int[] rightNums = Arrays.copyOfRange(nums, findPoint + 1, nums.length);
        int leftSearch = binarySearch(leftNums, target);
        int rightSearch = binarySearch(rightNums, target);
        // 校验结果(数据只有存在与左侧或右侧)
        // 如果左侧结果存在则返回
        if (leftSearch != -1) {
            return leftSearch;
        }
        // 如果左侧结果不存在则返回右侧结果
        if (rightSearch != -1) {
            return rightSearch + leftNums.length;
        }
        return -1;
    }


    /**
     * 寻找轴点：判断数组是否旋转
     * 如果未旋转（完全升序）：返回-1（表示轴点不存在）
     * 如果存在旋转（存在降序）：返回轴点索引位置
     */
    public int getPoint(int[] nums) {
        // 判断数组边界
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return -1;
        }
        // 判断是否完全升序
        for (int i = 0; i < nums.length - 1; i++) { // 注意数组边界处理
            if (nums[i] > nums[i + 1]) {
                // 出现降序，则轴点位置存在
                return i;
            }
        }
        return -1;
    }

    // 二分法检索
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 判断target是否匹配
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 结果不存在
        return -1; // return left 表示下一个可插入的位置
    }

}
