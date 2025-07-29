package com.noob.algorithm.daily.plan03.hot100_daily.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_02 {

    /**
     * 思路分析：
     * 统计节点个数（回归到遍历的思路）
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int cnt = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            cnt++;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // 返回结果
        return cnt;
    }


}
