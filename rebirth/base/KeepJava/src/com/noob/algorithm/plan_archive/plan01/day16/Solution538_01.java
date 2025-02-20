package com.noob.algorithm.plan_archive.plan01.day16;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡538 将二叉搜索树转换为累加树
 */
public class Solution538_01 {

    int sum = 0;

    // 思路：反序中序遍历 + 累加值回填
    public TreeNode convertBST(TreeNode root) {
        dfsByRDL(root);
        return root;
    }


    public void dfsByRDL(TreeNode node) {
        if (node == null) {
            return;
        }

        // R
        dfsByRDL(node.right);

        // D(累加节点值并回填)
        sum += node.val;
        node.val = sum;

        // L
        dfsByRDL(node.left);
    }
}
