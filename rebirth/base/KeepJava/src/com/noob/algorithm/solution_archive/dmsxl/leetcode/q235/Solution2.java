package com.noob.algorithm.solution_archive.dmsxl.leetcode.q235;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;
/**
 * 235 二叉搜索树的最近公共祖先
 */
public class Solution2 {

    // 迭代法：
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 迭代
        TreeNode cur = root; // 定义遍历指针
        while (cur != null) {
            // 判断当前节点值和p、q的关系
            if (cur.val > p.val && cur.val > q.val) {
                cur = cur.left; // 当前值均大于p、q则往左子树方向走
            } else if (cur.val < p.val && cur.val < q.val) {
                cur = cur.right;// 当前值均小于p、q则往右子树方向走
            } else {
                return cur; // 当前值满足区间范围，即为所得
            }
        }
        // 没有找到目标
        return null;
    }

}

