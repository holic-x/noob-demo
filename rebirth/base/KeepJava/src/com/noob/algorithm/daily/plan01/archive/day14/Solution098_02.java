package com.noob.algorithm.daily.plan01.archive.day14;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 098 验证二叉搜索树
 */
public class Solution098_02 {

    // public int preVal = Integer.MIN_VALUE; // 定义字段维护当前遍历节点的上一个中序序列的值
    public long preVal = Long.MIN_VALUE;

    // LDR:中序遍历，校验中序遍历序列是否为连续升序(空间优化版本)
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return inorder(root);
    }

    // 中序遍历
    public boolean inorder(TreeNode node) {
        if (node == null) {
            return true;
        }
        // 校验左子树
        if (!inorder(node.left)) {
            return false;
        }

        // 处理节点（校验中序序列有序性）
        if (preVal >= node.val) {
            return false; // 非升序序列
        } else {
            preVal = node.val; // 更新
        }

        // 校验右子树
        return inorder(node.right);
    }
}
