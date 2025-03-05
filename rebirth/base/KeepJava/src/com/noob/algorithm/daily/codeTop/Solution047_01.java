package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 🟡 047 全排列II - https://leetcode.cn/problems/permutations-ii/description/
 */
public class Solution047_01 {

    // 定义结果集合
    List<List<Integer>> res = new ArrayList<>(); // 结果集
    List<Integer> path = new ArrayList<>(); // 路径

    /**
     * 全排列：包含重复数字的序列，按照任意顺序返回所有不重复的全排列
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        backTrack(nums,0);
        return  res;
    }

    // 回溯处理
    private void backTrack(int[] nums, int idx) {
//        if(idx>nums.length){
//            return;
//        }
        // 当遍历到尾部记录结果集
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }

        // 定义集合做去重处理
        HashSet<Integer> set = new HashSet<>();

        // 回溯处理（固定位置交换）
        for (int i = idx; i < nums.length; i++) {
            if(!set.isEmpty() && set.contains(nums[i])){
                continue; // 重复，剪枝
            }
            set.add(nums[i]);

            path.add(nums[i]);
            swap(nums,i,idx); // 交换
            backTrack(nums,idx+1); // 递归处理
            path.remove(path.size()-1);
            swap(nums,i,idx); // 恢复现场
        }
    }

    // 定义交换方法
    private void swap(int[] nums,int x,int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
