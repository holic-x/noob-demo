package com.noob.algorithm.plan_archive.plan01.day27;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡046 全排列 - https://leetcode.cn/problems/permutations/description/
 */
public class Solution046_01 {

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> curPath = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 调用回溯算法
        backTrack(nums,0);
        // 返回结果
        return res;
    }


    /**
     * 定义回溯算法: 回溯核心（固定一位，随后递归进行交换）
     */
    private void backTrack(int[] nums, int idx) {
        // 如果到最后一个位置则记录结果
        if (idx >= nums.length) {
            res.add(new ArrayList<>(curPath));
            return;
        }

        // 回溯处理
        for (int i = idx; i < nums.length; i++) {
            // 选择交换位置并载入元素
            swap(nums, i, idx);
            curPath.add(nums[idx]);
            // 回溯
            backTrack(nums, idx + 1);
            // 复原现场
            swap(nums, i, idx);
            curPath.remove(curPath.size() - 1);
        }
    }

    // 定义交换算法(交换i、j位置的数组元素)
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Solution046_01 solution = new Solution046_01();
        List<List<Integer>> res = solution.permute(nums);
        PrintUtil.printGraphTable(res);
    }

}
