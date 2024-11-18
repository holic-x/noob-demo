package com.noob.algorithm.dmsxl.leetcode.q145;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 145 二叉树的后序遍历
 */
public class Solution1 {
    // 递归法
    List<Integer> postorderTraversal(TreeNode root){
        // 定义遍历结果集合
        List<Integer> res = new ArrayList<>();
        postorder(root,res);
        return res;
    }

    // 后序遍历（递归实现：LRD）
    public void postorder(TreeNode node,List<Integer> list){
        // 递归出口
        if(node==null){
            return;
        }
        // 递归过程（LRD）
        postorder(node.left,list);
        postorder(node.right,list);
        list.add(node.val);
    }
}
