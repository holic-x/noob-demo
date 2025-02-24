package com.noob.algorithm.solution_archive.leetcode.common150.q098;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 098 验证二叉搜索树
 */
public class Solution1 {

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        ldr(root, list); // 获取中序遍历结果
        // 验证是否为BST（即验证是否连续升序）
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) { // 出现降序(且二叉搜索树不能有相等的元素)
                return false;
            }
        }
        return true;
    }


    public void ldr(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        ldr(node.left, list);
        list.add(node.val);
        ldr(node.right, list);
    }

}
