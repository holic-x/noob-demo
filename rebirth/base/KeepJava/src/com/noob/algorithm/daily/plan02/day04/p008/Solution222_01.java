package com.noob.algorithm.daily.plan02.day04.p008;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_01 {

    /**
     * 思路分析：完全二叉树（除了最底层，其他层都填满）
     * 通用思路：遍历统计（层序遍历）
     */
    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int cnt = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();

            // 统计节点
            cnt++;

            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }

        // 返回节点个数
        return cnt;
    }
}
