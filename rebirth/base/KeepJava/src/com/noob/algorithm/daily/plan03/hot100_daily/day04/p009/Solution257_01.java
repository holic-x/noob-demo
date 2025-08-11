package com.noob.algorithm.daily.plan03.hot100_daily.day04.p009;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.*;

/**
 * 🟢 257.二叉树的所有路径 - https://leetcode.cn/problems/binary-tree-paths/
 */
public class Solution257_01 {

    /**
     * 思路分析：给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径
     */
    public List<String> binaryTreePaths(TreeNode root) {

        List<String> ans = new ArrayList<>();

        if (root == null) {
            return Collections.emptyList();
        }

        // BFS 双队列思路：节点队列、路径队列（可以用列表表示走到当前节点的路径）
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<String>> pathQueue = new LinkedList<>();
        // 初始化队列
        nodeQueue.offer(root);
        pathQueue.offer(Arrays.asList(String.valueOf(root.val)));

        // 遍历队列
        while (!nodeQueue.isEmpty()) {
            // 取出节点，验证节点路径
            TreeNode curNode = nodeQueue.poll();
            List<String> curPath = pathQueue.poll();

            // 校验当前节点是否为叶子节点，如果是则装配路径
            if (curNode.left == null && curNode.right == null) {
                ans.add(String.join("->", curPath));
            }

            // 处理当前节点的左右子节点
            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                List<String> newPath = new ArrayList<>(curPath);
                newPath.add(String.valueOf(curNode.left.val));
                pathQueue.offer(newPath);
            }

            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                List<String> newPath = new ArrayList<>(curPath);
                newPath.add(String.valueOf(curNode.right.val));
                pathQueue.offer(newPath);
            }

        }

        // 返回构建的结果
        return ans;
    }

}
