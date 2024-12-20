package com.noob.algorithm.plan01.day11;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟢 257 二叉树的所有路径
 * 路径输出格式：["1->2->5","1->3"]
 */
public class Solution257_02 {

    public List<String> res = new ArrayList<>(); // 记录结果
    public List<String> path = new ArrayList<>(); // 记录遍历路径(处理为节点)

    // DFS
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 初始化将root加入路径
        path.add(String.valueOf(root.val));
        // 调用BFS算法获取路径
        dfs(root);
        return res;
    }

    // DFS(回溯思路)
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        // 遇到叶子结点则记录结果集
        if (node.left == null && node.right == null) {
            res.add(String.join("->", path));
        }
        // 处理左右节点
        if (node.left != null) {
            path.add(String.valueOf(node.left.val));
            dfs(node.left);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            path.add(String.valueOf(node.right.val));
            dfs(node.right);
            path.remove(path.size() - 1);
        }
    }
}
