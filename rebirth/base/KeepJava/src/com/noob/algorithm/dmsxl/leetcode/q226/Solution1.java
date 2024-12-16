package com.noob.algorithm.dmsxl.leetcode.q226;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 226 翻转二叉树
 */
public class Solution1 {

    // 思路：层序遍历，依次遍历每个节点，交换其左右节点
    public TreeNode invertTree(TreeNode root) {
        // root 为 null
        if(root==null){
            return root;
        }

        // 构建辅助队列
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化队列

        // 遍历队列
        while(!queue.isEmpty()){
            // 取出元素，并交换其左右子节点
            TreeNode cur = queue.poll();
            // 交换(此处左右子节点的交换时机不受限入队前后，没有遍历需求，重在交换)
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            // 如果左右节点不为空则入队
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
