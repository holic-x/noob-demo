package com.noob.algorithm.plan_archive.plan01.day14;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.*;

/**
 * 🟢501 二叉搜索树中的众数
 */
public class Solution501_01 {

    public int[] findMode(TreeNode root) {
        // 调用递归函数获取二叉搜索树的中序遍历序列
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        // 返回所有众数（出现频率最高的元素）
        Map<Integer, Integer> map = new HashMap<>(); // map{数值，出现频率}
        int maxCnt = -1;
        for (int num : list) {
            int curCnt = map.getOrDefault(num, 0) + 1;
            map.put(num, curCnt);
            // 更新元素的最大出现频率
            maxCnt = Math.max(maxCnt, curCnt);
        }

        // 返回map中出现次数为maxCnt的元素
        List<Integer> res = new ArrayList<>();
        Set<Map.Entry<Integer, Integer>> entrys = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrys) {
            if (entry.getValue() == maxCnt) {
                res.add(entry.getKey());
            }
        }

        // 返回结果
//         return res.toArray(new int[res.size()]);
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    // 中序遍历
    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        // LDR
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
