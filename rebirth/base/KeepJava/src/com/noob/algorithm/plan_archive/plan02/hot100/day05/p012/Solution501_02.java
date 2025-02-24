package com.noob.algorithm.plan_archive.plan02.hot100.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.*;

/**
 * 🟢 501 二叉搜索树中的众数 - https://leetcode.cn/problems/find-mode-in-binary-search-tree/description/
 */
public class Solution501_02 {

    private List<Integer> res = new ArrayList<>(); // 记录众数结果集
    private int curKey = Integer.MIN_VALUE; // 记录当前遍历节点元素
    private int curCnt = -1; // 记录当前遍历节点出现频次
    private int maxCnt = -1; // 记录目前的最大出现频次

    /**
     * 寻找二叉搜索树中出现次数最多的数字（一个或多个，树节点值可重复）
     */
    public int[] findMode(TreeNode root) {
        // 调用递归检索
        inorder(root);
        // 返回结果
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    // 递归搜索
    private void inorder(TreeNode node){
        if(node==null){
            return;
        }

        // 中序遍历（LDR）
        inorder(node.left); // 递归左子树
        update(node); // 更新数据
        inorder(node.right); // 递归右子树
    }

    // 更新数据
    private void update(TreeNode node){
        // ① 先更新节点和出现频次
        if(curKey == node.val){
            // 出现连续重复
            curCnt++; // 计数+1
        }else{
            // 出现了新的节点
            curKey = node.val;
            curCnt = 1; // 从1开始计数
        }

        // ② 更新maxCnt
        if(curCnt==maxCnt){
            // 出现了相同的众数,直接载入结果集
            res.add(node.val);
        }else if(curCnt>maxCnt){
            // 出现了出现频次更高的数字，需要更新maxCnt
            maxCnt = curCnt;
            // 清空当前的结果集，重新载入新的众数
            res.clear();
            res.add(node.val);
        }else{
            // 无需操作
        }
    }

}
