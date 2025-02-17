package com.noob.algorithm.daily.plan02.day06.p015;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 131 分割回文串 - https://leetcode.cn/problems/palindrome-partitioning/description/
 */
public class Solution131_01 {

    List<List<String>> res = new ArrayList<>(); // 定义结果集
    List<String> path = new ArrayList<>(); // 定义当前路径（当前的回文子串切割方案）

    /**
     * 思路分析：将字符串s分割为每个字串都为回文字符串
     */
    public List<List<String>> partition(String s) {
        // 调用回溯算法
        backTrack(0, s);
        // 返回结果集
        return res;
    }

    /**
     * 回溯算法:idx为当前选择的切割位置
     * 如果字串x为回文字符串，则递归寻找下一个可切割的回文位置，以此类推
     */
    private void backTrack(int idx, String s) {
        // 递归出口：切割位置遍历到数组末尾则载入结果集
        if (idx >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 回溯处理
        for (int i = idx; i < s.length(); i++) {
            String subStr = s.substring(idx, i + 1); // 切割字符串
            // 校验subStr是否满足回文，满足则选定其为当前切割位置，继续递归寻找下一个切割位置
            if (isHuiWen(subStr)) {
                path.add(subStr);
                backTrack(i + 1, s); // 递归寻找下一个切割位置
                path.remove(path.size() - 1); // 恢复现场
            }
        }
    }

    // 校验字符串是否为回文字符串
    private boolean isHuiWen(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            // 指针移动
            left++;
            right--;
        }
        return true;
    }

    /*
    public static void main(String[] args) {
        String s = "aab";
        Solution131_01 so = new Solution131_01();
        so.partition(s);
    }
     */
}
