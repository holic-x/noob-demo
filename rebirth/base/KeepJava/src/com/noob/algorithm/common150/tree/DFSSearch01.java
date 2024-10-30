package com.noob.algorithm.common150.tree;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSSearch01 {

    /**
     * 非递归方式实现（借助栈辅助）
     * 先序遍历 todo
     */
    public List<Integer> dfs(TreeNode root){
        // 空树判断
        if(root==null){
            return new ArrayList<>();
        }

        // 存储遍历结果
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 根节点入栈

        // 遍历栈中元素，直到栈为空
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            list.add(cur.val);
            // 右节点不为空压右节点（后进先出）
            if(cur.right!=null){
                stack.push(cur.right);
            }
            // 左节点不为空先压左节点
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }

        // 返回结果
        return list;

    }
}
