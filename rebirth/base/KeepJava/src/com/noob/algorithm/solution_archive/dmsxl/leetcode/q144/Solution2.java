package com.noob.algorithm.solution_archive.dmsxl.leetcode.q144;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144 二叉树的前序遍历
 */
public class Solution2 {
    // 迭代法：借助辅助栈实现
    public List<Integer> preorderTraversal(TreeNode root) {
        // root 为null判断
        if(root==null){
            return new ArrayList<>();
        }

        // 初始化递归结果集
        List<Integer> res = new ArrayList<>();

        // 构建辅助栈
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 初始化将根节点入栈

        // 遍历栈元素
        while(!stack.isEmpty()){
            // 先遍历根节点
            TreeNode cur = stack.pop();
            res.add(cur.val);

            // 将当前根节点的右子节点、左子节点分别入栈（出栈时遍历才满足DLR顺序）
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }

        // 返回结果集
        return res;
    }
}
