package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 145 后序遍历 - https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class Solution145_01 {

    /**
     * 思路分析：
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postorder(root, ans);
        return ans;
    }

    // 递归法
    private void postorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        // 后序LRD
        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }

}
