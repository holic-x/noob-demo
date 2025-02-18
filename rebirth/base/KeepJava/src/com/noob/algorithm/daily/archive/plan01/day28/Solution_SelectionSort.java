package com.noob.algorithm.daily.archive.plan01.day28;

import java.util.Arrays;

/**
 * 选择排序
 */
public class Solution_SelectionSort {

    /**
     * 选择排序：从前往后依次固定（选出当前剩余序列中的min/max）
     */
    public void selectionSort1(int[] nums) {

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }


    /**
     * 选择排序：从前往后依次固定（选出当前剩余序列中的min/max）
     */
    public void selectionSort2(int[] nums) {

        int n = nums.length;

        int maxIdx = -1; // 记录每一轮比较的最值索引
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    // 记录最值索引
                    maxIdx = j;
                }
            }
            // 一轮比较结束，交换当前i位置与最值索引指向位置的元素
            int temp = nums[i];
            nums[i] = nums[maxIdx];
            nums[maxIdx] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 5, 15, 26, 27, 2, 36};
        Solution_SelectionSort solution = new Solution_SelectionSort();
        solution.selectionSort1(arr1);
        Arrays.stream(arr1).forEach(System.out::println);

        System.out.println("-----------");

        int[] arr2 = {3, 5, 15, 26, 27, 2, 36};
        solution.selectionSort2(arr2);
        Arrays.stream(arr2).forEach(System.out::println);
    }

}
