package com.noob.algorithm.daily.plan03.hot100_daily.day06.p014;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡216 组合总和III - https://leetcode.cn/problems/combination-sum-iii/description/
 */
public class Solution216_01 {


    /**
     * 思路分析：求[1,9]之间相加和为n的k个数，每个数字只能使用一次
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrack(k, n, 1);
        return ans;
    }


    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private int curPathSum = 0;

    private void backTrack(int k, int n, int idx) {
        // 递归出口
        if (path.size() == k) {
            // 校验路径和是否满足
            if (curPathSum == n) {
                ans.add(new ArrayList<>(path));
            }
        }

        // 回溯
        for (int i = idx; i <= 9; i++) {
            path.add(i);
            curPathSum += i;
            backTrack(k, n, i + 1);
            path.remove(path.size() - 1);
            curPathSum -= i;
        }

    }


}
