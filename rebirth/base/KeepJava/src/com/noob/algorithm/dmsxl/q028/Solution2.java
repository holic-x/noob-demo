package com.noob.algorithm.dmsxl.q028;

public class Solution2 {
    /**
     * 返回needle在haystack中出现的第1个位置
     */
    public int strStr(String haystack, String needle) {
        // return haystack.indexOf(needle);
        // 遍历字符串
        int hLen = haystack.length();
        char firstCh = needle.charAt(0);
        int nLen = needle.length();
        for (int i = 0; i < hLen; i++) { // 可以在此处限定i<hLen-nLen 界定边界
            // 如果首字符匹配则开始校验
            if (haystack.charAt(i) == firstCh) {
                // 自定义方法校验字符串是否匹配
                int start = i, end = i + nLen; // haystack 校验的起始位置
                int cur = 0; // needle 校验的位置
                boolean valid = true;
                if (end <= hLen) { // end 不能越界
                    for (int k = start; k < end; k++) {
                        if (haystack.charAt(k) != needle.charAt(cur++)) {
                            valid = false;
                            break; // 出现不匹配，无需继续校验
                        }
                    }
                    if (valid) {
                        return i; // 匹配方返回
                    }
                }
            }
        }
        // 没有匹配的内容
        return -1;
    }
}
