package com.noob.algorithm.solution_archive.dmsxl.leetcode.q077;

import java.util.ArrayList;
import java.util.List;

/**
 * 077 组合
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>(); // 定义全局结果集
    List<Integer> curPath = new ArrayList<>(); // 定义当前遍历路径的结果集

    public List<List<Integer>> combine(int n, int k) {
        // 调用回溯算法
        backTrack(n, k, 1); // 起始从1开始
        // 返回结果
        return res;
    }


    /**
     * 定义回溯算法
     *
     * @param n          n 个 元素
     * @param k          k 个 数 (k个数构成一个组合)
     * @param startIndex 当前遍历的元素起点位置（cur理解为startIndex更为准确）
     */
    public void backTrack(int n, int k, int startIndex) {
        // 递归出口:当当前节点路径节点个数满足k个则录入结果集
        if (curPath.size() == k) {
            // 处理结果（将当前路径加入到结果集）
            res.add(new ArrayList<>(curPath)); // 构建结果集需要new一个对象，避免引用影响
            return;
        }

        // 回溯过程
         for (int i = startIndex; i <= n; i++) { // 起始从1开始：[1,n]
//        for (int i = startIndex; i <= n - (k - curPath.size()) + 1; i++) { // 剪枝优化：只要确保startIdx的位置要满足可以构成k个元素的组合
            curPath.add(i);
            backTrack(n, k, i + 1); // 递归
            curPath.remove(curPath.size() - 1); // 复原现场
        }
    }


    public static void main(String[] args) {
        Solution1 solution1 = new  Solution1();
        solution1.combine(9,2);
        System.out.println(solution1.res);
    }
}
