package com.noob.algorithm.solution_archive.leetcode.common150.q205;

import java.util.HashMap;
import java.util.Map;

/**
 * 205 同构字符串
 */
public class Solution2 {

    /**
     * 定义一个Map存储S->T对应的映射集
     */
    public boolean isIsomorphic(String s, String t) {
        // 定义哈希表存储S->T的映射集合（key为s对应字符，value为其在T的对应映射字符）
        Map<Character, Character> map = new HashMap<>();
        // 遍历元素，构建映射集并进判断
        for (int i = 0; i < s.length(); i++) {
            char curS = s.charAt(i);
            char curT = t.charAt(i);
            // 如果map中key中包括a，则说明映射存在，需进一步校验t对应位置的值是否和value匹配
            if(map.containsKey(curS)){
                // 判断映射是否匹配
                if(curT!=map.get(curS)){
                    return false;
                }
            }else{
                // 如果map中key不包括curS，则拆分情况讨论，则进一步判断curT是否存在于value中
                if(map.containsValue(curT)){
                    // 如果存在，则说明curT字符已有映射关系存在，且该映射关系并不是curS，因此直接返回false
                    return false;
                }else{
                    // 如果不存在，则说明可以构建一组映射关系
                    map.put(curS,curT);
                }
            }
        }
        return true;
    }
}
