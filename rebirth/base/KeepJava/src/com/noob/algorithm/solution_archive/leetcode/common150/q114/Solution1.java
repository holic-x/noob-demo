package com.noob.algorithm.solution_archive.leetcode.common150.q114;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

/**
 * 114 二叉树展开为链表
 */
public class Solution1 {

    /**
     * 遍历的思路：
     * 1.定义cur指针进行遍历
     * 2.如果节点存在则判断其是否存在左子树需要移动
     * - 如果存在左子树，则进行移动操作：a.将左子树移动到右侧 b.原左子树位置置空 c.将其原右子树的位置拼接到移动后的左子树的最右侧节点
     * - 如果节点不存在左子树，则不需要执行任何操作
     * 3.本次遍历操作完成，继续下一次遍历（cur始终指向右侧节点，因为在这个过程中，左侧子树会被移动到右侧）
     */
    public void flatten(TreeNode root) {
        // 1.定义cur指针，指向当前遍历节点
        TreeNode cur = root;
        // 当节点不为叶子节点，则依次判断是否需要进行移动
        while (cur != null) {
            // 记录cur原来的左右子树（节点）
            TreeNode leftTree = cur.left;
            TreeNode rightTree = cur.right;
            // 如果左子树不为空，则需要将其搬到右侧
            if (leftTree != null) {
                // a.左子树处理
                cur.right = leftTree; // 将左子树搬到右侧
                cur.left = null; // 原来左子树的位置置空
                // b.右子树处理（找到移动后的leftTree的左右侧节点，然后将原rightTree放到其右侧）
                TreeNode findRightNode = leftTree; // 定义指针遍历寻找原左子树的最右侧节点
                while (findRightNode.right != null) { // 当右侧节点不为空则一直向下找，直到找到最右侧节点
                    findRightNode = findRightNode.right;
                }
                // 找到这个最右侧节点（叶子节点），然后将原来的rightTree放置在其右侧
                findRightNode.right = rightTree;
            }
            // 如果左子树为空，则不需要做任何操作，继续遍历下一个右侧节点
            cur = cur.right;
        }
    }
}
