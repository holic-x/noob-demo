package com.noob.algorithm.plan01.day10;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 111 二叉树的最小深度
 */
public class Solution111_03 {

    // DFS
    public int minDepth(TreeNode node) {
        return dfs(node);
    }

    public int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }

        // 根据node的左右节点是否为null分情况讨论
        TreeNode L = node.left, R = node.right;

        // ① 如果左右节点都为null，到了叶子节点返回1
        if (L == null && R == null) {
            return 1;
        }
        // ② 如果左、右节点有一个为null，则需要选择不为null的节点继续递归遍历深度
        if ((L == null && R != null) || (L != null && R == null)) {
            // return Math.max(minDepth(L), minDepth(R)) + 1;
            return minDepth(L) + minDepth(R) + 1; // 也可以是 minDepth(L) + minDepth(R) + 1，即l+r+1（因为这种情况下l、r中肯定有一个为0）
        }

        // ③ 如果左右节点均不为null，则递归选择两者中较小的深度返回
        return Math.min(dfs(node.left), dfs(node.right)) + 1;
    }

}
