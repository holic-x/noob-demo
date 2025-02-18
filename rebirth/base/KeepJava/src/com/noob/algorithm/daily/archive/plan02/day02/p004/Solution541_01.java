package com.noob.algorithm.daily.archive.plan02.day02.p004;

/**
 * 🟢 541 反转字符串II - https://leetcode.cn/problems/reverse-string-ii/description/
 */
public class Solution541_01 {
    /**
     * 思路分析：给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符
     */
    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] sArr = s.toCharArray();
        // 每计数到2k个字符，就反转前k；如果剩余的字符不足k则全部反转，足k则反转k个
        for (int i = 0; i < n; i += 2 * k) {
            /**
             * 反转前k(注意反转区间的取值范围起点和终点)
             * 如果是2k内的则反转前k，说明反转范围是[i,i+k)
             * 如果是剩余字符串则校验剩余字符串是否足k，足k则反转范围取[i,i+k)，不足k则将剩余全部反转即[i,n)
             *此处需注意区间范围有效取值，结合案例分析
             */
            reverseStr(sArr, i, Math.min(i + k, n) - 1);
        }
        // 返回反转后的内容
        return String.valueOf(sArr);
    }

    // 反转指定字符串区间元素
    private void reverseStr(char[] sArr, int start, int end) {
        while (start <= end) {
            // 交换元素
            char temp = sArr[start];
            sArr[start] = sArr[end];
            sArr[end] = temp;
            // 指针移动
            start++;
            end--;
        }
    }

    public static void main(String[] args) {

        Solution541_01 solution = new Solution541_01();
        solution.reverseStr("abcdefg", 2); // res:bacdfeg

    }
}
