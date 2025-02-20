package com.noob.algorithm.plan_archive.plan01.day15;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡701 二叉搜索树中的插入操作
 */
public class Solution701_02 {

    // 转化为二叉搜索树：遍历节点，寻找合适的插入位置
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }

        // 迭代法遍历
        TreeNode cur = root;
        while (cur != null) {
            // 校验cur.val与val的值
            int curVal = cur.val;
            if (curVal < val) {
                // 如果当前节点值小于val，说明val需要放置在右子树，则进一步找到右侧合适的插入位置
                if (cur.right == null) { // 当前节点右节点为null，可以插入
                    cur.right = new TreeNode(val);
                    // return root; // 返回处理后构建的新树
                    break;
                } else {
                    // 当前节点的右节点不为null，则继续向右遍历
                    cur = cur.right;
                }
            } else if (curVal > val) {
                // 如果当前节点值大于val，说明val需要放置在左子树，则进一步找到左侧合适的插入位置
                if (cur.left == null) { // 当前节点左节点为null，可以插入
                    cur.left = new TreeNode(val);
                    // return root; // 返回处理后构建的新树
                    break;
                } else {
                    // 当前节点的左节点不为null，则继续向左遍历
                    cur = cur.left;
                }
            }
        }
        // 返回
        return root;
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        Solution701_02 s = new Solution701_02();
        s.insertIntoBST(node1, 5);
    }
}
