package com.noob.algorithm.plan_archive.plan02.hot100.day13.sort;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

/**
 * 排序算法核心思路：快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        // int[] nums = new int[]{1, 3, -2, 5, 6};
        int[] nums = new int[]{1, 6, 3, 2, 5, 4};


        // 执行排序算法
        QuickSort sort = new QuickSort();
        sort.quick_sort(nums);

        // 输出排序后的结果
        PrintUtil.print(nums);
    }


    // 排序算法：快速排序
    private void quick_sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }


    // 快速排序核心算法
    private void quickSort(int[] nums, int left, int right) {
        // 递归出口(当待排序的子数组长度等于1的时候退出)
        if (left >= right) {
            return;
        }

        // ① 选主（多种选主方式：选第1个、选最后1个、选中间等方式）、分区
        int pivot = partition(nums, left, right);

        // ② 递归处理
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    // 选主：选择基准，分区：基于选出的pivot，将比pivot小的元素放在其左边、将比pivot大的元素放在其右边
    private int partition(int[] nums, int left, int right) {
        // 选择基准值（初始化），分区区间：[left,right]
        int base = nums[left]; // 选择第1个元素作为分区标准
        int idx = left; // idx 指向当前分区的起始位置（用于寻找基准的真正位置）
        // 从区间的第2个元素开始遍历
        for (int i = left + 1; i <= right; i++) {
            // 如果遇到比基准值大的数则跳过，如果遇到比基准值小的数则将大值交换到后面
            if (nums[i] <= base) {
                idx++;
                swap(nums, i, idx);
            }
        }
        // 上述遍历完成，将比较元素交换到期望位置
        swap(nums, left, idx);
        // 返回基准值索引位置
        return idx;
    }

    // 交换指定位置的数组元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
