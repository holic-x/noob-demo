package com.noob.algorithm.dmsxl.leetcode.q1382;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 1382 将二叉搜索树变平衡
 */
public class Solution1 {

    public TreeNode balanceBST(TreeNode root) {
        // 获取二叉搜索树中序遍历的结果(二叉搜索树中序遍历为有序列表)
        List<Integer> inorder = new ArrayList<>();
        dfs(root, inorder);
        // 根据中序遍历构建节点
        TreeNode newTreeRoot = buildHelper(inorder, 0, inorder.size() - 1);
        // 返回构建的平衡树
        return newTreeRoot;
    }

    // 中序遍历(LRD)
    public void dfs(TreeNode node, List<Integer> list) {
        // 递归出口
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

    // 平衡树构建辅助函数
    public TreeNode buildHelper(List<Integer> inorder, int start, int end) {

        // 条件控制
        if (start > end) {
            return null;
        }

        // 构建根节点
        int mid = (end - start) / 2 + start;
        TreeNode node = new TreeNode(inorder.get(mid));
        // 构建左右节点
        node.left = buildHelper(inorder, start, mid - 1);
        node.right = buildHelper(inorder, mid + 1, end);

        // 返回构建节点
        return node;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        Solution1 s = new Solution1();
        s.balanceBST(node1);
    }

}
