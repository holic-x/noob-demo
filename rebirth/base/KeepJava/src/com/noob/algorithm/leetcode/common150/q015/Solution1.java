package com.noob.algorithm.leetcode.common150.q015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15 三数之和
 */
public class Solution1 {

    /**
     * 双指针思路：
     * 理解出现三元组存在的情况：[0 0 0] [正 负相加]
     * 因此可以先对数组元素进行排序，然后循环遍历数组每个元素，并相应定义双指针来找这个三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 定义结果集存储
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        // 对数组进行排序
        Arrays.sort(nums);

        // 循环遍历数组元素（从第1个元素开始，数组中的每个元素都可能称为三元组最左边那个数，例如[-1,0,1]）:[cur,left,right]
        for(int i=0;i<nums.length-2;i++) { // i 从第1个元素开始到倒数第二个元素结束
            // 定义双指针，进行遍历
            int left = i+1;
            int right = nums.length-1;
            while(left<right) {
                // 判断此时的三元组是否满足
                int sum = nums[i] + nums[left] + nums[right];
                if(sum==0) {
                    // res.add(Arrays.asList(nums[i], nums[left], nums[right])); 需进行去重
                    List<Integer> target = Arrays.asList(nums[i], nums[left], nums[right]);
                    if(!res.contains(target)) {
                        res.add(target);
                    }

                    // 找到满足的三元组，指针继续靠拢
                    left++;
                    right--;
                }else if(sum<0) {
                    // 如果sum比较小，则应该移动指针让sum变大
                    left++;
                }else if(sum>0){
                    // 如果sum比较大，则应该移动指针让sum变小
                    right--;
                }
            }
        }

        // 返回结果
        return res;

    }
}
