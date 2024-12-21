package com.noob.algorithm.daily.plan01.day05;

import java.util.HashMap;

/**
 * 🟢383 赎金信
 * 判断 ransomNote 能不能由 magazine 里面的字符构成
 */
public class Solution383_01 {

    // 字符统计法
    public boolean canConstruct(String ransomNote, String magazine) {
        // 构建辅助集合存储magazine中出现的字符及其出现次数
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 遍历字符串ransomNote，依次和map进行匹配（看字符够不够用）
        for (char c : ransomNote.toCharArray()) {
            // 取出字符并使用
            map.put(c, map.getOrDefault(c, 0) - 1);
            // 判断更新后是否出现负数，如果出现负数则说明字符不够用
            if (map.get(c) < 0) {
                return false;
            }
        }
        return true;
    }
}
