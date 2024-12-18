package com.noob.algorithm.plan01.day09;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢100 相同的树
 */
public class Solution100_01 {

    public int tempVal = -999999; // 设计一个非限定范围的值，用于占位（标记null节点）

    /**
     * 迭代法：
     * 如果两个树结构完全相同，则其遍历顺序应完全一致（此处需注意对于空节点需占位，才能确保结构一致）
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        // 构建队列辅助遍历元素
        Queue<TreeNode> pQueue = new LinkedList<>();
        pQueue.offer(p);

        Queue<TreeNode> qQueue = new LinkedList<>();
        qQueue.offer(q);

        while (!pQueue.isEmpty() && !qQueue.isEmpty()) {
            // 分别取出两个队列的元素，然后进行一一匹配比较
            TreeNode curP = pQueue.poll();
            TreeNode curQ = qQueue.poll();
            if (curP.val != curQ.val) {
                return false; // 节点对应不一致，结构不同
            }
            // 处理两棵树的左右子树
            if (!(curP.left == null && curP.right == null)) { // 对于非叶子结点
                pQueue.offer(curP.left == null ? new TreeNode(tempVal) : curP.left); // 对于null节点也要进行占位
                pQueue.offer(curP.right == null ? new TreeNode(tempVal) : curP.right); // 对于null节点也要进行占位
            }
            if (!(curQ.left == null && curQ.right == null)) { // 对于非叶子结点
                qQueue.offer(curQ.left == null ? new TreeNode(tempVal) : curQ.left); // 对于null节点也要进行占位
                qQueue.offer(curQ.right == null ? new TreeNode(tempVal) : curQ.right); // 对于null节点也要进行占位
            }
        }

        // 如果两个队列都遍历完成（最终为空），则说明完全一致
        return pQueue.isEmpty() && qQueue.isEmpty();

    }
}
