package com.noob.algorithm.solution_archive.dmsxl.leetcode.q701;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 701 二叉树中的插入操作
 */
public class Solution1 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        // 定义遍历节点
        TreeNode cur = root;
        while (cur != null) {
            // 根据当前遍历节点值判断
            if (val < cur.val) {
                // 判断当前其左节点是否为空，为空则可插入
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break; // 节点插入完成，跳出循环
                } else {
                    cur = cur.left; // 继续寻找下一个可插入位置
                }
            } else if (val > cur.val) {
                // 判断当前其右节点是否为空，为空则可插入
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break; // 节点插入完成，跳出循环
                }else {
                    cur = cur.right; // 继续寻找下一个可插入位置
                }
            }
        }
        // 返回结果
        return root;
    }

}
