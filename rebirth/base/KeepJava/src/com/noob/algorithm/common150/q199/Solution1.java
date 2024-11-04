package com.noob.algorithm.common150.q199;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 199 二叉树的右视图
 */
public class Solution1 {

    /**
     * 思路：基于层次遍历的思路（封装每一层最右侧的数）
     */
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }

        Deque<TreeNode> queue = new LinkedList<>(); // 辅助队列
        queue.offer(root); // 初始化

        // 定义右视图结果集
        List<Integer> ans = new ArrayList<>();

        // 遍历元素
        while(!queue.isEmpty()){
            // 分层统计
            int size = queue.size();
             for(int i=0;i<size;i++){
                 TreeNode node = queue.poll();
                 // 判断当前遍历元素是否为当层的最后一个元素
                 if(i==size-1){
                     ans.add(node.val); // 将当层的最后一个元素加入右视图结果集
                 }
                 // 将node的左右节点加入队列
                 if(node.left!=null){
                     queue.offer(node.left);
                 }
                 if(node.right!=null){
                     queue.offer(node.right);
                 }
             }
        }

        // 返回结果
        return ans;
    }
}
