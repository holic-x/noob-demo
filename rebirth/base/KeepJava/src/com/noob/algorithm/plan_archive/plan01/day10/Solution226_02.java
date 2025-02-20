package com.noob.algorithm.plan_archive.plan01.day10;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢226 翻转二叉树
 */
public class Solution226_02 {

    /**
     * 翻转二叉树：
     * 迭代法：遍历每个节点，交换节点的左右子树
     */
    public TreeNode invertTree(TreeNode root) {
        // root 为 null 判断
        if (root == null) {
            return root;
        }
        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 取出当前节点处理
            TreeNode cur = queue.poll();
            // 将左右子节点入队
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            // 交换当前节点的左右子节点(此处左右子节点的交换时机不受限入队前后，没有遍历需求，重在交换)
            TreeNode tempNode = cur.left;
            cur.left = cur.right;
            cur.right = tempNode;
        }

        // 返回处理后的root
        return root;
    }
}
