package com.noob.algorithm.dmsxl.leetcode.q151;

/**
 * 151 反转字符串中的单词
 */
public class Solution1 {
    // 字符串切割、逆序遍历并拼接输出
    public String reverseWords(String s) {
        // 对字符串进行首尾去除空格处理
        s = s.trim();
        // 切割字符串（一个或多个空格）
        String[] strs = s.split("\\s+"); // 通过正则表达式切割
        // 逆序遍历字符串，拼接输出
        StringBuffer res = new StringBuffer();
        for(int i=strs.length-1;i>=0;i--){
            res.append(strs[i]);
            // 判断是否要添加空格（如果是最后一个元素则不需要加空格）
            if(i!=0){
                res.append(" ");
            }
        }
        // 返回结果
        return res.toString();
    }
}
