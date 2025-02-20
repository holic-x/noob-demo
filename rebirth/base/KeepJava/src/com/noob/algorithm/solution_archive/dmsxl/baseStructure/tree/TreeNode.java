package com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree;

/**
 * 树节点
 */
public class TreeNode {

    public int val; // 节点值
    public TreeNode left; // 左子节点
    public TreeNode right; // 右子节点

    // 构造函数
    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
