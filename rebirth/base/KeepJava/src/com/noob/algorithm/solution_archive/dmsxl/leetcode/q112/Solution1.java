package com.noob.algorithm.solution_archive.dmsxl.leetcode.q112;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 112 路径总和
 */
public class Solution1 {

    // 层序遍历+双队列：参考【所有路径】的思路（只不过此处存储的不是路径信息，而是路径之和数据）
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        Deque<Integer> pathSumQueue = new LinkedList<>();
        pathSumQueue.offer(root.val);

        // 遍历节点，同步更新路径和
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            int curPathSum = pathSumQueue.poll();

            // 判断当前节点是否为叶子节点（如果叶子节点，则说明已构成一条路径，需判断路径和是否与target匹配）
            if (cur.left == null && cur.right == null) {
                if (curPathSum == targetSum) {
                    return true;
                }
            }

            // 存在左右子节点则继续入队，并同步更新路径和
            if (cur.left != null) {
                nodeQueue.offer(cur.left);
                pathSumQueue.offer(curPathSum + cur.left.val);
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right);
                pathSumQueue.offer(curPathSum + cur.right.val);
            }

        }

        // 节点遍历完成，没找到匹配数据
        return false;
    }
}
