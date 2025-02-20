package com.noob.algorithm.solution_archive.leetcode.common150.q103;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.*;

/**
 * 103 二叉树的锯齿形层序遍历
 */
public class Solution1 {


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();

        Deque<TreeNode> queue = new LinkedList<>(); // 定义辅助队列
        queue.offer(root); // 初始化
        boolean leftToRight = false; // true:从左到右；false：从右到左 (因为初始化的时候已经是从左到右的顺序，因此此处初始化为false)
        // 遍历队列元素
        while (!queue.isEmpty()) {
            // 分层遍历
            List<Integer> curList = new ArrayList<>();
            int curSize = queue.size();

            for (int i = 0; i < curSize; i++) {
                // 取出节点
                TreeNode node = queue.poll();
                curList.add(node.val);
                // 加入左右节点(正常录入节点)
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (leftToRight) {
                Collections.reverse(curList);
            }
            ans.add(curList); // 当层遍历完成，加入结果集(判断是否需要对当层数据结果反转)
            leftToRight = !leftToRight; // 当层遍历完成，切换下一轮次的遍历顺序
        }
        // 返回遍历结果集
        return ans;
    }


}
