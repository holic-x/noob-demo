package com.noob.algorithm.common150.q167;

/**
 * 167 两数之和 II
 */
public class Solution1 {
    // 暴力法：双层循环
    public int[] twoSum(int[] numbers, int target) {
        // 嵌套循环遍历查找，元素不可重复使用
        for(int i = 0; i < numbers.length - 1; i++) {
            for(int j=i+1; j < numbers.length ; j++) {
                if(numbers[i] + numbers[j] == target) {
                    return new int[]{i+1,j+1}; // 坐标从1开始，因此此处返回的索引+1
                }
            }
        }
        return null;
    }
}
