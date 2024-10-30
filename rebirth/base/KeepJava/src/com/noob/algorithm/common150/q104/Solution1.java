package com.noob.algorithm.common150.q104;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.Stack;

/**
 * 104 二叉树的最大深度
 */
public class Solution1 {
    /**
     * 思路：广度优先遍历思路（借助栈进行辅助）
     */
    public int maxDepth(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 初始化

        while(!stack.isEmpty()){
            // 取出当前节点
            TreeNode cur = stack.pop();
            // 如果存在左节点则入栈
            if(cur.left!=null){
                stack.push(cur.left);
            }
            if(cur.right!=null){
                stack.push(cur.right);
            }
        }

        return 0;
    }
}
