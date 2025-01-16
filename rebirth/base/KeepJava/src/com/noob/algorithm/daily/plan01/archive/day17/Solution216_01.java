package com.noob.algorithm.daily.plan01.archive.day17;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 216 组合III
 */
public class Solution216_01 {

    public List<List<Integer>> res = new ArrayList<>(); // 记录结果集
    public List<Integer> curPath = new ArrayList<>(); // 记录组合路径
    public int curPathSum = 0; // 同步记录当前组合路径数字之和

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrack(n, k, 1);
        return res;
    }

    // 定义回溯算法
    public void backTrack(int n, int k, int idx) {
        // 递归出口
        if (curPath.size() == k && curPathSum == n) {
            res.add(new ArrayList<>(curPath));
            return;
        }

        // 组合：每个数字只能用一次（不可重复），只能使用数字1-9
        for (int i = idx; i <= 9; i++) {
            curPath.add(i);
            curPathSum += i;
            backTrack(n, k, i + 1);
            curPath.remove(curPath.size() - 1);
            curPathSum -= i;
        }
    }

}
