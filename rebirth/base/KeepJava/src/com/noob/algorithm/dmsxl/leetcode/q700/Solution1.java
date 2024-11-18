package com.noob.algorithm.dmsxl.leetcode.q700;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 700 二叉搜索树中的搜索
 */
public class Solution1 {

    // 迭代法
    public TreeNode searchBST(TreeNode root, int val) {

        while (root != null) {
            // 根据二叉搜索树特性进行检索
            int cur = root.val;
            if (cur == val) {
                return root;
            } else {
                // 根据cur与val的关系切换遍历方向
                if (cur > val) {
                    root = root.left;
                } else {
                    root = root.right;
                }
            }
        }

        // 未找到
        return null;
    }

}
