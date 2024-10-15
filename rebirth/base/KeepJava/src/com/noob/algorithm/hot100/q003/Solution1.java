package com.noob.algorithm.hot100.q003;

import java.util.ArrayList;
import java.util.List;

/**
 * 03 无重复的最长子串
 */
public class Solution1 {

    /**
     * 暴力思路：遍历每个元素，判断当前元素后的所有子串是否包括该元素，在遍历的过程中更新max
     */
    public int lengthOfLongestSubstring(String s) {
        // 定义最大子串的长度
        int max = 0;

        // 遍历元素，判断当前元素后的所有子串是否包括该元素，在遍历的过程中更新max（不包括才更新）
        for (int i = 0; i < s.length(); i++) {
            List<Character> list = new ArrayList<>();
            for (int j = i; j < s.length(); j++) {
                // 当出现重复的元素，则说明这个序列断了
                if (list.contains(s.charAt(j))) {
                    // 记录最大子串长度
                    max = Math.max(max, j - i + 1);
                    break;
                }
                list.add(s.charAt(j));
            }
        }
        return max;
    }

}
