package com.noob.algorithm.leetcode.common150.q101;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101 对称二叉树
 */
public class Solution2 {

    /**
     * 转换题意：相同的树(仿照思路)
     * 判断左右子树是否对称（左右节点比较）
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    /**
     * 基于层次遍历判断两个树是否对称
     */
    public boolean check(TreeNode p, TreeNode q) {
         // 借助队列辅助存储
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while(!queue.isEmpty()){
            // 两两比较
            TreeNode curP = queue.poll();
            TreeNode curQ = queue.poll();

            // 如果两个节点都为空，则无需比较，跳过本轮
            if(curP==null && curQ==null){
                continue;
            }
            // 如果其中一个为空，则不对称
            if(curP==null || curQ==null){
                return false;
            }
            // 如果两个都不为空，则进一步校验值
            if(curP.val != curQ.val){
                return false;
            }

            // 将左、右节点加入队列，进行下一步判断
            queue.add(curP.left);
            queue.add(curQ.right);
            queue.add(curP.right);
            queue.add(curQ.left);
        }

        return true;
    }

}
