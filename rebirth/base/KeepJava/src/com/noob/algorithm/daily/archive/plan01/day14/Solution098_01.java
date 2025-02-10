package com.noob.algorithm.daily.archive.plan01.day14;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 098 验证二叉搜索树
 */
public class Solution098_01 {

    // LDR:中序遍历，校验中序遍历序列是否为连续升序
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        // 校验获取的中序遍历序列是否为连续升序
        if (list.isEmpty() || list.size() == 1) {
            return true;
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) >= list.get(i)) {
                return false;
            }
        }
        return true;

    }

    // 中序遍历
    public void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        // 递归处理左节点
        inorder(node.left, list);
        // 处理节点
        list.add(node.val);
        // 递归处理右节点
        inorder(node.right, list);
    }
}
