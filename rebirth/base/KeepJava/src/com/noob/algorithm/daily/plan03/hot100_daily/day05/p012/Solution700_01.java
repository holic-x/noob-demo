package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 700 二叉搜索树中的搜索 - https://leetcode.cn/problems/search-in-a-binary-search-tree/description/
 */
public class Solution700_01 {


    // 迭代法搜索
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode cur = root;
        while (cur != null) {
            int curVal = cur.val;
            if (val < curVal) {
                // 目标值在左侧
                cur = cur.left;
            } else if (val > curVal) {
                // 目标值在右侧
                cur = cur.right;
            } else {
                return cur;
            }
        }

        return null;
    }

}
