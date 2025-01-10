package com.noob.algorithm.daily.plan01.day26;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 022 括号生成 - https://leetcode.cn/problems/generate-parentheses/description/
 */
public class Solution022_01 {

    List<String> res = new ArrayList<>(); // 记录结果集
    StringBuffer path = new StringBuffer(); // 当前括号组合路径

    /**
     * 思路分析：
     */
    public List<String> generateParenthesis(int n) {
        // 调用回溯算法
        backTrack(n);
        // 返回结果
        return res;
    }


    public void backTrack(int n) {
        // 校验当前生成的括号序列是否满足n对括号，以及括号是否匹配
        if (path.length() == 2 * n) {
            if (isValid(path.toString())) {
                res.add(new String(path));
            }
            return;
        }

        // 选择加入左括号或者右括号
        path.append("(");
        backTrack(n);
        path.deleteCharAt(path.length() - 1);

        path.append(")");
        backTrack(n);
        path.deleteCharAt(path.length() - 1);

    }

    private boolean isValid(String str) {
        // 校验左右括号是否匹配
        int balance = 0;// 定义平衡参数校验括号字符序列的有效性
        for (char c : str.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

}
