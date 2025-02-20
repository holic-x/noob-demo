package com.noob.algorithm.leetcode.common150.tree;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先遍历（先序DLR、中序LDR、后序LRD）
 */
public class DFSSearch {
    /**
     * 先序遍历：DLR
     * 根节点=》左节点=》右节点
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dlr(root,list);
        return list;
    }

    public void dlr(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        dlr(root.left, list);
        dlr(root.right, list);
    }


    /**
     * 中序遍历：LDR
     * 左节点=》根节点=》右节点
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        ldr(root,list);
        return list;
    }

    public void ldr(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        ldr(root.left, list);
        list.add(root.val);
        ldr(root.right, list);
    }

    /**
     * 后续遍历：LRD
     * 左节点=》右节点=》根节点
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        lrd(root,list);
        return list;
    }

    public void lrd(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        lrd(root.left, list);
        lrd(root.right, list);
        list.add(root.val);
    }

}
