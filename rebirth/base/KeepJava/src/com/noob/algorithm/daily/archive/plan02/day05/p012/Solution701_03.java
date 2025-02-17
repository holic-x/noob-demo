package com.noob.algorithm.daily.archive.plan02.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;
import sun.text.normalizer.UBiDiProps;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 701 二叉搜索树中的插入操作 - https://leetcode.cn/problems/insert-into-a-binary-search-tree/description/
 */
public class Solution701_03 {

    /**
     * 思路分析：拆合思路（树->数组->插入元素后的新数组->树）
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        insertByBinarySearch(list, val);
        TreeNode buildTree = buildHelper(list, 0, list.size() - 1);
        return buildTree;
    }

    // 获取中序遍历序列
    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        // LDR
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    // 往列表中插入元素（确保升序序列）
    private void insertByBinarySearch(List<Integer> list, int val) {
        // 二分法检索下一个可插入位置
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (val <= list.get(mid)) {
                right = mid - 1;
            } else if (val > list.get(mid)) {
                left = mid + 1;
            }
        }

        // left 为待插入位置
        list.add(left, val);
    }

    // 基于有序列表list构建新的二叉搜索树
    private TreeNode buildHelper(List<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }
        // 选择中点作为D
        // int mid = (end + start) / 2;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(list.get(mid));
        // 递归构建左、右子树
        node.left = buildHelper(list, start, mid - 1);
        node.right = buildHelper(list, mid + 1, end);
        // 返回构建的节点
        return node;
    }
    // 1 2 3 4 5 7

    public static void main(String[] args) {
        Solution701_03 s = new Solution701_03();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
        s.buildHelper(list, 0, 5);

    }

}
