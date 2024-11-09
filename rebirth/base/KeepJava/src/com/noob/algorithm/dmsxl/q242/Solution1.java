package com.noob.algorithm.dmsxl.q242;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 有效的字母异位词（242）
 */
public class Solution1 {

    // 【1】计数法
    public boolean isAnagram(String s, String t) {
        // 如果两个字符串长度不一致则必然非字母异位词
        if(s.length() != t.length()){
            return false;
        }
        // 分别获取两个字符串的统计集合
        Map<Character,Integer> smap = getCharacterNum(s);
        Map<Character,Integer> tmap = getCharacterNum(t);
        // 遍历集合判断字符和出现次数是否完全匹配
        Iterator iterator = smap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character,Integer> entry = (Map.Entry<Character,Integer>)iterator.next();
            if(tmap.get(entry.getKey())!=entry.getValue()){
                return false;
            }
        }
        // 校验通过
        return true;
    }

    // 定义方法统计两个字符串中每个字符出现的次数
    public Map<Character,Integer> getCharacterNum(String str){
        Map<Character,Integer> map = new HashMap<>();
        for(char ch : str.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        return map;
    }
}
