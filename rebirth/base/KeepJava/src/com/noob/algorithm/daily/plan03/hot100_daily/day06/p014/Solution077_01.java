package com.noob.algorithm.daily.plan03.hot100_daily.day06.p014;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 077 组合 - https://leetcode.cn/problems/combinations/description/
 * - 返回[1,n]中所有可能的k个数的组合
 */
public class Solution077_01 {


    /**
     * 思路分析：给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合
     * 选择范围[1,n]内，递归出口k个数
     */
    public List<List<Integer>> combine(int n, int k) {
        backTrack(n, k, 0);
        return ans;
    }

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    private void backTrack(int n, int k, int idx) {
        // 校验当前路径是否满足k个数
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 回溯处理
        for (int i = idx; i <= n; i++) {
            path.add(i);
            backTrack(n, k, i + 1);
            path.remove(path.size() - 1);
        }

    }

}
