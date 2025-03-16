package com.noob.algorithm.daily.codeTop;

import java.util.LinkedList;

/**
 * 🔴 679.24 点游戏 - https://leetcode.cn/problems/24-game/description/
 */
class Solution679_02 {
    boolean res = false;

    public boolean judgePoint24(int[] cards) {
        LinkedList<Double> list = new LinkedList<>();
        // 将每张卡片元素转化为double类型存储
        for (int card : cards) {
            list.add((double) card);
        }
        // 调用深度优先遍历算法进行计算
        dfs(list);
        return res;
    }

    private void dfs(LinkedList<Double> list) {
        // 最终集合只剩余1个元素，且满足一定精度范围差异则认为可凑成24点
        if (list.size() == 1 && Math.abs(list.get(0) - 24) < 1e-6) {
            res = true;
            return;
        }
        // 如果集合只剩下1个元素，但是不满足精度范围差异，则直接返回（递归出口）
        if (list.size() == 1) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (res) break;
            Double f = list.remove(i);
            for (int j = 0; j < list.size(); j++) {
                Double s = list.remove(j);
                for (int k = 0; k < 4; k++) {
                    if (k == 0) {
                        list.add(j, f + s);
                        dfs(list);
                        list.remove(j);
                    }
                    if (k == 1) {
                        list.add(j, f - s);
                        dfs(list);
                        list.remove(j);
                    }
                    if (k == 2) {
                        list.add(j, f * s);
                        dfs(list);
                        list.remove(j);
                    }
                    if (k == 3) {
                        list.add(j, f / s);
                        dfs(list);
                        list.remove(j);
                    }
                }
                list.add(j, s);
            }
            list.add(i, f);
        }
    }
}