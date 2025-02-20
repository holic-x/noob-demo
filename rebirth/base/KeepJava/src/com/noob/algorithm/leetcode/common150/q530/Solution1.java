package com.noob.algorithm.leetcode.common150.q530;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 530 二叉搜索树的最小绝对差
 */
public class Solution1 {
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        ldr(root, list); // 中序遍历
        // 对中序遍历后的序列进行最小值绝对值判断（相邻两数比较）
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            min = Math.min(min, Math.abs(list.get(i + 1) - list.get(i)));
        }
        return min;
    }

    public void ldr(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        // 中序遍历（left=》root=》right）
        ldr(node.left, list);
        list.add(node.val);
        ldr(node.right, list);
    }
}
