package com.noob.algorithm.plan01.day09;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 145 二叉树的后序遍历
 */
public class Solution145_01 {

    List<Integer> postorderTraversal(TreeNode root) {
        // 初始化结果集
        List<Integer> res = new ArrayList<>();
        // 调用lrd
        lrd(root, res);
        // 返回结果
        return res;
    }

    // 后序遍历LRD
    public void lrd(TreeNode node, List<Integer> list) {
        // 递归出口
        if (node == null) {
            return;
        }
        // 递归处理
        lrd(node.left, list); // L
        lrd(node.right, list); // R
        list.add(node.val); // D
    }
}
