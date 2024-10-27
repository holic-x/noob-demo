package com.noob.algorithm.common150.q242;

import java.util.Arrays;

public class Solution3 {
    public boolean isAnagram(String s, String t) {

        // 如果两个字符串长度不一致，则没有继续比较的必要(直接不满足)
        if(s.length()!=t.length()){
            return false;
        }

        // 将字符串转字符数组
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        // 对元素进行排序
        Arrays.sort(sArr);
        Arrays.sort(tArr);

        // 遍历元素，确认是否一一匹配
        for(int i=0;i<sArr.length;i++){
            if(sArr[i]!=tArr[i]){
                return false;
            }
        }

        return true;
    }
}
