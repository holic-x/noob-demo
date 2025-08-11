package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 669 修剪二叉搜索树 - https://leetcode.cn/problems/trim-a-binary-search-tree/description/ todo
 */
public class Solution669_01 {

    /**
     * 思路分析：迭代思路，寻找第一个满足在[low,right]区间内的节点，基于该节点的左右子树分别进行两边校验
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        TreeNode cur = root;
        while (cur != null) {
            int curVal = cur.val;
            if (curVal < low) {
                cur = cur.right;
            } else if (curVal > high) {
                cur = cur.left;
            } else {
                // 寻找到满足条件的节点，跳出循环遍历
                break;
            }
        }

        // 校验查找的目标节点是否存在
        if (cur == null) {
            return null; // 目标区间不存在有效节点，整棵树都要砍掉
        }

        // 目标区间存在满足条件的节点，继续迭代其左右子树
        TreeNode lPointer = cur;
        while (lPointer.left != null) {
            if (lPointer.left.val < low) {
                lPointer.left = lPointer.left.right; // 剪枝
            } else {
                lPointer = lPointer.left;
            }
        }

        TreeNode rPointer = cur;
        while (rPointer.right != null) {
            if (rPointer.right.val > high) {
                rPointer.right = rPointer.right.left; // 剪枝
            } else {
                rPointer = rPointer.right;
            }
        }

        // 返回最终构建的节点
        return cur;
    }

}
