package com.noob.algorithm.daily.search;

/**
 * 顺序查找
 */
public class SequentialSearch {

    // 顺序查找
    public static int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        // 没有找到
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 9, 6, 7, 8, 6};
        int value = search(arr, 9);
        System.out.println(value);
    }

}
