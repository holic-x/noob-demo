package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;


import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 101 对称二叉树 - https://leetcode.cn/problems/symmetric-tree/description/
 */
public class Solution101_02 {

    /**
     * 思路分析：
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 迭代法：采用BFS遍历思路，基于单队列或者双队列处理
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        // 遍历队列元素，每次取出两个节点进行校验
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            TreeNode q = queue.poll();

            // 校验p、q节点
            if (p == null && q == null) {
                continue;
            }
            if ((p == null && q != null) || (p != null && q == null)) {
                return false; // 不对称
            }
            if ((p != null && q != null) && p.val != q.val) {
                return false; // 不对称
            }

            // 将其左右子节点也纳入校验
            queue.offer(p.left);
            queue.offer(q.right);

            queue.offer(p.right);
            queue.offer(q.left);
        }

        // 校验通过
        return true;
    }

}
