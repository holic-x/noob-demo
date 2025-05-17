package com.noob.algorithm.daily.plan03.hot100_random.day02.p004;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * 🟢 LCR122 路径加密 - https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/
 * 概要：将字符串path中的.分隔符替换成空格" "，随后返回加密后的字符串
 */
public class Solution122_01 {

    /**
     * 思路分析：
     * 如果基于字符串拆分的方式，对于`......`这种情况拆出来的是空字符串，下面的思路并没有正确转化
     */
    public String pathEncryption(String path) {
        String[] split = path.split("\\.");
        // String ans = String.join(" ", split);
        // return ans;
        // 如果字符串不为空进行拼接
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            if (split[i] != null && !split[i].equals("")) {
                buffer.append(split[i]);
                // 判断是否为最后一个元素，决定拼接符的接入
                if (i != split.length - 1) {
                    buffer.append(" ");
                }
            }

        }
        // 返回结果
        return buffer.toString();
    }

    public static void main(String[] args) {
        Solution122_01 solution = new Solution122_01();
        solution.pathEncryption("......");
    }
}
