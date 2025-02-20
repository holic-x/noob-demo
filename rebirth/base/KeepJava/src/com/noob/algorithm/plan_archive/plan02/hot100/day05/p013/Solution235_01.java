package com.noob.algorithm.plan_archive.plan02.hot100.day05.p013;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 235 二叉搜索树的最近公共祖先 - https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 */
public class Solution235_01 {

    /**
     * 思路分析：自顶向下寻找的满足在[pVal,qVal]区间（p、q大小未确定，也可能是[qVal,pVal]）的第1个节点即为公共节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val, qVal = q.val;
        // 迭代思路
        TreeNode cur = root;
        while (cur != null) {
            // 校验节点值的边界关系：[pVal,qVal]\[qVal,pVal]
            int curVal = cur.val;
            if ((curVal >= pVal && curVal <= qVal) || curVal >= qVal && curVal <= pVal) {
                return cur; // 寻找到的第1个满足落在区间范围内的值即为公共节点
            } else if (curVal < pVal || curVal < qVal) {
                // 当前值落在区间左侧,则需往右寻找才能尽可能靠近区间
                cur = cur.right;
            } else if (curVal > qVal || curVal > pVal) {
                // 当前值落在区间右侧,则需往左寻找才能尽可能靠近区间
                cur = cur.left;
            }
        }
        // 无公共节点
        return null;
    }

}
