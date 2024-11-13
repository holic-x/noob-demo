package com.noob.algorithm.dmsxl.leetcode.q145;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 145 二叉树的后序遍历
 */
public class Solution2 {
    // 迭代法
    List<Integer> postorderTraversal(TreeNode root){
        if(root==null){
            return new ArrayList<>();
        }

        // 定义遍历结果集合
        List<Integer> res = new ArrayList<>();

        /**
         * 后序遍历（LRD）：反向操作
         * 思路分析： LRD 反转 为 DRL 的顺序（可以仿照前序遍历的思路实现DRL：即先根节点入栈，然后左子节点、右子节点分别入栈）
         * 算法实现：构建D->L->R的入栈顺序，得到D->R->L的出栈顺序，然后再将这个结果集反转得到LRD
         */

        // 构建辅助栈
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 初始化

        // 遍历栈
        while(!stack.isEmpty()){
            // 取出栈元素
            TreeNode cur = stack.pop();
            res.add(cur.val); // 先遍历D

            // 构建入栈顺序（先L后R）
            if(cur.left!=null){
                stack.push(cur.left);
            }
            if(cur.right!=null){
                stack.push(cur.right);
            }
        }

        // 最终反转结果集得到目标序列LRD
        Collections.reverse(res);

        // 返回结果集
        return res;
    }

}
