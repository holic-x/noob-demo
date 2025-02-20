package com.noob.algorithm.plan_archive.plan02.hot100.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 098.验证二叉搜索树 - https://leetcode.cn/problems/validate-binary-search-tree/description/
 */
public class Solution098_02 {

    // private int preNodeVal = Integer.MIN_VALUE;
    private long preNodeVal = Long.MIN_VALUE; // 扩大处理范围

    /**
     * 校验节点是否为一个有效的二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return dfs(root);
    }

    // 递归思路(基于中序遍历，校验递增趋势)
    private boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }

        // LDR
        boolean validLeft = dfs(node.left);

        // 处理节点
        int curVal = node.val;
        if(preNodeVal>=curVal){
            return false; // 不满足递增
        }else{
            preNodeVal = curVal; // 更新值，为下一个校验做准备
        }

        boolean validRight = dfs(node.right);

        return validLeft && validRight;

    }
}
