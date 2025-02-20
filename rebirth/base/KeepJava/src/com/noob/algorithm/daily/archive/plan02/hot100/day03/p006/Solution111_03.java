package com.noob.algorithm.daily.archive.plan02.hot100.day03.p006;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 🟢 111 二叉树的最小深度 - https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 */
public class Solution111_03 {

    // 最小深度
    int minDepth = Integer.MAX_VALUE;
    // 定义路径
    List<TreeNode> path = new ArrayList<>();

    /**
     * 思路分析：最小深度（根节点到最近的叶子节点的最短路径上的节点数量）
     * 基于递归回溯的思路（最小深度，即求最短路径的节点个数，可基于回溯的思路处理）
     */
    public int minDepth(TreeNode root) {
        // 根节点载入路径
        path.add(root);
        // 调用回溯算法
        backTrack(root);
        // 返回最小深度
        return minDepth == Integer.MAX_VALUE ? 0 : minDepth;
    }

    // 回溯法
    private void backTrack(TreeNode node) {
        // 递归出口
        if (node == null) {
            return;
        }

        // 遍历到叶子节点
        if (node.left == null && node.right == null) {
            // 记录当前路径的最小值
            minDepth = Math.min(minDepth, path.size()); // 节点个数为当前路径的遍历节点
        }

        // 遍历选择（回溯处理：选择左、右子节点）
        path.add(node.left); // 选择左节点
        backTrack(node.left); // 递归处理
        path.remove(path.size() - 1); // 恢复现场

        path.add(node.right); // 选择右节点
        backTrack(node.right); // 递归处理
        path.remove(path.size() - 1); // 恢复现场
    }

}
