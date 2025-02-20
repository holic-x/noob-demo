package com.noob.algorithm.plan_archive.plan01.day15;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡701 二叉搜索树中的插入操作
 */
public class Solution701_01 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // ① 获取二叉搜索树的中序遍历序列
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        // ② 将val插入中序遍历序列中适合的位置
        insertByBinarySearch(list, val);

        // ③ 递归构建二叉搜索树
        return buildHelper(list, 0, list.size() - 1);
    }

    // ① dfs(ldr:中序遍历)
    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);

    }

    // ② 二分法搜索，并插入序列在合适位置
    public void insertByBinarySearch(List<Integer> list, int val) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == val) {
                return;
            } else if (list.get(mid) < val) {
                left = mid + 1;
            } else if (list.get(mid) > val) {
                right = mid - 1;
            }
        }
        // 将val元素插入到left指定位置
        list.add(left, val);
    }

    // ③ 根据中序遍历序列构建二叉搜索树
    public TreeNode buildHelper(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        // 构建节点
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = buildHelper(list, left, mid - 1);
        node.right = buildHelper(list, mid + 1, right);
        // 返回构建节点
        return node;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        Solution701_01 s = new Solution701_01();
        s.insertIntoBST(node1, 5);
    }
}
