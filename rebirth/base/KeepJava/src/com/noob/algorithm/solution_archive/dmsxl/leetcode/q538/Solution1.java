package com.noob.algorithm.solution_archive.dmsxl.leetcode.q538;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 538 把二叉搜索树转换为累加树
 */
public class Solution1 {

    int sum = 0; // 全局变量记录累加值

    // 反序中序遍历（迭代法思路）
    public TreeNode convertBST(TreeNode root) {
        dfsByRDL(root);
        return root;
    }

    public void dfsByRDL(TreeNode node) {
        // 递归出口
        if (node == null) {
            return;
        }

        // R
        dfsByRDL(node.right);

        // D: 结果累加并回填数值
        sum = sum + node.val;
        node.val = sum; // 回填累加值

        // L
        dfsByRDL(node.left);
    }

}
