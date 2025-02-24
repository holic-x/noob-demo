package com.noob.algorithm.solution_archive.dmsxl.leetcode.q337;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 337 打家劫舍III
 */
public class Solution2 {

    public Map<TreeNode, Integer> map = new HashMap<>();

    /**
     * 遍历每个节点选择偷或者不偷(dfs)
     * 计算优化：记忆化递推（记录已遍历节点的结果，避免重复计算）
     */
    public int rob(TreeNode root) {
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return node.val;
        }
        // 递归出口补充（记忆化搜索，如果已经遍历过的节点直接返回结果值，不重复检索）
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // 偷父节点
        int val1 = node.val;
        if (node.left != null) {
            val1 += rob(node.left.left) + rob(node.left.right); // 跳过node的左节点
        }
        if (node.right != null) {
            val1 += rob(node.right.left) + rob(node.right.right); // 跳过node的右节点
        }

        // 不偷父节点
        int val2 = rob(node.left) + rob(node.right); // 考虑node的左右孩子

        int maxVal = Math.max(val1, val2);
        map.put(node, maxVal); // 记录当前节点的最大偷窃方案（最高偷窃金额）

        // 两种方案选择最大的方案,返回结果
        return maxVal;
    }

}
