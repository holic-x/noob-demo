package com.noob.algorithm.solution_archive.leetcode.common150.q167;

import java.util.HashSet;

/**
 * 167 两数之和 II
 */
public class Solution2 {

    /**
     * 双指针概念：数组本身有序，因此可以利用双指针分别从头、尾相向遍历，逐步靠拢，寻找target
     */
    public int[] twoSum(int[] numbers, int target) {
        // 定义双指针
        int left = 0, right = numbers.length - 1;

        // 指针相遇遍历结束
        while(left < right) {
            // 获取当前两个指针指向元素之和
            int sum = numbers[left] + numbers[right];
            if(sum==target){
                // 找到target，直接返回
                return new int[]{left+1, right+1}; // 数组从1开始，此处返回索引+1
            }else if(sum>target){
                // 如果sum比target大则说明要移动指针让sum变小
                right--;
            }else if(sum<target){
                // 如果sum比target小则说明要移动指针让sum变大
                left++;
            }
        }

        return new int[]{-1,-1};
    }


}
