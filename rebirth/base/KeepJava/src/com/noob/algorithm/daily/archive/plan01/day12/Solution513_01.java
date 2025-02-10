package com.noob.algorithm.daily.archive.plan01.day12;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡513 找树左下角的值
 */
public class Solution513_01 {

    // 层序遍历：自顶向下，从左到右（找最后一层的第1个元素：分层）
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int target = 0; // target 用于存储每一层的第1个元素
        while (!queue.isEmpty()) {
            // 分层遍历
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) {
                    target = cur.val; // target 用于记录每一层的第1个元素
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }

        // 返回结果(当所有节点遍历完成，此时target指向遍历的最后一层的第1个元素)
        return target;
    }
}
