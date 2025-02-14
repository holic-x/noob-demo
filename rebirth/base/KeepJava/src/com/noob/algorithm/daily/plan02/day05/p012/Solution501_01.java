package com.noob.algorithm.daily.plan02.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.*;

/**
 * 🟢 501 二叉搜索树中的众数 - https://leetcode.cn/problems/find-mode-in-binary-search-tree/description/
 */
public class Solution501_01 {

    private List<Integer> list = new ArrayList<>();

    /**
     * 寻找二叉搜索树中出现次数最多的数字（一个或多个，树节点值可重复）
     */
    public int[] findMode(TreeNode root) {
        // 调用递归检索
        inorder(root);
        // 获取list中的众数
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : list){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        List<Integer> res = new ArrayList<>();
        // 校验众数
        int maxCnt = -1;
        int maxVal = Integer.MIN_VALUE;
        Set<Integer> keySet = map.keySet();
        for(int key : keySet){
            int curCnt = map.get(key);
            if(curCnt>maxCnt){
                // 出现了频次更高的选项
                maxCnt = curCnt;
                maxVal = key;
                // 清空当前结果集，加入更高出现频次的数字
                list.clear();
                list.add(key);
            }else if(curCnt==maxCnt){
                // 出现了相同频次的选项,直接加入
                list.add(key);
            }else{
                // 无操作
            }
        }

        // 返回结果
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    // 递归搜索
    private void inorder(TreeNode node){
        if(node==null){
            return;
        }

        // 中序遍历（LDR）
        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }

}
