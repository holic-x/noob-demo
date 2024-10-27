package com.noob.algorithm.common150.q242;

/**
 * 242 有效的字母异位词
 */
public class Solution2 {
    public boolean isAnagram(String s, String t) {
        // 如果两个字符串长度不一致，则没有继续比较的必要(直接不满足)
        if (s.length() != t.length()) {
            return false;
        }

        // 遍历元素，比较剔除
        for(int i=0;i<s.length();i++){
            t = t.replaceFirst(s.charAt(i) + "","");
        }

        // 如果最终t中的所有元素被移除，则说明满足字母异位词条件
        return t.length()==0;
    }
}
