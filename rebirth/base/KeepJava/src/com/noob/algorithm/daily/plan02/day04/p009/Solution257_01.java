package com.noob.algorithm.daily.plan02.day04.p009;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 257.二叉树的所有路径 - https://leetcode.cn/problems/binary-tree-paths/
 */
public class Solution257_01 {

    private List<String> res = new ArrayList<>(); // 结果集定义
    private List<Integer> path = new ArrayList<>(); // 路径定义

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        path.add(root.val); // 初始化载入根节点
        // 调用方法
        backTrack(root);
        // 返回结果
        return res;
    }

    // 回溯思路获取所有路径
    private void backTrack(TreeNode node) {
        // 递归出口
        if (node == null) {
            return;
        }
        // 遍历到叶子节点则记录路径
        if (node.left == null && node.right == null) {
            res.add(printPath(new ArrayList<>(path)));
        }

        // 回溯过程（分别递归左、右子节点）
        if (node.left != null) {
            path.add(node.left.val); // 加入左子节点
            backTrack(node.left); // 递归
            path.remove(path.size() - 1); // 恢复现场
        }

        if (node.right != null) {
            path.add(node.right.val); // 加入右子节点
            backTrack(node.right); // 递归
            path.remove(path.size() - 1); // 恢复现场
        }
    }

    // 打印路径信息（转化为字符串）
    private String printPath(List<Integer> path) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i != path.size() - 1) {
                sb.append("->");
            }
        }
        return sb.toString();
    }
}
