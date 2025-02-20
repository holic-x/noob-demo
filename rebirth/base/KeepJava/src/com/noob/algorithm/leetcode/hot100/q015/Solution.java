package com.noob.algorithm.leetcode.hot100.q015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组
 * 答案中不可包括重复的三元组
 */
public class Solution {

    /**
     * 思路：排序+双指针
     * 先理解三元组的定义：要么全部为0，要么整数+负数组成0，因此可以考虑先对数组元素进行排序，然后利用两头的双指针来找三元组
     * 循环遍历每个数组元素，然后定义双指针分别从两头向中间缩，直到找到满足的三元组或者指针相遇则本次循环结束
     * 1.对数组进行排序
     * 2.循环遍历数组中的每个元素，然后通过双指针去定位每一个可能的双指针
     * 3.循环过程中对结果去重
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 定义响应结果
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        // 对数组进行排序(默认升序)
        Arrays.sort(nums);

        // 循环遍历每个元素，并在这个过程中借助双指针来定位三元组
        for(int i=0;i<nums.length-1;i++){
            // 定义双指针
            int left = i+1,right = nums.length-1;
            // 内层循环：寻找三元组
            while(left<right){
                int cur= nums[left]+nums[right]+nums[i];
                if(cur==0){
                    // 满足三元组条件，加入结果集（需去重操作）
                    List<Integer> target = Arrays.asList(nums[i],nums[left],nums[right]);
                    if(!res.contains(target)){
                        res.add(target);
                    }

                    // 找到一组三元组，指针继续移动看有没有其他的三元组
                    left++;
                    right--;
                }
                // 指针移动（需考虑指针移动的条件：和0比较）
                if(cur>0){
                    // cur大于0，说明当前值太大了，要稍微变小，则移动右指针（因为右侧是大数，往左移数会变小）
                    right--;
                }else if(cur<0){
                    // cur小于0，说明当前值太小了，要稍微变大，则移动左指针（因为左侧是小数，往右移数会变大）
                    left++;
                }
            }
        }
        // 最终返回结果集
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Solution solution = new Solution();
        System.out.println(solution.threeSum(nums));
    }

}
