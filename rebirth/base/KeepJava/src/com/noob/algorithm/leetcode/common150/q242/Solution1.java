package com.noob.algorithm.leetcode.common150.q242;

import java.util.*;

/**
 * 242 有效的字母异位词
 */
public class Solution1 {
    public boolean isAnagram(String s, String t) {

        // 如果两个字符串长度不一致，则没有继续比较的必要(直接不满足)
        if(s.length()!=t.length()){
            return false;
        }

        // 统计法，统计每个元素出现的次数是否一致
        Map<Character,Integer> sMap = new HashMap<>();
        Map<Character,Integer> tMap = new HashMap<>();

        // 遍历元素，统计字符出现次数
        for(int i=0;i<s.length();i++){
            // 统计s中字符出现次数
            char sCh = s.charAt(i);
            sMap.put(sCh,sMap.getOrDefault(sCh,0)+1);
            // 统计s中字符出现次数
            char tCh = t.charAt(i);
            tMap.put(tCh,tMap.getOrDefault(tCh,0)+1);
        }

        // 遍历统计的内容，确认是否一致
        if(sMap.size()!=tMap.size()){
            return false;
        }
        Set<Character> keys = sMap.keySet();
        for (Character key : keys) {
            if(sMap.get(key)!=tMap.get(key)){
                return false;
            }
        }

        return true;
    }
}
