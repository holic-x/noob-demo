package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 701 二叉搜索树中的插入操作 - https://leetcode.cn/problems/insert-into-a-binary-search-tree/description/
 * 需确保插入后更新的树满足二叉搜索树特性，可能有多种结果
 */
public class Solution701_01 {

    /**
     * 思路分析：
     * 暴力思路：二叉搜索树的中序遍历是有序序列，因此可以遍历root获得其LDR，然后将数据插入到合适的位置得到一个新的LDR，随后对这个新序列重新构建一个二叉搜索树
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        // 初始化待插入位置
        int insertIdx = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (val < list.get(i)) {
                insertIdx = i; // 找到第一个比val大的元素，插入到它的前面
                break;
            }
        }
        list.add(insertIdx, val);

        // 重新构建二叉搜索树
        return buildHelper(list, 0, list.size() - 1);
    }


    private void dfs(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        // LDR
        dfs(node.left, ans);
        ans.add(node.val);
        dfs(node.right, ans);
    }


    private TreeNode buildHelper(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        // 每次取中点作为D
        int midIdx = left + (right - left) / 2;
        TreeNode node = new TreeNode(list.get(midIdx));
        node.left = buildHelper(list, left, midIdx - 1);
        node.right = buildHelper(list, midIdx + 1, right);
        return node;
    }

}
