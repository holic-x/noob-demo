package com.noob.algorithm.solution_archive.dmsxl.leetcode.q131;

import java.util.ArrayList;
import java.util.List;

/**
 * 131 分割回文串
 */
public class Solution2 {

    List<List<String>> res = new ArrayList<>(); // 定义结果集
    List<String> temp = new ArrayList<>(); // 定义当前的切割子串方案

    // 思路：回溯法
    public List<List<String>> partition(String s) {
        // 指定回溯算法
        backTrack(s, 0);
        // 返回结果
        return res;
    }

    // 回溯法
    public void backTrack(String s, int idx) {
        // 回溯出口（当切割点遍历到字符串末尾时将当前结果集加入集合）
        if (idx == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // 回溯过程
        for (int i = idx; i < s.length(); i++) {
            // 从切割位置开始遍历，寻找下一个切割位置，判断对应范围子串是否满足回文串，满足才有必要继续走下去
            String subStr = s.substring(idx, i + 1);
            if (isHuiWen(subStr)) {
                // 当前遍历位置i满足回文串，处理当前节点，继续递归校验
                temp.add(subStr);
                backTrack(s, i + 1); // 传入目标字符串进行递归(切割过的地方不能重复切割)
                temp.remove(temp.size() - 1);// 复原现场
            }
        }
    }

    // 判断是否满足回文
    public boolean isHuiWen(String str) {
        // 双指针判断回文
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // 左右不匹配，非回文，返回false
            }
            // 指针移动，继续下一个判断
            left++;
            right--;
        }
        return true;
    }

}
