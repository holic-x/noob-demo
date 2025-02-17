package com.noob.algorithm.daily.archive.plan02.day06.p017;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 046 全排列 - https://leetcode.cn/problems/permutations/description/
 */
public class Solution046_02 {

    private List<List<Integer>> res = new ArrayList<>(); // 定义结果集合
    private List<Integer> path = new ArrayList<>(); // 定义路径

    public List<List<Integer>> permute(int[] nums) {
        // 调用回溯算法
        backTrack( nums);
        // 返回结果
        return res;
    }

    // 定义回溯算法
    private void backTrack(int[] nums) {
        // 递归出口(结果集处理)
        if (path.size() == nums.length) {
            // 全排列，载入结果集合
            res.add(new ArrayList<>(path));
        }

        // 选择列表处理(idx指向位置为当前选择位置，将其依次与i位置进行交换获得新的排列组合)
        for (int i = 0; i < nums.length; i++) {
            if(path.contains(nums[i])){
                continue; // 如果是已经出现过的元素则跳过
            }
            path.add(nums[i]);
            backTrack(nums);
            path.remove(path.size()-1);
        }
    }
}
