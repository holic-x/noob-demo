package com.noob.algorithm.solution_archive.dmsxl.leetcode.q047;

import java.util.*;

/**
 * 047 全排列II
 */
public class Solution2 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> curPath = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            curPath.add(nums[i]);
        }
        // 调用回溯方法
        backTrack(curPath, 0);
        // 返回结果
        return res;
    }

    // 回溯算法
    public void backTrack(List<Integer> nums, int idx) {
        if (idx == nums.size() - 1) { // idx 遍历到元素的最后一个元素，后面没有元素可以交换了，加载结果集
            res.add(new ArrayList<>(new ArrayList<>(nums)));
            return;
        }

        HashSet<Integer> set = new HashSet<>();

        // 回溯过程
        for (int i = idx; i < nums.size(); i++) {

            // 去重处理
            if(!set.isEmpty()&&set.contains(nums.get(i))){
                continue; // 重复，剪枝
            }
            set.add(nums.get(i));

            // 交换元素
            Collections.swap(nums, idx, i);
            // 递归
            backTrack(nums, idx + 1);
            // 复原现场
            Collections.swap(nums, i, idx);
        }
    }

}
