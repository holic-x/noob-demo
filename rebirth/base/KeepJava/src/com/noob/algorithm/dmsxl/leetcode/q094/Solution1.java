package com.noob.algorithm.dmsxl.leetcode.q094;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 094 二叉树的中序遍历
 */
public class Solution1 {
    // 递归法
    public List<Integer> inorderTraversal(TreeNode root){
        // 定义遍历结果集
        List<Integer> res = new ArrayList<>();
        inorder(root,res);
        return res;
    }

    // 中序遍历（递归实现：LDR）
    public void inorder(TreeNode node,List<Integer> list){
        // 递归出口
        if(node==null){
            return ;
        }
        // 递归过程（LDR）
        inorder(node.left,list);
        list.add(node.val);
        inorder(node.right,list);
    }
}
