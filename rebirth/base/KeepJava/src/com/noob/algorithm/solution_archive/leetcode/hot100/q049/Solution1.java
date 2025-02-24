package com.noob.algorithm.solution_archive.leetcode.hot100.q049;

import java.util.*;

/**
 * 字母异位词分组
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词（思路：排序法、计数法）
 */
public class Solution1 {

    // 排序法
    public List<List<String>> groupAnagrams(String[] strs) {
        // 定义返回结果
        List<List<String>> res = new ArrayList<List<String>>();

        // 定义对应的字母异位词分组映射
        Map<String,List<String>> map = new HashMap<>();

        // 排序法：先将所有的字符串序列进行排序，然后将排序后的结果去重加入集合
        for (String str : strs) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr); // 排序
            String sortedStr = new String(charArr); // 获取排序后的序列
            // 更新对应集合元素
            List<String> newList = map.getOrDefault(sortedStr, new ArrayList<>());
            newList.add(str);
            map.put(sortedStr, newList);
        }

        // 遍历结果集封装为返回结果
        Set<String> keys = map.keySet();
        keys.forEach(key -> {
            res.add(new ArrayList<>(map.get(key)));
        });

        // 返回结果
        return res;
    }
}
