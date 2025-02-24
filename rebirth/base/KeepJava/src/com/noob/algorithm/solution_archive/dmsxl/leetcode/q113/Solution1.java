package com.noob.algorithm.solution_archive.dmsxl.leetcode.q113;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 113 路径总和II
 */
public class Solution1 {

    /**
     * BFS + 双队列
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义路径结果集合
        List<List<Integer>> res = new ArrayList<>();

        Deque<TreeNode> nodeQueue = new LinkedList<>(); // 节点队列
        nodeQueue.offer(root);

        Deque<Integer> pathSumQueue = new LinkedList<>(); // 路径和队列
        pathSumQueue.offer(root.val);

        Deque<List<Integer>> pathQueue = new LinkedList<>(); // 路径信息队列（记录完整路径）
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        pathQueue.offer(path);

        // 遍历节点，同步更新路径和并校验叶子结点处的targetSum是否匹配
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            int curPathSum = pathSumQueue.poll();
            List<Integer> curPath = pathQueue.poll();

            // 校验targetSum是否匹配（叶子结点处校验）
            if (cur.left == null && cur.right == null) {
                if (curPathSum == targetSum) {
                    // 将当前路径信息更新到结果集
                    res.add(new ArrayList<>(curPath));
                }
            }

            // 如果左右子节点不为空，则继续入队
            if (cur.left != null) {
                nodeQueue.offer(cur.left); // 1.左节点入队
                pathSumQueue.offer(curPathSum + cur.left.val); // 2.同步更新对应路径和
                // 3.同步更新对应路径信息
                curPath.add(cur.left.val);
                pathQueue.offer(new ArrayList<>(curPath)); // 此处是new一个新的集合并将元素封装过去，避免引用影响
                curPath.remove(curPath.size() - 1); // 回溯：复原现场
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right); // 1.右节点入队
                pathSumQueue.offer(curPathSum + cur.right.val); // 2.同步更新对应路径和
                // 3.同步更新对应路径信息
                curPath.add(cur.right.val);
                pathQueue.offer(new ArrayList<>(curPath)); // 此处是new一个新的集合并将元素封装过去，避免引用影响
                curPath.remove(curPath.size() - 1); // 回溯：复原现场
            }
        }

        // 遍历结束，返回结果集
        return res;
    }

}
