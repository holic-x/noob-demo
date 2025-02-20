package com.noob.algorithm.solution_archive.leetcode.common150.q383;

import java.util.ArrayList;
import java.util.List;

/**
 * 383 赎金信
 */
public class Solution2 {

    /**
     * ransomNote 中的字符可否由magazine中的字符构成（magazine中的字符只能使用一次）
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // 校验ransomNote中的字符是否可由magazine构成
        for(int i = 0;i<ransomNote.length();i++){
            String findTarget = ransomNote.charAt(i) + "";
            // 如果不包含则返回false
            if(!magazine.contains(findTarget)){
                return false;
            }
            // 移除已经使用过的元素
            magazine = magazine.replaceFirst(findTarget,"");
        }
        return true;
    }
}
