package com.noob.algorithm.dmsxl.leetcode.q129;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 129 求根节点到叶节点数字之和
 */
public class Solution2 {

    // BFS 思路
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 构建队列辅助遍历
        Queue<TreeNode> nodeQueue = new LinkedList<>(); // 存储对应节点
        nodeQueue.offer(root);
        Queue<Integer> valQueue = new LinkedList<>(); // 存储对应的值（拼接值）
        valQueue.offer(root.val);

        // 定义结果
        int res = 0;

        // 遍历节点
        while (!nodeQueue.isEmpty()) {
            // 分层遍历处理
            int cnt = nodeQueue.size();
            while (cnt-- > 0) {
                // 取出节点
                TreeNode curNode = nodeQueue.poll();
                int curNodeVal = valQueue.poll(); // 从valQueue队列中取出节点值（注意此处并非curNode.val）

                // 节点处理：如果curNode为叶子节点则累加叶子结点的和
                if (curNode.left == null && curNode.right == null) {
                    res += curNodeVal; // 累加叶子结点的值
                }

                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                    valQueue.offer(curNodeVal * 10 + curNode.left.val); // 将节点值进行拼接（与nodeQueue的入队节点保持同步）
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                    valQueue.offer(curNodeVal * 10 + curNode.right.val);// 将节点值进行拼接（与nodeQueue的入队节点保持同步）
                }
            }
        }

        // 返回结果
        return res;
    }
}
