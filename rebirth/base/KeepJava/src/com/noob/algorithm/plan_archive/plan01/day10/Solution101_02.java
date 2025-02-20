package com.noob.algorithm.plan_archive.plan01.day10;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢 101 对称二叉树
 */
public class Solution101_02 {

    /**
     * 迭代法：构建队列对树进行比较
     */
    public boolean isSymmetric(TreeNode root) {
        return bfs(root, root);
    }

    // 层序遍历
    public boolean bfs(TreeNode p, TreeNode q) {
        // 构建辅助队列处理
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        // 校验队列
        while (!queue.isEmpty()) {
            // 每次取出两个节点进行处理
            TreeNode curP = queue.poll();
            TreeNode curQ = queue.poll();

            // 对curP、curQ 分情况讨论
            // ① curP和curQ均为空，说明匹配（校验符合，跳过本次处理）
            if (curP == null && curQ == null) {
                continue;
            }
            // ② curP、curQ中只有一个为空，说明不匹配
            if ((curP == null && curQ != null) || (curP != null && curQ == null)) {
                return false;
            }
            // ③ curP、curQ中均不为空，校验值是否匹配
            if (curP != null && curQ != null) {
                if (curP.val != curQ.val) {
                    return false;
                }
            }

            /**
             * 处理左右节点
             * 对称性校验是两棵树的左右子节点交叉校验：所以要确保每次入队的两个节点是待比较组
             * 此处使用同一个队列，则正常将待比较组入栈即可，每次取出两个节点进行比较
             * 如果使用不同队列处理，要确保结构一致，则对于空节点也要正常入队
             */
            queue.offer(curP.left);
            queue.offer(curQ.right);
            queue.offer(curP.right);
            queue.offer(curQ.left);
        }
        // 上述校验通过
        return true;
    }
}
