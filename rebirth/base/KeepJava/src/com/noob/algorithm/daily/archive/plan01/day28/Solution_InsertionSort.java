package com.noob.algorithm.daily.archive.plan01.day28;


import java.util.Arrays;

public class Solution_InsertionSort {

    // 插入排序
    public void insertionSort(int[] arr) {
        int n = arr.length;
        // 外层循环控制当前轮次（当前待插入元素位置i）
        for (int i = 1; i < n; i++) {
            // 记录当前待插入元素值(避免被直接覆盖)
            int curKey = arr[i];
            // 从[0,i-1]已排序的范围内从后往前寻找合适的插入位置
            int idx = i - 1; // 起始校验位置从i-1开始
            while (idx >= 0 && curKey < arr[idx]) {
                arr[idx + 1] = arr[idx]; // 当前idx指向元素较大，依次往后移动1位
                idx--;
            }
            // 更新插入位置
            arr[idx + 1] = curKey;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 15, 26, 27, 2, 36};
        Solution_InsertionSort solution = new Solution_InsertionSort();
        solution.insertionSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }


}
