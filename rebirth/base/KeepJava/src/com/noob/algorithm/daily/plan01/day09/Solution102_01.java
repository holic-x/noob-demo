package com.noob.algorithm.daily.plan01.day09;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡102 层序遍历
 */
public class Solution102_01 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        // root 校验
        if (root == null) {
            return new ArrayList<>();
        }

        // 构建结果集
        List<List<Integer>> res = new ArrayList<>();

        // 构建队列辅助分层遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化

        // 遍历队列，依次取出节点
        while (!queue.isEmpty()) {
            // 获取当前队列size（当层节点个数）
            int cnt = queue.size();
            List<Integer> list = new ArrayList<>();// 存储当层遍历结果
            while (cnt-- > 0) {
                TreeNode cur = queue.poll();
                list.add(cur.val); // 记录结果
                // 处理左右节点
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 当层遍历结束，将当前结果加入结果集
            res.add(list);
        }
        // 返回结果
        return res;
    }
}
