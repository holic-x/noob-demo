package com.noob.algorithm.daily.plan03.hot100_random.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 404 左叶子之和 - https://leetcode.cn/problems/sum-of-left-leaves/description/
 */
public class Solution404_01 {

    /**
     * 思路分析：❌❌❌ 错误思路：左叶子概念理解错误，将每一层的第1个叶子节点当作左叶子节点。实际上应该判断左子节点，且左子节点为叶子节点
     */
    public int sumOfLeftLeaves(TreeNode root) {

        if (root == null) {
            return 0;
        }

        // 迭代处理
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;

        while (!queue.isEmpty()) {
            int curSize = queue.size();
            boolean isMark = false;
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();

                // 判断第1个出现的叶子节点
                if (node.left == null && node.right == null) {
                    if (!isMark) {
                        // 当层还没处理过叶子节点，符合记录条件，进行处理
                        ans += node.val;
                        isMark = true; // 标记已经处理过当层的左叶子
                    }
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
        }

        // 返回结果
        return ans;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        Solution404_01 s = new Solution404_01();
        s.sumOfLeftLeaves(node);
    }

}
