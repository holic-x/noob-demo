package com.noob.algorithm.dmsxl.leetcode.q669;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 669 修剪二叉搜索树
 */
public class Solution3 {

    /**
     * 迭代法
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {

        if (root == null) {
            return root;
        }

        TreeNode cur = root; // 定义遍历节点
        // 1.找到符合节点值在指定区间范围的节点
        while (cur != null) {
            if (low <= cur.val && cur.val <= high) {
                break; // 找到目标节点，退出循环
            } else if (cur.val < low) {
                cur = cur.right; // 遍历节点在区间左侧，需往右移动
            } else if (cur.val > high) {
                cur = cur.left; // 遍历节点在区间右侧，需往左移动
            }
        }

        // 2.判断这个节点是否存在，如果不存在则直接返回null（说明没有满足区间范围的节点，直接返回null），如果存在则分情况讨论进行剪枝
        if (cur == null) {
            return null;
        }

        // 2.1 符合区间范围的节点node存在，以这个节点为起点，分别进行左右子树的判断和剪枝操作
        TreeNode lPointer = cur; // 以当前节点为起点，遍历左子树
        while (lPointer != null) {
            // 处理左孩子元素小于L的情况
            while (lPointer.left != null && lPointer.left.val < low) {
                // 需要剪枝的情况(leftNode.left肯定<low可以直接剪，leftNode也可以剪，因此leftNode直接替换为其右节点)
                lPointer.left = lPointer.left.right; // 剪掉leftNode、leftNode.left
            }
            lPointer = lPointer.left; // 移动节点等待下一次剪枝判断
        }

        // 2.2 同理，遍历右子树
        TreeNode rPointer = cur; // 以当前节点为起点，遍历右子树
        while (rPointer != null) {
            // 处理右孩子元素大于R的情况
            while (rPointer.right != null && rPointer.right.val > high) {
                rPointer.right = rPointer.right.left; //rightNode.val > high满足，则rightNode.right.val > high肯定满足，因此直接剪掉rightNode.right、rightNode
            }
            rPointer = rPointer.right; // 移动节点等待下一次剪枝判断
        }

        // 返回迭代后的节点信息（此处返回结果应以cur节点开始，前期遍历已经过滤掉不满足的节点记录了）
        return cur;
    }
}
