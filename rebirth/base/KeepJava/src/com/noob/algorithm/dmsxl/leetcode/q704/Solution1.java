package com.noob.algorithm.dmsxl.leetcode.q704;

/**
 * 704 二分查找
 */
public class Solution1 {

    /**
     * 二分法思路（开区间）
     */
    public int search(int[] nums, int target) {
        // 初始化左右指针
        int left = -1, right = nums.length; // todo 开区间

        // 遍历
        while (left + 1 < right) { // todo 开区间循环条件限制 (left从-1开始计数，此处要确保区间有效)
            // 获取mid指针
            int mid = left + (right - left) / 2;// 防止溢出（等价于(left+right)/2）
            if (nums[mid] < target) {
                // target在右侧区间，左侧缩边
                left = mid; // todo 开区间（区间更新：(mid,right））
            } else if (nums[mid] == target) {
                // 目标值存在，返回下标
                return mid;
            } else if (nums[mid] > target) {
                // target在左侧区间，右侧缩边
                right = mid; // todo 开区间（区间更新：(left,mid)）
            }
        }
        // 元素不存在返回-1
        return -1;
    }

    /**
     * 二分法思路（左闭右开区间）[left,right)
     */
    public int search2(int[] nums, int target) {
        // 初始化左右指针
        int left = 0, right = nums.length; // todo 左闭右开 (区间取值：[left,right))

        // 遍历
        while (left < right) { // todo 左闭右开循环条件限制（当left==right的情况下，此时[left,right) 会变成一个无效空间，因此此处取<小于）
            // 获取mid指针
            int mid = left + (right - left) / 2;// 防止溢出（等价于(left+right)/2）
            if (nums[mid] < target) {
                // target在右侧区间，左侧缩边
                left = mid + 1; // todo 左闭右开 （区间更新：[mid+1,right））
            } else if (nums[mid] == target) {
                // 目标值存在，返回下标
                return mid;
            } else if (nums[mid] > target) {
                // target在左侧区间，右侧缩边
                right = mid; // todo 左闭右开 （区间更新：[left,mid））
            }
        }
        // 元素不存在返回-1
        return -1;
    }

    /**
     * 二分法思路（闭区间）[left,right]
     */
    public int search1(int[] nums, int target) {
        // 初始化左右指针
        int left = 0, right = nums.length - 1; // todo 闭区间

        // 遍历
        while (left <= right) { // todo 闭区间循环条件限制
            // 获取mid指针
            int mid = left + (right - left) / 2; // 防止溢出（等价于(left+right)/2）
            if (nums[mid] < target) {
                // target在右侧区间，左侧缩边
                left = mid + 1; // todo 闭区间（区间更新：[mid+1,right]）
            } else if (nums[mid] == target) {
                // 目标值存在，返回下标
                return mid;
            } else if (nums[mid] > target) {
                // target在左侧区间，右侧缩边
                right = mid - 1; // todo 闭区间（区间更新：[left,mid-1]）
            }
        }
        // 元素不存在返回-1
        return -1;
    }

}
