package com.noob.algorithm.daily.plan03.hot100_daily.day06.p015;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 131 分割回文串 - https://leetcode.cn/problems/palindrome-partitioning/description/
 * 将字符串s分割为每个字串都为回文字符串
 */
public class Solution131_01 {

    /**
     * 思路分析：
     */
    public List<List<String>> partition(String s) {
        backTrack(s, 0);
        return ans;
    }


    /**
     * 分割回文串（回文串特点：正读&反读的顺序一致），此处要将s子串分割为每个子串都是回文串的场景，返回所有可能的方案
     *
     * @param args
     */

    private List<List<String>> ans = new ArrayList<>();
    private List<String> path = new ArrayList<>();

    private void backTrack(String s, int idx) {
        // 判断当前遍历位置是否到达末尾（所有字符是否遍历完成），确保纳入路径的都是满足条件的回文串
        if (idx >= s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 回溯处理
        for (int i = idx; i < s.length(); i++) {
            String subStr = s.substring(idx, i + 1); // 字符串截断校验
            if (isHuiWen(subStr)) {
                // 如果满足回文串需求，递归进入下一个截断位置的判断
                path.add(subStr);
                backTrack(s, i + 1); // 递归寻找下一个切割位置
                path.remove(path.size() - 1);
            }
        }

    }

    // 校验字符串是否为回文字符串
    private boolean isHuiWen(String str) {
        int left = 0, right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            // 继续校验下一个位置
            left++;
            right--;
        }
        // 校验通过
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        Solution131_01 so = new Solution131_01();
        so.partition(s);
    }
}
