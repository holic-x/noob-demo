package com.noob.algorithm.leetcode.q226;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 226.反转二叉树
 */
public class Solution1 {


    // 层序遍历反转（相当于每次遍历节点的时候就将其左右节点进行交换）
    public TreeNode invertTree(TreeNode root) {

        // 空节点判断
        if (root == null) {
            return null;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 遍历队列
        while (!queue.isEmpty()) {
            // 依次出队反转
            TreeNode node = queue.poll();
            // 将当前节点的左右节点进行反转
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // 如果存在左右节点则继续入队
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // 返回遍历、反转后的节点信息
        return root;
    }


    // 递归方式反转二叉树
    public TreeNode invertTree01(TreeNode root) {

        // 空节点不做处理
        if (root == null) {
            return null;
        }

        // 交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归交换左右子树的子节点
        invertTree01(root.left);
        invertTree01(root.right);

        // 返回响应结果
        return root;
    }

}

/* 二叉树节点类 */
class TreeNode {
    int val;         // 节点值
    TreeNode left;   // 左子节点引用
    TreeNode right;  // 右子节点引用

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
