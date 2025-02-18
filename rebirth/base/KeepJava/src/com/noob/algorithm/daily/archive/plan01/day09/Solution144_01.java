package com.noob.algorithm.daily.archive.plan01.day09;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 144 二叉树的前序遍历
 */
public class Solution144_01 {

    public List<Integer> preorderTraversal(TreeNode root) {
        // 初始化结果集
        List<Integer> res = new ArrayList<>();
        // 调用dfs（DLR）
        dlr(root, res);
        // 返回结果集
        return res;
    }

    // 前序遍历：DLR(preorder)
    public void dlr(TreeNode node, List<Integer> list) {
        // 递归出口
        if (node == null) {
            return;
        }
        // 处理
        list.add(node.val); // D
        dlr(node.left, list); // L
        dlr(node.right, list); // R
    }

}
