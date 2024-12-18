package com.noob.algorithm.dmsxl.leetcode.q129;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡 129 求根节点到叶节点数字之和
 */
public class Solution3 {

    // List<Integer> res = new ArrayList<>(); // 记录所有路径构成的数字的和（可以记录每个叶子结点的值加入列表，或者直接累加得到结果）
    int res = 0;
    StringBuffer path = new StringBuffer(); // 记录遍历路径构成的数字

    // 回溯法
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 初始化
        path.append(root.val); // 将根节点加入路径
        // 调用dfs
        dfs(root);
        // 返回结果
        return res;
    }

    // 递归处理
    public void dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return;
        }
        // 如果是叶子结点则处理结果
        if (node.left == null && node.right == null) {
            int curPathVal = Integer.valueOf(path.toString()); // 将字符串数字转化为int类型
            res += curPathVal; // 累加结果(或者将路径节点值加入结果集合)
        }

        /**
         * 递归处理（回溯处理）
         * for(路径选择列表){
         *    ①处理节点
         *    ②调用递归
         *    ③回溯（恢复现场）
         * }
         * 此处的选择列表实际为`左节点`、`右节点`
         */
        if (node.left != null) {
            path.append(node.left.val); // 处理节点
            dfs(node.left); // 递归
            path.deleteCharAt(path.length() - 1); // 恢复现场
        }

        if (node.right != null) {
            path.append(node.right.val); // 处理节点
            dfs(node.right); // 递归
            path.deleteCharAt(path.length() - 1); // 恢复现场
        }
    }
}
