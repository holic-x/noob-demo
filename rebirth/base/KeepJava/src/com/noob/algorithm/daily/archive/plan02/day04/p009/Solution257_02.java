package com.noob.algorithm.daily.archive.plan02.day04.p009;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 🟢 257.二叉树的所有路径 - https://leetcode.cn/problems/binary-tree-paths/
 */
public class Solution257_02 {

    /**
     * 思路分析：基于BFS遍历
     */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义结果集
        List<String> res = new ArrayList<>();

        // 定义队列记录节点
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        // 定义队列同步记录路径信息
        LinkedList<String> pathQueue = new LinkedList<>();
        pathQueue.offer(String.valueOf(root.val));

        // 遍历队列
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String curPath = pathQueue.poll();

            // 如果为叶子节点则需记录路径信息
            if (node.left == null && node.right == null) {
                res.add(new StringBuffer(curPath).toString());
            }

            // 载入节点，并同步更新路径信息
            if (node.left != null) {
                // 处理路径
                nodeQueue.offer(node.left);
                // 处理节点
                String newPath = curPath + "->" + node.left.val;
                pathQueue.offer(newPath);
            }
            if (node.right != null) {
                // 处理节点
                nodeQueue.offer(node.right);
                // 处理路径
                String newPath = curPath + "->" + node.right.val;
                pathQueue.offer(newPath);
            }
        }
        // 返回路径信息
        return res;
    }

}
