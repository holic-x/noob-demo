package com.noob.algorithm.daily.plan03.hot100_daily.day04.p009;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.*;

/**
 * 🟢 257.二叉树的所有路径 - https://leetcode.cn/problems/binary-tree-paths/
 */
public class Solution257_02 {

    List<String> ans = new ArrayList<>();
    List<String> path = new ArrayList<>();

    /**
     * 思路分析：给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径
     */
    public List<String> binaryTreePaths(TreeNode root) {

        dfs(root);

        // 返回构建的结果
        return ans;
    }


    // 递归：DFS（前序遍历思路）求路径
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 处理节点
        path.add(String.valueOf(node.val));

        // 校验结果
        if (node.left == null && node.right == null) {
            ans.add(String.join("->", path));
        } else {
            // 处理左右子节点
            dfs(node.left);
            dfs(node.right);
        }

        // 回溯
        path.remove(path.size() - 1);
    }
}
