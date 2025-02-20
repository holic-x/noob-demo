package com.noob.algorithm.daily.archive.plan02.hot100.day09.p028;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟡 337 打家劫舍III - https://leetcode.cn/problems/house-robber-iii/description/
 */
public class Solution337_02 {
    /**
     * 思路分析：除了root之外，其他节点呈现父子相连，如果两个直接相连的房子同时被偷则会告警
     * 记忆化递推思路，不用每个节点都重复校验1次（即已经遍历的节点可以直接返回结果，而不需要重复递归计算）
     */
    public int rob(TreeNode root) {
        // 调用递归方案
        return dfs(root);
    }

    private Map<TreeNode, Integer> map = new HashMap<>(); // 存储已遍历节点的最大偷窃金额

    /**
     * 基于递归思路处理
     * 对于每个节点有两种状态，偷、不偷
     * ① 如果偷当前节点：则不偷其子节点（跳过其子节点，选择考虑其孙子节点的处理）
     * ② 如果不偷：则偷其子节点
     */
    private int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }

        // 记忆化递推：校验当前节点是否已经遍历过
        if (map.containsKey(node)) {
            return map.get(node); // 当前节点已经出现/遍历过，直接返回此前递归的结果
        }

        // 选择是否要偷该节点，分情况讨论
        // ① 偷该节点，则跳过其子节点，考虑孙子节点
        int val1 = node.val;
        if (node.left != null) {
            val1 += (dfs(node.left.left) + dfs(node.left.right));
        }
        if (node.right != null) {
            val1 += (dfs(node.right.left) + dfs(node.right.right));
        }

        // ② 不偷该节点，则考虑子节点
        int val2 = dfs(node.left) + dfs(node.right);

        // 记忆化递推：将当前节点结果载入已遍历节点
        int maxVal = Math.max(val1, val2);
        map.put(node, maxVal);

        // 返回两种方案的最大偷窃金额
        return maxVal;
    }
}
