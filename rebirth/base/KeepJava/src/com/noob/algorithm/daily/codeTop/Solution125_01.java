package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 125 验证回文串
 */
public class Solution125_01 {

    public boolean isPalindrome(String s) {
        // ① 处理字符串（将所有大写转化为小写，移除非字母数字字符）
        List<Character> list = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                list.add(Character.toLowerCase(ch)); // 转化为小写并载入集合
            }
        }

        // ② 验证回文串（双指针）
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left) == list.get(right)) {
                // 继续下个位置校验
                left++;
                right--;
            } else {
                return false;
            }
        }

        // 校验通过
        return true;
    }
}
