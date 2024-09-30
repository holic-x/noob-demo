package com.noob.algorithm.leetcode.q108;


import java.util.*;

/**
 * 108.将有序数组转化为二叉搜索树
 */
public class Solution1 {

    // 数组本身有序，将中点作为根节点，中点左侧的作为左子树、中点右侧的作为右子树
    public TreeNode sortedArrayToBST(int[] nums) {

        // 判断传入的nums长度是否为0，如果为0则不需要构建
        if(nums == null || nums.length == 0) {
            return null;
        }

        // 计算中点
        int mid = nums.length / 2;
        // 创建根节点进行存储
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums,0,mid)); // 将数组左边的元素作为左节点
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums,mid+1,nums.length)); // 将数组右边的元素作为右节点
        // 返回root
        return root;
    }

}

/* 二叉树节点类 */
class TreeNode {
    int val;         // 节点值
    TreeNode left;   // 左子节点引用
    TreeNode right;  // 右子节点引用

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

