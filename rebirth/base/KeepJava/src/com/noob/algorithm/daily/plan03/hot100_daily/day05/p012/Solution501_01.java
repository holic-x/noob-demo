package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 501 二叉搜索树中的众数 - https://leetcode.cn/problems/find-mode-in-binary-search-tree/description/
 * 寻找二叉搜索树中出现次数最多的数字（一个或多个，树节点值可重复）
 */
public class Solution501_01 {

    /**
     * 思路分析：LDR 思路（二叉搜索树顺序有序），在遍历的同时更新结果集合
     */
    public int[] findMode(TreeNode root) {
        dfs(root);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    // LDR
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // node 不为null
        dfs(node.left);
        // 处理节点
        update(node);
        dfs(node.right);
    }

    List<Integer> ans = new ArrayList<>(); // 定义众数结果集
    int maxCnt = 0; // 定义目前出现的最大频次
    int preVal = -1; // 定义上一个遍历数的值
    int curCnt = 0; // 定义当前可能的众数的出现次数

    // 更新
    private void update(TreeNode node) {
        // 校验更新条件
        if (preVal == node.val) {
            // 说明出现了相同的值（连续重复），累计统计值
            curCnt++;
        } else {
            // 出现了新的节点，重置计数器
            preVal = node.val;
            curCnt = 1;
        }

        // 校验更新后的次数
        if (curCnt > maxCnt) {
            // 出现了更大的次数，则说明新的众数出现了,需要重新计数
            ans.clear();
            // 重置
            maxCnt = curCnt;
            preVal = node.val;
            ans.add(node.val); // 载入新的众数
        } else if (curCnt == maxCnt) {
            // 恰好等于 说明出现多个众数
            ans.add(node.val);
        }
    }

}
