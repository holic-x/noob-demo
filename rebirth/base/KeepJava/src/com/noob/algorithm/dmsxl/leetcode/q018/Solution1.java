package com.noob.algorithm.dmsxl.leetcode.q018;

import com.noob.algorithm.hot100.q015.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 018 四数之和
 */
public class Solution1 {

    // 暴力法：四层循环
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 定义结果集
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        int curSum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (curSum == target) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[k]);
                            temp.add(nums[l]);
                            // 判断是否存在集合，不存在重复则补充
                            if (!res.contains(temp)) {
                                res.add(temp);
                            }
                        }
                    }
                }
            }
        }
        // 返回结果集合
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,2,2,2};
        Solution1 solution1 = new Solution1();
        solution1.fourSum(nums,8);
    }

}
