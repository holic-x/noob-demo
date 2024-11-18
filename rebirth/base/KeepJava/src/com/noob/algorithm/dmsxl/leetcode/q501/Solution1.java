package com.noob.algorithm.dmsxl.leetcode.q501;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.*;

/**
 * 501 二叉搜索树中的众数
 */
public class Solution1 {

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }

        // 获取中序遍历序列，统计众数
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0; // 更新最大频次信息
        for (int item : list) {
            int cur = map.getOrDefault(item, 0);
            map.put(item, cur + 1);
            maxCount = Math.max(maxCount, cur + 1); // 更新最新的频次和最大频次关系
        }

        List<Integer> res = new ArrayList<>();
        // 遍历频次信息，构建众数结果集
        Set<Integer> ketSet = map.keySet();
        Iterator<Integer> iterator = ketSet.iterator();
        while (iterator.hasNext()) {
            int curKey = iterator.next();
            if (map.get(curKey) == maxCount) {
                res.add(curKey);
            }
        }

        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }

        // 返回结果集
        return resArr;
    }

    // 中序遍历
    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

}
