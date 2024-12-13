package com.noob.algorithm.dmsxl.leetcode.q450;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 450 删除二叉搜索树中的节点
 */
public class Solution2 {

    // 迭代法：遍历树节点，找到要删除的目标节点，如果目标节点存在左右节点则需进行处理 todo 覆盖场景不足
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // 遍历树节点，找到目标节点
        TreeNode cur = root;
        TreeNode pre = null; // 记录每个节点的上一个遍历节点
        while (cur != null) {
            if (key == cur.val) {
                break; // 找到目标节点，跳出循环，等待处理
            } else if (key < cur.val) {
                pre = cur; // 更新pre
                cur = cur.left; // 去左边找
            } else if (key > cur.val) {
                pre = cur; // 更新pre
                cur = cur.right; // 去右边找
            }
        }

        // 判断cur的值是否存在（不存在则说明待删除的目标节点，存在则说明需要处理这个待删除的目标节点）
        if (cur == null) {
            return root; // 没找到待删除的目标节点，直接返回root
        }

        /**
         * 删除目标节点操作包括两部分：
         * 1.处理待删除目标节点的左右子节点关系（判断左右子节点是否为null，根据情况分析），得到删除该节点之后重新构建的子树根节点newChildNode（它会被拼在原pre上）
         *   - 左右子节点为空：可以直接删除cur节点，构建后的newChildNode节点为null
         *   - 左节点为空、右节点不为空：删除cur节点后，右节点上位，newChildNode为curRight
         *   - 左节点不为空、右节点为空：删除cur节点后，左节点上位，newChildNode为curLeft
         *   - 左右节点都不为空：删除cur节点后，需要将原curLeft挂载在curRight的最左节点的左子树，newChildNode为更新后的curRight
         * 2.根据步骤1中得到的新子树，结合pre节点判断该子树挂载位置
         *   - pre 为 null，删除的是根节点，因此 newChildNode 就是删除后的新树
         *   - pre 不为 null，判断原来删除的节点位置是在左还是右（看其左右节点的值和val比较）
         *        - pre.left == val 左边，则将newChildNode 挂在 pre 左侧
         *        - pre.right == val 右边，则将newChildNode 挂在 pre 右侧
         */
        // 1.处理待删除目标节点的左右子节点关系
        TreeNode newChildNode;
        TreeNode curLeft = cur.left;
        TreeNode curRight = cur.right; // 新子树节点，原删除节点的左、右节点
        if (curLeft == null && curRight == null) {
            newChildNode = null;
        } else if (curLeft != null && curRight == null) {
            newChildNode = curLeft;
        } else if (curLeft == null && curRight != null) {
            newChildNode = curRight;
        } else {
            // 找到右子树的最左节点
            TreeNode findLeft = curRight;
            while (findLeft.left != null) {
                findLeft = findLeft.left;
            }
            // 将原左子树挂载到右子树的最左节点的左侧
            findLeft.left = curLeft;
            // 更新后的右子树作为新的子树根节点
            newChildNode = curRight;
        }

        // 2.根据pre的值判断将newChildNode挂载到哪个位置
        if (pre == null) {
            return newChildNode; // 删除的是根节点，则新构建的子树根节点就是所得
        }

        // pre 不为null，将pre的左右节点值与val比较，看其原来是在左边还是右边
        if (pre.left != null && pre.left.val == key) {
            pre.left = newChildNode;

        } else if (pre.right != null && pre.right.val == key) {
            pre.right = newChildNode;
        }
        // 返回处理后的结果
        return root;
    }
}
