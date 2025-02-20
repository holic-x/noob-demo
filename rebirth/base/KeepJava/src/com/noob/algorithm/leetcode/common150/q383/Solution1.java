package com.noob.algorithm.leetcode.common150.q383;

import java.util.ArrayList;
import java.util.List;

/**
 * 383 赎金信
 */
public class Solution1 {

    /**
     * ransomNote 中的字符可否由magazine中的字符构成（magazine中的字符只能使用一次）
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // 将magazine中的字符存入哈希表中
        List<Character> list = new ArrayList<>(magazine.length());
        for(int i=0;i<magazine.length();i++){
            list.add(magazine.charAt(i));
        }

        // 校验ransomNote中的字符是否可由magazine构成
        for(int i = 0;i<ransomNote.length();i++){
            // 如果不包含则返回false
            if(!list.contains(ransomNote.charAt(i))){
                return false;
            }
            // 移除已经使用过的元素
            int idx = list.indexOf(ransomNote.charAt(i));
            list.remove(idx);
        }

        return true;
    }
}
