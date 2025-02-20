package com.noob.algorithm.solution_archive.dmsxl.leetcode.q450;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 450 删除二叉搜索树的节点
 */
public class Solution3 {

    // 递归思路：DFS（DLR）
    public TreeNode deleteNode(TreeNode root, int key) {
        // 递归出口
        if (root == null) {
            return root; // root==null 要删除的节点不存在（可能是root为null，也可能是递归到叶子节点），不作任何删除操作
        }

        TreeNode cur = root; // 构建遍历节点用于遍历树

        // D:如果找到目标节点，执行删除操作（判断其左右子树的关系,返回的是更新后的子树根节点信息）
        if (key == cur.val) {
            TreeNode curLeft = cur.left;
            TreeNode curRight = cur.right;
            TreeNode refreshNode;
            // 根据左右子节点是否为空进行判断
            if (curLeft == null && curRight == null) {
                refreshNode = null; // 无子节点，直接删除
            } else if (curLeft != null && curRight == null) {
                refreshNode = curLeft; // 左节点上位
            } else if (curLeft == null && curRight != null) {
                refreshNode = curRight; // 右节点上位
            } else {
                // 左右子节点都不为空，需做处理（将原左节点挂载到原右节点的最左节点，然后更新后的右节点作为新的子树节点）
                TreeNode findLeft = curRight;
                while (findLeft.left != null) {
                    findLeft = findLeft.left; // 找最左
                }
                findLeft.left = curLeft;
                refreshNode = curRight;
            }
            // 将节点进行挂载
            return refreshNode;
        }


        // L:利用二叉搜索树的特点,如果key<cur.val,说明目标节点在左区间，则递归左子树删除节点
        if (key < cur.val) {
            root.left = deleteNode(cur.left, key);
        }


        // R:利用二叉搜索树的特点,如果key>cur.val,说明目标节点在右区间，则递归右子树删除节点
        if (key > cur.val) {
            root.right = deleteNode(cur.right, key);
        }

        return root;
    }


}