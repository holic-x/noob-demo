package com.noob.algorithm.dmsxl.leetcode.q383;

import java.util.HashMap;
import java.util.Map;

/**
 * 383 赎金信
 */
public class Solution1 {

    // 思路：计数法
    public boolean canConstruct(String ransomNote, String magazine) {
        // 统计magazine中每个字符的出现次数
        Map<Character,Integer> map = new HashMap<>();
        for(char ch : magazine.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        // 遍历ransomNote字符，判断是否匹配
        for(char ch : ransomNote.toCharArray()){
            if(map.containsKey(ch) && map.get(ch) > 0){ // 存在匹配字符，且使用次数大于0
                // 取出字符进行使用
                map.put(ch,map.get(ch)-1);
            }else{
                // 不存在匹配字符 或者 字符被用完了
                return false;
            }
        }

        return true;
    }

}
