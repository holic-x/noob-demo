package com.noob.algorithm.daily.archive.plan01.day26;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 022 括号生成 - https://leetcode.cn/problems/generate-parentheses/description/
 */
public class Solution022_02 {

    List<String> res = new ArrayList<>(); // 记录结果集
    StringBuffer path = new StringBuffer(); // 当前括号组合路径

    /**
     * 思路分析：
     */
    public List<String> generateParenthesis(int n) {
        // 调用回溯算法
        backTrack(n, 0, 0);
        // 返回结果
        return res;
    }

    public void backTrack(int n, int left, int right) {
        // 校验当前生成的括号序列是否满足n对括号，以及括号是否匹配(此处自定义isValid，而是通过在递归过程中进行剪枝)
        if (path.length() == 2 * n) {
            res.add(new String(path));
            return;
        }

        // 如果左括号不足则补齐
        if (left < n) {
            path.append("(");
            backTrack(n, left + 1, right);
            path.deleteCharAt(path.length() - 1);
        }

        // 如果右括号少于左括号则补齐
        if (right < left) {
            path.append(")");
            backTrack(n, left, right + 1);
            path.deleteCharAt(path.length() - 1);
        }

    }

}
