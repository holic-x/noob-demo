package com.noob.algorithm.solution_archive.dmsxl.leetcode.q501;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.*;

/**
 * 501 二叉搜索树中的众数
 */
public class Solution2 {

    // 定义当前遍历节点、出现频次、最大出现频次、众数结果集合
    List<Integer> res = new ArrayList<>();
    int curNodeVal;
    int curNodeCount = 0;
    int maxCount = 0;

    public int[] findMode(TreeNode root) {
        // 调用中序遍历方法
        dfs(root);
        // 封装结果集
        int[] nums = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            nums[i] = res.get(i);
        }
        return nums;
    }

    // 中序遍历:此处借助辅助参数构建以优化空间复杂度，不需要存储整个中序序列
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 左
        dfs(node.left);

        // 中(遍历当前节点，调用更新方法)
        update(node.val);

        // 右
        dfs(node.right);
    }

    public void update(int targetVal) {
        // 1.更新当前节点和相应计数
        if (curNodeVal != targetVal) { // 说明是新元素出现（如果是重复元素的话会存在连续相等的情况）
            // 新元素加入，重置计数
            curNodeVal = targetVal;
            curNodeCount = 1; // 节点计数重置计数
        } else {
            // 出现连续重复元素，统计累计，更新curNodeCount
            curNodeCount++;
        }

        // 2.maxCount更新
        if (curNodeCount == maxCount) {
            // 说明出现重复众数，加入结果集
            res.add(curNodeVal);
        } else if (curNodeCount > maxCount) {
            // 当前元素出现次数大于maxCount，说明出现了新的众数，直接清空当前的结果集并更新maxCount，开始新一轮校验
            maxCount = curNodeCount;
            res.clear();
            res.add(curNodeVal);
        }
    }

}
