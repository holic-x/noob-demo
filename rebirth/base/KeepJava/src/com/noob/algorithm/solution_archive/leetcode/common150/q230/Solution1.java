package com.noob.algorithm.solution_archive.leetcode.common150.q230;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 230 二叉搜索树中第K小的元素
 */
public class Solution1 {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        ldr(root,list); // 获取中序遍历后的集合（升序集合）
        return list.get(k-1); // 下标为k-1的元素即为第k小的元素
    }

    /**
     * 中序遍历（left =》 root =》 right）
     */
    public void ldr(TreeNode node, List<Integer> list){
        if(node==null){
            return;
        }
        ldr(node.left,list);
        list.add(node.val);
        ldr(node.right,list);
    }
}
