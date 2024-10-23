package com.noob.algorithm.common150.q058;

/**
 * 058 最后一个单词的长度
 */
public class Solution2 {
    // 思路：反向遍历（从第一个非空格字符开始遍历，后面遇到空格说明单词遍历结束了）
    public int lengthOfLastWord(String s) {

        // 定义结果
        int res = 0;

        // 逆序（反向遍历）
        for(int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            // 跳过尾部可能存在的空格字符，且要遍历的第一个字母肯定不为空(因此遍历退出的条件是字符为空格且res不为空)
            if(c==' ' && res !=0){
                break ;
            }

            // 非空格字符才计算
            if(c!=' '){
                res ++;
            }
        }

        // 返回结果
        return res;
    }
}
