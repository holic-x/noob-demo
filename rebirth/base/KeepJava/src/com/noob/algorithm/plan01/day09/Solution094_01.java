package com.noob.algorithm.plan01.day09;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 094 二叉树的中序遍历
 */
public class Solution094_01 {


    public List<Integer> inorderTraversal(TreeNode root) {
        // 初始化结果集
        List<Integer> res = new ArrayList<>();
        // 调用dfs
        ldr(root, res);
        // 返回结果
        return res;
    }

    // 中序遍历（ldr（inorder））
    public void ldr(TreeNode node, List<Integer> list) {
        // 递归出口
        if (node == null) {
            return;
        }
        // 递归处理
        ldr(node.left, list);// L
        list.add(node.val);// D
        ldr(node.right, list); // R
    }
}
