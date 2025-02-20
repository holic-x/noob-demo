package com.noob.algorithm.solution_archive.leetcode.common150.q199;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.*;

/**
 * 199 二叉树的右视图  错误思路 todo
 */
public class Solution2 {

    /**
     * 思路
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
         showRight(root,list);
        Collections.reverse(list);
        return list;
    }

    /**
     * 递归获取右节点，封装列表
     */
    public void showRight(TreeNode node,List<Integer> list){
        if(node==null){
            return ;
        }
        showRight(node.right,list);
        list.add(node.val);
    }

}


/*
class Solution704 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        list = showRight(root,list);
        Collections.reverse(list); // 反转
        return list;
    }

    public List<Integer> showRight(TreeNode node,List<Integer> list) {
        if(node == null){
            return list;
        }
        // 判断打印右节点
        showRight(node.right,list);
        list.add(node.val);
        return list;
    }

}
 */