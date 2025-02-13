package com.noob.algorithm.daily.plan02.day03.p007;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 145 后序遍历 - https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class Solution145_01 {

    /**
     * 递归思路
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 前序遍历
        postorder(root, list);
        // 返回结果
        return list;
    }

    // dfs：后续遍历（LRD）
    private void postorder(TreeNode node, List<Integer> list) {
        // 递归出口
        if (node == null) {
            return;
        }

        // LRD
        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }

}
