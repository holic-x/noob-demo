package com.noob.algorithm.daily.plan03.hot100_daily.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 513 寻找树左下角的值 - https://leetcode.cn/problems/find-bottom-left-tree-value/description/
 */
public class Solution513_02 {

    // 定义当前层数curLevel和最大层数maxLevel，如果递归过程第1次出现curLevel>maxLevel则说明换层，则可记录这个target
    // private int curLevel = -1;
    private int maxLevel = -1;
    private int target;


    /**
     * 思路分析：给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     * 假设二叉树中至少有一个节点
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        // 初始化target
        target = root.val;

        dfs(root, -1);
        return target;
    }

    // dfs 遍历
    private void dfs(TreeNode node, int curLevel) {
        if (node == null) {
            return;
        }

        // 校验curLevel与maxLevel
        if (curLevel > maxLevel) {
            // 分层，更新maxLevel、target（说明遍历到了每一层的第1个节点）
            maxLevel = curLevel;
            target = node.val;
        }

        // 递归处理左、右子树
        dfs(node.left, curLevel + 1);
        dfs(node.right, curLevel + 1);
    }
}
