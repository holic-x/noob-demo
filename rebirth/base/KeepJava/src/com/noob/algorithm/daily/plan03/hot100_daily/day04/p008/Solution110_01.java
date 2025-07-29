package com.noob.algorithm.daily.plan03.hot100_daily.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 110 平衡二叉树 - https://leetcode.cn/problems/balanced-binary-tree/description/
 */
public class Solution110_01 {

    /**
     * 思路分析：
     * 平衡二叉树：该树所有节点的左右子树的高度相差不超过 1
     * - 计算高度，随后校验高度差
     */
    public boolean isBalanced(TreeNode root){

        boolean ans = valid(root);

        return ans;
    }

    // 递归遍历节点计算高度差
    private boolean valid(TreeNode node){
        if(node==null){
            return true;
        }

        // node 不为null，分别获取高度
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // 校验高度差
        if(Math.abs(leftHeight-rightHeight)>1){
            return false; // 高度差超过1
        }else{
            // return true; // 高度差满足平衡条件，继续校验
            // 递归校验左右子树的内容
            return valid(node.left) && valid(node.right);
        }
    }


    // 计算高度
    private int height(TreeNode node){
        if(node==null){
            return 0;
        }
        // node 不为null，分别计算左右子树
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // 返回高度
        return Math.max(leftHeight,rightHeight) + 1;
    }


}
