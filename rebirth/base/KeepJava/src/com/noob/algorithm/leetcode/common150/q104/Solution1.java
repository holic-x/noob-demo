package com.noob.algorithm.leetcode.common150.q104;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.*;

/**
 * 104 二叉树的最大深度
 */
public class Solution1 {
    /**
     * 思路：层序遍历（BFS思路），计算层数
     */
    public int maxDepth(TreeNode root) {

        // 空节点校验
        if(root==null){
            return 0;
        }

        // 借助队列辅助存储
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root); // 初始化加入根节点

        // 定义变量存储下沉深度
        int depth = 0;

        // 遍历队列元素
        while(!queue.isEmpty()){
            // 分层遍历
            int n = queue.size(); // 记录当前队列长度（当层遍历）

            while(n>0){
                // 获取当前节点
                TreeNode cur = queue.poll();
                // 判断是否存在左右节点
                if(cur.left!=null){
                    queue.add(cur.left);
                }
                if(cur.right!=null){
                    queue.add(cur.right);
                }
                n--;
            }

            // 当层节点遍历完成
            depth++;
        }

        // 返回结果
        return depth;
    }
}
