package com.noob.algorithm.dmsxl.leetcode.q113;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;
import lombok.val;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 113 路径总和II
 */
public class Solution2 {

    List<List<Integer>> res = new ArrayList<>(); // 结果集
    List<Integer> path = new ArrayList<>(); // 当前遍历路径

    /**
     * DFS + 回溯
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 调用递归
        dfs(root, targetSum);
        return res;
    }

    // DFS（DLR） + 回溯：递归获取路径，递归过程中需要处理【路径信息】和【路径和信息】
    public void dfs(TreeNode node, int targetSum) {
        // 递归出口
        if (node == null) {
            return;
        }

        // 先更新完整路径
        targetSum = targetSum - node.val;
        path.add(node.val);

        // 判断当前路径是否匹配
        if (node.left == null && node.right == null) {
            // 判断叶子节点所在路径是否匹配
            if (targetSum == 0) { // 递减剩余值为0，则说明存在路径
                res.add(new ArrayList<>(path));
            }
        }

        // 执行递归
        dfs(node.left, targetSum);
        // 执行递归
        dfs(node.right, targetSum);
        // 回溯：复原递归参数
        path.remove(path.size() - 1);
    }

}
