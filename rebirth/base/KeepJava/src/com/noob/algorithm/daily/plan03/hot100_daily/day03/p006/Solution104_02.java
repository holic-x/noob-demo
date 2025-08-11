package com.noob.algorithm.daily.plan03.hot100_daily.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 104 二叉树的最大深度 - https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class Solution104_02 {

    /**
     * 思路分析：
     * - 根节点->最远叶子节点的最长路径的节点数
     * - 广度优先算法:计算层数
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;
        // 遍历队列
        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
            // 遍历完成，层数+1
            depth++;
        }

        // 返回层数
        return depth;
    }


}
