package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 912 排序数组 - https://leetcode.cn/problems/sort-an-array/description/
 */
public class Solution912_01 {

    /**
     * 快速排序：
     * 1.选择基准 pivot
     * 2.将比它小的元素放在其左侧、比它大的元素放在其右侧
     * 3.递归处理区间
     */
    public int[] sortArray(int[] nums) {
        sortHelper(nums, 0, nums.length - 1);
        // 返回结果
        return nums;
    }

    public void sortHelper(int[] nums, int left, int right) {
        // 递归出口
        if (left >= right) {
            return;
        }

        // 哨兵划分
        int pivotIdx = partition(nums, left, right);
        // 处理左右区间
        sortHelper(nums, left, pivotIdx - 1);
        sortHelper(nums, pivotIdx + 1, right);
    }

    // 排序
    private int partition(int[] nums, int left, int right) {
        // 选择基准（此处选择左边第1个元素）
        int pivot = nums[left];
        int pt = left;
        // 将比它小的元素放在其左侧、比它大的元素放在其右侧
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] <= pivot) {
                // 如果找到了比pivot小的元素，则将其与前面较大的元素交换位置
                pt++;
                // 交换元素位置
                swap(nums, pt, i);
            }
        }
        // 最后将pt指向位置与pivot交换
        swap(nums, left, pt);
        // 返回基准的索引位置
        return pt;
    }

    // 交换指定位置元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
