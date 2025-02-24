package com.noob.algorithm.plan_archive.plan01.day12;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡513 找树左下角的值
 */
public class Solution513_02 {

    // 层序遍历：自顶向下，从右到左（基于此遍历方向返回的是最后一层的最后一个遍历元素，也是最后一个出队的元素）
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int target = 0; // target 用于存储每一个遍历的元素
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            target = cur.val; // target 用于存储每一个遍历的元素

            // 每层遍历顺序：从右到左
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            if (cur.left != null) {
                queue.offer(cur.left);
            }
        }

        // 返回结果(当所有节点遍历完成，此时target指向遍历的最后一层的第1个元素)
        return target;
    }
}
