package com.noob.algorithm.daily.plan01.day01;

/**
 * 🟢 704 二分查找
 * https://leetcode.cn/problems/binary-search/description/
 */
public class Solution704 {

    /**
     * 二分法思路（闭区间）[left,right]
     */
    public int search(int[] nums, int target) {
        // 定义起始区间指针
        int start = 0, end = nums.length - 1; // 闭区间[start,end] = [0,len-1]
        // 二分检索
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            }
        }
        // 返回结果
        return -1; // 如果元素不存在则返回-1，left表示返回下一个可插入的位置
    }
}
