package com.noob.algorithm.plan_archive.plan01.day09;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢100 相同的树
 */
public class Solution100_02 {

    /**
     * 迭代法：
     * 如果两个树结构完全相同，则其遍历顺序应完全一致（此处需注意对于空节点需占位，才能确保结构一致）
     * 单队列思路：用一个队列接收两棵树的节点，每次取出两个节点校验，如果完全一致则认为两棵树的结构相同
     * - 此处需注意对非叶子结点的左右子节点的非空占位讨论，此处采取的方案是对于非叶子节点的左右节点均入队，然后再取出节点校验是否匹配的时候进行分情况讨论
     * - 由于不限定入队的子节点是否为空，因此需要对取出的两个节点进行null讨论
     * - ① curP、curQ 均为空，则说明匹配，跳过后面的子节点处理
     * - ② curP、curQ 中只有一个为null，说明不匹配（预期取出的两个节点要完全一致的，所以此处不需要讨论为什么只有一个为null。只要不匹配就返回false）
     * - ③ curP、curQ 两者均不为null，校验值是否匹配（若不匹配直接返回false），随后需要处理各自的左右子节点入队（p左q左、p右q右）
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 构建队列辅助遍历元素
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            // 每次校验取出队列的两个元素进行比较
            TreeNode curP = queue.poll();
            TreeNode curQ = queue.poll();

            // 此处限定空节点也可以入队占位，所以要对null的情况进行处理
            // ① 如果取到的两个节点都为空，则说明匹配，且两个节点都为空可以直接跳过后面的子节点处理
            if (curP == null && curQ == null) {
                continue;
            }

            // ② 如果取到的两个节点有一个为空则不匹配，返回false
            if ((curP == null && curQ != null) || (curP != null && curQ == null)) {
                return false;
            }

            // ③ 如果两个均为非空节点，且值不匹配则返回false
            if (curP.val != curQ.val) {
                return false; // 节点对应不一致，结构不同
            }

            // 处理两棵树的左右子树节点（p左q左、p右q右）：按照待比较元素分组,就算是null节点也要占位，确保顺序一致
            queue.offer(curP.left);
            queue.offer(curQ.left);
            queue.offer(curP.right);
            queue.offer(curQ.right);
        }

        // 校验通过说明一致
        return true;
    }
}
