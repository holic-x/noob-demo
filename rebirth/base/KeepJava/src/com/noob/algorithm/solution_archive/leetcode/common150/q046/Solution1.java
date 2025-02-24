package com.noob.algorithm.solution_archive.leetcode.common150.q046;

import java.util.*;

/**
 * 046 全排列
 */
class Solution1 {

    // 定义返回结果
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    // 暴力思路：多层嵌套
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num: nums){
            list.add(num);
        }
        // 调用方法进行递归
        dfs(list,0);
        // 返回结果
        return res;
    }

    // 定义dfs遍历方式
    public void dfs(List<Integer> nums, int idx) {
        // 递归出口
        if(idx==nums.toArray().length-1){
            res.add(new ArrayList<>(nums));
            return;
        }

        // 递归求解(idx表示固定的位置索引)
        for(int i = idx; i<nums.size();i++){
            Collections.swap(nums,i,idx);
            dfs(nums,idx+1); // 递归下一个位置
            Collections.swap(nums,idx,i); // 复原现场
        }
    }
}