package com.noob.algorithm.daily.plan01.archive.day11;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;
import com.noob.algorithm.dmsxl.graph.Pair;

/**
 * 🟢222 完全二叉树的节点个数
 */
public class Solution222_03 {

    /**
     * 分类统计：对递归思路进行改造，利用完全二叉树的特性
     * 递归法：基于遍历的思路，计算节点值
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }

        // node 不为 null，判断当前节点的子节点是否为完全二叉树，如果是则直接通过公式计算法返回，如果不是则递归处理
        // 获取左子树深度
        int leftTreeDepth = 0;
        TreeNode curLeft = node.left;
        while (curLeft != null) {
            leftTreeDepth++;
            curLeft = curLeft.left;
        }

        // 获取右子树深度
        int rightTreeDepth = 0;
        TreeNode curRight = node.right;
        while (curRight != null) {
            rightTreeDepth++;
            curRight = curRight.right;
        }

        // 如果节点的左右子树深度一致则说明当前节点是一棵完全二叉树，直接通过公式计算返回节点个数
        if (leftTreeDepth == rightTreeDepth) {
            return (2 << leftTreeDepth) - 1;
        }

        // 如果当前节点不是一棵完全二叉树，则沿用递归方式计算节点个数
        int leftNodeCnt = dfs(node.left);
        int rightNodeCnt = dfs(node.right);
        return leftNodeCnt + rightNodeCnt + 1;
    }
}
