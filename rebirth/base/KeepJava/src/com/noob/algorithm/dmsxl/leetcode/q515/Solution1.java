package com.noob.algorithm.dmsxl.leetcode.q515;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 515 在每个树行中最大值
 */
public class Solution1 {

    // 思路：基于分层遍历，记录行最大值，当层遍历结束封装结果集
    public List<Integer> largestValues(TreeNode root) {
        // root 为null处理
        if (root == null) {
            return new ArrayList<>();
        }
        // 定义结果集
        List<Integer> res = new ArrayList<>();

        // 构建辅助队列
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化队列

        // 遍历队列
        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            int curMax = Integer.MIN_VALUE; // 记录当层最大值

            // 遍历当层节点
            for (int i = 0; i < curSize; i++) {
                // 获取遍历节点
                TreeNode cur = queue.poll();

                // 更新最大值
                curMax = Math.max(curMax, cur.val);

                // 判断是否存在左右节点，存在则分别入队
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            // 当层遍历结束，记录结果
            res.add(curMax);
        }

        // 返回结果集
        return res;
    }
}
