package com.noob.algorithm.leetcode.common150.q290;

import java.util.HashMap;
import java.util.Map;

/**
 * 290 单词规律
 */
public class Solution1 {

    /**
     * 映射法：通过构建字符和对应字符串的映射进行匹配
     * Map<key,value>:key存储字符，value存储字符对应映射的字符串
     * 遍历pattern每个字符c，判断其的映射关系是否满足双向连接的规律
     * 1.如果map中的key包括c：说明存在c相关的映射关系，则进一步校验对应映射的字符串是否匹配
     * 2.如果map中的key不包括c：则根据value进一步校验
     * - 如果map中的value不包括str（当前校验的字符串），则可以构建一组新的映射关系
     * - 如果map中的value包括str（当前校验的字符串），则说明当前字符串str已经存在一组映射关系，且其并不为c（即不满足双向连接）
     */
    public boolean wordPattern(String pattern, String s) {

        // 定义哈希表存储字符和字符串的映射关系
        Map<Character,String> map = new HashMap<>();

        // 对字符串元素进行切割
        String[] strs = s.split("\\s+");

        // 如果长度不匹配则无需进一步校验
        if(pattern.length()!=strs.length){
            return false;
        }

        // 遍历pattern每个字符，校验是否满足双向连接匹配规则
        for(int i=0;i<pattern.length();i++){
            // 判断map中的key是包含当前字符
            char cur = pattern.charAt(i);
            if(map.containsKey(cur)){
                // 进一步校验value是否匹配
                if(!strs[i].equals(map.get(cur))){
                    return false;
                }
            }else {
                // 判断map的value中是否包括当前要校验的字符串
                if(map.containsValue(strs[i])){
                    return false; // 存在则说明当前字符串已经有映射关系了，且这个映射关系对照的字符并不是cur
                }else{
                    map.put(cur,strs[i]); // 不存在，则可构建一组新的映射关系
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String pattern = "aaa";
        String s = "aa aa aa aa";
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.wordPattern(pattern,s));
    }

}
