package com.noob.algorithm.leetcode.common150.q222;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 222 完全二叉树的节点个数
 */
public class Solution1 {

    /**
     * 层序遍历：迭代法
     * 核心：在原有的BFS遍历的基础上加入count计数
     */
    public int countNodes(TreeNode root) {
        // root 为null判断
        if(root==null){
            return 0;
        }

        Deque<TreeNode> queue = new LinkedList<>();// 辅助队列
        queue.offer(root); // 初始化

        int count = 0;// 定义计数器
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            // 节点计数
            count++;

            // 加入左、右节点
            if(cur.left!=null){
                queue.offer(cur.left);
            }
            if(cur.right!=null){
                queue.offer(cur.right);
            }
        }

        // 返回结果
        return count;
    }
}
