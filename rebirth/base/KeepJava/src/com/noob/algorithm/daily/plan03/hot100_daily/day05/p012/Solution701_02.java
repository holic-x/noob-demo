package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 701 二叉搜索树中的插入操作 - https://leetcode.cn/problems/insert-into-a-binary-search-tree/description/
 * 需确保插入后更新的树满足二叉搜索树特性，可能有多种结果
 */
public class Solution701_02 {

    /**
     * 思路分析：迭代思路处理
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        // 迭代思路处理
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < val) {
                // 当前遍历节点的值小于val，则可以将val插入到cur的右侧，此处则进一步判断其右子树是否已经存在：如果不存在可直接插入，如果存在则一直向右插入到最右
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break; // 插入完成，跳出循环
                } else {
                    cur = cur.right;
                }
            } else if (cur.val > val) {
                // 当前遍历节点的值大于val，则可以将val插入到cur的左侧，此处则进一步判断其左子树是否已经存在：如果不存在可直接插入，如果存在则一直向左插入到最左
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break; // 插入完成，跳出循环
                } else {
                    cur = cur.left;
                }
            }
        }

        // 返回处理后的节点
        return root;
    }

}
