package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 144 二叉树的前序遍历 - https://leetcode.cn/problems/binary-tree-preorder-traversal/submissions/598678754/
 */
public class Solution144_01 {

    /**
     * 思路分析：
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preorder(root,ans);
        return ans;
    }

    // 递归处理
    private void preorder(TreeNode node,List<Integer> list){
        if(node==null){
            return;
        }

        // 前序遍历：DLR
        list.add(node.val);
        preorder(node.left,list); // 递归处理左子树
        preorder(node.right,list); // 递归处理右子树
    }

}
