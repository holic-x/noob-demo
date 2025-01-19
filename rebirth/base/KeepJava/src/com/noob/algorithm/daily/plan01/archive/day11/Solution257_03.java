package com.noob.algorithm.daily.plan01.archive.day11;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 257 二叉树的所有路径
 * 路径输出格式：["1->2->5","1->3"]
 */
public class Solution257_03 {

    public List<String> res = new ArrayList<>(); // 记录结果

    // DFS
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 初始化将root加入路径
        List<String> path = new ArrayList<>();
        // 调用BFS算法获取路径
        dfs(root, path);
        return res;
    }

    // DFS(回溯思路)
    public void dfs(TreeNode node, List<String> path) {
        if (node == null) {
            return;
        }

        path.add(String.valueOf(node.val)); // 处理当前节点

        // 遇到叶子结点则记录结果集
        if (node.left == null && node.right == null) {
            res.add(String.join("->", path));
        } else {
            // 递归处理处理左右节点
            dfs(node.left, new ArrayList<>(path));
            dfs(node.right, new ArrayList<>(path));
        }
    }
}
