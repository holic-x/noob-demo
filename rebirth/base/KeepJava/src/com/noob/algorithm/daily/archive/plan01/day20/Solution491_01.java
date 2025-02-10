package com.noob.algorithm.daily.archive.plan01.day20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 🟡 491 非递减子序列
 */
public class Solution491_01 {

    public List<List<Integer>> res = new ArrayList<>(); // 记录结果集
    public List<Integer> curPath = new ArrayList<>(); // 定义当前路径

    public List<List<Integer>> findSubsequences(int[] nums) {
        // 调用回溯算法
        backTrack(nums, 0);
        // 返回结果
        return res;
    }

    public void backTrack(int[] nums, int index) {
        // 记录结果集（满足条件则加载结果集，递增子序列，2个数以上）
        if (curPath.size() >= 2) {
            res.add(new ArrayList<>(curPath));
        }

        HashSet<Integer> hs = new HashSet<>();

        for (int i = index; i < nums.length; i++) {
            // 判断是否满足递增条件、去重
            if (!curPath.isEmpty() && curPath.get(curPath.size() - 1) > nums[i] || hs.contains(nums[i])) {
                continue; // 跳过
            }
            hs.add(nums[i]);

            curPath.add(nums[i]); // 处理节点
            backTrack(nums, i + 1); // 递归
            curPath.remove(curPath.size() - 1); // 复原现场
        }
    }


}
