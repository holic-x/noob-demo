package com.noob.algorithm.daily.plan03.hot100_random.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_01 {

    private int cnt ;

    /**
     * 思路分析：
     * 统计节点个数可以通过遍历的方式进行统计，例如DFS、BFS统计
     */
    public int countNodes(TreeNode root) {
        dfs(root);
        return cnt;
    }

    // 递归处理
    private void dfs(TreeNode node){
        if(node==null){
            return;
        }

        cnt++;
        dfs(node.left);
        dfs(node.right);
    }
}
