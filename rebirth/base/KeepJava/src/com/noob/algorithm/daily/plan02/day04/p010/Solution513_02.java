package com.noob.algorithm.daily.plan02.day04.p010;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟡 513 寻找树左下角的值 - https://leetcode.cn/problems/find-bottom-left-tree-value/description/
 */
public class Solution513_02 {

    private int target = -1;
    private int maxLevel = -1;

    /**
     * 思路分析：找出二叉树最底层最左边的节点的值
     * 基于递归遍历（每遍历1层，记录当层第1个节点的值）
     * - 涉及target（目标值）、curLevel（递归层数）、maxLevel（当前遍历的最大层数）
     */
    public int findBottomLeftValue(TreeNode root) {
        // 调用递归算法
        dfs(root, 1);
        return target;
    }

    // 递归遍历
    private void dfs(TreeNode node, int curLevel) {
        if (node == null) {
            return;
        }
        // 如果当前遍历层数大于maxLevel说明遍历到了新的一层（触发maxLevel和target的更新）
        if (curLevel > maxLevel) {
            maxLevel = curLevel;
            target = node.val;
        }

        // 递归左、右子树
        dfs(node.left, curLevel + 1);
        dfs(node.right, curLevel + 1);
    }
}
