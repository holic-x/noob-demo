package com.noob.algorithm.dmsxl.leetcode.q669;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 669 修剪二叉搜索树
 */
public class Solution2 {

    /**
     * 迭代法：
     * 1.找到符合节点值在[low,high]范围内的节点cur
     * 2.迭代处理该节点的左右子树（左右子树的处理逻辑是类似的,核心关注两点：需要剪枝的情况、指针移动的情况，且左右子树的遍历指针要分开，起点都是从cur开始）
     * - 2.1 分别处理左节点的左右子树（左节点校验部分负责左边界low校验），此处leftNode、rightNode指代node的左右节点，算法实现的时候需注意引用问题(原地修改，不要构建新节点处理，否则还要重新赋值一遍才生效)
     * - 2.1.a.如果leftNode.val<low(需修剪的情况)，则根据二叉搜索树特性，则leftNode.left的所有值肯定也是小于low。因此可以直接剪掉leftNode和leftNode.left，只保留leftNode.right(即leftNode = leftNode.right)
     * - 2.1.b.如果leftNode.val>=low（指针移动的情况），则leftNode.right的所有值肯定大于等于low，因此【跳过】这些节点，移动指针（即node=node.left），循此往复，将所有的情况都覆盖在这些取值讨论中，直到遍历节点为空，遍历结束
     * <p>
     * - 2.2 处理右子树（右节点校验部分负责high校验）
     * - 2.2.a.如果rightNode.val>high(需修剪的情况)，则该右节点的右子树的所有值肯定大于high，因此可以直接剪掉rightNode和rightNode.right(即rightNode=rightNode.left)
     * - 2.2.b.如果右节点rightNode.val<=high（指针移动的情况），则rightNode.left的所有值肯定小于等于high，因此【跳过】这些节点，移动指针（即node=node.right），循此往复，将所有的情况都覆盖在这些取值讨论中，直到遍历节点为空，遍历结束
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {

        if (root == null) {
            return root;
        }

        TreeNode cur = root; // 定义遍历节点
        // 1.找到符合节点值在指定区间范围的节点
        while (cur != null) {
            if (low <= cur.val && cur.val <= high) {
                break; // 找到目标节点，退出循环
            } else if (cur.val < low) {
                cur = cur.right; // 遍历节点在区间左侧，需往右移动
            } else if (cur.val > high) {
                cur = cur.left; // 遍历节点在区间右侧，需往左移动
            }
        }

        // 2.判断这个节点是否存在，如果不存在则直接返回null（说明没有满足区间范围的节点，直接返回null），如果存在则分情况讨论进行剪枝
        if (cur == null) {
            return null;
        }

        // 2.1 符合区间范围的节点node存在，以这个节点为起点，分别进行左右子树的判断和剪枝操作
        TreeNode lPointer = cur; // 以当前节点为起点，遍历左子树
        while (lPointer.left != null) {
            // 根据当前节点的左节点leftNode的值与区间值左边界low的关系，进一步分析leftNode的左右子树和区间值的关系
            if (lPointer.left.val < low) {
                // 需要剪枝的情况(leftNode.left肯定<low可以直接剪，leftNode也可以剪，因此leftNode直接替换为其右节点)
                lPointer.left = lPointer.left.right; // 剪掉leftNode、leftNode.left
            } else {
                // leftNode.val >= low,则leftNode右子树一定符合条件，则继续遍历其左子树
                lPointer = lPointer.left; // 移动节点等待下一次剪枝判断
            }
        }

        // 2.2 同理，遍历右子树
        TreeNode rPointer = cur; // 以当前节点为起点，遍历右子树
        while (rPointer.right != null) {
            //  根据当前节点的右节点rightNode的值与区间值右边界high的关系，进一步分析rightNode的左右子树和区间值的关系
            if (rPointer.right.val > high) {
                // 需要剪枝的情况
                rPointer.right = rPointer.right.left; //rightNode.val > high满足，则rightNode.right.val > high肯定满足，因此直接剪掉rightNode.right、rightNode
            } else {
                // rightNode.val <= high，则rightNode左子树也一定符合条件，只需继续遍历其右子树
                rPointer = rPointer.right; // 移动节点等待下一次剪枝判断
            }
        }

        // 返回迭代后的节点信息（此处返回结果应以cur节点开始，前期遍历已经过滤掉不满足的节点记录了）
        return cur;
    }
}
