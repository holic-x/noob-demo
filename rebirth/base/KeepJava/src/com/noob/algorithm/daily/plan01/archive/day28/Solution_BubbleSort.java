package com.noob.algorithm.daily.plan01.archive.day28;

import java.util.Arrays;

public class Solution_BubbleSort {

    /**
     * 冒泡排序: 从头到尾依次两两进行比较，将较小/较大的元素置换到后面（相当于从后往前依次固定min、max）
     */
    public void bubbleSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) { // 连续比较两个元素，因此此处i取值为[0,n-1]
            for (int j = 0; j < n - i - 1; j++) { // 每次从后往前固定1位，因此已固定的位置可以不用重复比较（取决于i的位置）
                // 比较相邻两数的值，按照排序顺序进行处理(此处是从小到大排序，因此要将大数交换到后面)
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 15, 26, 27, 2, 36};
        Solution_BubbleSort solution = new Solution_BubbleSort();
        solution.bubbleSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

}
