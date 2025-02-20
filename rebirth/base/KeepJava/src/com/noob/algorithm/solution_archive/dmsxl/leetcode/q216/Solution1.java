package com.noob.algorithm.solution_archive.dmsxl.leetcode.q216;

import java.util.ArrayList;
import java.util.List;

/**
 * 216 组合总和 III
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>(); // 定义结果集
    List<Integer> curPath = new ArrayList<>(); // 定义当前路径
    int curPathSum = 0; // 定义当前路径节点之和（和curPath相匹配）

    public List<List<Integer>> combinationSum3(int k, int n) {
        // 调用回溯函数
        backTrack(k, n, 1);
        // 返回结果
        return res;
    }

    // 定义回溯函数
    public void backTrack(int k, int n, int startIdx) {
        // 回溯出口：curPath中k个节点累加和为n
        if (curPath.size() == k && curPathSum == n) {
            // 处理结果
            res.add(new ArrayList<>(curPath));
            return;
        }

        // 回溯过程
        for (int i = startIdx; i <= 9; i++) { // 只能使用[1,9]的数字

            // 1.处理节点
            curPath.add(i);
            curPathSum += i;
            // 2.递归
            backTrack(k, n, i + 1); // 注意此处递归是取当前遍历元素的下一个（i+1）
            // 3.复原现场
            curPath.remove(curPath.size() - 1);
            curPathSum -= i;
        }
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.combinationSum3(3, 7);
        System.out.println(solution1.res);
    }


}
