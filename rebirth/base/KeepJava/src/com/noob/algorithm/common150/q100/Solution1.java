package com.noob.algorithm.common150.q100;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 100 相同的树
 */
public class Solution1 {

    /**
     * 广度优先遍历
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        /**
         * 定义队列辅助存储（此处可以使用两个队列，也可使用同一个队列按层遍历即可）
         * 每次同步存入两个元素，同步取出两个元素进行比较
         */
       Deque<TreeNode> queue = new LinkedList<>();
       queue.offer(p);
       queue.offer(q);
       while(!queue.isEmpty()){
           // 弹出元素进行判断
           TreeNode pcur = queue.poll();
           TreeNode qcur = queue.poll();

           // 当两个节点的值不存在
           if(pcur==null || qcur==null){
               return pcur==qcur;
           }

           // 当两个节点的值存在
           if(pcur.val != qcur.val){
               return false;
           }

           if(pcur.left!=null){
               queue.offer(pcur.left);
           }
           if(qcur.left!=null){
               queue.offer(qcur.left);
           }
           if(pcur.right!=null){
               queue.offer(pcur.right);
           }
           if(qcur.right!=null){
               queue.offer(qcur.right);
           }
       }

       return true;
    }

}
