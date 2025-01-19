package com.noob.algorithm.daily.plan01.archive.day12;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢112 路径之和
 */
public class Solution112_01 {

    // BFS
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            // return targetSum == 0;
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        Queue<Integer> pathQueue = new LinkedList<>();
        pathQueue.offer(root.val);

        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            int pathSum = pathQueue.poll();

            // 处理节点：判断当前节点是否为叶子节点，及路径总和
            if (cur.left == null && cur.right == null) {
                // 如果为叶子结点，则校验路径总和是否与targetSum相同
                if (pathSum == targetSum) {
                    // 找到一条满足条件的路径，返回true
                    return true;
                }
            }

            if (cur.left != null) {
                nodeQueue.offer(cur.left);
                pathQueue.offer(pathSum + cur.left.val);
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right);
                pathQueue.offer(pathSum + cur.right.val);
            }
        }
        return false;
    }
}
