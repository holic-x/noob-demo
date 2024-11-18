package com.noob.algorithm.dmsxl.leetcode.q513;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 513 找树左下角的值
 */
public class Solution2 {

    int levelMax = -1; // 当前遍历的最大层数
    int target = -1; // 目标元素

    /**
     * DFS：递归
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 递归
        dfs(root, 0);
        return target;
    }

    // DFS：DLR
    public void dfs(TreeNode node, int curLevel) {
        if (node == null) {
            return;
        }

        // 判断是否为新层，如果为新的一层则更新levelMax并更新target（每次遍历新层的第一个节点为当层的左节点）
        if (curLevel > levelMax) {
            levelMax = curLevel; // 更新当前最大层
            target = node.val;
        }

        // 递归左、右节点
        dfs(node.left, curLevel + 1);
        dfs(node.right, curLevel + 1);
    }
}
