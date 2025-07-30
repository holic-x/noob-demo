package com.noob.algorithm.daily.plan03.hot100_daily.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 104 二叉树的最大深度 - https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class Solution104_03 {

    // max 记录当前的最大深度
    int max = 0;

    // path 记录节点遍历路径
    List<Integer> path = new ArrayList<>();

    /**
     * 思路分析：
     * - 根节点->最远叶子节点的最长路径的节点数
     * - 深度问题 DFS 统计思路：获取所有叶子节点的路径，然后校验路径节点个数的max、min =》回溯思路
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 初始化根节点路径
        path.add(root.val);

        // 调用DFS方法遍历路径
        dfs(root);

        // 返回结果
        return max;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            max = Math.max(max, new ArrayList<>(path).size());
        }

        if (node.left != null) {
            path.add(node.left.val);
            dfs(node.left);
            path.remove(path.size() - 1);
        }

        if (node.right != null) {
            path.add(node.right.val);
            dfs(node.right);
            path.remove(path.size() - 1);
        }
    }

}
