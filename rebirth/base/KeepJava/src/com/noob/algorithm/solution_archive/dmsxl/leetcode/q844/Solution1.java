package com.noob.algorithm.solution_archive.dmsxl.leetcode.q844;

/**
 * 🟢844 比较含退格的字符串
 */
public class Solution1 {
    // 处理 + 校验
    public boolean backspaceCompare(String s, String t) {
        // 分别处理两个字符串中的退格，将其转化为正确的字符串
        StringBuffer sBuffer = new StringBuffer();
        for (char cur : s.toCharArray()) {
            if (cur != '#') {
                sBuffer.append(cur);
            } else {
                // 遇到空格，需移除最近加入的元素
                // if(!sBuffer.isEmpty()){ // 有的删才执行
                if (sBuffer.length() > 0) {
                    sBuffer.deleteCharAt(sBuffer.length() - 1);
                }
            }
        }

        // 同理处理t
        StringBuffer tBuffer = new StringBuffer();
        for (char cur : t.toCharArray()) {
            if (cur != '#') {
                tBuffer.append(cur);
            } else {
                // 遇到空格，需移除最近加入的元素
                // if(!tBuffer.isEmpty()){
                if (sBuffer.length() > 0) {
                    tBuffer.deleteCharAt(tBuffer.length() - 1);
                }
            }
        }

        // 检验正常转化空格后的两个字符串是否一致
        return sBuffer.toString().equals(tBuffer.toString()); // 需转化为字符串比较
    }
}
