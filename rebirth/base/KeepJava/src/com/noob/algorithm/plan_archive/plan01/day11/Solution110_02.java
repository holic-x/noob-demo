package com.noob.algorithm.plan_archive.plan01.day11;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 🟢110 平衡二叉树
 */
public class Solution110_02 {

    // DFS:平衡二叉树每个节点的左右子树高度差不超过1（超出1说明非平衡）
    public boolean isBalanced(TreeNode root) {
        int validRes = depth(root);
        return validRes != -1; // 如果得到-1则说明树不平衡，否则返回的是正确的树高度
    }

    /**
     * 基于平衡二叉树的特性：核心思路是校验每个节点的左右子树的高度差是否大于1
     * 1.递归遍历每个节点，判断每个节点的左右子树的高度差是否大于1
     * 2.定义计算节点高度方法（即求节点的最大深度）
     * - 实际上上述涉及到两个递归调用的过程，可以考虑将其整理为1个递归过程，也就是说递归计算子树高度的同时判断高度差
     * 如果高度差大于1，则直接返回-1（一个执行的标识）（将-1逐步递归往上抛），如果遍历发现存在-1的高度说明不平衡则直接返回false
     */
    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 计算左右子树的高度
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        // 校验左右子树的高度是否有效（如果出现-1，说明出现了不平衡，则继续将其直接抛出）
        if (leftDepth == -1 || rightDepth == -1) {
            return -1;
        }

        // 校验当前节点左右子树高度差是否大于1（如果大于1则返回-1，用于标记当前子树不平衡）
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }

        // 如果当前左右子树的高度有效，则继续返回正确的高度值
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
