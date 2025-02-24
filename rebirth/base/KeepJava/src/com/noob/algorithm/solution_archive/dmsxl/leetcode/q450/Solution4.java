package com.noob.algorithm.solution_archive.dmsxl.leetcode.q450;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 450 删除二叉搜索树的节点
 */
public class Solution4 {

    // 递归思路：DFS（DLR）
    public TreeNode deleteNode(TreeNode root, int key) {
        return dfsDelNode(root,key);
    }


    /**
     * 递归删除的核心：此处遍历顺序不影响操作的结果，可以选择DLR进行遍历
     * D：找到目标节点`node`
     * - 遍历节点值和key匹配，则根据该节点是否存在左右节点进行处理
     * - 更新删除该节点后的节点关系（返回更新后的子树，即删除该节点之后的更新的子树）
     * LR：递归执行操作，递归更新当前的左、右子树
     * 此处的思路与迭代的逻辑很类似
     */
    public TreeNode dfsDelNode(TreeNode node, int key) {
        // 如果指定节点为null，说明待删除节点不存在（或者本身root为null、或者遍历到了叶子节点位置）
        if (node == null) {
            return node;
        }

        // 如果指定节点不为null，则进一步校验值

        // D: 删除指定节点，更新节点关系
        if (key == node.val) {
            // 判断待删除节点的左右子树状态，分情况讨论
            TreeNode curLeft = node.left;
            TreeNode curRight = node.right;
            TreeNode refreshNode; // 定义删除当前节点后，根据左右子树状态更新后的新节点（可以挂载到pre上）
            // 分情况讨论
            if (curLeft == null && curRight == null) {
                // 左右节点均为空，可以直接删除该节点
                refreshNode = null;
            } else if (curLeft != null && curRight == null) {
                // 左节点不为空，右节点为空，则左节点上位成为新的子树节点
                refreshNode = curLeft;
            } else if (curLeft == null && curRight != null) {
                // 右节点不为空，左节点为空，则右节点上位成为新的子树节点
                refreshNode = curRight;
            } else {
                // 左右节点都不为空，则需将原左子节点挂载到原右子节点的最左节点，此时更新后的右节点上位成为新的子树节点
                TreeNode findLeft = curRight; // 定义遍历节点查找原右子节点的最左节点
                while (findLeft.left != null) {
                    findLeft = findLeft.left;
                }
                findLeft.left = curLeft; // 将原左子节点挂载到原右子节点的最左节点
                refreshNode = curRight; // 更新后的右节点上位成为新的子树节点
            }
            // 返回节点
            return refreshNode;
        }

        // L R
        if (key < node.val) {
            node.left = dfsDelNode(node.left, key); // 递归遍历左节点执行删除操作
        } else if (key > node.val) {
            // 递归操作
            node.right = dfsDelNode(node.right, key); // 递归遍历右节点执行删除操作
        }

        // 返回更新后的节点
        return node;
    }
}
