package com.noob.algorithm.solution_archive.leetcode.common150.q226;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226 翻转二叉树
 */
public class Solution1 {

    /**
     * 基于层序遍历，每遍历一个节点，交换其左右节点
     */
    public TreeNode invertTree(TreeNode root) {

        // root为空判断
        if(root==null){
            return root;
        }

        // 借助队列辅助
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 遍历队列元素（按层）
        while(!queue.isEmpty()){
            // 取出元素
            TreeNode cur = queue.poll();
            // 交换其左右节点
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            // 继续遍历
            if(cur.left!=null){
                queue.offer(cur.left);
            }
            if(cur.right!=null){
                queue.offer(cur.right);
            }
        }
        // 返回结果
        return root;
    }


}
