package com.noob.algorithm.daily.archive.plan02.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 669 修剪二叉搜索树 - https://leetcode.cn/problems/trim-a-binary-search-tree/description/
 */
public class Solution669_01 {

    /**
     * 思路分析：修建二叉搜索树，让所有节点的值落在`[low,high]`中，需保留所有父子关系
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        /**
         * 迭代思路：遍历节点，校验节点值是否落在[low,right]
         * ① 如果节点值均没有落在[low,high]区间，说明整棵树都要裁掉
         * ② 找到第1个节点值落在[low,high]的节点cur，然后递归（迭代）剪裁这棵树
         * - cur 本身满足 low <= cur.val <= high,因此对于其左节点只需要校验low边界，同理对于右节点只需要校验high边界
         * - cur.left:
         *  - 如果leftNode.val < low 超出边界直接剪枝
         *  - 如果leftNode.val >= low 满足条件，等待下一轮
         * - cur.right:
         *  - 如果rightNode.val > high 超出边界直接剪枝
         *  - 如果rightNode.val <= low 满足条件，等待下一轮
         */
        // ① 寻找第1个落在[low,high]区间的节点
        TreeNode findNode = find(root, low, high);
        if (findNode == null) {
            return null; // 没有满足落在限定区间的节点，整颗树都裁剪
        }

        // ② 校验其左、右子节点与区间的关系,并进行剪枝
        TreeNode leftP = findNode; // 以当前节点为起点，遍历左子树
        while (leftP.left != null) {
            int leftVal = leftP.left.val;
            if (leftVal < low) {
                leftP.left = leftP.left.right;
            } else {
                leftP = leftP.left;
            }
        }

        TreeNode rightP = findNode;// 以当前节点为起点，遍历右子树
        while (rightP.right != null) {
            int rightVal = rightP.right.val;
            if (rightVal > high) {
                rightP.right = rightP.right.left;
            } else {
                rightP = rightP.right;
            }
        }

        // 返回迭代后的节点信息
        return findNode;
    }


    private TreeNode find(TreeNode node, int low, int high) {
        TreeNode cur = node;
        while (cur != null) {
            int curVal = cur.val;
            if (curVal >= low && curVal <= high) {
                return cur;
            } else if (curVal < low) {
                cur = cur.right; // 往右边找
            } else if (curVal > high) {
                cur = cur.left; // 往左边找
            }
        }
        return null; // 没找到
    }

}
