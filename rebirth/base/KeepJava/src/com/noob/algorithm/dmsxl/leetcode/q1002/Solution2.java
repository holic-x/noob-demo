package com.noob.algorithm.dmsxl.leetcode.q1002;

import java.util.*;

/**
 * 🟢1002 查找共用字符
 */
public class Solution2 {

    public List<String> commonChars(String[] words) {
        int len = words.length;
        /**
         * 选择words[0]中的字符作为判断基础（因为字符要出现在所有字符串，则可任意选择一个字符串）
         * 此处如果选用Set存储就会忽略掉元素重复出现的情况,因此选用map记录每个单词中每个字符的出现次数
         */
        Map<Character, Integer>[] map = new HashMap[len];
        // 记录每个字符串中每个字符的出现次数
        for (int i = 0; i < len; i++) {
            map[i] = new HashMap<>(); // 初始化
            for (char cur : words[i].toCharArray()) {
                map[i].put(cur, map[i].getOrDefault(cur, 0) + 1);
            }
        }

        List<String> res = new ArrayList<>();
        // 以words[0]为基础，校验其他字符串中是否出现一定次数的该字符
        Set<Character> keySet = map[0].keySet();
        for (char cur : keySet) {
            int curCnt = map[0].get(cur); // 获取当前遍历字符出现次数
            while (curCnt-- > 0) { // 对于多次重复出现的字符，需根据出现次数进行处理
                // 校验其他字符串是否均出现该字符
                boolean allMatch = true;
                for (int i = 0; i < len; i++) {
                    boolean match = false;
                    for (Map.Entry<Character, Integer> entry : map[i].entrySet()) {
                        if (entry.getKey() == cur && entry.getValue() > 0) { // 字符匹配且字符可用次数大于0
                            match = true; // 当前匹配，设定标识
                            entry.setValue(entry.getValue() - 1); // 使用次数-1（表示已经校验过的字符不重复校验）
                            break;
                        }
                    }
                    allMatch &= match; // 控制校验状态
                }
                // 如果完全匹配（即所有单词中都出现该字符）
                if (allMatch) {
                    res.add(String.valueOf(cur));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"bella", "label", "roller"};
        Solution2 s = new Solution2();
        s.commonChars(words);
    }
}
