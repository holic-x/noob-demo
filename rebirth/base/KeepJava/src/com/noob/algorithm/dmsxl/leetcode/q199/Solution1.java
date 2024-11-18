package com.noob.algorithm.dmsxl.leetcode.q199;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 199 二叉树的右视图
 */
public class Solution1 {

    // 思路：返回每一层的最右侧节点
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }

        // 定义结果集
        List<Integer> res = new ArrayList<>();

        // 定义辅助队列
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化

        // 遍历队列
        while(!queue.isEmpty()){
            // 分层遍历
            int curSize = queue.size();
            for(int i=0;i<curSize;i++){
                // 取出元素
                TreeNode cur = queue.poll();
                // 判断当层遍历索引是否为最后一个
                if(i==curSize-1){
                    res.add(cur.val); // 如果是当层最后一个则加入结果集
                }

                // 如果存在左右节点，分别入队
                if(cur.left!=null){
                    queue.offer(cur.left);
                }
                if(cur.right!=null){
                    queue.offer(cur.right);
                }
            }
        }

        // 返回结果
        return res;
    }
}
