package com.noob.algorithm.solution_archive.dmsxl.leetcode.q257;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 257 二叉树的所有路径
 */
public class Solution2 {

    /**
     * 层序遍历思路：nodeQueue 节点队列（用于辅助节点遍历）、pathQueue 路径队列（记录当前已遍历节点的路径，与nodeQueue对照）
     * 遍历每一层节点时，将当前节点与上一层节点的序列进行拼接，然后入队
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // root 为null 判断
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义路径结果集合
        List<String> res = new ArrayList<>();

        // 定义nodeQueue节点队列（用于辅助节点遍历）
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        // 定义pathQueue路径队列（对应记录节点的路径信息，与nodeQueue对照）
        Deque<String> pathQueue = new LinkedList<>();
        pathQueue.offer(String.valueOf(root.val));

        // 遍历节点队列，同步更新路径信息
        while (!nodeQueue.isEmpty()) {
            // 同步取出节点
            TreeNode cur = nodeQueue.poll();
            String curPath = pathQueue.poll();

            // 遇到叶子节点则更新结果
            if (cur.left == null && cur.right == null) {
                res.add(curPath);
            }

            // 非叶子节点则继续遍历并同步更新路径
            if (cur.left != null) {
                nodeQueue.offer(cur.left);
                pathQueue.offer(curPath + "->" + cur.left);
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right);
                pathQueue.offer(curPath + "->" + cur.right);
            }
        }
        // 返回结果集
        return res;
    }
}
