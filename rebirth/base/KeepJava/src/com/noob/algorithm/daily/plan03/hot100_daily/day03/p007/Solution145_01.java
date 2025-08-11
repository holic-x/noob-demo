package com.noob.algorithm.daily.plan03.hot100_daily.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 145 后序遍历 - https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class Solution145_01 {

    /**
     * 思路分析：LRD
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root);
        return ans;
    }

    List<Integer> ans = new ArrayList<>();

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);    // 左子树处理
        dfs(node.right);   // 右子树处理
        ans.add(node.val); // 记录结果
    }

}
