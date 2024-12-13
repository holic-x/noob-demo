package com.noob.algorithm.dmsxl.leetcode.q337;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 337 打家劫舍III
 */
public class Solution1 {

    /**
     * 遍历每个节点选择偷或者不偷(dfs)
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }

        // 偷父节点
        int val1 = root.val;
        if (root.left != null) {
            val1 += rob(root.left.left) + rob(root.left.right); // 跳过root的左节点
        }
        if (root.right != null) {
            val1 += rob(root.right.left) + rob(root.right.right); // 跳过root的右节点
        }

        // 不偷父节点
        int val2 = rob(root.left) + rob(root.right); // 考虑root的左右孩子

        // 两种方案选择最大的方案
        return Math.max(val1, val2);
    }

}
