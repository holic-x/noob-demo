package com.noob.algorithm.daily.plan02.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 450 删除二叉搜索树中的节点 - https://leetcode.cn/problems/delete-node-in-a-bst/description/
 */
public class Solution450_01 {

    /**
     * 思路分析：删除二叉搜索树的节点，需要考虑其左右子节点的关系
     * ① 找到目标节点（记录当前遍历节点和上一个遍历节点）
     * ② 删除目标节点，并更新节点挂载关系（其左、右子节点）
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode preNode = null, cur = root; // 初始化上一个遍历节点，和树节点遍历指针

        // ① 检索目标节点
        while (cur != null) {
            int curVal = cur.val;
            if (key == curVal) {
                break; // 找到了目标节点
            } else if (key < curVal) {
                preNode = cur; // 更新preNode
                cur = cur.left; // 向左寻找
            } else if (key > curVal) {
                preNode = cur; // 更新preNode
                cur = cur.right; // 向右寻找
            }
        }
        // 校验是否找到目标节点
        if (cur == null) {
            return root; // 没有找到目标节点，无需执行删除操作
        }

        // ② 找到目标节点，更新挂载关系（即确认目标节点删除后谁会上位成为新的子树根节点）
        TreeNode leftNode = cur.left, rightNode = cur.right, newRoot = null;
        if (leftNode == null && rightNode == null) {
            // 删除的是叶子节点，无需处理
        } else if ((leftNode != null && rightNode == null) || (leftNode == null && rightNode != null)) {
            // 子节点中只有一个为null，则不为null的那个子节点会上位
            newRoot = (leftNode == null ? rightNode : leftNode);
        } else {
            // 子节点均不为null，则需将原来的leftNode挂载到rightNode的最左下方，让更新后的rightNode上位
            TreeNode findLeft = rightNode;
            while (findLeft.left != null) {
                findLeft = findLeft.left;
            }
            findLeft.left = leftNode; // 让leftNode挂载到rightNode的最左下方
            newRoot = rightNode; // 更新后的rightNode上位
        }

        // ③ 更新preNode与newRoot的关系
        if (preNode == null) {
            return newRoot; // 如果preNode为初始化的null，说明此时待删除节点为根节点，返回构建的newRoot即可
        }
        // preNode 不为null，需要处理preNode和newRoot的挂载关系（根据key值判断其原来是挂在左边还是右边）
        int preNodeVal = preNode.val;
        if (key < preNodeVal) {
            // 挂在左侧
            preNode.left = newRoot;
        } else if (key > preNodeVal) {
            // 挂在右侧
            preNode.right = newRoot;
        }
        // 返回更新后的内容
        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        Solution450_01 s = new Solution450_01();
        s.deleteNode(node1, 3);
    }
}
