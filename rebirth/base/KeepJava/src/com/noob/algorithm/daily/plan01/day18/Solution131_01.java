package com.noob.algorithm.daily.plan01.day18;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 131 分割回文串
 */
public class Solution131_01 {


    public List<List<String>> res = new ArrayList<>(); // 记录结果集
    public List<String> temp = new ArrayList<>(); // 记录当前切割方案的字符串

    public List<List<String>> partition(String s) {
        // 调用回溯算法
        backTrack(s, 0);
        // 返回结果
        return res;
    }


    // 定义回溯算法
    public void backTrack(String s, int idx) {
        // 如果切割到字符串尾部则结束
        if (idx == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // 回溯处理
        for (int i = idx; i < s.length(); i++) {
            // 选择切割位置，判断子串是否满足回文串
            String subStr = s.substring(idx, i + 1);
            if (isHuiWen(subStr)) {
                temp.add(subStr); // 选择子串
                backTrack(s, i + 1); // 如果当前切割的字符串满足回文，则继续递归处理下一个回文串
                temp.remove(temp.size() - 1);// 恢复现场
            }
        }
    }

    // 校验字符串是否为回文串
    public boolean isHuiWen(String str) {
        for (int left = 0, right = str.length() - 1; left < right; left++, right--) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
        }
        return true;
    }

}
