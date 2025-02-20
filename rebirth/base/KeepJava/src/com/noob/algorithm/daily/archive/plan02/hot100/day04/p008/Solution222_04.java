package com.noob.algorithm.daily.archive.plan02.hot100.day04.p008;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_04 {

    /**
     * 思路分析：完全二叉树（除了最底层，其他层都填满）
     * 需要基于完全二叉树的特性来优化算法（例如递归优化：针对不同的树选择不同的遍历方式）
     * 例如满二叉树可以通过公式：2^h^-1 得到所有节点个数， 而对于其他普通的二叉树则选用递归的方式
     * 此处先理解节点个数统计的递归核心:L+R+1
     */
    public int countNodes(TreeNode root) {
        int cnt = dfs(root);
        return cnt;
    }

    // 以当前节点为根节点的子树的节点个数 = L + R + 1
    private int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }
        int L = dfs(node.left);// 递归统计左子树
        int R = dfs(node.right); // 递归统计右子树
        // 返回当前子树的节点个数
        return L + R + 1;
    }
}
