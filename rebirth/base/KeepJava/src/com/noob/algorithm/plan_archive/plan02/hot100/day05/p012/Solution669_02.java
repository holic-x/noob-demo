package com.noob.algorithm.plan_archive.plan02.hot100.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 669 修剪二叉搜索树 - https://leetcode.cn/problems/trim-a-binary-search-tree/description/
 */
public class Solution669_02 {

    /**
     * 思路分析：修建二叉搜索树，让所有节点的值落在`[low,high]`中，需保留所有父子关系
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        /**
         * 迭代思路：遍历节点，校验节点值是否落在[low,right]
         * ① 如果节点值均没有落在[low,high]区间，说明整棵树都要裁掉
         * ② 找到第1个节点值落在[low,high]的节点cur，然后递归（迭代）剪裁这棵树
         * - cur 本身满足 low <= cur.val <= high,因此对于其左节点只需要校验low边界，同理对于右节点只需要校验high边界
         * - cur.left:
         *  - 如果leftNode.val < low 超出边界直接剪枝
         *  - 如果leftNode.val >= low 满足条件，等待下一轮
         * - cur.right:
         *  - 如果rightNode.val > high 超出边界直接剪枝
         *  - 如果rightNode.val <= low 满足条件，等待下一轮
         */

        return dfs(root, low, high);
    }


    // 递归校验
    private TreeNode dfs(TreeNode node, int low, int high) {
        if (node == null) {
            return null;
        }

        // 校验节点值与区间的关系，如果落在区间外则直接剪，落在区间内则递归处理
        int nodeVal = node.val;
        /*
        if (nodeVal >= low && nodeVal <= high) {
            // 递归处理
            node.left = dfs(node.left, low, high);
            node.right = dfs(node.right, low, high);
        } else if (nodeVal < low) {
            node.left = null;
            node.right = dfs(node.right, low, high);
        } else if (nodeVal > high) {
            node.right = null;
            node.left = dfs(node.left, low, high);
        }
        return node;
         */

        if (nodeVal >= low && nodeVal <= high) {
            // 递归处理
            node.left = dfs(node.left, low, high);
            node.right = dfs(node.right, low, high);
            return node;
        } else if (nodeVal < low) {
            return dfs(node.right, low, high);
        } else if (nodeVal > high) {
            return dfs(node.left, low, high);
        }
        return null;
    }

}
