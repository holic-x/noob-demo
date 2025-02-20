package com.noob.algorithm.plan_archive.plan01.day15;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡701 二叉搜索树中的插入操作
 */
public class Solution701_03 {

    // 转化为二叉搜索树：遍历节点，寻找合适的插入位置
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return dfs(root, val);
    }

    // 递归处理构建新树:先中规中矩进行递归遍历，然后在递归遍历的过程中选择遍历方向
    public TreeNode dfs(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val); // 节点为null，构建新节点
        }

        // 判断当前遍历新节点值大小和val的比较
        int curVal = node.val;
        if (curVal < val) {
            node.right = dfs(node.right, val);
        } else if (curVal > val) {
            node.left = dfs(node.left, val);
        }
        // 返回节点
        return node;
    }

}
