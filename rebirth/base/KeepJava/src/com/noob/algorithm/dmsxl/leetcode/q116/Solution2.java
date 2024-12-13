package com.noob.algorithm.dmsxl.leetcode.q116;

import com.noob.algorithm.dmsxl.baseStructure.tree.Node;

/**
 * 116 填充每个节点的下一个右侧节点指针
 * 完美二叉树
 */
public class Solution2 {

    public Node connect(Node root) {
        // 判断root为null的情况
        if (root == null) {
            return null;
        }
        // 递归封装next
        connectNext(root);
        return root;
    }

    public void connectNext(Node node) {
        /**
         * 递归出口：如果node.left为null则结束
         * 对于完美二叉树而言，除了叶子结点所有父节点都有两个子节点，因此如果左子节点为空则无需继续递归
         */
        if (node.left == null) {
            return;
        }

        // 递归过程（分别设置当前节点的左子节点、右子节点的next指针）
        node.left.next = node.right; // 设置左子节点的next指针

        // 如果node.next存在则设置右子节点的next指针
        if (node.next != null) {
            node.right.next = node.next.left;
        }

        // 对节点的左右子节点分别进行递归
        connectNext(node.left);
        connectNext(node.right);
    }
}
