package com.noob.algorithm.plan_archive.plan02.hot100.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 701 二叉搜索树中的插入操作 - https://leetcode.cn/problems/insert-into-a-binary-search-tree/description/
 */
public class Solution701_02 {

    /**
     * 思路分析：需确保插入后更新的树满足二叉搜索树特性，可能有多种结果
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        // 迭代思路
        TreeNode cur = root;
        while (cur != null) {
            int curVal = cur.val;
            if (val < curVal) {
                // val 需插入左侧(如果当前节点左节点为null则可直接插入，否则继续向左寻找下一个可插入位置)
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break; // 插入操作完成则跳出
                } else {
                    cur = cur.left;
                }
            } else if (val > curVal) {
                // val 需插入右侧（如果当前节点右节点为null则可直接插入，否则继续向右寻找下一个可插入位置）
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break; // 插入操作完成则跳出
                } else {
                    cur = cur.right;
                }
            }
        }
        // 返回处理结果
        return root;
    }

}
