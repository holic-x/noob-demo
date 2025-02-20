package com.noob.algorithm.plan_archive.plan02.hot100.day04.p010;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 404 左叶子之和 - https://leetcode.cn/problems/sum-of-left-leaves/description/
 */
public class Solution404_01 {

    /**
     * 思路分析：基于层序遍历思路
     * ❌ 错误思路：统计除了第1层之外的每层的第1个叶子节点元素(错误，不符合左叶子的定义)
     * 正确思路：存在左叶子表示存在左节点且该左节点为叶子节点（cur.left!=null && cur.left.left==null && cur.left.right==null）
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();

                // 校验节点是否存在左叶子，存在则累加
                if (node.left != null && node.left.left == null && node.left.right == null) {
                    sum += node.left.val; // 存在左叶子，累加左叶子和
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        Solution404_01 s = new Solution404_01();
        s.sumOfLeftLeaves(node);
    }

}
