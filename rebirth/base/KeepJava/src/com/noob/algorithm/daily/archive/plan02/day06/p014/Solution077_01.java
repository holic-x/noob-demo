package com.noob.algorithm.daily.archive.plan02.day06.p014;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 077 组合 - https://leetcode.cn/problems/combinations/description/
 * - 返回[1,n]中所有可能的k个数的组合
 */
public class Solution077_01 {

    List<List<Integer>> res = new ArrayList<>(); // 结果集
    List<Integer> path = new ArrayList<>(); // 路径选择

    /**
     * 思路分析：回溯思路（[1,n]中选择数字，返回所有可能的k个数的组合）
     */
    public List<List<Integer>> combine(int n, int k) {
        // 调用回溯算法
        backTrack(1, n, k);
        // 返回结果
        return res;
    }


    // 回溯算法
    private void backTrack(int idx, int n, int k) {
        // 递归出口
//        if (idx > n) {
//            return;
//        }

        // 结果集载入
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
        }

        // 回溯处理:从[idx,n]范围内选择k个数字
        for (int i = idx; i <= n; i++) {
            path.add(i); // 选择数字
            backTrack(i + 1, n, k); // 递归
            path.remove(path.size() - 1); // 恢复现场
        }
    }
}
