package com.noob.algorithm.dmsxl.leetcode.q530;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 530 二叉搜索树的最小绝对差
 */
public class Solution2 {

    TreeNode preNode = null; // 记录当前遍历节点的上一个节点

    int minSubVal = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 中序遍历
        ldr(root);
        // 返回结果
        return minSubVal;
    }

    // 中序遍历（LDR）
    public void ldr(TreeNode node) {
        if (node == null) {
            return;
        }
        // 左
        ldr(node.left);

        // 中
        // 更新minSubVal（注意NPE处理）
        if (preNode != null) {
            minSubVal = Math.min(minSubVal, node.val - preNode.val);
        }

        // 更新preNode指针
        preNode = node; // 当前遍历节点会作为下一个节点的pre（其更新时机是跟着"中"这个步骤后面走）

        // 右
        ldr(node.right);
    }
}
