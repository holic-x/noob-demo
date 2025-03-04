package com.noob.algorithm.plan_archive.plan02.hot100.day13.sort;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

/**
 * 插入排序：
 */
public class InsertionSort {

    public static void main(String[] args) {
        // int[] nums = new int[]{1, 3, -2, 5, 6};
        int[] nums = new int[]{1, 6, 3, 2, 5, 4};

        InsertionSort is = new InsertionSort();
        is.insertion_sort(nums);

        // 输出排序后的结果
        PrintUtil.print(nums);
    }


    /**
     * 排序算法：从前往后进行固定，从已排序区间[0,i-1]从后往前寻找可插入的位置
     */
    private void insertion_sort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int curKey = nums[i]; // 待插入元素
            int idx = i - 1; // 待插入位置（从[0,i-1]区间从后往前寻找可插入位置）
            while (idx >= 0 && nums[idx] > curKey) {
                nums[idx + 1] = nums[idx]; // 将arr[idx]向右移动一位
                idx--; // 相当于从后往前找到第1个比curKey(待插入元素)小的位置
            }
            nums[idx + 1] = curKey; // 找到该位置，执行插入操作（相当于插入到idx后面）
        }
    }


}
