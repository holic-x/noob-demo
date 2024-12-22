package com.noob.algorithm.dmsxl.leetcode.q100;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 🟢100 相同的树
 */
public class Solution1 {

    // 迭代法：定义队列辅助存储（此处可以使用两个队列，也可使用同一个队列按层遍历即可），每次同步存入两个元素，同步取出两个元素进行比较
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 构建辅助队列
        Deque<TreeNode> queue = new LinkedList<>(); // 此处使用1个队列，确保数据插入顺序是按照待比较元素分组
        queue.offer(p);
        queue.offer(q);

        // 遍历队列
        while (!queue.isEmpty()) {
            // 每次取出两个元素进行比较
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            // 如果node1、node2均为null
            if (node1 == null && node2 == null) {
                continue; // 满足条件，直接进入下一轮比较
            }
            // 如果node1、node2一方为null
            if (node1 == null || node2 == null) {
                return false; // 不匹配，直接返回false
            }
            // 如果node1、node2均不为null
            if (node1.val != node2.val) {
                return false; // 两个节点的值不匹配，直接返回false
            }

            // 将节点存入队列（按照待比较元素分组,就算是null节点也要占位，确保顺序一致）
            queue.offer(node1.left);
            queue.offer(node2.left);
            queue.offer(node1.right);
            queue.offer(node2.right);
        }
        // 经过上述校验，验证相同的树
        return true;
    }
}
