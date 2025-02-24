package com.noob.algorithm.solution_archive.dmsxl.leetcode.q513;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 513 找树左下角的值
 */
public class Solution1 {

    /**
     * 层序遍历
     * - 思路1【自上而下，从左到右】：返回最后一层的第1个元素
     * - 思路2【自上而下，从右到左】：最后出队的元素即为**最底层 最左边**节点的值（🚀推荐，不需要额外分层）
     */
    public int findBottomLeftValue(TreeNode root) {
        if(root==null){
            return 0;
        }

        int target = 0;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            target = cur.val; // 更新target
            // 自上而下、从右到左
            if(cur.right!=null){
                queue.offer(cur.right);
            }
            if(cur.left!=null){
                queue.offer(cur.left);
            }
        }
        return target; // 最后一个出队元素即为所得
    }
}
