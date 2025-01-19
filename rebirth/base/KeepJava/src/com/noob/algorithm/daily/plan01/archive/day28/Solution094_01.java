package com.noob.algorithm.daily.plan01.archive.day28;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 094 二叉树的中序遍历 - https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_01 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 调用方法进行遍历
        dfs(root,list);
        // 返回结果
        return list;
    }

    // 递归法
    private void dfs(TreeNode node,List<Integer> list){
        if(node==null){
            return;
        }

        // 中序遍历（LDR）
        dfs(node.left,list);
        list.add(node.val);
        dfs(node.right,list);
    }

}
