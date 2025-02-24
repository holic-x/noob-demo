package com.noob.algorithm.solution_archive.leetcode.common150.q049;

import java.util.*;

/**
 * 049 字母异位词分组
 */
public class Solution2 {
    /**
     * 计数法：
     * count[] 形式：存储每个字母出现的次数（count[0]存储的是a出现次数，count[b]存储的是b出现次数，以此类推）
     * Map<k,v>：k 字符出现次数统计结果序列，v对应分组的字母异位词列表
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 定义结果
        Map<String,List<String>> map = new HashMap<>(); // key 字符出现次数统计结果序列，value对应匹配的字符串

        // 遍历字符串数据，分别进行统计
        for(String str : strs){
            int count[] = new int[26];
            for(int i=0;i<str.length();i++){
                count[str.charAt(i) - 'a'] ++; // 将字母转化为对应的数字进行存储：`a`对应0，`b`对应1等
            }

            // 统计完成将统计序列组合成key进行存储
            StringBuffer key = new StringBuffer();
            for(int i=0;i<26;i++){
                key.append('a'+i).append(count[i]);
            }

            // 根据key更新字母异位词列表
            List<String> targetList = map.getOrDefault(key.toString(),new ArrayList<>());
            targetList.add(str);
            map.put(key.toString(),targetList);
        }

        // 返回结果
        return new ArrayList<>(map.values());

    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        Solution2 s = new Solution2();
        s.groupAnagrams(strs);
    }
}
