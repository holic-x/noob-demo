package com.noob.algorithm.leetcode.common150.q049;

import java.util.*;

/**
 * 049 字母异位词分组
 */
public class Solution1 {
    /**
     * 排序法：排序后的字母异位词序列是完全一致的
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 定义结果
        Map<String,List<String>> map = new HashMap<>(); // key 字母异位词排序后的序列，value对应匹配的字符串

        // 遍历数组元素进行依次校验
        for(int i=0;i<strs.length;i++){
            // 获取校验源字符数组并进行排序
            char[] targetArr = strs[i].toCharArray();
            Arrays.sort(targetArr); // 排序
            String key = new String(targetArr);
            // 获取对应字母异位词映射，如果不存在则存入
            List<String> targetList = map.getOrDefault(key,new ArrayList<>());
            targetList.add(strs[i]); // 更新对应分组信息
            map.put(key,targetList); // 更新map
        }

        // 返回响应结果
        List<List<String>> res= new ArrayList<>();
        map.forEach((k,v)->{res.add(v);});
        return res;
    }
}
