package com.noob.algorithm.dmsxl.leetcode.q617;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 617 合并二叉树
 */
public class Solution1 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // root1\root2 的 null 判断
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        // 构建两个队列分别用于遍历root1、root2 树
        Deque<TreeNode> queue1 = new LinkedList<>();
        queue1.offer(root1);

        Deque<TreeNode> queue2 = new LinkedList<>();
        queue2.offer(root2);

        // 构建新队列存储新树节点
        Deque<TreeNode> mergeQueue = new LinkedList<>();
        TreeNode mergeRoot = new TreeNode(root1.val + root2.val);
        mergeQueue.offer(mergeRoot);


        /**
         * 同时遍历两棵树,如果其中一个队列为空则退出遍历
         * 此处之所以不考虑多出来的节点是因为在遍历过程中的处理（分为左节点处理、右节点处理）：
         * - 如果两个节点都不为null，则构建新节点进行挂载
         * - 如果两个节点中某一个节点为null，则会将非null的节点直接挂到mergeTree指定位置上
         * - 如果两个节点都为null，不做任何操作（默认null）
         */
        while (!queue1.isEmpty() && !queue2.isEmpty()) {

            // 同时取出队列中的节点进行遍历、合并
            TreeNode cur1 = queue1.poll();
            TreeNode cur2 = queue2.poll();
            TreeNode mergeNode = mergeQueue.poll();

            // 左节点、右节点判断
            TreeNode left1 = cur1.left, right1 = cur1.right;
            TreeNode left2 = cur2.left, right2 = cur2.right;

            // 判断当前遍历节点的左节点情况
            if (left1 != null && left2 != null) {
                // 两棵树的对应位置节点都不为空，需相加后构成新节点
                TreeNode mergeLeftNode = new TreeNode(left1.val + left2.val);
                mergeNode.left = mergeLeftNode;
                // 节点入队
                queue1.offer(left1);
                queue2.offer(left2);
                mergeQueue.offer(mergeLeftNode);

            } else if (left1 != null) {
                // left1 不为空，则选择挂载 left1
                mergeNode.left = left1;
            } else if (left2 != null) {
                // left2 不为空，则选择挂载 left2
                mergeNode.left = left2;
            }

            // 判断当前遍历节点的右节点情况
            if (right1 != null && right2 != null) {
                // 两棵树的对应位置节点都不为空，需相加后构成新节点
                TreeNode mergeLeftNode = new TreeNode(right1.val + right2.val);
                mergeNode.right = mergeLeftNode;

                // 节点入队
                queue1.offer(right1);
                queue2.offer(right2);
                mergeQueue.offer(mergeLeftNode);

            } else if (right1 != null) {
                // right1 不为空，则选择挂载 right1
                mergeNode.right = right1;
            } else if (right2 != null) {
                // right2 不为空，则选择挂载 right2
                mergeNode.right = right2;
            }
        }

        return mergeRoot;
    }

}
