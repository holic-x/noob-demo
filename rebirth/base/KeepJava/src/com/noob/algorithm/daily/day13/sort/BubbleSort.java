package com.noob.algorithm.daily.day13.sort;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.Arrays;

/**
 * 冒泡排序：从小到大排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -2, 5, 6};
        BubbleSort bs = new BubbleSort();
        bs.bubble_sort(nums);
        // 输出排序后的结果
        PrintUtil.print(nums);
    }

    /**
     * 排序：每次将较大的值排到后面（[待排序区间，已排序区间]）
     * 相邻两个数据两两进行比较，将较大的数交换到后面
     */
    public void bubble_sort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) { // 外层i确定比较轮次
            for (int j = 0; j < n - i - 1; j++) { // 内层j确定当前比较轮次比较位置
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

}
