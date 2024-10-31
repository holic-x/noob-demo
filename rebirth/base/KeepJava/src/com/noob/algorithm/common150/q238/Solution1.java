package com.noob.algorithm.common150.q238;

import java.util.Arrays;

/**
 * 238 除自身以外的乘积
 */
public class Solution1 {
    // 双层循环（暴力法）：依次求除自身之外的乘积
    public int[] productExceptSelf(int[] nums) {
        // 定义结果
        int[] res = new int[nums.length];
        // 初始化res(1乘以任何数都为其本身)
        Arrays.fill(res, 1); // 填充所有元素都为1
        // 循环遍历计算乘积
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(i!=j){
                    res[i]*=nums[j];
                }
            }
        }
        // 返回结果
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] res = new Solution1().productExceptSelf(nums);
        System.out.println(Arrays.toString(res));
    }

}
