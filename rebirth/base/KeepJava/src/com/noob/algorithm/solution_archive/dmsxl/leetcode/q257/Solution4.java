package com.noob.algorithm.solution_archive.dmsxl.leetcode.q257;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257 二叉树的所有路径
 */
public class Solution4 {

    /**
     * DFS 思路：类似地，遍历节点的同时 同步 更新路径信息
     */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 定义结果集，记录所有路径
        List<String> res = new ArrayList<>();
        dfs(root, res, "");
        return res;
    }

    // DFS (DLR)
    public void dfs(TreeNode node, List<String> res, String curPath) {
        // 递归出口
        if (node == null) {
            return;
        }

        // node 不为null，记录路径信息并继续递归遍历
        curPath = curPath + node.val;

        // 如果左、右子节点都为null，说明是叶子节点，同步更新路径信息
        if (node.left == null && node.right == null) {
            res.add(String.join("->", curPath));
        } else {
            curPath = curPath + "->"; // 分隔符
            // 如果子节点不为null，继续递归遍历子节点
            dfs(node.left, res, curPath);
            dfs(node.right, res, curPath);
        }
    }
}
