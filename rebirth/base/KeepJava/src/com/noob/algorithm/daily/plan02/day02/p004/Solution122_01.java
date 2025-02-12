package com.noob.algorithm.daily.plan02.day02.p004;

/**
 * 🟢 LCR122 路径加密 - https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/
 */
public class Solution122_01 {

    /**
     * 将字符串path中的.分隔符替换成空格" "，随后返回加密后的字符串
     */
    public String pathEncryption(String path) {
        // 方式1：replace API 替换
        // String res = path.replace(".", " ");
        // return res;

        // 方式2：字符串分隔 + 组合
        String[] strs = path.split("\\.");
        // String res = String.join(" ", strs);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            if (!"".equals(strs[i])) {
                sb.append(strs[i]);
                if (i != strs.length - 1) {
                    sb.append(" "); // 添加间隔符
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution122_01 solution = new Solution122_01();
        solution.pathEncryption("......");
    }
}
