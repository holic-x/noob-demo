package com.noob.algorithm.daily.plan01.archive.day12;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢404 左叶子之和
 */
public class Solution404_01 {

    // 层序遍历：
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode cur = queue.poll();

                // 判断每个节点是否存在左叶子节点，将其左叶子累加
                if (cur.left != null && cur.left.left == null && cur.left.right == null) {
                    res += cur.left.val;
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        // 返回结果
        return res;
    }
}
