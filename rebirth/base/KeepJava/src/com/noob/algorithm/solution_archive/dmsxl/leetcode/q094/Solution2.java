package com.noob.algorithm.solution_archive.dmsxl.leetcode.q094;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 094 二叉树的中序遍历
 */
public class Solution2 {
    // 迭代法
    public List<Integer> inorderTraversal(TreeNode root){
        // 定义遍历结果集
        List<Integer> res = new ArrayList<>();

        // 中序遍历（迭代：借助指针帮助遍历元素，借助栈处理元素）
        TreeNode cur = root; // 指针用于遍历节点
        Stack<TreeNode> stack = new Stack<>(); // 栈用于处理元素
        // 当指针不为空或者栈不为空时进行处理
        while(cur!=null || !stack.isEmpty()){
            if(cur!=null){
                stack.push(cur); // 将访问的节点入栈
                cur = cur.left; // 一直向左遍历直到到达最左的子节点
            }else{
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        // 返回结果
        return res;
    }

}
