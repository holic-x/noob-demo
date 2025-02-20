package com.noob.algorithm.plan_archive.plan01.day09;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 🟢100 相同的树
 */
public class Solution100_03 {

    /**
     * 递归法：判断两棵树是否结构完全一致，则需满足根节点匹配，则其左右子树也一一匹配，基于此可以采用递归的思路处理
     * - 递归处理的核心在于讨论节点在什么情况下要匹配，且需要讨论节点为空的情况（实际和迭代法讨论的情况类似）
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        /**
         * 递归出口：讨论p、q节点的null情况
         * ① p、q 均不为 null：两个节点匹配，返回true
         * ② p、q 中仅有一个为 null：两个节点无法匹配，返回false
         * ③ p、q 均为 null：需进一步校验两个节点的val是否匹配，根据校验结果处理，如果不匹配则返回false
         * 对于节点而言，其左右子树也要相应匹配
         */
        if (p == null && q == null) {
            return true;
        }

        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
        }

        // 递归处理
        boolean validLeft = isSameTree(p.left, q.left);
        boolean validRight = isSameTree(p.right, q.right);
        return validLeft && validRight;
    }
}
