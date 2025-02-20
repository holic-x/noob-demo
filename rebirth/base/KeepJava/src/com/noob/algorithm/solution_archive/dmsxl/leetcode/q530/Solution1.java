package com.noob.algorithm.solution_archive.dmsxl.leetcode.q530;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 530 二叉搜索树的最小绝对差
 */
public class Solution1 {

    public int getMinimumDifference(TreeNode root) {
        if(root==null){
            return 0;
        }

        // 1.获取中序序列
        List<Integer> list = new ArrayList<>();
        ldr(root,list);
        // 2.校验相邻两数
        int minSubVal = Integer.MAX_VALUE;
        for(int i=0;i<list.size()-1;i++){
            // 更新最小绝对差
            // minSubVal = Math.min(minSubVal,Math.abs(list.get(i+1)-list.get(i)));
            minSubVal = Math.min(minSubVal,list.get(i+1)-list.get(i));
        }
        // 返回结果
        return minSubVal;
    }

    // 中序遍历（LDR）
    public void ldr(TreeNode node, List<Integer> list){
        if(node==null){
            return;
        }
        ldr(node.left,list);
        list.add(node.val);
        ldr(node.right,list);
    }
}
