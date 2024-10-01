package com.noob.algorithm.leetcode.q114;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 114.二叉树展开为链表
 */
public class Solution1 {


    public void flatten(TreeNode root) {
        /**
         * 1.记录root的左右子树
         * 2.将左子树left移动到右子树right的位置
         * 3.然后将原来的右子树right移动到左子树最右边的节点
         * 4.依次类推，直到左边的节点被全部移过去
         */

        // 定义cur记录当前节点位置
        TreeNode cur = root;
        while (cur != null) {
            // 1.记录当前节点的左右子树
            TreeNode leftTree = cur.left;
            TreeNode rightTree = cur.right;

            // 如果左子树为空则直接进入下一个节点，不为空则进行移动操作
            if (leftTree != null) {
                // 2.将左子树移动到右子树的位置
                cur.right = leftTree;
                cur.left = null; // 原有的左节点位置置空
                // 3.将原右子树移动到现在这个新的右子树最右边的节点（找出这个最右边节点）
                TreeNode finder = leftTree;
                while (finder.right != null) {
                    finder = finder.right;
                }
                finder.right = rightTree; // 将右子树移动到其右节点
            }

            // 更新cur指针位置(下一个指针指向当前节点的右节点)
            cur = cur.right;
        }
    }


}

/* 二叉树节点类 */
class TreeNode {
    int val;         // 节点值
    TreeNode left;   // 左子节点引用
    TreeNode right;  // 右子节点引用

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

