package com.noob.algorithm.daily.plan01.day14;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.*;

/**
 * 🟢501 二叉搜索树中的众数
 */
public class Solution501_02 {

    public List<Integer> res = new ArrayList<>();
    public int curNodeVal; // 当前元素值
    public int curNodeCnt = 0; // 当前元素出现的频次（与curNodeVal对照）
    public int maxNodeCnt = 0; // 元素的最大出现频次

    public int[] findMode(TreeNode root) {
        // 递归处理
        dfs(root);

        // 返回结果
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    // 中序遍历
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        // LDR
        dfs(node.left);

        // 更新
        update(node.val);

        dfs(node.right);
    }

    // 更新：当前元素出现的频次和最大频次的关系
    public void update(int targetVal) {
        // 1.更新节点及其对应出现次数
        if (curNodeVal == targetVal) {
            // 说明元素重复出现，继续统计出现次数
            curNodeCnt++;
        } else {
            // 说明有新的元素出现，重置计数器
            curNodeVal = targetVal;
            curNodeCnt = 1;
        }

        // 2.更新maxNodeCnt
        if (curNodeCnt == maxNodeCnt) {
            // 出现了相同频次的元素，加入结果集
            res.add(curNodeVal);
        } else if (curNodeCnt > maxNodeCnt) {
            // 出现了更大出现频次，重置结果集
            maxNodeCnt = curNodeCnt;
            res.clear();
            res.add(curNodeVal);
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;

        Solution501_02 s = new Solution501_02();
        s.findMode(node1);
    }
}
