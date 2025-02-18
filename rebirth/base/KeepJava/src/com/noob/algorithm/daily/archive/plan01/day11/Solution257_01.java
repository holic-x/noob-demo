package com.noob.algorithm.daily.archive.plan01.day11;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟢 257 二叉树的所有路径
 * 路径输出格式：["1->2->5","1->3"]
 */
public class Solution257_01 {
    // BFS
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 调用BFS算法获取路径
        List<String> res = bfs(root);
        return res;
    }

    // BFS:层序遍历（双队列辅助遍历，一个用于维护节点，一个用于存储拼接的路径）
    public List<String> bfs(TreeNode node) {
        List<String> res = new ArrayList<>();

        // 构建双队列辅助遍历
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(node);
        Queue<String> pathQueue = new LinkedList<>();
        pathQueue.offer(new String(node.val + ""));

        // 遍历队列
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            String curPath = pathQueue.poll();

            // 遇到叶子节点，记录结果集
            if (cur.left == null && cur.right == null) {
                res.add(curPath);
            }

            // 遍历节点，更新路径
            if (cur.left != null) {
                nodeQueue.offer(cur.left);
                pathQueue.offer(curPath + "->" + cur.left.val);
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right);
                pathQueue.offer(curPath + "->" + cur.right.val);
            }
        }

        // 返回结果
        return res;
    }
}
