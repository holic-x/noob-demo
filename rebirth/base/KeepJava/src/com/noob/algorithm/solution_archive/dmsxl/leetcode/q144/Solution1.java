package com.noob.algorithm.solution_archive.dmsxl.leetcode.q144;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 144 二叉树的前序遍历
 */
public class Solution1 {
    // 递归法
    public List<Integer> preorderTraversal(TreeNode root) {
        // 初始化递归结果集
        List<Integer> res = new ArrayList<>();
        preorder(root,res);
        return res;
    }

    // 递归方式实现前序遍历
    public void preorder(TreeNode node,List<Integer> list){
        // 递归出口
        if(node==null){
            return ;
        }
        // 递归过程（前序遍历DRL）
        list.add(node.val);
        preorder(node.left,list);
        preorder(node.right,list);
    }
}
