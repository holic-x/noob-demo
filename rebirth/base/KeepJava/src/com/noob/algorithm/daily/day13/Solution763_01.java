package com.noob.algorithm.daily.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🟡 763 划分字母区间 - https://leetcode.cn/problems/partition-labels/submissions/603151233/
 */
public class Solution763_01 {

    /**
     * 思路分析：
     * ① 遍历字符串，获取每个字符的最远出现位置
     * ② 再次遍历字符串，当遍历位置恰好到达目前已经出现的字符的最远出现位置，则进行截断
     */
    public List<Integer> partitionLabels(String s) {
        // 定义结果集存储数据
        List<Integer> ans = new ArrayList<>();

        // ① 遍历字符串，获取每个字符的最远出现位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            map.put(cur, i); // 最远位置覆盖
        }

        // ② 再次遍历字符串，判断截取位置
        int curMaxIdx = 0; // 记录当前已出现的字符的最远出现位置 // 初始化为0、-1均可
        int cutIdx = -1; // 记录截取位置（用于辅助获取截取字符串长度）
        for (int i = 0; i < s.length(); i++) {
            // 更新curMaxIdx
            curMaxIdx = Math.max(curMaxIdx, map.get(s.charAt(i)));
            // 如果i位置恰好走到这个位置，则进行截取
            if (curMaxIdx == i) {
                ans.add(i - cutIdx); // 添加截断结果
                cutIdx = i; // 更新截取位置
            }
        }
        // 返回结果
//        return ans.stream().mapToInt(Integer::valueOf).toArray();
        return ans;
    }

}
