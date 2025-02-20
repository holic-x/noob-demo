package com.noob.algorithm.plan_archive.plan01.day17;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 077 组合
 */
public class Solution077_01 {

    public List<List<Integer>> res = new ArrayList<>(); // 记录组合结果
    public List<Integer> curPath = new ArrayList<>(); // 记录当前路径

    // 组合：非重复
    public List<List<Integer>> combine(int n, int k) {
        // 调用回溯算法
        backTrack(1, n, k);
        // 返回结果集
        return res;
    }

    // 回溯法
    public void backTrack(int idx, int n, int k) {
        // 当选择了k个数则添加集合
        if (curPath.size() == k) {
            res.add(new ArrayList<>(curPath));
            return;
        }

        // 回溯处理
        for (int i = idx; i <= n; i++) { // 选择列表
            curPath.add(i); // 选择
            backTrack(i + 1, n, k); // 回溯
            curPath.remove(curPath.size() - 1); // 恢复现场
        }
    }
}
