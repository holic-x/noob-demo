package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

/**
 * 🟢 1047 删除字符串中的所有相邻重复项 - https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/
 */
public class Solution1047_02 {

    /**
     * 概要：删除两个相邻且相同的字母
     * 思路分析：
     * - 引入栈辅助处理：栈顶元素与当前遍历目标元素，如果栈为空或者栈顶元素与当前遍历元素不匹配的话则将元素加入栈，否则做消除（直接弹出）
     * - 可以使用StringBuffer模拟栈操作
     */
    public String removeDuplicates(String s) {
        StringBuffer buffer = new StringBuffer();
        // 定义栈顶指针模拟
        int topIdx = -1;

        // 遍历字符串处理元素
        for (char ch : s.toCharArray()) {
            // 如果模拟栈为空(topIdx = -1或者buffer.size==0)或者栈顶元素与cur元素不匹配则正常载入元素
            if (topIdx == -1 || buffer.charAt(topIdx) != ch) {
                buffer.append(ch);
                topIdx++; // 更新索引
            } else {
                // 其他场景（栈顶元素与cur元素匹配则消除（移除栈顶元素，继续遍历下一个位置））
                buffer.deleteCharAt(topIdx);
                topIdx--; // 更新索引
            }
        }

        // 直接返回模拟栈对应的字符串（已经保证了顺序）
        return buffer.toString();
    }
}
