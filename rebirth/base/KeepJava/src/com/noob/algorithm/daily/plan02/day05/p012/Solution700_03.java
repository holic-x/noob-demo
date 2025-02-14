package com.noob.algorithm.daily.plan02.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 700 二叉搜索树中的搜索 - https://leetcode.cn/problems/search-in-a-binary-search-tree/description/
 */
public class Solution700_03 {

    /**
     * 思路分析：基于迭代思路检索，定义遍历指针指向遍历节点，然后根据值比较缩小范围直到叶子节点
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        // 定义遍历指针
        TreeNode cur = root; // 初始化指向根节点
        while (cur != null) {
            // 校验遍历节点值
            if (cur.val == val) {
                return cur;
            } else if (cur.val > val) {
                // 目标值在左子树，遍历指针向左移动
                cur = cur.left;
            } else if (cur.val < val) {
                // 目标值在右子树，遍历指针向右移动
                cur = cur.right;
            }
        }
        // 没有找到目标
        return null;
    }
}
