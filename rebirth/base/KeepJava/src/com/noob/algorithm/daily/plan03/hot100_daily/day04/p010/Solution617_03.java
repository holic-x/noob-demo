package com.noob.algorithm.daily.plan03.hot100_daily.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 617 合并二叉树 - https://leetcode.cn/problems/merge-two-binary-trees/description/
 */
public class Solution617_03 {

    /**
     * 思路分析：单队列版本的另一种实现思路（用TreeNode[]存储待处理的一组节点）
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{root1, root2});

        while (!queue.isEmpty()) {
            TreeNode[] nodes = queue.poll();
            // 数据更新覆盖
            nodes[0].val += nodes[1].val;

            // 左节点处理
            if (nodes[0].left != null && nodes[1].left != null) {
                queue.offer(new TreeNode[]{nodes[0].left, nodes[1].left});
            } else if (nodes[0].left == null) {
                nodes[0].left = nodes[1].left;
            }

            // 右节点处理
            if (nodes[0].right != null && nodes[1].right != null) {
                queue.offer(new TreeNode[]{nodes[0].right, nodes[1].right});
            } else if (nodes[0].right == null) {
                nodes[0].right = nodes[1].right;
            }
        }

        // 返回更新后的root1
        return root1;
    }

}
