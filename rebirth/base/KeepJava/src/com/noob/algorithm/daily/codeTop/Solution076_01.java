package com.noob.algorithm.daily.codeTop;

/**
 * 🔴 076 最小覆盖子串 - https://leetcode.cn/problems/minimum-window-substring/
 */
public class Solution076_01 {

    /**
     * 思路分析：最小覆盖子串（s中涵盖t所有字符的最小子串）
     * 滑动窗口思路：遍历字符串s，不断移入元素，然后判断当前子串是否满足覆盖子串概念，如果满足则不断移出窗口元素直到不满足，在这个过程中计算min
     */
    public String minWindow(String s, String t) {
        // 定义两个数组统计当前窗口内元素的出现个数以及t中元素的出现个数
        int[] sSubCnt = new int[128];
        int[] tCnt = new int[128];
        // 统计tCnt（初始化）
        for (char ch : t.toCharArray()) {
            tCnt[ch]++; // 字符统计
        }

        // 遍历字符串s，随后通过滑动窗口的形式得到min
        int min = Integer.MAX_VALUE;
        String minSubStr = "";
        // 定义双指针用于界定窗口边界
        int left = 0, right = 0;
        for (char ch : s.toCharArray()) {
            // 将字符划入窗口
            sSubCnt[ch]++;
            right++;
            // 校验当前窗口是否满足覆盖子串，持续校验，直到出现不满足覆盖子串才划入新数据
            while (valid(sSubCnt, tCnt)) {
                // 更新min min = Math.min(min, right - left + 1);
                if (min > right - left + 1) {
                    // 需更新min和对应子串
                    min = right - left + 1;
                    minSubStr = s.substring(left, right); // 注意此处的字符串截取范围
                }
                // 如果满足子串条件，则左窗口划出
                sSubCnt[s.charAt(left)]--;
                left++;
            }
        }
        // 返回结果
        return minSubStr;
    }


    // 校验子串s是否覆盖了t（通过字符统计的方式处理）sSubCnt\tCnt 分别表示对应字符的出现次数
    private boolean valid(int[] sSubCnt, int[] tCnt) {
        // 此处校验大小写字母
        for (int i = 'a'; i <= 'z'; i++) {
            if (sSubCnt[i] < tCnt[i]) {
                return false; // 当前字符不足以覆盖
            }
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            if (sSubCnt[i] < tCnt[i]) {
                return false; // 当前字符不足以覆盖
            }
        }
        // 校验通过，放行
        return true;
    }

}
