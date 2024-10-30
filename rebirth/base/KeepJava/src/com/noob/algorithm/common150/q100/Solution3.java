package com.noob.algorithm.common150.q100;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 100 相同的树
 */
public class Solution3 {

    /**
     * 广度优先遍历
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        /**
         * 定义队列辅助存储（此处可以使用两个队列，也可使用同一个队列按层遍历即可）
         * 每次同步存入两个元素，同步取出两个元素进行比较
         */
        Queue<TreeNode> queue = new LinkedList<>(); // 此处使用LinkedList存储，允许存入null数据
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            // 弹出元素进行判断（每次取出两个元素判断）
            TreeNode pcur = queue.poll();
            TreeNode qcur = queue.poll();

            // 两个节点都为空（跳出本次循环，进行下一轮比较）
            if (pcur == null && qcur == null) {
                continue;
            }

            // 当节点不为空，校验是否匹配
            if (pcur == null || qcur == null || pcur.val != qcur.val) {
                return false;
            }

            // 插入左节点
            queue.offer(pcur.left);
            queue.offer(qcur.left);
            // 插入右节点
            queue.offer(pcur.right);
            queue.offer(qcur.right);
        }

        return true;
    }
}
