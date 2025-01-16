package com.noob.algorithm.daily.plan01.archive.day16;

import com.noob.algorithm.daily.base.TreeNode;
import com.noob.algorithm.dmsxl.graph.Pair;

/**
 * 🟡 669 修剪二叉搜索树
 */
public class Solution669_01 {

    /**
     * 思路：找到第1个满足[low,high]区间范围内的节点，然后基于其左右子节点进行剪枝分析
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {

        // ① 遍历节点：找到第1个满足[low,high]区间范围内的节点
        TreeNode cur = root;
        while (cur != null) {
            // 如果节点在[low,high]区间范围，则跳出检索
            int curVal = cur.val;
            if (curVal >= low && curVal <= high) {
                break;
            } else if (curVal < low) {
                cur = cur.right; // 小于目标区间，则往大的方向搜索
            } else if (curVal > high) {
                cur = cur.left; // 大于目标区间，则往小的方搜索
            }
        }

        // ② 基于这个节点，递归检索处理其左右子节点：节点左侧的值均小，节点右侧的值均大
        if (cur == null) {
            return null; // 不存在指定区间的节点，直接删除整棵树
        }

        // 左子树处理：将左子节点与low边界比较
        TreeNode lPointer = cur;
        while (lPointer.left != null) {
            // 校验左子节点值与low的关系
            if (lPointer.left.val < low) {
                // lPointer.left.val<low
                lPointer.left = lPointer.left.right; // 当前遍历位置节点的左子节点可以直接剪(二叉搜索树特性)，则其右子节点上位等待下次遍历
            } else {
                // lPointer.left.val>=low
                lPointer = lPointer.left; // 继续下一个节点的遍历
            }
        }

        // 右子树处理：将右子节点与high边界比较
        TreeNode rPointer = cur;
        while (rPointer.right != null) {
            // 校验右子节点值与high的关系
            if (rPointer.right.val > high) {
                // rPointer.right.val>high
                rPointer.right = rPointer.right.left;
            } else {
                // rPointer.val<=high
                rPointer = rPointer.right; // 继续下个节点的遍历
            }
        }

        // 返回处理后的节点
        return cur;
    }
}