package com.noob.algorithm.plan_archive.baseStructure;

/**
 * TreeNode 树节点定义
 */
public class TreeNode {
    public int val;
    public TreeNode left; // 左子节点
    public TreeNode right;// 右子节点

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
