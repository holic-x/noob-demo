package com.noob.algorithm.daily.archive.plan02.hot100.day06.p014;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡216 组合总和III - https://leetcode.cn/problems/combination-sum-iii/description/
 */
public class Solution216_01 {

    private List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    private List<Integer> path = new ArrayList<>(); // 定义当前路径
    private int curPathSum = 0; // 定义当前路径和（与path对照，为其元素之和）

    /**
     * 思路分析：求[1,9]之间相加和为n的k个数，每个数字只能使用一次
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        // 调用递归算法
        backTrack(1, n, k);
        // 返回结果
        return res;
    }

    // 回溯算法
    private void backTrack(int idx, int n, int k) {
        // 递归出口(可由for控制)
//        if(idx>=10){
//            return;
//        }
        // 结果处理
        if (path.size() == k) {
            if (curPathSum == n) {
                res.add(new ArrayList<>(new ArrayList<>(path)));
                return;
            }
        }

        // 回溯处理(从[idx,9]中选择1个数字)
        for (int i = idx; i <= 9; i++) {
            path.add(i);
            curPathSum += i;
            backTrack(i + 1, n, k); // 递归处理
            path.remove(path.size() - 1);
            curPathSum -= i;
        }
    }

}
