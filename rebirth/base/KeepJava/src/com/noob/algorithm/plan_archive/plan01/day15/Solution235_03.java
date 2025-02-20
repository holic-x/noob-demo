package com.noob.algorithm.plan_archive.plan01.day15;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 235 二叉搜索树的最近公共祖先
 */
public class Solution235_03 {

    // 迭代法
    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        // 定义遍历指针，根据遍历节点和p、q节点的值进行比较处理
        if (node == null) {
            return null;
        }

        TreeNode cur = node;
        while (cur != null) {
            // 判断curVal与p、q节点val的关系
            int curVal = cur.val;
            if (curVal < p.val && curVal < q.val) {
                cur = cur.right; // 往右边走
            } else if (curVal > p.val && curVal > q.val) {
                cur = cur.left; // 往左边走
            } else {
                return cur; // 找到的第一个满足[p,q]\[q,p]区间的值即为最近公共祖先
            }
        }

        return null;

    }

}
