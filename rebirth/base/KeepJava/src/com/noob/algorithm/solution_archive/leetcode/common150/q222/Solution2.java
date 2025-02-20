package com.noob.algorithm.solution_archive.leetcode.common150.q222;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 222 完全二叉树的节点个数
 */
public class Solution2 {

    /**
     * DFS：节点个数=左子树节点个数+右子树节点个数+1（自身）
     * 递归法
     */
    public int countNodes(TreeNode root) {
        // root 为null判断
        if(root==null){
            return 0;
        }
        int leftCount = countNodes(root.left); // left
        int rightCount = countNodes(root.right); // right
        return leftCount + rightCount + 1; // root
    }
}
