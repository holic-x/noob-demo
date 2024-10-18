package com.noob.algorithm.hot100.q041;

import java.util.Arrays;

/**
 * 041 缺失的第一个正数
 * 直接解析
 */
public class Solution1 {
    /**
     * 可以先对数组元素进行非正整数过滤（排除负数和0的判断）、排序：
     * 如果最小值不等于1，则直接返回1（1是最小的正整数）
     * 如果最小值等于1，则继续遍历数组，判断相邻的两个数之间步长是否为1，如果不为1则直接返回cur+1
     *   -（因为递增最小步长是1，如果相邻不是1的话说明中间有空位可以插入一个正整数，直接返回cur+1）
     *   - 如果数组遍历到末尾，说明相邻两个数之间没有空位，则返回最大值+1
     */
    public int firstMissingPositive(int[] nums) {
        int res = 0;

        // 过滤非正整数
        nums = Arrays.stream(nums).filter((a)->a>0).toArray();

        // 对数组元素进行排序
        Arrays.sort(nums);

        if(nums[0]!=1){
            return 1;
        }else{
            // 遍历数组元素，依次判断相邻两个数的步长是否为1，如果不为1则直接返回cur+1;
            for(int i=1;i<nums.length;i++){
                if(nums[i] - nums[i-1]!=1){
                   res = nums[i-1] + 1; // 相邻两个数步长判断
                }
            }
            // 如果res为初始值0，说明这个数不在1-N的范围内
            if(res==0){
                res = nums[nums.length-1] + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,0};
        System.out.println(new Solution1().firstMissingPositive(nums));
    }

}
