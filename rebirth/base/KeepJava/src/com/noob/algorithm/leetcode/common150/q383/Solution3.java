package com.noob.algorithm.leetcode.common150.q383;

import java.util.Arrays;

/**
 * 383 赎金信
 */
public class Solution3 {

    /**
     * ransomNote 中的字符可否由magazine中的字符构成（magazine中的字符只能使用一次）
     * 排序+指针比较法
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // 对两个字符串内容进行排序
        char[] ransomNoteArr = ransomNote.toCharArray();
        char[] magazineArr = magazine.toCharArray();
        Arrays.sort(ransomNoteArr);
        Arrays.sort(magazineArr);

        // 依次遍历数组元素，如果ransomNote可以走完全程则说明匹配
        int rp = 0,mp = 0; // rp 表示ransomNote遍历指针，mp表示magazine遍历指针
        while(rp<ransomNoteArr.length && mp<magazineArr.length){
            if(ransomNoteArr[rp]==magazineArr[mp]){
                // 匹配，则两个指针继续向前
                rp++;
                mp++;
            }else{
                // 不匹配，则寻找下一个满足的mp位置
                mp++;
            }
        }

        // 遍历完成（当两个字符串其中一个走到尾部，说明遍历结束）
        return rp==ransomNoteArr.length;
    }
}
