package com.noob.algorithm.dmsxl.leetcode.q257;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 257 二叉树的所有路径
 */
public class Solution1 {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<String> curStr = new LinkedList<>();
        dfs(root, res, curStr);
        return res;
    }

    public void dfs(TreeNode node, List<String> res, List<String> curStr) {
        if (node == null) {
            return;
        }

        // 非叶子节点，继续遍历
        curStr.add(String.valueOf(node.val));

        if (node.left == null && node.right == null) {
            // 遍历到叶子结点，记录当前路径
            res.add(String.join("->", curStr));
            return;
        }

        dfs(node.left, res, curStr);
        curStr.remove(String.valueOf(node.val));

        dfs(node.right, res, curStr);
        curStr.remove(String.valueOf(node.val));
    }

}
