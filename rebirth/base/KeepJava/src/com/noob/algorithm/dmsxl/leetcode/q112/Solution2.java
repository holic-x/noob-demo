package com.noob.algorithm.dmsxl.leetcode.q112;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 112 路径总和
 */
public class Solution2 {

    // 递归法
    public boolean hasPathSum(TreeNode root, int targetSum) {

    }

    public int dfs(TreeNode node, int curPathSum, int targetSum) {
        if (node == null) {
            return 0;
        }

        // 遇到叶子节点，判断curPathSum
        if(node.left==null&&node.right==null){
            if(curPathSum==targetSum){
                return ;
            }
        }

        int mid = curPathSum + node.val;
        int left = dfs(node.left, curPathSum,targetSum);
        int right = dfs(node.right, curPathSum,targetSum);
        return mid + left + right;
    }


}
