package com.noob.algorithm.leetcode.q543;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 543.二叉树直径
 */
public class Solution1 {

    // 定义直径
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return diameter;
    }

    // 定义计算树的最大深度方法
    public int depth(TreeNode node){
        if(node == null){
            return 0;
        }
        // 计算左子树
        int leftDepth = depth(node.left);
        // 计算右子树深度
        int rightDepth = depth(node.right);
        // 更新最大值
        diameter = Math.max(diameter, leftDepth + rightDepth);
        // 返回最大深度
        return Math.max(leftDepth, rightDepth) + 1;
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
