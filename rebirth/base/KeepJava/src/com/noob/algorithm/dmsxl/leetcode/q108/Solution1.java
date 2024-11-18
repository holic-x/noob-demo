package com.noob.algorithm.dmsxl.leetcode.q108;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

public class Solution1 {
    // 递归构建
    public TreeNode sortedArrayToBST(int[] nums) {
        // 数组本身有序，指定构建区间（闭区间[left,right]）
        return buildHelper(nums,0,nums.length-1);
    }

    // 递归构建(DFS思路：DLR)
    public TreeNode buildHelper(int[] nums,int left,int right){
        // 如果边界越界则不符合，返回null
        if(left>right){
            return null;
        }

        // 构建node
        int mid = left + (right-left)/2;
        TreeNode node = new TreeNode(nums[mid]);
        // 递归构建左、右节点
        node.left = buildHelper(nums,left,mid-1);
        node.right = buildHelper(nums,mid+1,right);
        // 返回构建好的节点
        return node;
    }
}
